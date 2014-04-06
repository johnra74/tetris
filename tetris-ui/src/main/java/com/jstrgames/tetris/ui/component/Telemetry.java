package com.jstrgames.tetris.ui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

public class Telemetry implements IDrawable {

	private DecimalFormat df = new DecimalFormat("0.00");
	
	// we'll be reading the stats every second
	private final static int STAT_INTERVAL = 1000; // 1 sec
	// the average will be caculated by storing the last n FPSs
	private final static int FPS_HISTORY_NR = 10;
	// last time the status was stored
	private long lastStatusStore = 0;
	// the status time counter
	private long statusIntervalTimer = 0L;
	// number of frames skipped in a store cycle (1 sec)
	private long framesSkippedPerStatCycle = 0L;
	
	// number of rendered frames in an interval
	private int frameCountPerStatCycle = 0;
	// the last FPS values
	private double fpsStore[];
	// the number of times the stat has been read
	private long statsCount = 0;
	// the average FPS since the game started
	private double averageFps = 0.0;
	
	public Telemetry() {
		// initialize timing elements
		fpsStore = new double[FPS_HISTORY_NR];
		for (int i = 0; i < FPS_HISTORY_NR; i++) 
		{
			fpsStore[i] = 0.0;
		}		
	}
	/**
	 * The statistics - it is called every cycle, it checks if time since last
	 * store is greater than the statistics gathering period (1 sec) and if so
	 * it calculates the FPS for the last period and stores it.
	 *
	 *  It tracks the number of frames per period. The number of frames since
	 *  the start of the period are summed up and the calculation takes part
	 *  only if the next period and the frame count is reset to 0.
	 */
	public void storeStats(int framesSkipped) 
	{
		framesSkippedPerStatCycle += framesSkipped;
		
		frameCountPerStatCycle++;

		// check the actual time
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);

		if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) 
		{
			// calculate the actual frames pers status check interval
			double actualFps = (double)(frameCountPerStatCycle / (STAT_INTERVAL / 1000));

			//stores the latest fps in the array
			fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;

			// increase the number of times statistics was calculated
			statsCount++;

			double totalFps = 0.0;
			// sum up the stored fps values
			for (int i = 0; i < FPS_HISTORY_NR; i++) 
			{
				totalFps += fpsStore[i];
			}

			// obtain the average
			if (statsCount < FPS_HISTORY_NR) 
			{
				// in case of the first 10 triggers
				averageFps = totalFps / statsCount;
			} 
			else 
			{
				averageFps = totalFps / FPS_HISTORY_NR;
			}

			// resetting the counters after a status record (1 sec)
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;

			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			// Log.d(TAG, "Average FPS:" + df.format(averageFps));
			
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		StringBuilder builder = new StringBuilder("fps: ");
		builder.append(df.format(averageFps));
		builder.append(" skip: ");
		builder.append(framesSkippedPerStatCycle);
		
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Verdana", Font.BOLD, 8));		
		graphics.drawString(builder.toString(), 10, 10);		
	}
	
	@Override
	public int getWidth() {
		return 100;
	}
	
	@Override
	public int getHeight() {
		return 25;
	}

}
