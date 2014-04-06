package com.jstrgames.tetris.core.controller;

import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstrgames.tetris.core.model.Coordinate;
import com.jstrgames.tetris.core.model.IBoard;
import com.jstrgames.tetris.core.model.IShape;
import com.jstrgames.tetris.core.model.IShape.Shape;

/**
 * this will control the tetris board when shape action complete
 * 
 * @author Johnathan Ra
 * @company JSTR Games, LLC.
 *
 */
public class BoardController {
	private final static Logger LOG = LoggerFactory.getLogger(BoardController.class);
			
	private final IBoard board;
	private final IShape shape;
	
	private TreeSet<Integer> markedRowsForCleaning;
	
	/**
	 * default constructor
	 * 
	 * @param board
	 * @param shape
	 */
	public BoardController(IBoard board, IShape shape) {
		this.board = board;
		this.shape = shape;
		this.markedRowsForCleaning = new TreeSet<Integer>();
	}
	
	/**
	 * return true if already filled
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean isFilled(Coordinate coordinate) {
		boolean retVal = true;
		Shape[][] state = board.getGridState();
		// Note: we're okay with Y being in negative realm
		// shapes start above the board and began falling
		if(coordinate.getY() >= 0) {
			retVal = (state[coordinate.getX()][coordinate.getY()] != null);
		}
		
		return retVal;
	}
	
	/**
	 * returns true if within bound
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean isOutofBound(Coordinate coordinate) {
		boolean retVal = true;
		Shape[][] state = board.getGridState();
		if( coordinate.getX() >= 0 && coordinate.getX() < state.length) {
			
			// Note: we're okay with Y having a negative value since
			// shapes start above the board and began falling
			if (coordinate.getY() < state[0].length) {				
				retVal = false;
			}
		}
		return retVal;
	}
	
	/**
	 * method will scan for filled rows on end of shape drop
	 * 
	 * @return
	 */
	public int scanForFilledRows() {
		TreeSet<Integer> scannedRows = new TreeSet<Integer>();		
		Shape[][] state = this.board.getGridState();
		
		Coordinate[] fillCoordinates = this.shape.getFillCoordinates();
		for(Coordinate coordinate:fillCoordinates) {
			Integer y = new Integer(coordinate.getY());
			if(scannedRows.contains(y)) {
				continue; // row already scanned
			}
			
			scannedRows.add(y); // keep track of scanned rows
			
			boolean markRowForClear = true;
			for(int x = 0 ; x < state.length; x++) {
				if(isFilled(new Coordinate(x, y))) {					
					continue; // filled.. continue to next cell
				}
				if(LOG.isDebugEnabled()) {
					LOG.debug("row[" + y + "] contains empty cell(s)!");
				}
				markRowForClear = false;				
				break;
			}
			
			if(markRowForClear) {
				LOG.info("row[" + y + "] has been marked for clearing!");
				markedRowsForCleaning.add(y);
			}
		}
		
		return markedRowsForCleaning.size();
	}
	
	/**
	 * method clear filled rows
	 */
	public void clearFilledRows() {	
		while(this.markedRowsForCleaning.size() > 0) {
			Integer row = this.markedRowsForCleaning.pollFirst();
			this.board.removeRow(row.intValue());
		}	
	}
}
