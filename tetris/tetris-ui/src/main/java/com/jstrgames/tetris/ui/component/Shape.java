package com.jstrgames.tetris.ui.component;

import java.awt.Graphics;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;

public class Shape implements IDrawable {	
	private final IShape shape;
	private final int width;
	private final int height;	
		
	public Shape(IShape shape, int scale) {
		this.shape = shape;
		this.width = DEFAULT_CELL_WIDTH_PIXEL*scale;
		this.height = DEFAULT_CELL_HEIGHT_PIXEL*scale;	
	}

	@Override
	public void draw(Graphics graphics) {
		if(this.shape == null) return; // there's no shape.. no need to draw
		
		graphics.setColor(Board.getColor(this.shape.getShape()));	
		
		Coordinate[] coordinates = this.shape.getFillCoordinates();
		for(Coordinate coordinate:coordinates) {
			graphics.fill3DRect(
					coordinate.getX()*this.width, 
					coordinate.getY()*this.height, 
					this.width, 
					this.height, 
					true);
		}
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
}
