package com.jstrgames.tetris.core.model;

/**
 * represents a I-shape whose pivot is the second block.
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ShapeI extends AbstractShape {

	/**
	 * default constructor to pre-populate I-shape coordinate
	 * 
	 * @param pivot
	 */
	public ShapeI(Coordinate pivot) {
		this.pivot = pivot;
		this.fillCoordinates = new Coordinate[4];
		this.fillCoordinates[0] = new Coordinate(this.pivot.getX(), this.pivot.getY() - 1);
		this.fillCoordinates[1] = this.pivot;
		this.fillCoordinates[2] = new Coordinate(this.pivot.getX(), this.pivot.getY() + 1);
		this.fillCoordinates[3] = new Coordinate(this.pivot.getX(), this.pivot.getY() + 2);
	}
	
	/**
	 * inform the caller this is I-Shape
	 * 
	 * @return
	 */
	@Override
	public Shape getShape() {
		return Shape.LETTER_I;
	}

}
