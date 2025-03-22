package chicken_game;

public interface Icg {
	public void move();
	public int[] attack();
	public void change_direction(int direction);
	public int[] get_location();
	public void hit(int damage);
	public boolean is_alive();
	public int set_vars(int min, int max, int var);
	public boolean successful_action(double chance);
	public String get_name();
	public char get_symbol();
}