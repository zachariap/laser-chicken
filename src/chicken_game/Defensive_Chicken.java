package chicken_game;

public class Defensive_Chicken extends Chicken_Character {

	private double chance_to_block;

	public Defensive_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction, int max_rows, int max_columns, double chance_to_block) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min, damage_max, direction, max_rows, max_columns);

		this.chance_to_block = chance_to_block;
	}

	public Defensive_Chicken() {
		super();
		this.chance_to_block = .25;
	}

	//Defensive_Chicken will have a .25 chance of blocking 50% damage each turn
	@Override
	public void hit(int damage) {
		if(super.successful_action(this.chance_to_block)) {
			damage /= 2;
		}
		super.hit(damage); 
	}
	public void clipper() {
		System.out.println("Clipping");
	}
}