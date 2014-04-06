package com.jstrgames.tetris.core;

import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;

/** 
 * The user of this interface has the control of the game 
 * 
 * @author Johnathan Ra 
 * @company JSTR Games, LLC.
 * 
 */
public interface IGame {
	IShape getShape();
	IQueue getQueue();
	IBoard getBoard();
	Level getLevel();
	Score getScore();
	boolean isGameOver();
}
