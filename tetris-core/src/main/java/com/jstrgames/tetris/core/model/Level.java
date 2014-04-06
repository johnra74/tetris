package com.jstrgames.tetris.core.model;

/**
 * model representing game level
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class Level {
	private static final int MAX_LEVEL = 20;
	private static final int MAX_ROWS_TO_LEVELUP = 10;
	
	private int level;
	private int rowsCleared;
	
	/**
	 * default constructor
	 * 
	 * @param level
	 */
	public Level(int level) {
		this.level = level;
		this.rowsCleared = 0;
	}
	
	/**
	 * retrieve current game level
	 * 
	 * @return
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * add number of rows cleared
	 * 
	 * @param rowsCleared
	 */
	public void addRowCleared(int rowsCleared) {
		this.rowsCleared += rowsCleared;
		if(this.rowsCleared >= MAX_ROWS_TO_LEVELUP) {
			levelUp();
			this.rowsCleared = this.rowsCleared % MAX_ROWS_TO_LEVELUP;
		}
	}
	
	/**
	 * helper method to level up
	 */
	private void levelUp() {
		if(this.level != MAX_LEVEL) {
			this.level++;
		}
	}
}
