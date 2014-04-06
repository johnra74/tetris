package com.jstrgames.tetris.core.model;

import com.jstrgames.tetris.core.model.IShape.Shape;

/**
 * contains all the final state of fallen shapes
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 * 
 */
public class Board implements IBoard {	
	private Shape[][] gridState;
	private int maxColumns;
	private int maxRows;
	
	/**
	 * default constructor
	 * 
	 * @param gridState
	 */
	public Board(Shape[][] gridState) {
		this.gridState = gridState;
		this.maxColumns = this.gridState.length;
		this.maxRows = this.gridState[0].length;
	}

	/**
	 * add final state of shape to board
	 * 
	 * @param shape
	 */
	public void add(IShape shape) {
		Coordinate[] fillCoordinates = shape.getFillCoordinates();
		for(Coordinate coordinate:fillCoordinates) {
			gridState[coordinate.getX()][coordinate.getY()] = shape.getShape();
		}
	}
	
	/**
	 * remove filled row
	 * 
	 * @param row
	 */
	@Override
	public void removeRow(int row) {
		for(int y = row; y > 0; y--) {
			for(int x = 0; x < this.maxColumns; x++) {
				this.gridState[x][y] = this.gridState[x][y-1];
			}
		}
		
		// clear top most row as row has been removed
		for(int x = 0; x < this.maxColumns; x++) {
			this.gridState[x][0] = null;
		}
	}
	
	/**
	 * retrieve the current board state
	 * 
	 * @return
	 */
	@Override
	public Shape[][] getGridState() {
		return this.gridState;
	}
	
	/**
	 * retrieve total number of columns
	 * 
	 * @return
	 */
	@Override
	public int getColumnCount() {
		return this.maxColumns;
	}
	
	/**
	 * retrieve total number of rows
	 * 
	 * @return
	 */
	@Override
	public int getRowCount() {
		return this.maxRows;
	}
	
}
