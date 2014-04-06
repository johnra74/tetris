import static javax.swing.JFrame.EXIT_ON_CLOSE
import static java.awt.BorderLayout.*

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import groovy.beans.Bindable;
import groovy.swing.SwingBuilder

import com.jstrgames.tetris.core.ShapeQueue;
import com.jstrgames.tetris.core.TetrisEngine;
import com.jstrgames.tetris.core.model.Board;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.Level;
import com.jstrgames.tetris.core.model.Score;
import com.jstrgames.tetris.core.model.IShape.Shape;
import com.jstrgames.tetris.snd.MediaPlayer;
import com.jstrgames.tetris.ui.GameScreen;
import com.jstrgames.tetris.ui.RenderEngine;
import com.jstrgames.tetris.ui.component.Telemetry;
import com.jstrgames.tetris.ui.listener.ControlListener;

def  DEFAULT_STARTING_LEVEL = 0;
def  DEFAULT_SCORE = 0;
def  MAX_HEIGHT = 20;
def  MAX_WIDTH = 10;

def board = new Shape[MAX_WIDTH][MAX_HEIGHT + IBoard.TOP_BOUND];
def boardUI = new Board(board);
def score = new Score(DEFAULT_SCORE);
def level = new Level(DEFAULT_STARTING_LEVEL);
def queue = new ShapeQueue(12789346);
def stats = new Telemetry();

def gameEngine = new TetrisEngine(boardUI, score, level, queue);
def control = new ControlListener(gameEngine);

def screen = new GameScreen(gameEngine, stats, "digital", 2);
screen.addKeyListener(control);
screen.setFocusable(true);
def renderEngine = new RenderEngine(gameEngine, screen, stats);

def swing = new SwingBuilder()  

swing.edt {
	frame(id: "frame",
		  title: "Tetris", 
		  pack: true, 
		  show: true,  
		  defaultCloseOperation: EXIT_ON_CLOSE,
		  preferredSize: [340, 500]) {
        borderLayout()
		
		container (screen,
			id:'gameScreen',
			constraints: CENTER)
    }  
} 

def player = new MediaPlayer("tetris.mid");
def gameThread = new Thread(renderEngine);
gameThread.start();
player.play();
