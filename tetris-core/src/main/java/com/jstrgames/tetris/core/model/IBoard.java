package com.jstrgames.tetris.core.model;

import com.jstrgames.tetris.core.model.IShape.Shape;

/** 
 * The user of this interface has the control of the game's board
 * 
 * @author Johnathan Ra 
 * @company JSTR Games, LLC.
 * 
 */
public interface IBoard {
	public final static int TOP_BOUND = 2;
	
	Shape[][] getGridState();
	void removeRow(int row);
	int getRowCount();
	int getColumnCount();
}
