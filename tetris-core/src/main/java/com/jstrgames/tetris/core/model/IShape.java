package com.jstrgames.tetris.core.model;

/** 
 * The user of this interface has the control of the shape
 * 
 * @author Johnathan Ra 
 * @company JSTR Games, LLC.
 * 
 */
public interface IShape {
	enum Shape { LETTER_I, LETTER_J, LETTER_L, LETTER_O, LETTER_S, LETTER_T, LETTER_Z, }
	
	Coordinate getPivot();	
	Coordinate[] getFillCoordinates();
	void setFillCoordinates(Coordinate[] coordinates);
	Shape getShape();
}
