package project3;

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
		int BOARDSIZE = 6;
		int X_START_POS = 3;
		int Y_START_POS = 4;

		Scanner in = new Scanner(System.in);

		System.out.println("What size board?");
		BOARDSIZE = in.nextInt();

		System.out.println("What starting row?");
		Y_START_POS = in.nextInt();

		System.out.println("What starting column?");
		X_START_POS = in.nextInt();

		System.out.println("Graphical (slow) or text-based (fast)? (g/t)");
		String choice = in.next();

		// Create a new board
		KnightsTourBoard board;
		if (choice.toUpperCase().charAt(0) == 'G')
			board = new GraphicalKnightsTourBoard(BOARDSIZE);
		else
			board = new TextKnightsTourBoard(BOARDSIZE);

		in.close();

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
