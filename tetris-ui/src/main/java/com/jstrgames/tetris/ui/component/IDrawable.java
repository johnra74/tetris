package com.jstrgames.tetris.ui.component;

import java.awt.Graphics;

public interface IDrawable {
	public final static int DEFAULT_CELL_WIDTH_PIXEL = 10;
	public final static int DEFAULT_CELL_HEIGHT_PIXEL = 10;
	
	void draw(Graphics graphics);
	int getWidth();
	int getHeight();
	
}
