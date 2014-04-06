package com.jstrgames.tetris.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.TetrisEngine;
import com.jstrgames.tetris.ui.component.Telemetry;

public class RenderEngine implements Runnable {
	private final static Logger LOG = LoggerFactory.getLogger(RenderEngine.class);
	
	private final static int DEFAULT_MAX_FPS = 45;
	private final static int DEFAULT_MAX_FRAME_SKIP = 5;
	
	private final int MAX_FPS ;
	// maximum number of frames to be skipped;
	private final int MAX_FRAME_SKIP;
	// the frame period
	private final int FRAME_PERIOD;
	
	private final GameScreen screen;
	private final Telemetry stats;
	private final Thread gameEngine;
	
	public RenderEngine(TetrisEngine gameEngine, GameScreen screen, Telemetry stats) {
		this.gameEngine = new Thread(gameEngine);
		this.screen = screen;
		this.stats = stats;
		
		this.MAX_FPS = DEFAULT_MAX_FPS;
		this.MAX_FRAME_SKIP = DEFAULT_MAX_FRAME_SKIP;
		this.FRAME_PERIOD = 1000 / this.MAX_FPS;
	}
	
	public RenderEngine(TetrisEngine gameEngine, GameScreen screen, Telemetry stats, int maxFps, int maxFrameSkip) {
		this.gameEngine = new Thread(gameEngine);
		this.screen = screen;
		this.stats = stats;
		
		this.MAX_FPS = maxFps;
		this.MAX_FRAME_SKIP = maxFps;
		this.FRAME_PERIOD = 1000 / this.MAX_FPS;

	}
	
	@Override
	public void run() {
		long beginTime; 	// the time when the cycle begun		
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime; 		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped
		
		sleepTime = 0;
		Object lock = new Object();
		
		this.gameEngine.start();
		while(true) {			
			synchronized (lock) {
				beginTime = System.currentTimeMillis();
				framesSkipped = 0; // resetting the frames skipped	
				
				// update game state
				this.screen.render();
				
				// draw game screen
				this.screen.repaint();
				
				timeDiff = System.currentTimeMillis() - beginTime;
				//calc sleep time
				sleepTime = (int) (FRAME_PERIOD - timeDiff);
				if(sleepTime > 0) {
					try {
						// refresh rate is too high.. sleep to slow down
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						LOG.warn("failed to sleep!", e);
					}				
				}
				
				// if sleep time is negative, skip frame to catch up
				while(sleepTime < 0 && framesSkipped < MAX_FRAME_SKIP ) {
					// update game to catch up
					this.screen.render();
					
		    		sleepTime += FRAME_PERIOD;
		    		framesSkipped++;
				}
				
		    	if(framesSkipped > 0)
		    	{
		    		if(LOG.isDebugEnabled()) {
		    			LOG.debug("Skipped:" + framesSkipped);
		    		}
		    	}
		    	
		    	// for stats	    	
		    	this.stats.storeStats(framesSkipped);
			}
		}
	}

}
