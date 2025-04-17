package chicken_game;
import java.util.Random;



public class Chicken_Character implements Icg {
	private String name;
	private char symbol;
	private int max_hp;
	private int current_hp;
	private int x_coord;
	private int y_coord;
	private double base_chance;
	private int damage_max, damage_min;
	private int direction;
	private int max_columns;
	private int max_rows;

	public Chicken_Character (String name, int hp, int x_coord, int y_coord, double base_chance, 
			int damage_min, int damage_max, int direction, int max_rows, int max_columns) {
		this.name = name;
		this.symbol = this.name.charAt(0);
		this.max_hp = hp;
		this.current_hp = hp;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.base_chance = base_chance;
		this.damage_max = damage_max;
		this.damage_min = damage_min;
		this.direction = direction;
		this.max_columns = max_columns; 
		this.max_rows = max_rows;
	}
	public Chicken_Character() {
		this.symbol = this.name.charAt(0);
		this.max_hp = 100;
		this.current_hp = 100;
		this.x_coord = 0;
		this.y_coord = 0;
		this.base_chance = .6; 
		this.damage_max = 20;
		this.damage_min = 2;
		this.direction = 3;
		this.max_columns = 9;
		this.max_rows = 9;
	}

	public int get_current_hp(int current_hp) {
		return this.current_hp;
	}

	public int get_max_hp(int max_hp) {
		return this.max_hp;
	}

	public String facing() {
		switch(this.direction) {
		case 1:
			return "North";
		case 2:
			return "East";
		case 3:
			return "South";
		case 4:
			return "West";
		default:
			return "Error";
		}
	}
	@Override
	public void move() {
		int[] new_position = new_movement();
		this.y_coord = new_position[0];
		this.x_coord = new_position[1];
	}
	@Override
	public int set_vars(int min, int max, int var) {
		if(var < min) {
			var = min;
		}else if ( var > max) {
			var = max;
		}
		return var;
	}
	@Override
	public boolean is_alive() {
		return this.current_hp > 0;
	}
	@Override
	public boolean successful_action(double chance) {
		Random rand = new Random();
		if(rand.nextDouble() <= chance) {
			return true;
		}else {
			return false;
		}
	}

	public int get_hp() {
		return this.current_hp;
	}
	@Override
	public String get_name() {
		return this.name;
	}
	@Override
	public char get_symbol() {
		return this.symbol;
	}
	@Override
	public void change_direction(int direction) {
		this.direction = this.set_vars(1, 4,  direction);
	}
	@Override
	public int[] attack() {
		int[] fire_solution = new int[3];
		int[] new_position = new_movement();
		fire_solution[0] = new_position[0];
		fire_solution[1] = new_position[1];
		fire_solution[2] = 0;
		if(this.successful_action(this.base_chance)) {
			fire_solution[2] = My_Utilities.calc_random(damage_min, damage_min);
		}else {
			System.out.println("You missed");
		}
		return fire_solution;
	}

	private int[] new_movement() {
		int[] movement = new int[2];
		switch(this.direction) {
		case 1:
			movement = movement_north();
			break;
		case 2:
			movement = movement_east();
			break;
		case 3:
			movement = movement_south();
			break;
		case 4:
			movement = movement_west();
			break;
		}
		return movement;
	}
	private int[] movement_north() {
		int y = set_vars(0, this.max_rows-2, this.y_coord - 2);
		int[] new_movement = {y, this.x_coord};
		return new_movement;
	}
	private int[] movement_south() {
		int y = set_vars(0, this.max_rows-2, this.y_coord + 2);
		int[] new_movement = {y, this.x_coord};
		return new_movement;
	}
	private int[] movement_west() {
		int x = set_vars(0, this.max_columns-2, this.x_coord - 2);
		int[] new_movement = {this.y_coord, x};
		return new_movement;
	}
	private int[] movement_east() {
		int x = set_vars(0, this.max_columns-2, this.x_coord + 2);
		int[] new_movement = {this.y_coord, x};
		return new_movement;
	}
	public int get_direction() {
		return this.direction;
	}
	@Override
	public int[] get_location() {
		int[] location = {this.y_coord, this.x_coord};

		return location;
	}
	@Override
	public void hit(int damage) {
		this.current_hp = set_vars(0, this.max_hp, this.current_hp - damage);

	}

}