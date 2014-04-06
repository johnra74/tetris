package com.jstrgames.tetris.core.model;

/**
 * represents a T-shape object whose pivot is the intersection point.
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ShapeT extends AbstractShape {
	
	/**
	 * default constructor to pre-populate T-shape coordinate
	 * 
	 * @param pivot
	 */
	public ShapeT(Coordinate pivot) {
		this.pivot = pivot;
		this.fillCoordinates = new Coordinate[4];
		
		this.fillCoordinates[0] = new Coordinate(this.pivot.getX()-1, this.pivot.getY());
		this.fillCoordinates[1] = this.pivot;
		this.fillCoordinates[2] = new Coordinate(this.pivot.getX(), this.pivot.getY()-1);
		this.fillCoordinates[3] = new Coordinate(this.pivot.getX()+1, this.pivot.getY());
	}

	/**
	 * inform the caller this is T-Shape
	 * 
	 * @return
	 */
	@Override
	public Shape getShape() {
		return Shape.LETTER_T;
	}

}
