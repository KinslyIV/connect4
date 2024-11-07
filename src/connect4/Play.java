package connect4;

public class Play {

	public static void main(String[] args) {
		boolean hasPlayed;
		Game game = new Game();
		StdDraw.enableDoubleBuffering();
		
		game.displayGUI();
		
		
		while (true) {
			
			// Play User
			do {
			hasPlayed = game.playRed(getColumnFromMouseClick());
			}
			while(!hasPlayed);
			
			game.displayGUI();
			
			// Checks if Red won
			if (game.checkWinRed()) {
				StdDraw.text(3, 6, "Red Wins!");
				game.displayGUI();
				break;
			}
			
			// Play Yellow
			do {
			hasPlayed = game.playYellow((int)(Math.random() * 7));
			}
			while(!hasPlayed);
			
			game.displayGUI();
			
			//Checks if Yellow won
			if (game.checkWinYellow()) {
				StdDraw.text(3.5, 7, "Yellow Wins!");
				game.displayGUI();
				break;
			}
		}
	}
	
	// Get the column from the mouse click
	public static int getColumnFromMouseClick() {
      while (!StdDraw.isMousePressed()) {
          // Wait for the mouse to be pressed
      }
      
      while (StdDraw.isMousePressed()) {
          // Keep looping until the mouse is released
      }
      
      // Get the x-coordinate of the mouse click
      double x = StdDraw.mouseX();

      // Determine the column based on the x-coordinate
      int column = (int) Math.round(x);

      // Ensure the column is within bounds
      if (column < 0) {
          column = 0;
      } else if (column >= 7) {
          column = 7 - 1;
      }

      return column;
  }

}
