package project3extension;

import java.util.Scanner;

/**
 * 
 * @author nbreems
 */
public class KnightsTourTester {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long totalStart = System.currentTimeMillis();
		int BOARDSIZE = 8;
		for(int i = 0; i < BOARDSIZE; i++){
			for(int j = 0; j < BOARDSIZE; j++){
				int X_START_POS = i;
				int Y_START_POS = j;
		
				// Create a new board
				KnightsTourBoard board = new TextKnightsTourBoard(BOARDSIZE);
		
				// Set the original position
		//		board.setPositionValue(X_START_POS, Y_START_POS, 1);
		
				// Start the clock
				long start = System.currentTimeMillis();
		
				// Attempt to solve the Knights Tour (pass it our current board, the
				// position we're
				// on now, and the next visit number to be made
				if (KnightsTourSolver.solveKnightsTour(board, X_START_POS, Y_START_POS,
						1)) {
					board.printBoard();
					System.out.println("It took "
							+ (System.currentTimeMillis() - start) / 1000
							+ " seconds to successfully find a solution.");
				} else {
					board.printBoard();
					System.out.println("After " + (System.currentTimeMillis() - start)
							/ 1000 + " seconds, unable to find a solution.");
				}
			}
		}
		System.out.println("Total Time: " + (System.currentTimeMillis() - totalStart) / 1000);
	}
}
