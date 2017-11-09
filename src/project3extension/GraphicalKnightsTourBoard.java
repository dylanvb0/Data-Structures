package project3extension;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

class GraphicalKnightsTourBoard extends JFrame implements KnightsTourBoard {

	private static final long serialVersionUID = 1L;
	private int boardSize;
    private int tryCountInt;
    
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel secondPanel;
    private javax.swing.JTextField[][] chessBoard;
    private javax.swing.JTextField tryCount;
    private javax.swing.JTextField progress;


    public GraphicalKnightsTourBoard(int x) {
        boardSize = x;
    
        mainPanel = new javax.swing.JPanel();
        secondPanel = new javax.swing.JPanel();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();

        setTitle("Knight's Tour");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mainPanel.setLayout(new java.awt.GridLayout(boardSize,boardSize));
        mainPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 12, 12)));
        mainPanel.setMinimumSize(new java.awt.Dimension(424, 424));
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
    
        secondPanel.setLayout(new java.awt.GridLayout(4,1));
        secondPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12,12,12,12)));
        secondPanel.setMinimumSize(new java.awt.Dimension(424,50));
        getContentPane().add(secondPanel,java.awt.BorderLayout.SOUTH);
    
        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        exitMenuItem.setMnemonic('E');
        exitMenuItem.setText("Exit");
        exitMenuItem.setToolTipText("Quit Team, Quit!");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);
        mainMenu.add(fileMenu);
        setJMenuBar(mainMenu);

        chessBoard = new javax.swing.JTextField[boardSize][boardSize];
    
        for (int i=0; i < boardSize; i++){
            for (int j=0; j < boardSize; j++) {
                chessBoard[i][j] = new javax.swing.JTextField(" .",  3);
                mainPanel.add(chessBoard[i][j]);
            }
        }
    
        secondPanel.add(new javax.swing.JLabel("Try Count:"));
        tryCount = new javax.swing.JTextField("0");
        tryCountInt = 0;
        secondPanel.add(tryCount);
        secondPanel.add(new javax.swing.JLabel("Progress:"));
        progress = new javax.swing.JTextField("");
        secondPanel.add(progress);
        
        getContentPane().validate();
        getContentPane().setVisible(true);
        pack();

        // Center in the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width - frameSize.width) / 2,
                              (screenSize.height - frameSize.width) / 2));
    
        setVisible(true);
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) { System.exit(0); }                                            
    private void exitForm(java.awt.event.WindowEvent evt) { System.exit(0); }                         

    public int getPositionValue(int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize)
            throw new java.lang.IllegalArgumentException("Bad call to getValue().");
    
        int toReturn;
        try{  toReturn = Integer.parseInt(chessBoard[x][y].getText()); }
        catch (java.lang.NumberFormatException e) { toReturn = -1; }
        return toReturn;
    }
    
    public void setPositionValue(int x, int y, int num){
        String val;
        if (num == -1)
            val = " .";
        else
            val = new Integer(num).toString();
        chessBoard[x][y].setText(val);
        tryCountInt++;
        if (tryCountInt % 1000 == 0)
            tryCount.setText(new Integer(tryCountInt).toString());
    }
    
    public int getBoardSize() {return boardSize;}
    public void setProgressIndicator(int prog) {
        progress.setText(new Integer(prog).toString());
    }
    public void printBoard() {
        // ignore this -- we're always showing the board.
    }
}
