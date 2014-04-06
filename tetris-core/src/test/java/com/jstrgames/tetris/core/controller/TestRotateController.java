package com.jstrgames.tetris.core.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.core.model.ShapeI;
import com.jstrgames.tetris.core.model.ShapeO;

public class TestRotateController {
	private IGame game;
	
	@Before
	public void setupBoard() {
		// for testing purpose we will only have a 5 x 5 board
		// with bottom row filled and middle cell in next to last
		// bottom row.
		Shape[][] state = new Shape[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 4) {
					state[i][j] = Shape.LETTER_L;
				}
			}
		}
		// next to last row
		state[2][3] = Shape.LETTER_L;
		state[4][3] = Shape.LETTER_L;
		// last row
		state[0][4] = Shape.LETTER_T;
		state[1][4] = Shape.LETTER_T;
		state[2][4] = Shape.LETTER_T;
		state[3][4] = Shape.LETTER_J;
		state[4][4] = Shape.LETTER_J;
		
		IBoard board = new Board(state);
		game = mock(IGame.class);
		when(game.getBoard()).thenReturn(board);
	}
	
	@Test
	public void testPipeCanRotateClockwise() {
		ShapeI pipe = new ShapeI(new Coordinate(2,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new RotateController(this.game, Rotate.CLOCKWISE);
		assertTrue(controller.execute());
		
	}

	@Test
	public void testPipeCanRotateCounterClockwise() {
		ShapeI pipe = new ShapeI(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new RotateController(this.game, Rotate.COUNTER_CLOCKWISE);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testSquareCanRotateClockwise() {
		ShapeO square = new ShapeO(new Coordinate(2,1));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new RotateController(this.game, Rotate.CLOCKWISE);
		assertTrue(controller.execute());
		
	}

	@Test
	public void testSquareCanRotateCounterClockwise() {
		ShapeO square = new ShapeO(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new RotateController(this.game, Rotate.COUNTER_CLOCKWISE);
		assertTrue(controller.execute());
	}
}
