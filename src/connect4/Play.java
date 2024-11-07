package connect4;

public class Play {

	public static void main(String[] args) {
		Game game = new Game();
		String winner;
		StdDraw.enableDoubleBuffering();
		
		game.displayGUI();
		
		
		while (true) {
			
			// Play User
			while(!game.playRed(getColumnFromMouseClick())) {
			// Repeat till valid move
			}
			
			game.displayGUI();
			winner = game.getWinner();
			if(winner != null){
				break;
			}
			
			// Play Yellow
			while(!game.playYellow((int)(Math.random() * 7))) {
				// Repeat till vail move
			}
			
			game.displayGUI();
			winner = game.getWinner();
			if(winner != null){
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
