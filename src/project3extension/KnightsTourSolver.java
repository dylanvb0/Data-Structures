package project3extension;

import java.util.Stack;

/**
 *
 * @author nbreems
 */
public class KnightsTourSolver {
	static Stack<Cell> solutions = new Stack<Cell>();
    /**
     *
     * @param board
     * @param x
     * @param y
     * @param num
     * @return
     */
    static public boolean solveKnightsTour(KnightsTourBoard board, int x, int y, int num) {
    	// Base Case - Board is filled
    	if(num == board.getBoardSize() * board.getBoardSize() + 1){
    		return true;
    	}
    	// exception will be thrown if position is off the board
    	try{
    		// check if the position is filled
    		if(board.getPositionValue(x, y) != -1){
    			return false;
    		}else{
    			board.setPositionValue(x, y, num);
    			solutions.push(new Cell(x, y, num));
    		}
    	}catch(IllegalArgumentException e){
    		return false;
    	}
    	// call all possible moves
    	if(solveKnightsTour(board, x-2, y+1, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x-1, y+2, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x-1, y+2, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x+1, y+2, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x+2, y-1, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x+1, y-2, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x-1, y-2, num + 1)){
    		return true;
    	}
    	if(solveKnightsTour(board, x-2, y-1, num + 1)){
    		return true;
    	}
    	// none of them worked, remove all higher number cells
    	while(solutions.peek().num >= num){
    		Cell current = solutions.pop();
    		board.setPositionValue(current.x, current.y, -1);
    	};

        return false;        
        
        /* Your job is to complete this recursive function.  You are
	 * given a board, an (x,y) position on the board, and the num
	 * of your next "visit" on the tour.  (i.e., position (x,y) was
	 * visit number num-1.)  If you can successfully complete the
	 * tour starting from the current layout, return true.  If it
	 * isn't possible to complete the tour from this state, return
	 * false. */
    
    }
    
}    
