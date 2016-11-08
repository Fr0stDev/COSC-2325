package queens;
// 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface QueensSolverEngine {
    public static final int MIN_QUEENS = 4;  //fewest number of Queens solver will process
    public static final int MAX_QUEENS = 12; // max number of Queens solver will process
    
    // finds all solutions to the nqueens problem for current number of queens
	public void solveAll(); 
	
	//pre: numberQueens >= MIN_QUEENS && numberQueens <= MAX_QUEENS
	// throws RuntimeException if numberQueens out of range
	public void resetSolver(int numberQueens); 
	
	
	public int getNumSolutions();
	// returns number of solutions on this board

	// sets a stop flag to true in order to stop the solveAll from solving, or false to allow it to execute
	public void setStopFlag(boolean flag);

	// returns array of queen positions on board, index is row number, value at index is column number 
	// length of return value is exactly the numberQueens currently set for this engine
	public int [] getQueenPositions();

	// returns number of queens being solved for
	public int getNumQueens();

	// for events to be fired
	// boiler plate standard methods for use with a model - adds change listener instance to this model's listener list
	public void addChangeListener(ChangeListener stackEngineListener);

	// removes change listener from this model's listener list.
	public void removeChangeListener(ChangeListener stackEngineListener);

	// raises or fires off a change event so that any listeners can hear it and thus update their situation (ie the view)
	public void fireChangeEvent(ChangeEvent event);


	/* a private possible implementation of a method which could be called by solveAll() -- the general method that finds all solutions
	 *  this method below recursively tries to place a queen in each
	     column of the board until a solution is found, prints it, and tries
	     all remaining positions, finds all solutions in a brute force, blind search of all possibilities.


	   private void solve (int col)
	    {
		// check to see if we are at the base case, a queen 
		//  in all columns of the board (same size as numqueens)
		if (col >= numqueens)
		    {
			solcount ++;  // count a solution and add code to update frame's count
			showBoard();  // will redraw the board with the solution 
			return;  // get out of the recursion.
		    }
		// if we get here we try to solve for current column

		int row = findNextRow(0,col);    // get a row for current col
		while(row < numqueens)       // while we get a good row
		    {
			placeQueen(row, col); // put the queen at row,col on the board
			solve(col+1);   // try the next col since we got a good row
			removeQueen(row,col);  // remove Queen at row,col and try another row
			row = findNextRow(row+1,col);   // try a different row same col
		    } // end of while
	    } // end of solve

	*/

}