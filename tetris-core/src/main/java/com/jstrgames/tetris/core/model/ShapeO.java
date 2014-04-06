package com.jstrgames.tetris.core.model;

/**
 * represents a O-shape object whose pivot is top left corner.
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ShapeO extends AbstractShape {

	/**
	 * default constructor to pre-populate O-shape coordinate
	 * 
	 * @param pivot
	 */
	public ShapeO(Coordinate pivot) {
		this.pivot = pivot;
		this.fillCoordinates = new Coordinate[4];
		this.fillCoordinates[0] = pivot;
		this.fillCoordinates[1] = new Coordinate(this.pivot.getX()+1, this.pivot.getY());
		this.fillCoordinates[2] = new Coordinate(this.pivot.getX(), this.pivot.getY()+1);
		this.fillCoordinates[3] = new Coordinate(this.pivot.getX()+1, this.pivot.getY()+1);
	}
	
	/**
	 * inform the caller this is O-Shape
	 * 
	 * @return
	 */
	@Override
	public Shape getShape() {
		return Shape.LETTER_O;
	}
	
}
