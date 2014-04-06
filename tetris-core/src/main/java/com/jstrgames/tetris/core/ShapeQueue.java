package com.jstrgames.tetris.core;

import java.util.Random;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.ShapeI;
import com.jstrgames.tetris.core.model.ShapeJ;
import com.jstrgames.tetris.core.model.ShapeL;
import com.jstrgames.tetris.core.model.ShapeO;
import com.jstrgames.tetris.core.model.ShapeS;
import com.jstrgames.tetris.core.model.ShapeT;
import com.jstrgames.tetris.core.model.ShapeZ;

/**
 * this class holds the tetris shape queue
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 * 
 */
public class ShapeQueue implements IQueue {
	private final Random random;
	private IShape nextShape;
	
	/**
	 * defalt constructor
	 * 
	 * @param randomSeed
	 */
	public ShapeQueue(long randomSeed) {
		this.random = new Random(randomSeed);
		this.nextShape = next();
	}
	
	/**
	 * removes and returns the next item in the queue
	 * 
	 * @return
	 */
	@Override
	public IShape pop() {
		final IShape retShape = this.nextShape;
		this.nextShape = next();
		return retShape;
	}

	/**
	 * preview next shape without removing it
	 * 
	 * @return
	 */
	@Override
	public IShape peek() {
		return this.nextShape;
	}
	
	/**
	 * helper method to generate new tetris shape
	 * 
	 * @return
	 */
	private IShape next() {
		IShape shape;
		Coordinate coordinate = new Coordinate(4, 1);
		switch(random.nextInt(7)) {
			case 0:
				shape = new ShapeO(coordinate);
				break;
			case 1:
				shape = new ShapeI(coordinate);
				break;
			case 2:
				shape = new ShapeJ(coordinate);
				break;
			case 3:
				shape = new ShapeL(coordinate);
				break;
			case 4:
				shape = new ShapeS(coordinate);
				break;	
			case 5:
				shape = new ShapeT(coordinate);
				break;	
			default:
				shape = new ShapeZ(coordinate);
				break;
		}
		
		return shape;
	}
}
