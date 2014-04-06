package com.jstrgames.tetris.core;

import com.jstrgames.tetris.core.model.IShape;

/** 
 * The user of this interface has control over the tetris shape queue
 * 
 * @author Johnathan Ra 
 * @company JSTR Games, LLC.
 * 
 */
public interface IQueue {
	IShape pop();
	IShape peek();
}
