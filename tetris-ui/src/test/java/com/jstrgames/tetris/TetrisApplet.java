package com.jstrgames.tetris;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;

import com.jstrgames.tetris.core.ShapeQueue;
import com.jstrgames.tetris.core.TetrisEngine;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;
import com.jstrgames.tetris.ui.GameScreen;
import com.jstrgames.tetris.ui.RenderEngine;
import com.jstrgames.tetris.ui.component.Telemetry;
import com.jstrgames.tetris.ui.listener.ControlListener;

public class TetrisApplet extends Applet {
	private static final long serialVersionUID = 1420914402593070217L;
	
	private final static String DEFAULT_FONT = "digital";
	
	private final static int DEFAULT_STARTING_LEVEL = 0;
	private final static int DEFAULT_SCORE = 0;
	private final static int MAX_HEIGHT = 20;
	private final static int MAX_WIDTH = 10;
		
	private GameScreen screen;
	private TetrisEngine gameEngine;
	private RenderEngine renderEngine;
	private Telemetry stats;
	
	private Thread gameThread;
		
	@Override
	public void init() {
		super.init();
		Shape[][] grid = new Shape[MAX_WIDTH][MAX_HEIGHT + IBoard.TOP_BOUND];
		
		Board board = new Board(grid);
		this.gameEngine = new TetrisEngine(board, 
										   new Score(DEFAULT_SCORE), 
										   new Level(DEFAULT_STARTING_LEVEL), 
										   new ShapeQueue(System.currentTimeMillis()));
		this.stats = new Telemetry();
		this.screen = new GameScreen(this.gameEngine, this.stats, DEFAULT_FONT, 2);
		this.renderEngine = new RenderEngine(this.gameEngine, this.screen, this.stats);
		
		this.gameThread = new Thread(this.renderEngine);
		
		setLayout(new BorderLayout());		
		add(BorderLayout.CENTER, this.screen);
		addKeyListener(new ControlListener(this.gameEngine));
		setFocusable(true);
		setBackground(Color.WHITE);		
	}

	@Override
	public void start() {	
		gameThread.start();
	}
	
	@Override
	public void stop() {
		super.stop();
	}
}
