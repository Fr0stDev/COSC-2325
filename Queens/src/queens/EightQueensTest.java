package queens;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import queens.QueensSolverEngine;
//import queens.QueensSolverEngineImpl;
//import queens.QueensSolverEngine;
public class EightQueensTest {

	@Before
	public void setUp() throws Exception {
		String username = System.getProperty("user.name");
		System.out.println("****  BEGIN OUTPUT for " + username + "  ****");
	}

	@After
	public void tearDown() throws Exception {
		String username = System.getProperty("user.name");
		System.out.println("****  END OUTPUT for " + username+ "  ****");
	}
	@Test
	public void testQueens() {

		int nq = QueensSolverEngine.MIN_QUEENS;
		QueensSolverEngine solver = new QueensSolverEngineImpl(nq);
		int []pos = solver.getQueenPositions();
		assertEquals (pos.length, nq);
		for(int i=0; i< nq; i++)
		{
			assertEquals(pos[i], -1);	
		}
		solver.solveAll();
		int numSolutions = solver.getNumSolutions();
		assertEquals(numSolutions,2);
		nq=25;
		try {
			solver.resetSolver(nq);
			System.out.println("FAILURE should throw RuntimeException if number of Queens greater than " + QueensSolverEngine.MAX_QUEENS);
		}catch (RuntimeException e) {
			System.out.println("SUCCESS: RuntimeException -- number of queens: " + nq + "  is out of range must be between " + QueensSolverEngine.MIN_QUEENS + " and " + QueensSolverEngine.MAX_QUEENS);
		}
		nq = 3;
		try {
			solver.resetSolver(nq);
			System.out.println("FAILURE should throw RuntimeException if number of Queens less than " + QueensSolverEngine.MIN_QUEENS);
		}catch (RuntimeException e) {
			System.out.println("SUCCESS: RuntimeException -- number of queens: " + nq + "  is out of range must be between " + QueensSolverEngine.MIN_QUEENS + " and " + QueensSolverEngine.MAX_QUEENS);
		}
		assertEquals(solver.getNumQueens(), 4);
		nq = 0;
		try {
			solver.resetSolver(nq);
			System.out.println("FAILURE should throw RuntimeException if number of Queens less than " + QueensSolverEngine.MIN_QUEENS);
		}catch (RuntimeException e) {
			System.out.println("SUCCESS: RuntimeException -- number of queens: " + nq + "  is out of range must be between " + QueensSolverEngine.MIN_QUEENS + " and " + QueensSolverEngine.MAX_QUEENS);
		}
		assertEquals(solver.getNumQueens(), 4);
	}
		@Test
		public void testSolve() {
			int numSolutions=0;
			int nq = QueensSolverEngine.MIN_QUEENS;
			QueensSolverEngine solver = new QueensSolverEngineImpl(nq);
			solver.solveAll();
			assertEquals(solver.getNumSolutions(), 2);
			solver.solveAll();
			assertEquals(solver.getNumSolutions(), 4);
			assertEquals(solver.getNumQueens(), 4);
			nq = 8;
			try {
				solver.resetSolver(nq);
				solver.solveAll();
				numSolutions = solver.getNumSolutions();
				assertEquals(numSolutions,92);
				System.out.println("SUCCESS number of solutions for " + nq + " queens is " + numSolutions); 
			}catch (RuntimeException e) {
				System.out.println("FAILURE: RuntimeException -- number of queens: " + nq + "  should run and solve. " );
			}
			assertEquals(solver.getNumQueens(), 8);
			nq = 6;
			try {
				solver.resetSolver(nq);
				solver.solveAll();
				numSolutions = solver.getNumSolutions();
				assertEquals(numSolutions,4);
				System.out.println("SUCCESS number of solutions for " + nq + " queens is " + numSolutions); 
			}catch (RuntimeException e) {
				System.out.println("FAILURE: RuntimeException -- number of queens: " + nq + "  should run and solve. " );
			}
			assertEquals(solver.getNumQueens(), 6);
			nq = 7;
			try {
				solver.resetSolver(nq);
				solver.solveAll();
				numSolutions = solver.getNumSolutions();
				assertEquals(numSolutions,40);
				System.out.println("SUCCESS number of solutions for " + nq + " queens is " + numSolutions); 
			}catch (RuntimeException e) {
				System.out.println("FAILURE: RuntimeException -- number of queens: " + nq + "  should run and solve. " );
			}
			
		}
		@Test
		public void testNumberQueens() {
			int nq = QueensSolverEngine.MIN_QUEENS;
			QueensSolverEngine solver = new QueensSolverEngineImpl(nq);
			assertEquals(solver.getNumQueens(), QueensSolverEngine.MIN_QUEENS);
			nq = QueensSolverEngine.MAX_QUEENS;
			solver.resetSolver(nq);
			assertEquals(solver.getNumQueens(), nq);
			solver.resetSolver(nq);
			assertEquals(solver.getNumQueens(), QueensSolverEngine.MAX_QUEENS);
			nq = 8;
			solver.resetSolver(nq);
			assertEquals(solver.getNumQueens(), 8);
			nq = QueensSolverEngine.MAX_QUEENS+1;
			try {
				solver.resetSolver(nq);	
				System.out.println("FAILURE should throw RuntimeException if resetSolver receives invalid number of queens:" + nq);
			}catch (RuntimeException e) {
				System.out.println("SUCCESS: RuntimeException resetSolver must receive number of queens\nbetween "
			                      + QueensSolverEngine.MIN_QUEENS + " and " + QueensSolverEngine.MAX_QUEENS +
						  		   " inclusive when resetting received: " + nq + " as number of queens.");
			}
			nq = QueensSolverEngine.MIN_QUEENS-1;
			try {
				solver.resetSolver(nq);	
				System.out.println("FAILURE should throw RuntimeException if resetSolver receives invalid number of queens:" + nq);
			}catch (RuntimeException e) {
				System.out.println("SUCCESS: RuntimeException resetSolver must receive number of queens\nbetween "
			                      + QueensSolverEngine.MIN_QUEENS + " and " + QueensSolverEngine.MAX_QUEENS +
						  		   " inclusive when resetting, received: " + nq + " as number of queens.");
			}
			
		}
	}

