package com.jstrgames.tetris.core.model;

/**
 * represents a L-shaped object whose pivot is the second block.
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ShapeL extends AbstractShape {

	/**
	 * default constructor to pre-populate L-shape coordinate
	 * 
	 * @param pivot
	 */
	public ShapeL(Coordinate pivot) {
		this.pivot = pivot;
		this.fillCoordinates = new Coordinate[4];
		this.fillCoordinates[0] = new Coordinate(this.pivot.getX(), this.pivot.getY() - 1);
		this.fillCoordinates[1] = this.pivot;
		this.fillCoordinates[2] = new Coordinate(this.pivot.getX(), this.pivot.getY() + 1);
		this.fillCoordinates[3] = new Coordinate(this.pivot.getX() + 1, this.pivot.getY() + 1);
	}
	
	/**
	 * inform the caller this is L-Shape
	 * 
	 * @return
	 */
	@Override
	public Shape getShape() {
		return Shape.LETTER_L;
	}

}
