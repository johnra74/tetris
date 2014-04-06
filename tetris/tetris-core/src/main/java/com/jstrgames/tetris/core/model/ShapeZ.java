package com.jstrgames.tetris.core.model;

/**
 * represents a Z-shape object whose pivot is the top right point
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class ShapeZ extends AbstractShape {
	
	/**
	 * default constructor to pre-populate Z-shape coordinate
	 * 
	 * @param pivot
	 */
	public ShapeZ(Coordinate pivot) {
		this.pivot = pivot;
		this.fillCoordinates = new Coordinate[4];
		
		this.fillCoordinates[0] = new Coordinate(this.pivot.getX()-1, this.pivot.getY());
		this.fillCoordinates[1] = this.pivot;
		this.fillCoordinates[2] = new Coordinate(this.pivot.getX(), this.pivot.getY()+1);
		this.fillCoordinates[3] = new Coordinate(this.pivot.getX()+1, this.pivot.getY()+1);
	}

	/**
	 * inform the caller this is Z-Shape
	 * 
	 * @return
	 */
	@Override
	public Shape getShape() {
		return Shape.LETTER_Z;
	}

}
