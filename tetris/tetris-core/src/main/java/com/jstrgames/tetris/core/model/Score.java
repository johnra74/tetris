package com.jstrgames.tetris.core.model;

/**
 * model representing game score
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class Score {
	private int score;
	
	/**
	 * default constructor
	 * 
	 * @param score
	 */
	public Score(int score) {
		this.score = score;
	}
	
	/**
	 * retrieve current score
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * increase score
	 * 
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
}
