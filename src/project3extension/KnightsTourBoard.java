package project3extension;

/**
 * The KnightsTourBoard interface defines the functions that will be implemented
 * by any KnightTourBoard object.
 * 
 * @author (Nick Breems) 
 * @version (2)
 */

public interface KnightsTourBoard
{
    /**
     * Gets the value of a particular position on the board.
     * 
     * @param x     The column value of the position
     * @param y     The row value of the position
     * @return      The value stored in the position, which refers to
     * the order in which that position was visited on the Knight's tour.
     */
    public int getPositionValue(int x, int y) ;
    
    /**
     * Sets the visit order for a position on the board
     * 
     * @param x     The column of the position
     * @param  y    The row of the position
     * @param num   The number of the order in which this position was visited. 
     * (The starting position is 1, the second place visited is 2, etc...
     */
    public void setPositionValue(int x, int y, int num) ;
    
    /**
     * Gets the size of the board.
     * 
     * @return      The size of this chess board
     */
    public int getBoardSize() ;
    
    /**
     * Sets the progress indicator.  This function allows the implementing object to 
     * show the current progress of the search.  (Of course, with backtracking the
     * progress is not monotonically increasing -- very often the tour will appear
     * to be near the end, but then need to backtrack significantly and start over from
     * a much earlier position.)
     * 
     * @param  prog    How far into the Knight's Tour we currently are
     */
    public void setProgressIndicator(int prog) ;
    
    /**
     * Prints the current layout of the board.
     * 
     */
    public void printBoard();
}
