package chicken_game;
import java.util.Random;
import java.util.Scanner;

public class My_Utilities {
        public static int calc_random(int min, int max) {
            Random rand = new Random();
            int rand_val = Math.abs(rand.nextInt());
            rand_val= (rand_val % (max-min+1))+ min;
            return rand_val;
        }
        
        public static int get_user_int(Scanner scan, int min, int max) {
		int user_input  = min - 1;
		boolean keep_asking = true;
		while(keep_asking || user_input < min || user_input > max) {
			System.out.println("Enter a value between " + min + " and " + 
					max);
			try {
				user_input=scan.nextInt();
				keep_asking = false;
			}catch(Exception e) {
				System.out.println("Invalid input.");
			}finally {
				scan.nextLine(); 
			}
		}
		return user_input;

	}
    }

