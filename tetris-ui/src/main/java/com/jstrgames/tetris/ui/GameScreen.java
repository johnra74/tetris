package com.jstrgames.tetris.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.ui.component.Board;
import com.jstrgames.tetris.ui.component.IDrawable;
import com.jstrgames.tetris.ui.component.Level;
import com.jstrgames.tetris.ui.component.Preview;
import com.jstrgames.tetris.ui.component.Score;
import com.jstrgames.tetris.ui.component.Shape;
import com.jstrgames.tetris.ui.component.Telemetry;

public class GameScreen extends Component {
	private static final long serialVersionUID = 7742582686627944894L;
	private static final float DEFAULT_FONT_SIZE = 10F;
	private static final String GAMEOVER = "GAME OVER";
	
	private final IGame game;
	private final IDrawable preview;
	private final Telemetry telemetry;
	
	private final int scale;
	private final int width;
	private final int height;
	
	private Image doubleBufferGame;
	private Image doubleBufferLevel;
	private Image doubleBufferScore;
	private Image doubleBufferStat;	
	private Image doubleBufferPreview;
	private Font font;
	private boolean showStats;
	
	public GameScreen(IGame game, Telemetry telemetry, String fontName, int scale) {
		this.showStats = true;
		this.game = game;
		this.scale = scale;
		this.telemetry = telemetry;
		try {
			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			final InputStream istreamFont = classLoader.getResourceAsStream(fontName + ".ttf");
			this.font = Font.createFont(Font.TRUETYPE_FONT, istreamFont);
			this.font = this.font.deriveFont(DEFAULT_FONT_SIZE + (DEFAULT_FONT_SIZE / 2) * scale);
		} catch (Exception e) {
			this.font = new Font(Font.MONOSPACED, Font.BOLD, (int)(DEFAULT_FONT_SIZE + (DEFAULT_FONT_SIZE / 2)* scale));
		}
		
		this.preview = new Preview(font, this.game.getQueue());
		
		IBoard board = this.game.getBoard();
		this.width =  board.getColumnCount() * scale * Board.DEFAULT_CELL_WIDTH_PIXEL;
		this.height = (board.getRowCount() + 4) * scale * Board.DEFAULT_CELL_HEIGHT_PIXEL;
	}
	
	public void setShowStats(boolean showStats) {
		this.showStats = showStats;
	}
	
	public void render() {
		Board board = new Board(this.game.getBoard(), scale);
		Shape shape = new Shape(this.game.getShape(), scale);
		Score score = new Score(this.font, this.game.getScore().getScore());
		Level level = new Level(this.font, this.game.getLevel().getLevel());
		
		if( this.doubleBufferGame == null) {
			this.doubleBufferGame = createImage(this.width, this.height);
		}
		
		if(this.doubleBufferLevel == null) {
			this.doubleBufferLevel = createImage(100*scale, 30*scale);
		}
		
		if(this.doubleBufferScore == null) {
			this.doubleBufferScore = createImage(100*scale, 30*scale);
		}
		
		if(this.doubleBufferPreview == null) {
			this.doubleBufferPreview = 
					createImage(this.preview.getWidth()*5, 
								this.preview.getHeight()*5+30);
		}
		
		Graphics gameGraphics = this.doubleBufferGame.getGraphics();
		FontMetrics fm = gameGraphics.getFontMetrics(this.font);
		int height = fm.getHeight();
		
		gameGraphics.clearRect(0, -20, this.width, this.height);		
		board.draw(gameGraphics);		
		shape.draw(gameGraphics);
		
		Graphics previewGraphics = this.doubleBufferPreview.getGraphics();
		preview.draw(previewGraphics);
		
		Graphics scoreGraphics = this.doubleBufferScore.getGraphics();
		scoreGraphics.clearRect(0, 0, height*10, height*2);	
		score.draw(scoreGraphics);
		
		Graphics levelGraphics = this.doubleBufferLevel.getGraphics();
		levelGraphics.clearRect(0, 0, height*10, height*2);	
		level.draw(levelGraphics);
		
		if(showStats) {			
			if(this.doubleBufferStat == null) {
				this.doubleBufferStat = createImage(this.width, 20);
			}
			
			Graphics statGraphics = this.doubleBufferStat.getGraphics();
			statGraphics.clearRect(0, 0, this.width, 20);	
			this.telemetry.draw(statGraphics);
		}
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {		
		if(this.game.isGameOver()) {
			g.setFont(this.font.deriveFont(36f));
			FontMetrics fontMetrics = g.getFontMetrics();
			int x = (this.width - fontMetrics.stringWidth(GAMEOVER)) / 2 ;
			int y = (this.height - fontMetrics.getHeight()) / 4;
			g.setColor(Color.RED);
			g.drawString(GAMEOVER, x+10, y);
		} else {
			g.drawImage(this.doubleBufferGame, 10, 0, this);
			g.drawImage(this.doubleBufferScore, this.width + 25, 10, this);
			g.drawImage(this.doubleBufferLevel, this.width + 25, 60, this);
			g.drawImage(this.doubleBufferStat, 10, 0, this);
			g.drawImage(this.doubleBufferPreview, this.width + 25, 120, this);
		}
	}
}
