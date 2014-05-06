package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

public abstract class AbstractRotateHandler implements IRotateHandler {

	protected IRotateHandler nextHandler;
	
	@Override
	public void setNextHandler(IRotateHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	@Override
	public Coordinate[] executeRequest(RotateRequest request) {
		final Coordinate[] coordinate;
		final IShape shape = request.getShape();
		final Rotate rotate = request.getRotate();
		
		if( shape.getShape() == this.getShape()) {
			coordinate = getTargetCoordinates(shape, rotate);
		} else {
			if(this.nextHandler == null) {
				throw new HandlerNotFoundException("No handler defined for " + shape.getShape());
			} else {
				coordinate = this.nextHandler.executeRequest(request);
			}
		}
		
		return coordinate;
	}

	/**
	 * helper method to retrieve target rotation coordinate for I shape
	 * 
	 * @param pipe
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(IShape shape, Rotate rotate) {
		Coordinate[] targetCoordinate;
		switch(rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shape);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shape);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}
		
		return targetCoordinate;
	}


	protected abstract Shape getShape();
	protected abstract Coordinate[] rotateClockwise(IShape shape);
	protected abstract Coordinate[] rotateCounterClockwise(IShape shape);

}
