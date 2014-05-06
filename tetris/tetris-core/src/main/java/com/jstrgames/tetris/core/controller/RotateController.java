package com.jstrgames.tetris.core.controller;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.controller.rotate.RotateProcessor;
import com.jstrgames.tetris.core.controller.rotate.RotateRequest;
import com.jstrgames.tetris.core.controller.rotate.ShapeIRotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeJRotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeLRotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeORotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeSRotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeTRotateHandler;
import com.jstrgames.tetris.core.controller.rotate.ShapeZRotateHandler;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;

/**
 * Rotation controller
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class RotateController extends AbstractActionController {

	private final Rotate rotate;
	private static final RotateProcessor processor;
	
	static {
		processor = new RotateProcessor(new ShapeIRotateHandler());
		processor.addHandler(new ShapeJRotateHandler());
		processor.addHandler(new ShapeLRotateHandler());
		processor.addHandler(new ShapeORotateHandler());
		processor.addHandler(new ShapeSRotateHandler());
		processor.addHandler(new ShapeTRotateHandler());
		processor.addHandler(new ShapeZRotateHandler());
	}
	
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
	protected boolean isValidAction() {
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
		final Coordinate[] targetCoordinate;
		final IShape shape = this.game.getShape();		
		final RotateRequest request = new RotateRequest(shape, this.rotate);
		
		targetCoordinate = processor.execute(request);
		
		return targetCoordinate;
	}
	
}
