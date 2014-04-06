package com.jstrgames.tetris.core;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.controller.BoardController;
import com.jstrgames.tetris.core.controller.MoveController;
import com.jstrgames.tetris.core.controller.IMovable.Direction;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;

/**
 * core tetris game engine.
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 * 
 */
public class TetrisEngine implements Runnable, IGame, IEndOfMoveListener {
	private final static Logger LOG = LoggerFactory.getLogger(TetrisEngine.class);
	
	private final static long DEFAULT_PAUSE_SECONDS = 1L;
	private final static int SCORE_PER_ROW_CLEARED = 100;
	private final static int SCORE_TETRIS_CLEARED_FACTOR = 2;
	private final static int SCORE_BONUS_TETRIS_BACK_TO_BACK = 400;
	
	private final long DEFAULT_DROP_SECONDS;
	private final long LEVELUP_DROP_MILISECONDS;
	
	private final Board board;
	private final Level level;
	private final Score score;
	private final IQueue shapeGenerator;
	
	private boolean paused;
	private boolean gameover;
	private boolean isBackToBackTetris;
	private IShape shape;
	private long dropTicks;
	
	/**
	 * create a game with specified board, score, level, and share queue. this
	 * constructor is used restart saved game
	 * 
	 * @param board
	 * @param score
	 * @param level
	 * @param shapeGenerator
	 */
	public TetrisEngine(Board board, Score score, Level level, IQueue shapeGenerator) {
		this.board = board;
		this.level = level;
		this.score = score;
		this.shapeGenerator = shapeGenerator;
		this.shape = this.shapeGenerator.pop();
		
		this.paused = false;
		this.gameover = false;
		this.isBackToBackTetris = false;
		
		this.DEFAULT_DROP_SECONDS = 1L;
		this.LEVELUP_DROP_MILISECONDS = 100L;
		
		this.dropTicks = initializeDropTicks();
	}
	
	/**
	 * create a game with specified board, score, level, share queue, and override
	 * default drop speed.
	 * 
	 * @param board
	 * @param score
	 * @param level
	 * @param shapeGenerator
	 * @param defaultDropSec
	 * @param lvlUpDropMilis
	 */
	public TetrisEngine(Board board, Score score, Level level, IQueue shapeGenerator, int defaultDropSec, int lvlUpDropMilis) {
		this.board = board;
		this.level = level;
		this.score = score;
		this.shapeGenerator = shapeGenerator;
		this.shape = this.shapeGenerator.pop();
		
		this.paused = false;
		this.gameover = false;
		this.isBackToBackTetris = false;
		
		this.DEFAULT_DROP_SECONDS = defaultDropSec;
		this.LEVELUP_DROP_MILISECONDS = lvlUpDropMilis;
		
		this.dropTicks = initializeDropTicks();	
	}
	
	/**
	 * returns current tetris shape on board
	 * 
	 * @return
	 */
	@Override
	public IShape getShape() {
		return this.shape;
	}
	
	/**
	 * returns game's queue
	 * 
	 * @return
	 */
	@Override
	public IQueue getQueue() {
		return this.shapeGenerator;
	}
	
	/**
	 * returns current level
	 * 
	 * @return
	 */
	@Override
	public Level getLevel() {
		return this.level;
	}
	
	/**
	 * returns current game score
	 * 
	 * @return
	 */
	@Override
	public Score getScore() {
		return this.score;
	}
	
	/**
	 * returns game board
	 * 
	 * @return
	 */
	@Override
	public IBoard getBoard() {
		return this.board;
	}
	
	/**
	 * pause game
	 */
	public void pause() {
		this.paused = true;
	}
	
	/**
	 * unpause game
	 */
	public void unpause() {
		this.paused = false;
	}
	
	/**
	 * check if game has ended
	 * 
	 * @return
	 */
	@Override
	public boolean isGameOver() {
		return this.gameover;
	}
	
	/**
	 * action to take when end of move
	 * 
	 */
	@Override
	public void endOfMove() {
		if(isOutOfBound(this.shape.getFillCoordinates())) {
			LOG.info("can't drop and shape is out of bound.. game over");
			this.gameover = true;
		} else {
			// bound shapre to board
			boundShapeToBoard();
			// get next shape.. can't drop any more
			this.shape = shapeGenerator.pop();
			if(this.shape == null) {
				// no shape were generated.. end game.. mainly used for mock testing
				LOG.info("no more shape in queue.. game over");
				this.gameover = true;
			}
		}
	}
	
	/**
	 * main game thread
	 */
	@Override
	public void run() {
		long startingTick = System.currentTimeMillis();
		
		while(!this.gameover) {
			if(this.paused) {
				try {
					Thread.sleep(TimeUnit.SECONDS.toMillis(DEFAULT_PAUSE_SECONDS));
				} catch (InterruptedException e) {
					LOG.warn("Failed to pause for " + DEFAULT_PAUSE_SECONDS + " second(s)", e);
				}
				continue;
			}
			
			long durationSinceLastDrop = System.currentTimeMillis() - startingTick;
			if(durationSinceLastDrop >= this.dropTicks) {
				if(LOG.isDebugEnabled()) {
					LOG.debug("time expired.. force drop one notch");					
				}
				MoveController controller = new MoveController(this, Direction.DOWN, this);
				if(controller.execute()) {
					if(LOG.isDebugEnabled()) {
						LOG.debug("time expired.. force drop one notch");					
					}					
				} 
				startingTick = System.currentTimeMillis();
			} else {
				try {
					Thread.sleep(this.dropTicks - durationSinceLastDrop);
				} catch (InterruptedException e) {
					LOG.info("failed sleeping until next drop");
				}
			}
		}
	}
	
	/**
	 * helper method to commit current shape to board
	 */
	private void boundShapeToBoard() {
		this.board.add(this.shape);
		BoardController boardController = new BoardController(this.board, this.shape);
		int rowsCleared = boardController.scanForFilledRows();
		if(rowsCleared > 0) {				
			boardController.clearFilledRows();
			this.level.addRowCleared(rowsCleared);
			if(rowsCleared == 4) {
				// Tetris!
				this.score.addScore(SCORE_PER_ROW_CLEARED * rowsCleared * SCORE_TETRIS_CLEARED_FACTOR);
				if(this.isBackToBackTetris) {
					// back-to-back tetris! bonus!
					this.score.addScore(SCORE_BONUS_TETRIS_BACK_TO_BACK);
				}
				this.isBackToBackTetris = true;
			} else {
				this.isBackToBackTetris = false;
				this.score.addScore(SCORE_PER_ROW_CLEARED * rowsCleared);
			}
		} 
	}
	
	/**
	 * helper method to check if current shape is out of bound
	 * 
	 * @param coordinates
	 * @return
	 */
	private boolean isOutOfBound(Coordinate[] coordinates) {
		boolean retVal = false;
		for(Coordinate coordinate: coordinates) {
			if(coordinate.getY() < IBoard.TOP_BOUND) {
				retVal = true;
				break;
			}
		}		
		return retVal;
	}
	
	/**
	 * helper method to calculate initial drop ticks
	 * 
	 * @return
	 */
	private long initializeDropTicks() {
		int currentLevel = this.level.getLevel();
		long ticks = TimeUnit.SECONDS.toMillis(DEFAULT_DROP_SECONDS);		
		ticks = ticks - (LEVELUP_DROP_MILISECONDS * currentLevel);		
		return ticks;
	}
}
