package chicken_game;

public class Blast_Chicken extends Offensive_Chicken {
	private double chance_to_blast;
	public Blast_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction, int max_rows, int max_columns, double chance_to_blast) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction, max_rows, max_columns);
		this.chance_to_blast = chance_to_blast;

	}

	public Blast_Chicken() {
		super();
		this.chance_to_blast = .3;
	}
	//Blast chicken will have a chance to move twice the distance
	@Override
	public void move() {
		if(super.successful_action(chance_to_blast)) {
			super.move();
			super.move();
		}
		else {
			super.move();
		}

	}
}