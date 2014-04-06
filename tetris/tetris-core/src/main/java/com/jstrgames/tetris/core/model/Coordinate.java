package com.jstrgames.tetris.core.model;

/**
 * holds the coordinate details
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 * 
 */
public class Coordinate {
	private final int x;
	private final int y;

	/**
	 * default constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * return the x-coordinate
	 * 
	 * @return
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * return the y-coordinate
	 * 
	 * @return
	 */
	public int getY() {
		return this.y;
	}
}
