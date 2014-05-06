package com.jstrgames.tetris.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.IGame;

/**
 * abstract action controller used to execute action (i.e. move or rotate) on 
 * current shape
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 * 
 */
public abstract class AbstractActionController implements IActionController {
	private final static Logger LOG = LoggerFactory.getLogger(AbstractActionController.class);
	
	public final static boolean MANUAL_ACTION_FLAG = true;
		
	protected final IGame game;
	
	/**
	 * default controller with handle to game
	 * 
	 * @param game
	 */
	public AbstractActionController(IGame game) {
		this.game = game;
	}
	
	/**
	 * executes action. on success, commits change to shape
	 * 
	 * @return
	 */
	@Override
	public boolean execute() {
		boolean isSuccess = false;
		if(!game.isGameOver() && isValidAction()) {
			// commit action
			game.getShape().setFillCoordinates(this.getTargetCoordinates());
			isSuccess = true;
		} else {
			if(LOG.isDebugEnabled()) {
				LOG.debug("Failed to complete action!");
			}
		}
		return isSuccess;
	}
	
	/**
	 * abstract helper method to confirm of action is valid
	 * 
	 * @return
	 */
	protected abstract boolean isValidAction();
	
}
