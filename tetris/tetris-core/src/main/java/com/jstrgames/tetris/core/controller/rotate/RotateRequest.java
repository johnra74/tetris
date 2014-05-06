package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.model.IShape;

public class RotateRequest {
	private Rotate rotate;
	private IShape shape;
	
	public RotateRequest(IShape shape, Rotate rotate) {
		this.shape = shape;
		this.rotate = rotate;
	}
	
	public Rotate getRotate(){
		return this.rotate;
	}
	
	public IShape getShape() {
		return this.shape;
	}
}
