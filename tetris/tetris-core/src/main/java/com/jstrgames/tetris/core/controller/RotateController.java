package com.jstrgames.tetris.core.controller;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.ShapeI;
import com.jstrgames.tetris.core.model.ShapeJ;
import com.jstrgames.tetris.core.model.ShapeL;
import com.jstrgames.tetris.core.model.ShapeS;
import com.jstrgames.tetris.core.model.ShapeT;
import com.jstrgames.tetris.core.model.ShapeO;
import com.jstrgames.tetris.core.model.ShapeZ;

/**
 * Rotation controller
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class RotateController extends AbstractActionController {

	private final Rotate rotate;
	
	/**
	 * default constructor
	 * 
	 * @param game
	 * @param rotate
	 */
	public RotateController(IGame game, Rotate rotate) {
		super(game);
		this.rotate = rotate;		
	}

	/**
	 * validate if action is valid
	 * 
	 * @return
	 */
	@Override
	protected boolean isValidateAction() {
		ActionValidator validator = new ActionValidator(this.game, this);
		return validator.validate();
	}
	
	/**
	 * method to retrieve preview rotation action coordinate
	 * 
	 * @return
	 */
	@Override
	public Coordinate[] getTargetCoordinates() {
		Coordinate[] targetCoordinate;
		IShape shape = this.game.getShape();
		if(shape instanceof ShapeI) {
			targetCoordinate = getTargetCoordinates((ShapeI) shape);
		} else if(shape instanceof ShapeJ) {
			targetCoordinate = getTargetCoordinates((ShapeJ) shape);
		} else if(shape instanceof ShapeL) {
			targetCoordinate = getTargetCoordinates((ShapeL) shape);
		} else if(shape instanceof ShapeO) {
			targetCoordinate = getTargetCoordinates((ShapeO) shape);
		} else if(shape instanceof ShapeS) {
			targetCoordinate = getTargetCoordinates((ShapeS) shape);
		} else if(shape instanceof ShapeT) {
			targetCoordinate = getTargetCoordinates((ShapeT) shape);
		} else if(shape instanceof ShapeZ) {
			targetCoordinate = getTargetCoordinates((ShapeZ) shape);
		} else {
			targetCoordinate = shape.getFillCoordinates();
		}
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve target rotation coordinate for O shape
	 * 
	 * @param square
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeO square) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(square);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(square);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve target rotation coordinate for I shape
	 * 
	 * @param pipe
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeI pipe) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(pipe);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(pipe);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve target rotation coordinate for J shape
	 * 
	 * @param shareJ
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeJ shareJ) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shareJ);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shareJ);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve rotation coordinate for L shape
	 * @param shareL
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeL shapeL) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shapeL);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shapeL);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve rotation coordinate for T shape
	 * 
	 * @param shapeT
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeT shapeT) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shapeT);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shapeT);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve rotation coordinate for S shape
	 * 
	 * @param shapeS
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeS shapeS) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shapeS);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shapeS);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to retrieve rotation coordinate for Z shape
	 * 
	 * @param shapeZ
	 * @return
	 */
	private Coordinate[] getTargetCoordinates(ShapeZ shapeZ) {
		Coordinate[] targetCoordinate;
		switch(this.rotate) {
			case CLOCKWISE:
				targetCoordinate = rotateClockwise(shapeZ);
				break;
				
			case COUNTER_CLOCKWISE:
				targetCoordinate = rotateCounterClockwise(shapeZ);
				break;
				
			default:
				targetCoordinate = new Coordinate[0];				
				break;
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to rotate O shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeO shape) {
		// rotating O shape results in same state so just return self
		return shape.getFillCoordinates();
	}

	/**
	 * helper method to rotate O shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateCounterClockwise(ShapeO shape) {
		// rotating O shape results in same state so just return self
		return shape.getFillCoordinates();
	}
	
	/**
	 * helper method to rotate I shape
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeI shape) {
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
	private Coordinate[] rotateCounterClockwise(ShapeI shape) {
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
	
	/**
	 * helper method to rotate J shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeJ shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// _| -> |___
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() - 2);
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			//  _    ___
			// |  ->    |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() + 2);
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// |___ ->  _
			//         |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// ___
			//    | -> _|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		}
		return targetCoordinate;
	}

	/**
	 * helper method to rotate  J shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateCounterClockwise(ShapeJ shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) {
			// _| -> ___
			//          |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getY() > sourceCoordinate[1].getY()) {
			//  _ -> |___
			// |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		} else if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			// |___ ->
			//          _|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() + 2);
		} else if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// ___  ->  _
			//    |    |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY() - 2);
		}
		return targetCoordinate;
	}
	
	/**
	 * helper method to rotate L shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeL shape) {
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
	 * helper method to rotate L shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateCounterClockwise(ShapeL shape) {
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
	
	/**
	 * helper method to rotate T shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeT shape) {
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
	private Coordinate[] rotateCounterClockwise(ShapeT shape) {
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
	
	/**
	 * helper method to rotate Z shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeZ shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getX() < sourceCoordinate[1].getX()) {
			// _
			//  |_
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		} else if (sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) { 
			// _| -> _
			//|       |_
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		}
	
		return targetCoordinate;
	}

	/**
	 * helper method to rotate Z shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateCounterClockwise(ShapeZ shape) {
		// rotating clockwise or counter-clockwise produces same state.. call clockwise
		return rotateClockwise(shape);
	}
	
	/**
	 * helper method to rotate S shape clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateClockwise(ShapeS shape) {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = shape.getFillCoordinates();
		
		if(sourceCoordinate[0].getX() > sourceCoordinate[1].getX()) {
			//   _ -> |_
			// _|       |
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() - 1, sourceCoordinate[0].getY() - 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() + 1, sourceCoordinate[2].getY() - 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() + 2, sourceCoordinate[3].getY());
		} else if (sourceCoordinate[0].getY() < sourceCoordinate[1].getY()) { 
			//|_        _
			//  |  -> _|
			targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX() + 1, sourceCoordinate[0].getY() + 1);
			targetCoordinate[1] = sourceCoordinate[1];
			targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX() - 1, sourceCoordinate[2].getY() + 1);
			targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX() - 2, sourceCoordinate[3].getY());
		}
	
		return targetCoordinate;
	}

	/**
	 * helper method to rotate S shape counter-clockwise
	 * 
	 * @param shape
	 * @return
	 */
	private Coordinate[] rotateCounterClockwise(ShapeS shape) {
		// rotating clockwise or counter-clockwise produces same state.. call clockwise
		return rotateClockwise(shape);
	}

}
