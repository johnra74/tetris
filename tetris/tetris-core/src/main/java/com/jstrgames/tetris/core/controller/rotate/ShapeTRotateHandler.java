package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

/**
 * Rotate Handler will rotate T-Shape Tetris clockwise or counter-clockwise
 * 
 * @author Johnathan
 *
 */
public class ShapeTRotateHandler extends AbstractRotateHandler {

	@Override
	protected Shape getShape() {
		return Shape.LETTER_T;
	}

	/**
	 * helper method to rotate T shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// |- -> T
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 1, sourceCoordinate[3].getY() - 1);
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			// -| -> upside down T
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 1, sourceCoordinate[3].getY() + 1);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// T -> -|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 1, sourceCoordinate[3].getY() - 1);
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// upside down T -> |-
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 1, sourceCoordinate[3].getY() + 1);
		}
		return targetCoordinate;
	}

	/**
	 * helper method to rotate T-shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateCounterClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// |- -> upside down T
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 1, sourceCoordinate[3].getY() - 1);
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			// -| -> T
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 1, sourceCoordinate[3].getY() + 1);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// T -> |-
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 1, sourceCoordinate[3].getY() + 1);
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// upside down T -> -|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 1, sourceCoordinate[3].getY() - 1);
		}
		return targetCoordinate;
	}
	
}
