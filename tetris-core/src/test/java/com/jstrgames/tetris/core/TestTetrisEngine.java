package com.jstrgames.tetris.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jstrgames.tetris.core.controller.BoardController;
import com.jstrgames.tetris.core.controller.IRotateable.Rotate;
import com.jstrgames.tetris.core.controller.RotateController;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;
import com.jstrgames.tetris.core.model.ShapeI;
import com.jstrgames.tetris.core.model.ShapeT;
import com.jstrgames.tetris.core.model.ShapeO;

public class TestTetrisEngine {
	private final static int DEFAULT_START_LEVEL = 0;
	private final static int DEFAULT_START_SCORE = 0;
	
	private final static int BOARD_WIDTH = 5;
	private final static int BOARD_DEPTH = 10;
	
	private IQueue mockGenerator;
	private TetrisEngine gameEngine;
	private Board board;
			
	@Before
	public void setUp() {
		// black 5 x 10 board
		Shape[][] grid = new Shape[BOARD_WIDTH][BOARD_DEPTH];
		this.board = new Board(grid);
		
		mockGenerator = mock(IQueue.class);
		gameEngine = new TetrisEngine(board, new Score(DEFAULT_START_SCORE), new Level(DEFAULT_START_LEVEL), mockGenerator);
	}

	/**
	 * Scenario 1: 4 squares dropped on right. 5th shape (a pipe) dropped to left 
	 * <pre>
	 *   _ _ _ _ _
	 *  | |   |   |
	 *  | |_ _|_ _|
	 *  | |   |   |
	 *  |_|_ _|_ _|
	 * </pre>
	 */
	@Test
	public void testValidTetrisScenario1() {
		IShape[] shape = new IShape[5];
		shape[0] = new ShapeO(new Coordinate(1, 0));
		shape[1] = new ShapeO(new Coordinate(1, 0));
		shape[2] = new ShapeO(new Coordinate(3, 0));
		shape[3] = new ShapeO(new Coordinate(3, 0));
		shape[4] = new ShapeI(new Coordinate(0, 0));
		
		when(mockGenerator.pop())
			.thenReturn(shape[0])  // drop square on column 2 & 3
			.thenReturn(shape[1])  // drop square on top of above
			.thenReturn(shape[2])  // drop square on column 4 & 5
			.thenReturn(shape[3])  // drop square on top of above
			.thenReturn(shape[4])  // drop pipe on column 1 -> TETRIS!
			.thenReturn(null);     // end game by not returning no shape
				
		Thread t = new Thread(gameEngine);
		t.start();
		
		while(t.isAlive()) {
			try {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1L));			
			} catch (InterruptedException e) {
				fail("Failed to wait for mock to end");
			} 
		}
		
		IBoard board = gameEngine.getBoard();
		BoardController boardController = new BoardController(board, null);
		for(int x = 0; x < BOARD_WIDTH; x++ ) {
			for(int y = 0; y < BOARD_DEPTH; y++ ) {
				assertFalse(boardController.isFilled(new Coordinate(x, y)));
			}
		}
	}
	

	/**
	 * Scenario 2: 1 pipe to right flat, 2 squares dropped on top. 
	 * 1 pipe on top of square flat. pipe dropped to left 
	 * <pre>
	 *   _ _ _ _ _
	 *  | |_ _ _ _|
	 *  | |   |   |
	 *  | |_ _|_ _|
	 *  |_|_ _ _ _|
	 * </pre>
	 */
	@Test
	public void testValidTetrisScenario2() {
		IShape[] shape = new IShape[5];
		shape[0] = new ShapeI(new Coordinate(3, 0));
		shape[1] = new ShapeO(new Coordinate(1, 0));
		shape[2] = new ShapeO(new Coordinate(3, 0));
		shape[3] = new ShapeI(new Coordinate(2, 0));
		shape[4] = new ShapeI(new Coordinate(0, 0));
		
		when(mockGenerator.pop())
			.thenReturn(shape[0])  // drop pipe horizontally on column 1 -> 4
			.thenReturn(shape[1])  // drop square on column 2 & 3
			.thenReturn(shape[2])  // drop square on column 4 & 5
			.thenReturn(shape[3])  // drop pipe horizontally on column 1 -> 4
			.thenReturn(shape[4])  // drop pipe vertically on column 1 -> TETRIS!
			.thenReturn(null);     // end game by not returning no shape
		
		Thread t = new Thread(gameEngine);
		t.start();
		
		// pre-rotate by using mock game 
		IGame rotateShape1 = mock(IGame.class);
		when(rotateShape1.getShape()).thenReturn(shape[0]);
		when(rotateShape1.getBoard()).thenReturn(this.board);	
		RotateController rotateShape1Ctrl = new RotateController(rotateShape1, Rotate.CLOCKWISE);
		assertTrue(rotateShape1Ctrl.execute());
		
		// pre-rotate by using mock game 
		IGame rotateShape4 = mock(IGame.class);
		when(rotateShape4.getShape()).thenReturn(shape[3]);
		when(rotateShape4.getBoard()).thenReturn(this.board);	
		RotateController rotateShape4Ctrl = new RotateController(rotateShape4, Rotate.COUNTER_CLOCKWISE);
		assertTrue(rotateShape4Ctrl.execute());
		
		while(t.isAlive()) {
			try {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1L));			
			} catch (InterruptedException e) {
				fail("Failed to wait for mock to end");
			} 
		}
		
		IBoard board = gameEngine.getBoard();
		BoardController boardController = new BoardController(board, null);
		for(int x = 0; x < BOARD_WIDTH; x++ ) {
			for(int y = 0; y < BOARD_DEPTH; y++ ) {
				assertFalse(boardController.isFilled(new Coordinate(x, y)));
			}
		}
	}
	
	/**
	 * Scenario 3: 4 T-Shape to right with pipe dropped to left 
	 * <pre>
	 *   _ _ _ _ _
	 *  | |_   _| |
	 *  | | |_|_  |
	 *  | |  _| |_|
	 *  |_|_|_ _ _|
	 * </pre>
	 */
	@Test
	public void testValidTetrisScenario3() {
		IShape[] shape = new IShape[5];
		shape[0] = new ShapeT(new Coordinate(3, 1));
		shape[1] = new ShapeT(new Coordinate(4, 1));
		shape[2] = new ShapeT(new Coordinate(1, 1));
		shape[3] = new ShapeT(new Coordinate(2, 1));
		shape[4] = new ShapeI(new Coordinate(0, 0));
		
		when(mockGenerator.pop())
			.thenReturn(shape[0])  // drop T on column 2 -> 4
			.thenReturn(shape[1])  // drop T rotated counter-clock wise on column 3 -> 4
			.thenReturn(shape[2])  // drop T rotated clock wise on column 1 -> 2
			.thenReturn(shape[3])  // drop T rotated clock wise (2x) on column 1 -> 3
			.thenReturn(shape[4])  // drop pipe vertically on column 1 -> TETRIS!
			.thenReturn(null);     // end game by not returning no shape
		
		// pre-rotate by using mock game 
		IGame rotateShape2 = mock(IGame.class);
		when(rotateShape2.getShape()).thenReturn(shape[1]);
		when(rotateShape2.getBoard()).thenReturn(this.board);		
		RotateController rotateShape2Ctrl = new RotateController(rotateShape2, Rotate.COUNTER_CLOCKWISE);
		assertTrue(rotateShape2Ctrl.execute());
		
		// pre-rotate by using mock game
		IGame rotateShape3 = mock(IGame.class);
		when(rotateShape3.getShape()).thenReturn(shape[2]);
		when(rotateShape3.getBoard()).thenReturn(this.board);	
		RotateController rotateShape3Ctrl = new RotateController(rotateShape3, Rotate.CLOCKWISE);
		assertTrue(rotateShape3Ctrl.execute());
		
		// pre-rotate by using mock game
		IGame rotateShape4 = mock(IGame.class);
		when(rotateShape4.getShape()).thenReturn(shape[3]);
		when(rotateShape4.getBoard()).thenReturn(this.board);
		RotateController rotateShape4Ctrl = new RotateController(rotateShape4, Rotate.CLOCKWISE);
		assertTrue(rotateShape4Ctrl.execute());
		assertTrue(rotateShape4Ctrl.execute());
		
		Thread t = new Thread(this.gameEngine);
		t.start();
		
		while(t.isAlive()) {
			try {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1L));			
			} catch (InterruptedException e) {
				fail("Failed to wait for mock to end");
			} 
		}
		
		IBoard board = gameEngine.getBoard();
		BoardController boardController = new BoardController(board, null);
		for(int x = 0; x < BOARD_WIDTH; x++ ) {
			for(int y = 0; y < BOARD_DEPTH; y++ ) {
				assertFalse(boardController.isFilled(new Coordinate(x, y)));
			}
		}
	}
}
