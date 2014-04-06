package com.jstrgames.tetris.ui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.jstrgames.tetris.core.IQueue;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IShape;

public class Preview implements IDrawable {
	private final static String LABEL = "NEXT";
	
	private final IQueue queue;
	private final Font font;
	private final int width;
	private final int height;	
		
	public Preview(Font font, IQueue queue) {
		this.font = font;
		this.queue = queue;
		this.width = DEFAULT_CELL_WIDTH_PIXEL;
		this.height = DEFAULT_CELL_HEIGHT_PIXEL;	
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.clearRect(0, 10, this.width*5, this.width*5);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 25, this.width*5, this.height*5);
		
		graphics.setFont(this.font);
		graphics.drawString(LABEL, 5, 25-2);
		
		if(this.queue == null) return; // there's no shape.. no need to draw
		IShape shape = this.queue.peek();
		
		graphics.setColor(Board.getColor(shape.getShape()));	
		int x, y, xOffset, yOffset;
		switch(shape.getShape()) {
			case LETTER_I:
				x = 3; y = 0; xOffset = (this.width/2)+5; yOffset = 30;
				break;
			case LETTER_J:
				x = 3; y = 0; xOffset = (this.width/2)+10; yOffset = (this.height/2) + 30;
				break;
			case LETTER_L:
				x = 3; y = 0; xOffset = (this.width/2); yOffset = (this.height/2) + 30;
				break;
			case LETTER_T:
				x = 3; y = 1; xOffset = (this.width/2)+5; yOffset = 30;
				break;
			case LETTER_S:
			case LETTER_Z:
				x = 3; y = 0; xOffset = (this.width/2)+5; yOffset = 30;
				break;

			default:
				x = 3; y = 0; xOffset = 5; yOffset = 30;
				break;
		}
		
		Coordinate[] coordinates = shape.getFillCoordinates();
		for(Coordinate coordinate:coordinates) {
			graphics.fill3DRect(
					((coordinate.getX()-x)*this.width)+xOffset, 
					((coordinate.getY()+y)*this.height)+yOffset, 
					this.width, 
					this.height, 
					true);
		}
	}
}
