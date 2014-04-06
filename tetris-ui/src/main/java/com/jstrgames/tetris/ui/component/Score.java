package com.jstrgames.tetris.ui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Score implements IDrawable {
	private final static NumberFormat FORMAT = new DecimalFormat("0000000");
	private final static String LABEL = "SCORE";
	
	private final String score;
	private final Font font;
	
	public Score(Font font, int score) {
		this.font = font;
		this.score = FORMAT.format(score);		
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.setFont(this.font);
		
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int width = fontMetrics.stringWidth(FORMAT.format(0));
		int height = fontMetrics.getHeight();
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(5, 25, width+4, height+4);
		
		graphics.setFont(this.font);
		graphics.drawString(LABEL, 5, 25-2);
		
		graphics.setColor(Color.GREEN);	
		graphics.drawString(this.score, 5+2, 25+height+2);
	}

	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return 50;
	}

}
