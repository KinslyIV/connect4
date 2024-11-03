package connect4;

public class Game {

	// Different Box states
	private enum Box {
		EMPTY,
		RED,
		YELLOW;
	}
	
	private Box[][] board; // Declares Game bard
	private int wonCoordinates[][] = new int[2][2];
	private int[] nextBoxes; // Array of indices of next boxes that can be filled in every column
	
	private void initialiseNextBoxes(int n) {
		
		this.nextBoxes = new int[n];
		for (int i=0; i<n; i++) {
			this.nextBoxes[i] = this.board.length-1;
		}
	}
	
	// Decrements 
	private void changeNextBox(int col) {
		
		this.nextBoxes[col] -= 1;
	}
	
	// Initialise the game board to empty boxes
	private void initialseGameBoard() {
		
		for (int i=0; i < this.board.length; i++) {
			for (int j=0; j< this.board[i].length; j++) {
				this.board[i][j] = Box.EMPTY;
			}
		}
	}
		
	// Creates a Game board of default size 6x7
	public Game() {
			
			this(6, 7);
		}
	
	
	// Constructor to specify size of Game board
	public Game(int m, int n) {
		
		this.board = new Box[m][n];
		this.initialseGameBoard();
		this.initialiseNextBoxes(n);
	}
	
	// Play function for red player
	//returns true if move was made n false otherwise
	public boolean playRed(int col) {
		
		if(isValidMove(col)) {
			this.play(col, Box.RED);
			return true;
		}
		else
			return false;
	}
	
	// Play function for yellow player 
	//returns true if move was made n false otherwise
	public boolean playYellow(int col) {
		
		if(isValidMove(col)) {
			this.play(col, Box.YELLOW);
			return true;
		}
		else
			return false;
	}
	
	// Play function implementation
	private void play(int col, Box player) {
		
		int row = this.nextBoxes[col];
		this.board[row][col] = player;
		this.changeNextBox(col);
			
	}
	
	private boolean isValidMove(int col) {
		if (col < 0 || col >= this.board[0].length)
			// out of bound error
			return false;
			
		else {
			// Full error if false 
			return this.nextBoxes[col] >= 0;
		}
		
	}
	
	// Checks if yellow has won
	public boolean checkWinYellow() {
		return this.checkWin(Box.YELLOW);
	}
	
	// Checks if Red has won
	public boolean checkWinRed() {
		return this.checkWin(Box.RED);
	}
	
	private boolean checkWin(Box player) {
		
		return this.checkAllRow(player) || this.checkAllCol(player) || 
				this.checkAllDiagLeft(player) || this.checkAllDiagRight(player);
	}
	
	private boolean checkAllCol(Box player) {
		for (int i=0; i<this.board[0].length; i++) {
			if (this.checkCol(i, player))
				return true;
		}
		
		return false;
	}
	
	private boolean checkAllRow(Box player) {
		for (int i=0; i<this.board.length; i++) {
			if (this.checkRow(i, player))
				return true;
		}
		
		return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given row 
	private boolean checkRow(int row, Box player) {
		int count = 0;
		for (int i=0; i<this.board[row].length && count < 4; i++) {
			if(this.board[row][i] == player)
				count++;
			else
				count =0;
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	// Checks if a player has aligned 4 consecutive boxes on a given column 
	private boolean checkCol(int col, Box player) {
		int count = 0;
		for (int i=0; i<this.board.length && count < 4; i++) {
			if(this.board[i][col] == player)
				count++;
			else
				count =0;
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
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
	
	private boolean checkDiagRight(int row, int col, Box player) {
		int count = 0;
		for (int i=row, j=col; i<this.board.length && j < this.board[row].length && count < 4; i++, j++) {
			if(this.board[i][j] == player)
				count++;
			else
				count =0;
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	private boolean checkDiagLeft(int row, int col, Box player) {
		int count = 0;
		for (int i=row, j=col; i >= 0 && j < this.board[row].length && count < 4; i--, j++) {
			if(this.board[i][j] == player)
				count++;
			else
				count =0;
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	public void display() {

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
		
//		for (int col: this.nextBoxes)
//			System.out.print("  "+col+"  |");
//		System.out.println("\n");
	}

	
	public void displayGUI() {
		
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
        
        StdDraw.show(20);
	}
}
