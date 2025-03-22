package chicken_game;

import java.util.Scanner;

import utilitiescs.Utilities;

class Game {
	private Scanner scan;
	private boolean keep_playing = true, game_over = false;
	public int turn;
	Chicken_Character cc1, cc2;
	private Board board;
	private int rows, columns;

	public Game(Scanner scan, int rows, int columns) {
		this.scan = scan;
		this.rows = rows;
		this.columns = columns;

	}
	//method to introduce the game
	private void rules() {
		System.out.println("Welcome to Lazer Chicken.");
		System.out.println("\nIn this game your goal will be to defeat the opponent.");
		System.out.println("\nUse the controls and board presented to keep progress of the game.");
		System.out.println("\nHave fun!\n");
	}
	private void combat(Chicken_Character attacker, Chicken_Character defender) {
		int[] solution = attacker.attack();
		int size = solution.length;
		int locations = size / 2;
		int[] def_loc = defender.get_location();
		int damage = solution[size - 1];
		if(damage != 0) {
			for(int i = 0; i < locations; i = i + 2) {
				if (def_loc[0] == solution[i] && def_loc [1] == solution [i + 1]) {
					defender.hit(damage);
					System.out.println(attacker.get_name() + " hit "+ defender.get_name() + " and did " + damage + " damage.");
				}else if (in_path(solution, attacker , defender)){
					defender.hit(damage);
					System.out.println(attacker.get_name() + " hit "+ defender.get_name() + " and did " + damage + " damage.");
				}else {
					System.out.println("Combat failed");
				}
			}
		}
	}

	//Accounting for the possibility that the enemy can still be in range of combat even if two spaces away
	private boolean in_path(int[] solution, Chicken_Character attacker, Chicken_Character defender) {
		//(y,x)
		int my_direction = attacker.get_direction();
		int enemyX = defender.get_location()[1];
		int enemyY = defender.get_location()[0];
		int attackerX = attacker.get_location()[1];
		int attackerY = attacker.get_location()[0];
		//north
		if(my_direction == 1 && enemyY < attackerY && enemyX == attackerX && enemyY > solution[0]){
			return true;
		}
		//east
		else if(my_direction == 2 && enemyX > attackerX && enemyY == attackerY && enemyX < solution[1]) {
			return true;
		}
		//south
		else if(my_direction == 3 && enemyY > attackerY && enemyX == attackerX && enemyY < solution[0]) {
			return true;
		}//west
		else if(my_direction == 4 && enemyX < attackerX && enemyY == attackerY && enemyX > solution[1]) {
			return true;
		}
		return false;
	}

	private void check_game_over() {
		if(cc1.is_alive() == false) {
			this.game_over = true;
			System.out.println("Computer wins.");
		}else if(cc2.is_alive() == false) {
			this.game_over = true;
			System.out.println(this.cc1.get_name() + " wins");
		}

	}
	private void move(Chicken_Character cc1, Chicken_Character cc2) {
		int[] old_location = cc1.get_location();
		board.mark_board(old_location[0], old_location[1], '-');
		cc1.move();
		int[] new_location = cc1.get_location();
		board.mark_board(new_location[0], new_location[1], cc1.get_symbol());
		int[] other_location = cc2.get_location();
		if(new_location[0] == other_location[0] && new_location[1] == other_location[1]) {
			board.mark_board(new_location[0], new_location[1], '*');
		}else {
			board.mark_board(other_location[0], other_location[1], cc2.get_symbol());
		}
	}
	private void human_change_direction() {
		System.out.println("What direction do you want to change to? \n1 = north \n2 = east"
				+ "\n3 = south \n4 = west");
		int choice = Utilities.get_user_int(scan, 1, 4);
		cc1.change_direction(choice);
		System.out.println(cc1.get_name() + " is facing " + cc1.facing());
	}
	private void computer_change_direction() {
		int choice = Utilities.calc_random(1, 4);
		cc2.change_direction(choice);
		System.out.println(cc2.get_name() + " changed direction and is facing " + cc2.facing());
	}
	private void display_info() {
		System.out.println();
		System.out.println("------------");
		System.out.printf("Name:%-10s\nFacing:%-6s\nHP:%4d\n",
				cc1.get_name(), cc1.facing(), cc1.get_hp());
		System.out.println();
		System.out.printf("Name:%-10s\nFacing:%-6s\nHP:%4d\n", 
				cc2.get_name(), cc2.facing(), cc2.get_hp());
	}
	public void play_game() {
		initialize_game();
		while (!game_over) {
			take_turns();
		}
	}
	private void initialize_game() {
		rules();
		this.board = new Board(this.rows, this.columns);
		this.game_over = false;
		this.keep_playing = true;
		this.turn = 0;
		this.initialize_characters();
	}
	private void take_turns() {
		display_info();
		System.out.println();
		this.board.display_board();
		if(this.turn % 2 == 0) {
			take_human_turn();
		}else {
			take_computer_turn();
		}
		this.turn ++;
		check_game_over();
	}
	private void take_human_turn() {
		System.out.println(cc1.get_name() + " it is your turn.");
		System.out.println("Do you want to \nchange direction(1)\nmove(2)\ncombat(3)");
		int choice = Utilities.get_user_int(scan, 1, 3);
		switch(choice) {
		case 1:
			human_change_direction();
			break;
		case 2:
			move(cc1, cc2);
			break;
		case 3:
			combat(cc1, cc2);
			break;
		}
	}
	private void take_computer_turn() {
		System.out.println(cc2.get_name() + " it is your turn.");
		System.out.println("Do you want to \nchange direction(1)\nmove(2)\ncombat(3)");
		int choice = Utilities.calc_random(1, 3);
		switch(choice) {
		case 1:
			//System.out.println(cc2.get_name() + " changed direction.");
			computer_change_direction();
			break;
		case 2:
			System.out.println(cc2.get_name() + " moved.");
			move(cc2, cc1);
			break;
		case 3:
			System.out.println(cc2.get_name() + " combats.");
			combat(cc2, cc1);
			break;
		}
	}
	private void initialize_characters() {
		int[] stats = new int[7];
		stats[0] = 20;
		stats[1] = 0;
		stats[3] = 60;
		stats[4] = 3;
		stats[5] = 20;
		stats[6] = 2;
		initialize_human_character(stats);
		initialize_computer_character(stats);
	}
	//chicken menu
	private Chicken_Character character_menu(Chicken_Character cc, String name, int[] stats, int choice){
		switch(choice) {
		case 1:
			cc = intitalize_blast_chicken(cc, name, stats);
			System.out.println("Welcome blast chicken...");
			break;
		case 2:
			cc = intitalize_lucky_chicken(cc, name, stats);
			System.out.println("Welcome lucky chicken...");
			break;
		}
		return cc;
	}
	private void initialize_human_character(int[] stats) {
		System.out.println("Enter your name");
		String name = scan.nextLine();	
		stats[6] = 2;
		System.out.println("Do you want to be a blast chicken(1) or a lucky chicken(2)?");
		System.out.println("\nBlast chicken has a chance of propelling double the distance, as well as doubling damage every 3 turns.");
		System.out.println("\nLucky chicken has a chance of regenerating health, as well as blocking damage.\n");
		int choice = Utilities.get_user_int(scan, 1, 2);
		cc1 = character_menu(cc1,name,stats, choice);		
		this.board.mark_board(cc1.get_location()[0], cc1.get_location()[1], cc1.get_symbol());
	}
	private void initialize_computer_character(int[] stats) {
		String name = "Computer Chicken";
		stats[1] = this.columns - 1 ; 
		stats[2] = this.rows - 1;
		stats[6] = 4;
		int choice = Utilities.calc_random(1, 2);
		cc2 = character_menu(cc2, name, stats, choice);
		this.board.mark_board(cc2.get_location()[0], cc2.get_location()[1], cc2.get_symbol());
	}

	private Chicken_Character intitalize_lucky_chicken(Chicken_Character cc, String name, int[] stats) {
		int min_regen = 2;
		int max_regen = 10;
		double chance_to_regen = .3;
		double chance_to_block = .25;
		cc = new Lucky_Chicken(name, stats[0], stats[1], stats[2], stats[3]/100.0, stats[4], stats[5], stats[6], this.rows, this.columns, 
				chance_to_block, chance_to_regen, min_regen, max_regen);
		return cc;
	}

	private Chicken_Character intitalize_blast_chicken(Chicken_Character cc, String name, int[] stats) {
		double chance_to_blast = .3;
		cc = new Blast_Chicken(name, stats[0], stats[1], stats[2], stats[3]/100.0, stats[4], stats[5], stats[6], 
				this.rows, this.columns, chance_to_blast);
		return cc;
	}

}