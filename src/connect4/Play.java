package connect4;

import java.util.Scanner;

public class Play {
	
	private static final Scanner readCol = new Scanner(System.in);

	public static void main(String[] args) {
		boolean hasPlayed;
		Game game = new Game();

		int move=0;

		game.display();
		
		while (true) {
			
			do {
			System.out.print("Red's Turn : ");
			move = readCol.nextInt();
			hasPlayed = game.playRed(move);
			}
			while(!hasPlayed);
			
			if (game.checkWinRed()) {
				System.out.println("Red has won");
				break;
			}
			
			// Play Yellow
			do {
			move = (int)(Math.random() * 7);
			System.out.println("Yellow's Turn : " + move);
			hasPlayed = game.playYellow(move);
			}
			while(!hasPlayed);
			
			game.display();

			if (game.checkWinYellow()) {
				System.out.println("Yellow has won");
				break;
			}
		}
	}
}
