package com.jstrgames.tetris.core.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.jstrgames.tetris.core.IGame;
import com.jstrgames.tetris.core.controller.IMovable.Direction;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.core.model.ShapeI;
import com.jstrgames.tetris.core.model.ShapeO;

public class TestMoveController {
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
		when(this.game.getBoard()).thenReturn(board);
	}
	
	@Test
	public void testPipeCanMoveLeft() {
		ShapeI pipe = new ShapeI(new Coordinate(1,1));		
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testPipeCannotMoveLeft() {
		ShapeI pipe = new ShapeI(new Coordinate(3,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testPipeCannotMoveLeftOutOfBound() {
		ShapeI pipe = new ShapeI(new Coordinate(0,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertFalse(controller.execute());
	}
			
	@Test
	public void testPipeCanMoveRight() {
		ShapeI pipe = new ShapeI(new Coordinate(0,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testPipeCannotMoveRight() {
		ShapeI pipe = new ShapeI(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testPipeCannotMoveRightOutOfBound() {
		ShapeI pipe = new ShapeI(new Coordinate(4,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testPipeCanMoveDown() {
		ShapeI pipe = new ShapeI(new Coordinate(1,0));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.DOWN);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testPipeCannotMoveDown() {
		ShapeI pipe = new ShapeI(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(pipe);
		IActionController controller = new MoveController(this.game, Direction.DOWN);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testSquareCanMoveLeft() {
		ShapeO square = new ShapeO(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testSquareCannotMoveLeft() {
		ShapeO square = new ShapeO(new Coordinate(2,2));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testSquareCannotMoveLeftOutOfBound() {
		ShapeO square = new ShapeO(new Coordinate(0,2));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.LEFT);
		assertFalse(controller.execute());
	}
		
	@Test
	public void testSquareCanMoveRight() {
		ShapeO square = new ShapeO(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testSquareCannotMoveRight() {
		ShapeO square = new ShapeO(new Coordinate(1,2));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testSquareCannotMoveRightOutOfBound() {
		ShapeO square = new ShapeO(new Coordinate(3,2));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.RIGHT);
		assertFalse(controller.execute());
	}
	
	@Test
	public void testSquareCanMoveDown() {
		ShapeO square = new ShapeO(new Coordinate(1,0));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.DOWN);
		assertTrue(controller.execute());
	}
	
	@Test
	public void testSquareCannotMoveDown() {
		ShapeO square = new ShapeO(new Coordinate(1,1));
		when(this.game.getShape()).thenReturn(square);
		IActionController controller = new MoveController(this.game, Direction.DOWN);
		assertFalse(controller.execute());
	}
}
