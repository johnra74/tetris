package com.jstrgames.tetris.core.model;

import com.jstrgames.tetris.core.controller.IMovable;
import com.jstrgames.tetris.core.controller.IRotateable;

/**
 * abstract shape 
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public abstract class AbstractShape implements IShape, IMovable, IRotateable {
	protected Coordinate pivot;
	protected Coordinate[] fillCoordinates;
	
	// Implement IShape
	/**
	 * retrieve this shape's pivot coordinate
	 * 
	 * @return
	 */
	@Override
	public Coordinate getPivot() {
		return this.pivot;
	}

	/**
	 * retrieve this shape's fill coordinates relative to the game board
	 * 
	 * @return
	 */
	@Override
	public Coordinate[] getFillCoordinates() {
		return this.fillCoordinates;
	}

	/**
	 * set this shape's fill coordinates
	 * 
	 * @param
	 */
	@Override
	public void setFillCoordinates(Coordinate[] coordinates) {
		this.fillCoordinates = coordinates;
		this.pivot = this.fillCoordinates[1];		
	}
	
	// Implement IRotateable
	/**
	 * update this shape coordinate with the new rotated coordinate
	 * 
	 * @param
	 */
	@Override
	public void rotate(Coordinate[] coordinates) {
		setFillCoordinates(coordinates);
	}
	
	// Implement IMovable
	/**
	 * update this shape coordinate with the new moved coordinate
	 */
	@Override
	public void move(Coordinate[] coordinates) {
		setFillCoordinates(coordinates);
	}
	
	/**
	 * extended method will return this shape type
	 * 
	 * @return
	 */
	public abstract Shape getShape();
	
}
