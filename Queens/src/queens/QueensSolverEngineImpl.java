package queens;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class QueensSolverEngineImpl implements QueensSolverEngine {
	
	private boolean[] rows;
	private int[] position;
	private boolean[] rightDiag;
	private boolean[] leftDiag;
	private int numQueens;
	private int numSolutions;
	
	private boolean stopFlag;
	
    private EventListenerList eventListenerList;
    private final ChangeEvent CHANGE_EVENT = new ChangeEvent(this);
	
	public QueensSolverEngineImpl() {
	
        this.eventListenerList = new EventListenerList();
        resetSolver(4);
        
	}
	
	public QueensSolverEngineImpl(int nq) {
		
        this.eventListenerList = new EventListenerList();
        resetSolver(nq);
        
	}

    // finds all solutions to the nqueens problem for current number of queens
	public void solveAll() {
		
		setStopFlag(false);
		solve(0);
		
	}
	//pre: numberQueens >= MIN_QUEENS && numberQueens <= MAX_QUEENS
	// throws RuntimeException if numberQueens out of range
	public void resetSolver(int nq) {
		
		if (nq < MIN_QUEENS || nq > MAX_QUEENS) {
			throw new RuntimeException();
		}
		
		setStopFlag(true);
		
		this.numQueens = nq;
		this.numSolutions = 0;
		this.rows = new boolean[numQueens];
		this.position = new int[numQueens];
		this.rightDiag = new boolean[2 * numQueens - 1];
		this.leftDiag = new boolean[2 * numQueens - 1];
		
		for (int i = 0; i < numQueens; i++) {
			rows[i] = false;
			position[i] = -1;
		}
		
		for (int i = 0; i < (numQueens * 2) - 1; i++) {
			leftDiag[i] = false;
			rightDiag[i] = false;
		}
		
        this.fireChangeEvent(CHANGE_EVENT);
	}

	// returns number of solutions on this board
	public int getNumSolutions() {
		return this.numSolutions;
	}

	// sets a stop flag to true in order to stop the solveAll from solving, or false to allow it to execute
	public void setStopFlag(boolean flag) {
		
		this.stopFlag = flag;
		
	}

	// returns array of queen positions on board, index is row number, value at index is column number 
	// length of return value is exactly the numberQueens currently set for this engine
	public int[] getQueenPositions() {
		int[] array = new int[this.getNumQueens()];
		
		for (int i = 0; i < this.getNumQueens(); i++) {
			array[i] = this.position[i];
		}
		
		return array;
	}

	// returns number of queens being solved for
	public int getNumQueens() {
		return this.numQueens;
	}

	public void addChangeListener(ChangeListener changeListener) {
        eventListenerList.add(ChangeListener.class, changeListener);
		
	}

	public void removeChangeListener(ChangeListener changeListener) {
        eventListenerList.remove(ChangeListener.class, changeListener);
		
	}

	public void fireChangeEvent(ChangeEvent event) {
		// Guaranteed to return a non-null array
		Object[] listeners = eventListenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==ChangeListener.class) {
				((ChangeListener)listeners[i+1]).stateChanged(event);
			}
		}
	}
	
	//Returns string
	public String toString() {
		//String temp = "Solving for " + this.numQueens + " queens \n";
		String temp = "\nSolution Found!" + "\n";
		
		for(int i = 0; i < this.numQueens; i++) {
			for (int k = 0; k < this.numQueens; k++) {
				if (position[i] == k) {
					temp += "Q";
				}
				else {
					temp += "X";
				}
			}
			temp += "\n";
		}
		return temp;
	}
	
	/*
	 * Private Stuff
	 */
	
	private boolean isSafe(int row, int col) {
		if (rows[row] == true) {
			return false;
		}
		
		if (this.leftDiag[(row - col) + (numQueens - 1)] == true) {
			return false;
		}
		if (this.rightDiag[(row + col)]) {
			return false;
		}
		
		return true;
	}
	
	private int findNextRow(int row, int col) {
		int start = row;
		while (start < numQueens) {
			if (isSafe(start, col)) {
				return start;
			}
		start++;
		}
	return start;
	}
	
	private void solve (int col) {
		 
		if (stopFlag == true) {
			return;
		}
		// check to see if we are at the base case, a queen 
		//  in all columns of the board (same size as numqueens)
		if (col >= numQueens) {
			
			this.numSolutions ++;  // count a solution and add code to update frame's count
			showBoard();  // will redraw the board with the solution 
			this.fireChangeEvent(CHANGE_EVENT);
			return;  // get out of the recursion.
		}
		// if we get here we try to solve for current column

		int row = findNextRow(0,col); // get a row for current col
		
		while(row < numQueens && stopFlag == false) {  // while we get a good row 
			
			placeQueen(row, col); // put the queen at row,col on the board
			solve(col+1);   // try the next col since we got a good row
			removeQueen(row,col);  // remove Queen at row,col and try another row
			row = findNextRow(row+1,col);   // try a different row same col
		} // end of while
	} // end of solve
	
	private void placeQueen(int row, int col) {
		rows[row] = true;
		position[row] = col;
		
		this.leftDiag[(row - col) + (numQueens - 1)] = true;
		this.rightDiag[(row + col)] = true;
		
        this.fireChangeEvent(CHANGE_EVENT);
	}
	
	private void removeQueen(int row, int col) {
		rows[row] = false;
		position[row] = -1;
		
		this.leftDiag[(row - col) + (numQueens - 1)] = false;
		this.rightDiag[(row + col)] = false;
		
        this.fireChangeEvent(CHANGE_EVENT);
	}
	
	private void showBoard() {
		System.out.println(this);
	}

}
