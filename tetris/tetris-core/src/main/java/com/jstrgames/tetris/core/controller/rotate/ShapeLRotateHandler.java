package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

/**
 * Rotate Handler will rotate L-Shape Tetris clockwise or counter-clockwise
 * 
 * @author Johnathan
 *
 */
public class ShapeLRotateHandler extends AbstractRotateHandler {

	@Override
	protected Shape getShape() {
		return Shape.LETTER_L;
	}

	/**
	 * method will to rotate L shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// |_ ->  ___
			//       |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			//  _     ___|
			//   | -> 
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			//  ___  -> _
			// |         |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() - 2);
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// ___| -> |_
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() + 2);
		}
		return targetCoordinate;
	}

	/**
	 * method will rotate L shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateCounterClockwise(IShape shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// |_ -> __|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() - 2);
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			//  _      __
			//   | -> |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() + 2);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			//  __ -> L
			// |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// __| -> _
			//         |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		}
		return targetCoordinate;
	}
}
