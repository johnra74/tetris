package com.jstrgames.tetris.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.IQueue;
import com.jstrgames.tetris.core.ShapeQueue;
import com.jstrgames.tetris.core.TetrisEngine;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;
import com.jstrgames.tetris.snd.MediaPlayer;
import com.jstrgames.tetris.ui.GameScreen;
import com.jstrgames.tetris.ui.RenderEngine;
import com.jstrgames.tetris.ui.component.Telemetry;
import com.jstrgames.tetris.ui.listener.ControlListener;

public class Tetris extends Applet {
	private static final Logger LOG = LoggerFactory.getLogger(Tetris.class);
	
	private static final long serialVersionUID = 3833991488488187015L;
	
	private static final int DEFAULT_STARTING_LEVEL = 0;
	private static final int DEFAULT_SCORE = 0;
	private static final int MAX_HEIGHT = 20;
	private static final int MAX_WIDTH = 10;
	
	private MediaPlayer player;
	private RenderEngine renderEngine;
	private TetrisEngine gameEngine;
	
	@Override
	public void init() {
		final Shape[][] board = new Shape[MAX_WIDTH][MAX_HEIGHT + IBoard.TOP_BOUND];
		
		final Board boardUI = new Board(board);
		final Score score = new Score(DEFAULT_SCORE);
		final Level level = new Level(DEFAULT_STARTING_LEVEL);
		final IQueue queue = new ShapeQueue(System.currentTimeMillis());		
		final Telemetry stats = new Telemetry();
		
		this.gameEngine = new TetrisEngine(boardUI, score, level, queue);	
		
		final ControlListener control = new ControlListener(this.gameEngine);
		final BorderLayout layout = new BorderLayout();
		final GameScreen screen = new GameScreen(this.gameEngine, stats, "digital", 2);
		
		screen.setShowStats(false);
		setFocusable(true);
		
		addKeyListener(control);
		setLayout(layout); 
		add(screen, BorderLayout.CENTER);
		
		this.renderEngine = new RenderEngine(this.gameEngine, screen, stats);		
		try {
			this.player = new MediaPlayer("tetris.mid");
		} catch (InvalidMidiDataException e) {
			LOG.warn("Error loading midi.. skipping music", e);
		} catch (IOException e) {
			LOG.warn("Error loading midi.. skipping music", e);
		} catch (MidiUnavailableException e) {
			LOG.warn("Error loading midi.. skipping music", e);
		} catch (NullPointerException e) {
			LOG.warn("Error loading midi.. skipping music", e);
		}
	}
	
	@Override
	public void start() {	
		Thread gameThread = new Thread(this.renderEngine);
		gameThread.start();
		if(this.player != null) {
			this.player.play();
		}
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
