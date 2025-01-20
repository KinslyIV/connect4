package connect4;

public class Game {

	// Different Box states
	public enum Box {
		EMPTY,
		RED,
		YELLOW;
	}
	
	private Box Winner = Box.EMPTY;
	private int ROWS;
	private int COLUMNS;
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
		this.ROWS = m;
		this.COLUMNS = n;
		this.initialseGameBoard();
		this.initialiseNextBoxes(n);
	}

	// Initialises the nextBoxes array to the last row of the board
	private void initialiseNextBoxes(int n) {
		
		this.nextBoxes = new int[n];
		for (int i=0; i<n; i++) {
			this.nextBoxes[i] = this.ROWS-1;
		}
	}

	// Initialise the game board to empty boxes
	private void initialseGameBoard() {
		
		for (int i=0; i < this.ROWS; i++) {
			for (int j=0; j< this.COLUMNS; j++) {
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
		if (col < 0 || col >= this.COLUMNS){
			// out of bound error
			return false;
		}	
		else {
			// Full error if false 
			return this.nextBoxes[col] >= 0;
		}
		
	}
	
	// Checks if a player has won
	private boolean checkWin(Box player) {
		
		return this.checkAllRows(player) || this.checkAllColumns(player) || 
				this.checkAllDiagLeft(player) || this.checkAllDiagRight(player);
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any column 
	// and add the values to the winning sequence
	private boolean checkAllColumns(Box player) {
		for (int col=0; col<this.COLUMNS; col++) {
			int count = 0;
			for (int row=0; row<this.ROWS; row++) {
				if(this.board[row][col] == player) {
					count++;
					this.addToWinningSequence(row, col, count);
					if (count >= 4) return true;
				}
				else{
					this.resetWinnigSequence();
					count =0;
				}	
			}
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any row
	private boolean checkAllRows(Box player) {
		for (int row=0; row<this.ROWS; row++) {
			int count = 0;
			for (int col=0; col<this.COLUMNS; col++) {
				if(this.board[row][col] == player) {
					count++;
					this.addToWinningSequence(row, col, count);
					if (count >= 4) return true;
				}
				else{
					this.resetWinnigSequence();
					count =0;
				}	
			}
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any diagonal
	private boolean checkAllDiagRight(Box player) {
		for (int row = 0; row < this.ROWS - 3; row++) {
			for (int col = 0; col < this.COLUMNS - 3; col++) {
				if(checkDiagRight(row, col, player)) return true;
			}
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on any diagonal
	private boolean checkAllDiagLeft(Box player) {
		for (int row = 0; row < this.ROWS - 3; row++) {
			for (int col = 3; col < this.COLUMNS; col++) {
				if(checkDiagLeft(row, col, player)) return true;
			}
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given diagonal
	private boolean checkDiagRight(int row, int col, Box player) {
		if(this.board[row][col] == player && this.board[row+1][col+1] == player && 
		this.board[row+2][col+2] == player && this.board[row+3][col+3] == player) {
			return true;}

		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given diagonal
	private boolean checkDiagLeft(int row, int col, Box player) {
		if(this.board[row][col] == player && this.board[row+1][col-1] == player && 
		this.board[row+2][col-2] == player && this.board[row+3][col-3] == player) {
			return true;}

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

		for (int i=0; i < this.ROWS; i++) {
			for (int j=0; j< this.COLUMNS; j++) {
				
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

	// Draw line between on the winning sequence
	private void drawWinningLine() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setPenRadius(0.02);
		StdDraw.line(this.winningSequence[0][1], this.ROWS - 1 - this.winningSequence[0][0], this.winningSequence[3][1], this.ROWS - 1 - this.winningSequence[3][0]);
	}

	// Displays the Game on GUI
	public void displayGUI(){
		if(this.Winner == Box.EMPTY){
			this.displayBoardGUI();
			StdDraw.show();
		}
		else {
			this.displayWinText();
			this.displayBoardGUI();
			this.drawWinningLine();
			StdDraw.show();
		}
	}

	// Displays the Game board on GUI
	private void displayBoardGUI() {
		
		double RADIUS = 0.4;
		
//		StdDraw.setCanvasSize(700, 600);
		// Set the scale of the drawing canvas
        StdDraw.setScale(-0.5, this.COLUMNS - 0.5);

        // Background color
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledRectangle((this.COLUMNS - 1) / 2.0, (this.ROWS - 1) / 2.0, this.COLUMNS / 2.0, this.ROWS / 2.0);

        // Draw the slots as empty circles
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
            	if (this.board[i][j] == Box.EMPTY) {
                    StdDraw.setPenColor(StdDraw.WHITE);  // Empty slot
                } else if (this.board[i][j] == Box.YELLOW) {
                    StdDraw.setPenColor(StdDraw.YELLOW);  // Yellow disc
                } else if (this.board[i][j] == Box.RED) {
                    StdDraw.setPenColor(StdDraw.RED);  // Red disc
                }
                StdDraw.filledCircle(j, this.ROWS - 1 - i, RADIUS);
            }
		}
	}

	private void displayWinText() {
		StdDraw.setPenRadius(2);
		StdDraw.setPenColor(StdDraw.BLACK);
		int[] position = {3, 6};
		if (this.Winner == Box.RED) {
			StdDraw.text(position[0], position[1], "Red has Won!!!");
		}
		else if (this.Winner == Box.YELLOW) {
			StdDraw.text(position[0], position[1], "Yellow has Won!!!");
		}

	}

}
