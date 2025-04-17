package chicken_game;


public class Lucky_Chicken extends Defensive_Chicken {

	private double chance_to_regen;
	private int max_regen;
	private int min_regen;


	public Lucky_Chicken(String name, int hp, int x_coord, int y_coord, double base_chance, int damage_min,
			int damage_max, int direction, int max_rows, int max_columns, double chance_to_block, 
			double chance_to_regen, int min_regen, int max_regen) {
		super(name, hp, x_coord, y_coord, base_chance, damage_min,
				damage_max, direction,  max_rows, max_columns, chance_to_block);

		this.chance_to_regen = chance_to_regen;
		this.min_regen = min_regen;
		this.max_regen = max_regen;
	}

	public Lucky_Chicken() {
		this.chance_to_regen = .3;
		this.min_regen = 1;
		this.max_regen = 2;

	}
	//Lucky_Chicken will have a chance of regenerating damage
	@Override
	public void hit(int damage) {
		super.hit(damage);
		if(super.is_alive()) {
			if(super.successful_action(chance_to_regen)) {
				int regen_amount = My_Utilities.calc_random(min_regen, max_regen) * -1;
				super.hit(regen_amount);
				System.out.println(super.get_name() + " regenerates for " + regen_amount + "points");
			}
		}
	}
}