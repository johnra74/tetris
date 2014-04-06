package com.jstrgames.tetris.core.controller;

import com.jstrgames.tetris.core.model.Coordinate;

/**
 * user of this interface can control execution of action 
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public interface IActionController {

	boolean execute();
	Coordinate[] getTargetCoordinates();
}
