package chicken_game;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Game g1 = new Game(scan, 10, 10);
		g1.play_game();

	}

}