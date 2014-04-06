package com.jstrgames.tetris.ui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jstrgames.tetris.core.TetrisEngine;
import com.jstrgames.tetris.core.controller.IActionController;
import com.jstrgames.tetris.core.controller.MoveController;
import com.jstrgames.tetris.core.controller.RotateController;
import com.jstrgames.tetris.core.controller.IMovable.Direction;
import com.jstrgames.tetris.core.controller.IRotateable.Rotate;

public class ControlListener implements KeyListener {
	private final TetrisEngine gameEngine;
	
	public ControlListener(TetrisEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		Direction direction;
		Rotate rotate;
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = Direction.LEFT;
				rotate = null;
				break;
				
			case KeyEvent.VK_RIGHT:
				direction = Direction.RIGHT;
				rotate = null;
				break;
				
			case KeyEvent.VK_DOWN:
				direction = Direction.DOWN;
				rotate = null;
				break;
				
			case KeyEvent.VK_Q:
				direction = null;
				rotate = Rotate.COUNTER_CLOCKWISE;
				break;
				
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				direction = null;
				rotate = Rotate.CLOCKWISE;
				break;
				
			default:
				direction = null;
				rotate = null;
				break;
		}
		
		IActionController controller;
		if(direction != null) {
			controller = new MoveController(this.gameEngine, direction);
			controller.execute();
		}
		
		if(rotate != null) {
			controller = new RotateController(this.gameEngine, rotate);
			controller.execute();			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}
}
