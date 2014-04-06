package com.jstrgames.tetris.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape;

/**
 * this class will validate tetris actions
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ActionValidator {
	private final static Logger LOG = LoggerFactory.getLogger(ActionValidator.class);
	
	protected final IBoard board;
	protected final IActionController actionController;
	protected final IShape shape;
	
	/**
	 * default constructor
	 * 
	 * @param game
	 * @param actionController
	 */
	public ActionValidator(IGame game, IActionController actionController) {
		this.board = game.getBoard();
		this.shape = game.getShape();
		this.actionController = actionController;
	}
	
	/**
	 * validate given action with within bound and is valid move
	 * 
	 * @return
	 */
	public boolean validate() {
		boolean retVal = true;		
		Coordinate[] targetCoordinates = this.actionController.getTargetCoordinates();
		if(isOutofBound(targetCoordinates)) {
			LOG.debug("Illegal Move: Out of Bound!");
			retVal = false;
		} else {
			retVal = isValidAction(targetCoordinates);
			if(!retVal) {
				if(LOG.isDebugEnabled()) {
					LOG.debug("Illegal Action");
				}
			}
		}
		return retVal;
	}
	
	/**
	 * validate give item can drop further
	 * 
	 * @return
	 */
	public boolean cannotDropFurther() {
		boolean retVal = false;	
		Coordinate[] currentCoordinate = this.shape.getFillCoordinates();
		
		// if less than the board, check if filled
		for(Coordinate coordinate : currentCoordinate) {
			int x_coord = coordinate.getX();
			int y_coord = coordinate.getY() + 1; // shift all down by one
			if(y_coord < this.board.getRowCount()){
				// check if cell below is open
				BoardController boardController = new BoardController(this.board, this.shape);
				if(boardController.isFilled(new Coordinate(x_coord, y_coord))) {
					retVal = true;
					break; // break the moment one of the bottom cell is blocked
				}
			} else {
				// can't drop any more since we're at the bottom
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}	
		
	/**
	 * helper method to validate if action can be performed
	 * 
	 * @param targetCoordinates
	 * @return
	 */
	private boolean isValidAction(Coordinate[] targetCoordinates) {
		boolean retVal = true;		
		BoardController boardController = new BoardController(this.board, this.shape);
		for(Coordinate coordinate:targetCoordinates) {
			if(boardController.isFilled(coordinate)) {
				retVal = false;
				break;
			}
		}		
		return retVal;
	}
	
	/**
	 * helper method to check if share is within bound
	 * 
	 * @param targetCoordinates
	 * @return
	 */
	private boolean isOutofBound(Coordinate[] targetCoordinates) {
		boolean retVal = false;
		BoardController boardController = new BoardController(this.board, this.shape);
		for(Coordinate coordinate:targetCoordinates) {
			if(boardController.isOutofBound(coordinate)) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
}
