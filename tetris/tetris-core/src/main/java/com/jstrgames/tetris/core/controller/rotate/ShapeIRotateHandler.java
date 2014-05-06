package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

public class ShapeIRotateHandler extends AbstractRotateHandler {

	@Override
	protected Shape getShape() {
		return Shape.LETTER_I;
	}
	
	/**
	 * helper method to rotate I shape
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// verticle
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY() - 2);
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// horizontal to right
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY() + 2);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// horizontal to left
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY() + 2);
		}
		return targetCoordinate;
	}

	/**
	 * helper method to rotate I shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateCounterClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// verticle
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY() - 2);
		}  else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// horizontal to right
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY() + 2);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// horizontal to left
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY() + 2);
		}
		return targetCoordinate;
	}

}
