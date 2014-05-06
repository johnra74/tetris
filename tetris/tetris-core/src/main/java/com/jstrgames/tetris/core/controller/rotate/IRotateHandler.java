package com.jstrgames.tetris.core.controller.rotate;

import com.jstrgames.tetris.core.model.Coordinate;

public interface IRotateHandler {
	
	void setNextHandler(IRotateHandler nextHandler);
	Coordinate[] executeRequest(RotateRequest request);
	
}
