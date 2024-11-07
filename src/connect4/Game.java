package connect4;

public class Game {

	// Different Box states
	private enum Box {
		EMPTY,
		RED,
		YELLOW;
	}
	
	private Box Winner = Box.EMPTY;
	private Box[][] board; // Declares Game bard
	private int winningSequence[][] = new int[4][2];
	private int[] nextBoxes; // Array of indices of next boxes that can be filled in every column
	

	// Creates a Game board of default size 6x7
	public Game() {
		this(6, 7);
		StdDraw.enableDoubleBuffering();

		}

	// Constructor to specify size of Game board
	public Game(int m, int n) {
		
		this.board = new Box[m][n];
		this.initialseGameBoard();
		this.initialiseNextBoxes(n);
	}

	// Initialises the nextBoxes array to the last row of the board
	private void initialiseNextBoxes(int n) {
		
		this.nextBoxes = new int[n];
		for (int i=0; i<n; i++) {
			this.nextBoxes[i] = this.board.length-1;
		}
	}

	// Initialise the game board to empty boxes
	private void initialseGameBoard() {
		
		for (int i=0; i < this.board.length; i++) {
			for (int j=0; j< this.board[i].length; j++) {
				this.board[i][j] = Box.EMPTY;
			}
		}
	}
	
	// Decrements 
	private void changeNextBox(int col) {
		
		this.nextBoxes[col] -= 1;
	}
	
	// Makes the move then verifies if the player has won
	public boolean playRed(int col) {
		Box player = Box.RED;
		if(!isValidMove(col)){
			return false;
		}

		this.makeMove(col, player);

		if(this.checkWin(player)) {
			this.setWinner(player);
		}

		return true;
	}

	// Makes the move then verifies if the player has won
	public boolean playYellow(int col) {
		Box player = Box.YELLOW;
		if(!isValidMove(col)){
			return false;
		}

		this.makeMove(col, player);

		if(this.checkWin(player)) {
			this.setWinner(player);
		}

		return true;
	}

	// Sets winner
	private void setWinner(Box player){
		this.Winner = player;
	}

	// Get Winner
	public String getWinner(){
    switch (this.Winner) {
        case Box.RED:
            return "RED";
        case Box.YELLOW:
            return "YELLOW";
        default:
            return null;
    }
}
	
	// Play function implementation
	private void makeMove(int col, Box player) {
		
		int row = this.nextBoxes[col];
		this.board[row][col] = player;
		this.changeNextBox(col);
			
	}
	
	// Checks if a move is valid
	private boolean isValidMove(int col) {
		if (col < 0 || col >= this.board[0].length)
			// out of bound error
			return false;
			
		else {
			// Full error if false 
			return this.nextBoxes[col] >= 0;
		}
		
	}

	// Draw line between on the winning sequence
	private void drawWinningLine() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.line(this.winningSequence[0][0], this.winningSequence[0][1], this.winningSequence[3][0], this.winningSequence[3][1]);
	}
	
	// Checks if a player has won
	private boolean checkWin(Box player) {
		
		return this.checkAllRow(player) || this.checkAllCol(player) || 
				this.checkAllDiagLeft(player) || this.checkAllDiagRight(player);
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any column
	private boolean checkAllCol(Box player) {
		for (int i=0; i<this.board[0].length; i++) {
			if (this.checkCol(i, player))
				return true;
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any row
	private boolean checkAllRow(Box player) {
		for (int i=0; i<this.board.length; i++) {
			if (this.checkRow(i, player))
				return true;
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given row 
	// and add the values to the winning sequence
	private boolean checkRow(int row, Box player) {
		int count = 0;
		for (int i=0; i<this.board[row].length && count < 4; i++) {
			if(this.board[row][i] == player) {
				count++;
				this.addToWinningSequence(row, i, count);
			}
			else{
				this.resetWinnigSequence();
				count =0;
			}	
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given column 
	// and add the values to the winning sequence
	private boolean checkCol(int col, Box player) {
		int count = 0;
		for (int i=0; i<this.board.length && count < 4; i++) {
			if(this.board[i][col] == player) {
				count++;
				this.addToWinningSequence(i, col, count);
			}
			else{
				this.resetWinnigSequence();
				count =0;
			}	
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any diagonal
	private boolean checkAllDiagRight(Box player) {
		for (int i=0, j=1; j >= 1 && j < 4; j++) {
			if (this.checkDiagRight(i, j, player))
				return true;
		}
		
		for (int i=0, j=0; i >= 0 && i < 3; i++) {
			if (this.checkDiagRight(i, j, player))
				return true;
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any diagonal
	private boolean checkAllDiagLeft(Box player) {
		for (int i=5, j=1; j >= 1 && j < 4; j++) {
			if (this.checkDiagLeft(i, j, player))
				return true;
		}
		
		for (int i=5, j=0; i <= 5 && i > 2; i++) {
			if (this.checkDiagLeft(i, j, player))
				return true;
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given diagonal
	private boolean checkDiagRight(int row, int col, Box player) {
		int count = 0;
		for (int i=row, j=col; i<this.board.length && j < this.board[row].length && count < 4; i++, j++) {
			if(this.board[i][j] == player) {
				count++;
				this.addToWinningSequence(i, j, count);
			}
			else{
				this.resetWinnigSequence();
				count =0;
			}	
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given diagonal
	private boolean checkDiagLeft(int row, int col, Box player) {
		int count = 0;
		for (int i=row, j=col; i >= 0 && j < this.board[row].length && count < 4; i--, j++) {
			if(this.board[i][j] == player) {
				count++;
				this.addToWinningSequence(i, j, count);
			}
			else{
				this.resetWinnigSequence();
				count =0;
			}		
		}
		if (count >= 4)
			return true;
		else
			return false;
	}

	// Adds a box's position to the winning sequence

	private void addToWinningSequence(int row, int col, int count) {
		this.winningSequence[count-1][0] = row;
		this.winningSequence[count-1][1] = col;
	}

	private void resetWinnigSequence() {
		for(int i=0; i < winningSequence.length; i++) {
			this.winningSequence[i][0] = 0;
			this.winningSequence[i][1] = 0;
		}
	}
	
	// Displays the Game board on console
	public void displayConsole() {

		for (int i=0; i < this.board.length; i++) {
			for (int j=0; j< this.board[i].length; j++) {
				
				switch(this.board[i][j]) {
				case Box.EMPTY:
					System.out.print("     |");
					break;
				case Box.RED:
					System.out.print(" RED |");
					break;
				case Box.YELLOW:
					System.out.print(" YEL |");
					break;
				
				}
			}
			
			System.out.println("\n");
		}	
		
	}

	// Displays the Game on GUI
	public void displayGUI(){
		if(this.Winner == Box.EMPTY){
			this.displayBoardGUI();
		}
		else {
			this.drawWinningLine();
			this.displayBoardGUI();
		}
	}

	// Displays the Game board on GUI
	private void displayBoardGUI() {
		
		int COLUMNS = this.board[0].length;
		int ROWS = this.board.length;
		double RADIUS = 0.4;
		
//		StdDraw.setCanvasSize(700, 600);
		// Set the scale of the drawing canvas
        StdDraw.setScale(-0.5, COLUMNS - 0.5);

        // Background color
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledRectangle((COLUMNS - 1) / 2.0, (ROWS - 1) / 2.0, COLUMNS / 2.0, ROWS / 2.0);

        // Draw the slots as empty circles
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
            	if (this.board[i][j] == Box.EMPTY) {
                    StdDraw.setPenColor(StdDraw.WHITE);  // Empty slot
                } else if (this.board[i][j] == Box.YELLOW) {
                    StdDraw.setPenColor(StdDraw.YELLOW);  // Yellow disc
                } else if (this.board[i][j] == Box.RED) {
                    StdDraw.setPenColor(StdDraw.RED);  // Red disc
                }
                StdDraw.filledCircle(j, ROWS - 1 - i, RADIUS);
            }
		}
        
        StdDraw.show();
	}
}
