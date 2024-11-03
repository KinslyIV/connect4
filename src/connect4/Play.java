package connect4;

//import java.util.Scanner;

public class Play {
	
//	private static final Scanner readCol = new Scanner(System.in);

	public static void main(String[] args) {
		boolean hasPlayed;
		Game game = new Game();
		
		game.displayGUI();
		
		while (true) {
			
			do {
//			System.out.print("Red's Turn : ");
			hasPlayed = game.playRed(getColumnFromMouseClick());
			}
			while(!hasPlayed);
			
			game.displayGUI();
			
			if (game.checkWinRed()) {
				
				
//				System.out.println("Red has won");
				break;
			}
			
			// Play Yellow
			do {
//			System.out.println("Yellow's Turn : ");
			hasPlayed = game.playYellow((int)(Math.random() * 7));
			}
			while(!hasPlayed);
			
			game.displayGUI();
			
			if (game.checkWinYellow()) {
//				System.out.println("Yellow has won");
				break;
			}
		}
	}
	
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
