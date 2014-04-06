package com.jstrgames.tetris.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.IEndOfMoveListener;
import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.controller.IMovable.Direction;
import com.jstrgames.tetris.core.model.Coordinate;

/**
 * this class will control the movement of tetris shapes
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class MoveController extends AbstractActionController {
	private final static Logger LOG = LoggerFactory.getLogger(MoveController.class);
	
	private final Direction direction;
	private final IEndOfMoveListener listener;
	private final boolean isAutoDropAction;
	
	/**
	 * default constructor
	 * 
	 * @param game
	 * @param direction
	 */
	public MoveController(IGame game, Direction direction) {
		super(game);

		this.direction = direction;
		this.isAutoDropAction = false;
		this.listener = null;
	}
	
	/**
	 * constructor with reference to notify listener when end of moves occurs
	 * 
	 * @param game
	 * @param direction
	 * @param listener
	 */
	public MoveController(IGame game, Direction direction, IEndOfMoveListener listener) {
		super(game);

		this.direction = direction;
		this.isAutoDropAction = true;
		this.listener = listener;
	}	

	/**
	 * method to validate action
	 * 
	 * @return
	 */
	@Override
	protected boolean isValidateAction() {
		ActionValidator validator =
				new ActionValidator(this.game, this);
		boolean isValid = validator.validate();
		if(isValid) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("move is valid");
			}
		} else if(isAutoDropAction){
			if(validator.cannotDropFurther()) {
				listener.endOfMove(); 
				isValid = true;
			}
		}
		return validator.validate();
	}
	
	/**
	 * method to retrieve a preview action coordinates
	 * 
	 * @return
	 */
	@Override
	public Coordinate[] getTargetCoordinates() {
		Coordinate[] targetCoordinate;
		switch(this.direction) {
			case DOWN:
				targetCoordinate = moveDown();
				break;
			case LEFT:
				targetCoordinate = moveLeft();
				break;
			case RIGHT:
				targetCoordinate = moveRight();
				break;
			default:
				targetCoordinate = new Coordinate[0];
				break;					
		}		
		return targetCoordinate;
	}
	
	/**
	 * helper method to move shape to right
	 * 
	 * @return
	 */
	private Coordinate[] moveRight() {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = this.game.getShape().getFillCoordinates();
		targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX()+1, sourceCoordinate[0].getY());
		targetCoordinate[1] = new Coordinate(sourceCoordinate[1].getX()+1, sourceCoordinate[1].getY());
		targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX()+1, sourceCoordinate[2].getY());
		targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX()+1, sourceCoordinate[3].getY());
		return targetCoordinate;
	}

	/**
	 * helper method to move shape to left
	 * 
	 * @return
	 */
	private Coordinate[] moveLeft() {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = this.game.getShape().getFillCoordinates();
		targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX()-1, sourceCoordinate[0].getY());
		targetCoordinate[1] = new Coordinate(sourceCoordinate[1].getX()-1, sourceCoordinate[1].getY());
		targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX()-1, sourceCoordinate[2].getY());
		targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX()-1, sourceCoordinate[3].getY());
		return targetCoordinate;
	}

	/**
	 * helper method to drop shape one notch
	 * 
	 * @return
	 */
	private Coordinate[] moveDown() {
		Coordinate[] targetCoordinate = new Coordinate[4];
		Coordinate[] sourceCoordinate = this.game.getShape().getFillCoordinates();
		targetCoordinate[0] = new Coordinate(sourceCoordinate[0].getX(), sourceCoordinate[0].getY()+1);
		targetCoordinate[1] = new Coordinate(sourceCoordinate[1].getX(), sourceCoordinate[1].getY()+1);
		targetCoordinate[2] = new Coordinate(sourceCoordinate[2].getX(), sourceCoordinate[2].getY()+1);
		targetCoordinate[3] = new Coordinate(sourceCoordinate[3].getX(), sourceCoordinate[3].getY()+1);
		return targetCoordinate;
	}

}
