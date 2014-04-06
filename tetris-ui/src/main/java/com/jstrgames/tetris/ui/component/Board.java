package com.jstrgames.tetris.ui.component;

import java.awt.Color;
import java.awt.Graphics;

import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape.Shape;

public class Board implements IDrawable {
	private final IBoard board;
	
	private final int width;
	private final int height;
	
	private final int numOfRows;
	private final int numOfCols;
	private final Shape[][] grid;
	
	public Board(IBoard board, int scale) {
		this.board = board;
		this.width = DEFAULT_CELL_WIDTH_PIXEL*scale;
		this.height = DEFAULT_CELL_HEIGHT_PIXEL*scale;	
		
		this.grid = this.board.getGridState();
		this.numOfCols = grid.length;
		this.numOfRows = grid[0].length;
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 
				this.height, 
				this.width*this.numOfCols, 
				this.height*(this.numOfRows-1));
		
		for(int x = 0 ; x < numOfCols; x++) {
			for(int y = 1; y < numOfRows; y++) {
				Shape shape = grid[x][y];
				if(shape != null) {
					graphics.setColor(Board.getColor(shape));
					graphics.fill3DRect(
							x*this.width, 
							y*this.height, 
							this.width, 
							this.height, 
							true);
				}
			}
		}

	}
	
	@Override
	public int getWidth() {
		return this.width*this.numOfCols;
	}

	@Override
	public int getHeight() {
		return this.height*this.numOfRows;
	}
		
	public static Color getColor(Shape shape) {
		Color color;
		switch(shape) {
			case LETTER_O:
				color = Color.YELLOW;
				break;
			case LETTER_I:
				color = Color.CYAN;
				break;
			case LETTER_J:
				color = Color.BLUE;
				break;
			case LETTER_L:
				color = Color.ORANGE;
				break;
			case LETTER_T:
				color = Color.MAGENTA;
				break;
			case LETTER_S:
				color = Color.GREEN;
				break;
			case LETTER_Z:
				color = Color.RED;
				break;
			default:
				color = Color.PINK;
				break;
		}
		return color;
	}

}
