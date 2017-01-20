package ExpectiMax;
import java.util.ArrayList;

public class Probabilities 
{
	public ArrayList<Chance> probs;
	
	/**
	 * calculates the probabilities for all the positions in the beginning of the game of a specific player
	 * @param playerID the player whose pieces are unknown, so the probabilities have to be checked
	 */
	public Probabilities(int playerID) {
		probs = new ArrayList<Chance>();
		if (playerID == 1) {
			Chance chance = new Chance(3, 1 / 4, 1, 1);
			probs.add(chance);
			chance.set (3, 1 / 4, 1, 1);
			probs.add(chance);
			chance.set(7, 1 / 4, 1, 1);
			probs.add(chance);
			chance.set(2, 1 / 4, 1, 1);
			probs.add(chance);

			chance.set(4, 1 / 4, 1, 2);
			probs.add(chance);
			chance.set(4, 1 / 4, 1, 2);
			probs.add(chance);
			chance.set(7, 1 / 4, 1, 2);
			probs.add(chance);
			chance.set(6, 1 / 4, 1, 2);
			probs.add(chance);

			chance.set(5, 1 / 4, 1, 3);
			probs.add(chance);
			chance.set(5, 1 / 4, 1, 3);
			probs.add(chance);
			chance.set(4, 1 / 4, 1, 3);
			probs.add(chance);
			chance.set(10, 1 / 4, 1, 3);
			probs.add(chance);

			chance.set(8, 1 / 4, 1, 4);
			probs.add(chance);
			chance.set(8, 1 / 4, 1, 4);
			probs.add(chance);
			chance.set(10, 1 / 4, 1, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 1, 4);
			probs.add(chance);

			chance.set(3, 1 / 4, 2, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 2, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 2, 1);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 1);
			probs.add(chance);

			chance.set(11, 1 / 4, 2, 2);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 2);
			probs.add(chance);
			chance.set(8, 1 / 4, 2, 2);
			probs.add(chance);
			chance.set(4, 1 / 4, 2, 2);
			probs.add(chance);

			chance.set(2, 1 / 4, 2, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 3);
			probs.add(chance);
			chance.set(4, 1 / 2, 2, 3);
			probs.add(chance);

			chance.set(2, 1 / 4, 2, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 2, 4);
			probs.add(chance);
			chance.set(8, 1 / 4, 2, 4);
			probs.add(chance);

			chance.set(2, 1 / 4, 2, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 4);
			probs.add(chance);
			chance.set(11, 1 / 4, 3, 1);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 1);
			probs.add(chance);

			chance.set(7, 1 / 4, 3, 2);
			probs.add(chance);
			chance.set(7, 1 / 4, 3, 2);
			probs.add(chance);
			chance.set(1, 1 / 4, 3, 2);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 2);
			probs.add(chance);

			chance.set(7, 1 / 4, 3, 3);
			probs.add(chance);
			chance.set(7, 1 / 4, 3, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 3, 3);
			probs.add(chance);
			chance.set(8, 1 / 4, 3, 3);
			probs.add(chance);

			chance.set(2, 1 / 4, 3, 4);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 4);
			probs.add(chance);
			chance.set(5, 1 / 4, 3, 4);
			probs.add(chance);
			chance.set(5, 1 / 4, 3, 4);
			probs.add(chance);

			chance.set(3, 1 / 4, 4, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 1);
			probs.add(chance);
			chance.set(2, 1 / 4, 4, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 4, 1);
			probs.add(chance);

			chance.set(1, 1 / 4, 4, 2);
			probs.add(chance);
			chance.set(1, 1 / 4, 4, 2);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 2);
			probs.add(chance);
			chance.set(7, 1 / 4, 4, 2);
			probs.add(chance);

			chance.set(8, 1 / 4, 4, 3);
			probs.add(chance);
			chance.set(8, 1 / 4, 4, 3);
			probs.add(chance);
			chance.set(5, 1 / 4, 4, 3);
			probs.add(chance);
			chance.set(1, 1 / 4, 4, 3);
			probs.add(chance);

			chance.set(5, 1 / 4, 4, 4);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 4);
			probs.add(chance);
			chance.set(9, 1 / 4, 4, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 4, 4);
			probs.add(chance);

			chance.set(4, 1 / 4, 5, 1);
			probs.add(chance);
			chance.set(4, 1 / 4, 5, 1);
			probs.add(chance);
			chance.set(4, 1 / 4, 5, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 5, 1);
			probs.add(chance);

			chance.set(8, 1 / 4, 5, 2);
			probs.add(chance);
			chance.set(6, 1 / 4, 5, 2);
			probs.add(chance);
			chance.set(11, 1 / 4, 5, 2);
			probs.add(chance);
			chance.set(7, 1 / 4, 5, 2);
			probs.add(chance);

			chance.set(9, 1 / 4, 5, 3);
			probs.add(chance);
			chance.set(9, 1 / 4, 5, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 5, 3);
			probs.add(chance);

			chance.set(6, 1 / 4, 5, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 5, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 4);
			probs.add(chance);

			chance.set(11, 1 / 4, 6, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 6, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 6, 1);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 1);
			probs.add(chance);

			chance.set(11, 3 / 4, 6, 2);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 2);
			probs.add(chance);

			chance.set(2, 1 / 4, 6, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 3);
			probs.add(chance);
			chance.set(6, 1 / 4, 6, 3);
			probs.add(chance);

			chance.set(6, 1 / 4, 6, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 6, 4);
			probs.add(chance);
			chance.set(2, 1 / 2, 6, 4);
			probs.add(chance);

			chance.set(0, 1 / 4, 7, 1);
			probs.add(chance);
			chance.set(0, 1 / 4, 7, 1);
			probs.add(chance);
			chance.set(0, 1 / 4, 7, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 7, 1);
			probs.add(chance);

			chance.set(11, 1 / 4, 7, 2);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 2);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 2);
			probs.add(chance);
			chance.set(6, 1 / 4, 7, 2);
			probs.add(chance);

			chance.set(7, 1 / 4, 7, 3);
			probs.add(chance);
			chance.set(7, 1 / 4, 7, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 3);
			probs.add(chance);
			chance.set(4, 1 / 4, 7, 3);
			probs.add(chance);

			chance.set(2, 1 / 4, 7, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 7, 4);
			probs.add(chance);
			chance.set(3, 1 / 4, 7, 4);
			probs.add(chance);
			chance.set(9, 1 / 4, 7, 4);
			probs.add(chance);

			chance.set(11, 1 / 4, 8, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 8, 1);
			probs.add(chance);

			chance.set(4, 1 / 4, 8, 2);
			probs.add(chance);
			chance.set(4, 1 / 4, 8, 2);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 2);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 2);
			probs.add(chance);

			chance.set(7, 1 / 4, 8, 3);
			probs.add(chance);
			chance.set(7, 1 / 4, 8, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 3);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 3);
			probs.add(chance);

			chance.set(10, 1 / 4, 8, 4);
			probs.add(chance);
			chance.set(10, 1 / 4, 8, 4);
			probs.add(chance);
			chance.set(3, 1 / 4, 8, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 8, 4);
			probs.add(chance);

			chance.set(11, 1 / 4, 9, 1);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 9, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 9, 1);
			probs.add(chance);

			chance.set(5, 1 / 4, 9, 2);
			probs.add(chance);
			chance.set(5, 1 / 4, 9, 2);
			probs.add(chance);
			chance.set(6, 1 / 4, 9, 2);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 2);
			probs.add(chance);

			chance.set(8, 1 / 4, 9, 3);
			probs.add(chance);
			chance.set(8, 1 / 4, 9, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 3);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 3);
			probs.add(chance);

			chance.set(2, 1 / 4, 9, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 4);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 4);
			probs.add(chance);

			chance.set(3, 1 / 4, 10, 1);
			probs.add(chance);
			chance.set(3, 1 / 4, 10, 1);
			probs.add(chance);
			chance.set(0, 1 / 4, 10, 1);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 1);
			probs.add(chance);


			chance.set(4, 1 / 4, 10, 2);
			probs.add(chance);
			chance.set(4, 1 / 4, 10, 2);
			probs.add(chance);
			chance.set(11, 1 / 4, 10, 2);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 2);
			probs.add(chance);

			chance.set(5, 1 / 4, 10, 3);
			probs.add(chance);
			chance.set(5, 1 / 4, 10, 3);
			probs.add(chance);
			chance.set(3, 1 / 4, 10, 3);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 3);
			probs.add(chance);

			chance.set(6, 1 / 4, 10, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 4);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 4);
			probs.add(chance);

		} else if (playerID == 2) {
			Chance chance = new Chance(3, 1 / 4, 1, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 1, 10);
			probs.add(chance);
			chance.set(7, 1 / 4, 1, 10);
			probs.add(chance);
			chance.set(2, 1 / 4, 1, 10);
			probs.add(chance);

			chance.set(4, 1 / 4, 1, 9);
			probs.add(chance);
			chance.set(4, 1 / 4, 1, 9);
			probs.add(chance);
			chance.set(7, 1 / 4, 1, 9);
			probs.add(chance);
			chance.set(6, 1 / 4, 1, 9);
			probs.add(chance);

			chance.set(5, 1 / 4, 1, 8);
			probs.add(chance);
			chance.set(5, 1 / 4, 1, 8);
			probs.add(chance);
			chance.set(4, 1 / 4, 1, 8);
			probs.add(chance);
			chance.set(10, 1 / 4, 1, 8);
			probs.add(chance);

			chance.set(8, 1 / 4, 1, 7);
			probs.add(chance);
			chance.set(8, 1 / 4, 1, 7);
			probs.add(chance);
			chance.set(10, 1 / 4, 1, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 1, 7);
			probs.add(chance);

			chance.set(3, 1 / 4, 2, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 2, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 2, 10);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 10);
			probs.add(chance);

			chance.set(11, 1 / 4, 2, 9);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 9);
			probs.add(chance);
			chance.set(8, 1 / 4, 2, 9);
			probs.add(chance);
			chance.set(4, 1 / 4, 2, 9);
			probs.add(chance);

			chance.set(2, 1 / 4, 2, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 8);
			probs.add(chance);
			chance.set(4, 1 / 4, 2, 8);
			probs.add(chance);

			chance.set(2, 1 / 4, 2, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 2, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 2, 7);
			probs.add(chance);
			chance.set(8, 1 / 4, 2, 7);
			probs.add(chance);

			chance.set(3, 1 / 4, 3, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 3, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 3, 10);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 10);
			probs.add(chance);

			chance.set(7, 1 / 4, 3, 9);
			probs.add(chance);
			chance.set(7, 1 / 4, 3, 9);
			probs.add(chance);
			chance.set(1, 1 / 4, 3, 9);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 9);
			probs.add(chance);

			chance.set(7, 1 / 4, 3, 8);
			probs.add(chance);
			chance.set(7, 1 / 4, 3, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 3, 8);
			probs.add(chance);
			chance.set(8, 1 / 4, 3, 8);
			probs.add(chance);

			chance.set(2, 1 / 4, 3, 7);
			probs.add(chance);
			chance.set(4, 1 / 4, 3, 7);
			probs.add(chance);
			chance.set(5, 1 / 4, 3, 7);
			probs.add(chance);
			chance.set(5, 1 / 4, 3, 7);
			probs.add(chance);

			chance.set(3, 1 / 4, 4, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 10);
			probs.add(chance);
			chance.set(2, 1 / 4, 4, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 4, 10);
			probs.add(chance);

			chance.set(1, 1 / 4, 4, 9);
			probs.add(chance);
			chance.set(1, 1 / 4, 4, 9);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 9);
			probs.add(chance);
			chance.set(7, 1 / 4, 4, 9);
			probs.add(chance);

			chance.set(8, 1 / 4, 4, 8);
			probs.add(chance);
			chance.set(8, 1 / 4, 4, 8);
			probs.add(chance);
			chance.set(5, 1 / 4, 4, 8);
			probs.add(chance);
			chance.set(1, 1 / 4, 4, 8);
			probs.add(chance);

			chance.set(5, 1 / 4, 4, 7);
			probs.add(chance);
			chance.set(3, 1 / 4, 4, 7);
			probs.add(chance);
			chance.set(9, 1 / 4, 4, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 4, 7);
			probs.add(chance);

			chance.set(4, 1 / 4, 5, 10);
			probs.add(chance);
			chance.set(4, 1 / 4, 5, 10);
			probs.add(chance);
			chance.set(4, 1 / 4, 5, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 5, 10);
			probs.add(chance);

			chance.set(8, 1 / 4, 5, 9);
			probs.add(chance);
			chance.set(6, 1 / 4, 5, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 5, 9);
			probs.add(chance);
			chance.set(7, 1 / 4, 5, 9);
			probs.add(chance);

			chance.set(9, 1 / 4, 5, 8);
			probs.add(chance);
			chance.set(9, 1 / 4, 5, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 5, 8);
			probs.add(chance);

			chance.set(6, 1 / 4, 5, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 5, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 5, 7);
			probs.add(chance);

			chance.set(11, 3 / 4, 6, 10);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 10);
			probs.add(chance);

			chance.set(11, 1 / 4, 6, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 6, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 6, 9);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 9);
			probs.add(chance);

			chance.set(2, 1 / 4, 6, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 8);
			probs.add(chance);
			chance.set(6, 1 / 4, 6, 8);
			probs.add(chance);

			chance.set(6, 1 / 4, 6, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 6, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 6, 7);
			probs.add(chance);

			chance.set(0, 1 / 4, 7, 10);
			probs.add(chance);
			chance.set(0, 1 / 4, 7, 10);
			probs.add(chance);
			chance.set(0, 1 / 4, 7, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 7, 10);
			probs.add(chance);

			chance.set(11, 1 / 4, 7, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 9);
			probs.add(chance);
			chance.set(6, 1 / 4, 7, 9);
			probs.add(chance);

			chance.set(7, 1 / 4, 7, 8);
			probs.add(chance);
			chance.set(7, 1 / 4, 7, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 7, 8);
			probs.add(chance);
			chance.set(4, 1 / 4, 7, 8);
			probs.add(chance);

			chance.set(2, 1 / 4, 7, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 7, 7);
			probs.add(chance);
			chance.set(3, 1 / 4, 7, 7);
			probs.add(chance);
			chance.set(9, 1 / 4, 7, 7);
			probs.add(chance);

			chance.set(11, 1 / 4, 8, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 8, 10);
			probs.add(chance);

			chance.set(4, 1 / 4, 8, 9);
			probs.add(chance);
			chance.set(4, 1 / 4, 8, 9);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 9);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 9);
			probs.add(chance);

			chance.set(7, 1 / 4, 8, 8);
			probs.add(chance);
			chance.set(7, 1 / 4, 8, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 8, 8);
			probs.add(chance);
			chance.set(5, 1 / 4, 8, 8);
			probs.add(chance);

			chance.set(10, 1 / 4, 8, 7);
			probs.add(chance);
			chance.set(10, 1 / 4, 8, 7);
			probs.add(chance);
			chance.set(3, 1 / 4, 8, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 8, 7);
			probs.add(chance);

			chance.set(11, 1 / 4, 9, 10);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 9, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 9, 10);
			probs.add(chance);

			chance.set(5, 1 / 4, 9, 9);
			probs.add(chance);
			chance.set(5, 1 / 4, 9, 9);
			probs.add(chance);
			chance.set(6, 1 / 4, 9, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 9);
			probs.add(chance);

			chance.set(8, 1 / 4, 9, 8);
			probs.add(chance);
			chance.set(8, 1 / 4, 9, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 8);
			probs.add(chance);
			chance.set(11, 1 / 4, 9, 8);
			probs.add(chance);

			chance.set(2, 1 / 4, 9, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 7);
			probs.add(chance);
			chance.set(2, 1 / 4, 9, 7);
			probs.add(chance);

			chance.set(3, 1 / 4, 10, 10);
			probs.add(chance);
			chance.set(3, 1 / 4, 10, 10);
			probs.add(chance);
			chance.set(0, 1 / 4, 10, 10);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 10);
			probs.add(chance);

			chance.set(4, 1 / 4, 10, 9);
			probs.add(chance);
			chance.set(4, 1 / 4, 10, 9);
			probs.add(chance);
			chance.set(11, 1 / 4, 10, 9);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 9);
			probs.add(chance);

			chance.set(5, 1 / 4, 10, 8);
			probs.add(chance);
			chance.set(5, 1 / 4, 10, 8);
			probs.add(chance);
			chance.set(3, 1 / 4, 10, 8);
			probs.add(chance);
			chance.set(2, 1 / 4, 10, 8);
			probs.add(chance);

			chance.set(6, 1 / 4, 10, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 7);
			probs.add(chance);
			chance.set(6, 1 / 4, 10, 7);
			probs.add(chance);
		}
	}


	public void updateProbabilities(int x1, int y1, int x2, int y2)
	{

		for(int i = 0; i<probs.size(); i++)
		{
			if(probs.get(i).coords[0] == x1 && probs.get(i).coords[1] == y1)
			{
				probs.get(i).changePosition(x2, y2);
			}
		}
	}

}
