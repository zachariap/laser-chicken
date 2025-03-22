package chicken_game;

public class Board {
	private char[][] board;
	private int rows, columns;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.initialize_board();
	}
	public void initialize_board() {
		this.board = new char[rows][columns];
		for(int i = 0; i< this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				this.board[i][j] = '-';
			}
		}
	}
	public void display_board() {
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				System.out.printf(" %c ", board[i][j]);
			}
			System.out.println();
		}
	}
	public void mark_board(int y, int x, char symbol) {
		this.board[y][x] = symbol;
	}
}