package project3extension;

class TextKnightsTourBoard implements KnightsTourBoard {

    private int boardSize;
    private int[][] chessBoard;
    private long tryCount;
    private int progress;

    public TextKnightsTourBoard(int x) {
        boardSize= x;

        chessBoard = new int[boardSize][boardSize];
    
        for (int i=0; i < boardSize; i++){
            for (int j=0; j < boardSize; j++) {
                chessBoard[i][j] = -1;
            }
        }
    
        tryCount = 0;
        progress = 0;
    }

    public int getPositionValue(int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize)
            throw new java.lang.IllegalArgumentException("Bad call to getPositionValue().");
    
        return chessBoard[x][y];
    }
    
    public void setPositionValue(int x, int y, int num){
        chessBoard[x][y] = num;
        tryCount++;
        if (tryCount % 1000000 == 0 || num + 1 >= boardSize * boardSize) {
            System.out.print(".");
        }
    }
    
    public int getBoardSize() {return boardSize;}
    public void setProgressIndicator(int prog) {
        progress = prog;
    }
    
    public void printBoard() {
        int n;
        for (int j = 0; j < boardSize; j++) {
            for (int i = 0; i < boardSize; i++) {
                n = chessBoard[i][j];
                if (n < 100) System.out.print(" ");
                if (n < 10) System.out.print(" ");
                if (n != -1)
                    System.out.print(n);
                else
                    System.out.print(".");
                System.out.print(" ");
            }
            System.out.println("");
        }       
    }
}
