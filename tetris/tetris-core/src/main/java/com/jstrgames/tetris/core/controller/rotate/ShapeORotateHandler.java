package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

/**
 * Rotate Handler will rotate O-Shape Tetris clockwise or counter-clockwise
 * 
 * @author Johnathan
 *
 */
public class ShapeORotateHandler extends AbstractRotateHandler {

	@Override
	protected Shape getShape() {
		return Shape.LETTER_O;
	}

	/**
	 * helper method to rotate O shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateClockwise(IShape shape) {
		// rotating O shape results in same state so just return self
		return shape.getFillCoordinates();
	}

	/**
	 * helper method to rotate O shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	protected Coordinate[] rotateCounterClockwise(IShape shape) {
		// rotating O shape results in same state so just return self
		return shape.getFillCoordinates();
	}
}
