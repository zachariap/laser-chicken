package chicken_game;

public class Offensive_Chicken extends Chicken_Character {
	int attack_counter;

	public Offensive_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction, int max_rows, int max_columns) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction, max_rows, max_columns);

		attack_counter = 1;
	}
	public Offensive_Chicken() {
		super();
	}

	//Offensive_Chicken will double damage every 3 attack turns
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		if (attack_counter % 3 == 0) {
			solution[2] *= 2;
		}
		attack_counter++;
		return solution;
	}
}