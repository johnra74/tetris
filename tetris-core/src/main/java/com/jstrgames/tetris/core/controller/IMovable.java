package com.jstrgames.tetris.core.controller;

import com.jstrgames.tetris.core.model.Coordinate;

/**
 * user of this interface can control the movement
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public interface IMovable {
	enum Direction { LEFT, RIGHT, DOWN }
	void move(Coordinate[] location);
}
