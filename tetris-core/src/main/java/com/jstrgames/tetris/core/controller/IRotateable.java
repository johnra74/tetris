package com.jstrgames.tetris.core.controller;

import com.jstrgames.tetris.core.model.Coordinate;

/**
 * the user of this interface can control the rotation
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public interface IRotateable {
	enum Rotate { CLOCKWISE, COUNTER_CLOCKWISE }
	void rotate(Coordinate[] location);
}
