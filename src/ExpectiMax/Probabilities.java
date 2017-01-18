package ExpectiMax;
import java.util.ArrayList;

public class Probabilities 
{
	public ArrayList<Chance> probs;
	
	/**
	 * calculates the probabilities for all the positions in the beginning of the game of a specific player
	 * @param playerID the player whose pieces are unknown, so the probabilities have to be checked
	 */
	public Probabilities(int playerID)
	{
		probs = new ArrayList<Chance>();
		if(playerID == 1)
		{
			Chance chance = new Chance(3,1/2,1,1);
			probs.add(chance);
			chance.set(7, 1/4, 1, 1);
			probs.add(chance);
			chance.set(2, 1/4, 1, 1);
			probs.add(chance);
			
			chance.set(4, 1/2, 1, 2);
			probs.add(chance);
			chance.set(7, 1/4, 1, 2);
			probs.add(chance);
			chance.set(6, 1/4, 1, 2);
			probs.add(chance);
			
			chance.set(5, 1/2, 1, 3);
			probs.add(chance);
			chance.set(4, 1/4, 1, 3);
			probs.add(chance);
			chance.set(10, 1/4, 1, 3);
			probs.add(chance);
			
			chance.set(8, 1/2, 1, 4);
			probs.add(chance);
			chance.set(10, 1/4, 1, 4);
			probs.add(chance);
			chance.set(2, 1/4, 1, 4);
			probs.add(chance);
			
			chance.set(3, 1/2, 2, 1);
			probs.add(chance);
			chance.set(11, 1/4, 2, 1);
			probs.add(chance);
			chance.set(2, 1/4, 2, 1);
			probs.add(chance);
			
			chance.set(11, 1/4, 2, 2);
			probs.add(chance);
			chance.set(2, 1/4, 2, 2);
			probs.add(chance);
			chance.set(8, 1/4, 2, 2);
			probs.add(chance);
			chance.set(4, 1/4, 2, 2);
			probs.add(chance);
			
			chance.set(2, 3/4, 2, 3);
			probs.add(chance);
			chance.set(4, 1/2, 2, 3);
			probs.add(chance);
			
			chance.set(2, 1/2, 2, 4);
			probs.add(chance);
			chance.set(6, 1/4, 2, 4);
			probs.add(chance);
			chance.set(8, 1/4, 2, 4);
			probs.add(chance);
			
			chance.set(3, 1/2, 3, 1);
			probs.add(chance);
			chance.set(11, 1/4, 3, 1);
			probs.add(chance);
			chance.set(4, 1/4, 3, 1);
			probs.add(chance);
			
			chance.set(7, 1/2, 3, 2);
			probs.add(chance);
			chance.set(1, 1/4, 3, 2);
			probs.add(chance);
			chance.set(4, 1/4, 3, 2);
			probs.add(chance);
			
			chance.set(7, 1/2, 3, 3);
			probs.add(chance);
			chance.set(11, 1/4, 3, 3);
			probs.add(chance);
			chance.set(8, 1/4, 3, 3);
			probs.add(chance);
			
			chance.set(2, 1/4, 3, 4);
			probs.add(chance);
			chance.set(4, 1/4, 3, 4);
			probs.add(chance);
			chance.set(5, 1/2, 3, 4);
			probs.add(chance);
			
			chance.set(3, 1/2, 4, 1);
			probs.add(chance);
			chance.set(2, 1/4, 4, 1);
			probs.add(chance);
			chance.set(11, 1/4, 4, 1);
			probs.add(chance);
			
			chance.set(1, 1/2, 4, 2);
			probs.add(chance);
			chance.set(3, 1/4, 4, 2);
			probs.add(chance);
			chance.set(7, 1/4, 4, 2);
			probs.add(chance);
			
			chance.set(8, 1/2, 4, 3);
			probs.add(chance);
			chance.set(5, 1/4, 4, 3);
			probs.add(chance);
			chance.set(1, 1/4, 4, 3);
			probs.add(chance);
			
			chance.set(5, 1/4, 4, 4);
			probs.add(chance);
			chance.set(3, 1/4, 4, 4);
			probs.add(chance);
			chance.set(9, 1/4, 4, 4);
			probs.add(chance);
			chance.set(2, 1/4, 4, 4);
			probs.add(chance);
			
			chance.set(4, 3/4, 5, 1);
			probs.add(chance);
			chance.set(3, 1/4, 5, 1);
			probs.add(chance);
			
			chance.set(8, 1/4, 5, 2);
			probs.add(chance);
			chance.set(6, 1/4, 5, 2);
			probs.add(chance);
			chance.set(11, 1/4, 5, 2);
			probs.add(chance);
			chance.set(7, 1/4, 5, 2);
			probs.add(chance);
			
			chance.set(9, 1/2, 5, 3);
			probs.add(chance);
			chance.set(2, 1/4, 5, 3);
			probs.add(chance);
			chance.set(11, 1/4, 5, 3);
			probs.add(chance);
			
			chance.set(6, 1/2, 5, 4);
			probs.add(chance);
			chance.set(2, 1/2, 5, 4);
			probs.add(chance);
			
			chance.set(11, 3/4, 6, 1);
			probs.add(chance);
			chance.set(2, 1/4, 6, 1);
			probs.add(chance);
			
			chance.set(11, 3/4, 6, 2);
			probs.add(chance);
			chance.set(2, 1/4, 6, 2);
			probs.add(chance);
			
			chance.set(2, 3/4, 6, 3);
			probs.add(chance);
			chance.set(6, 1/4, 6, 3);
			probs.add(chance);
			
			chance.set(6, 1/2, 6, 4);
			probs.add(chance);
			chance.set(2, 1/2, 6, 4);
			probs.add(chance);
			
			chance.set(0, 3/4, 7, 1);
			probs.add(chance);
			chance.set(3, 1/4, 7, 1);
			probs.add(chance);
			
			chance.set(11, 3/4, 7, 2);
			probs.add(chance);
			chance.set(6, 1/4, 7, 2);
			probs.add(chance);
			
			chance.set(7, 1/2, 7, 3);
			probs.add(chance);
			chance.set(11, 1/4, 7, 3);
			probs.add(chance);
			chance.set(4, 1/4, 7, 3);
			probs.add(chance);
			
			chance.set(2, 1/2, 7, 4);
			probs.add(chance);
			chance.set(3, 1/4, 7, 4);
			probs.add(chance);
			chance.set(9, 1/4, 7, 4);
			probs.add(chance);
			
			chance.set(11, 3/4, 8, 1);
			probs.add(chance);
			chance.set(3, 1/4, 8, 1);
			probs.add(chance);
			
			chance.set(4, 1/2, 8, 2);
			probs.add(chance);
			chance.set(5, 1/2, 8, 2);
			probs.add(chance);
			
			chance.set(7, 1/2, 8, 3);
			probs.add(chance);
			chance.set(11, 1/4, 8, 3);
			probs.add(chance);
			chance.set(5, 1/4, 8, 3);
			probs.add(chance);
			
			chance.set(10, 1/2, 8, 4);
			probs.add(chance);
			chance.set(3, 1/4, 8, 4);
			probs.add(chance);
			chance.set(2, 1/4, 8, 4);
			probs.add(chance);
			
			chance.set(11, 1/2, 9, 1);
			probs.add(chance);
			chance.set(3, 1/2, 9, 1);
			probs.add(chance);
			
			chance.set(5, 1/2, 9, 2);
			probs.add(chance);
			chance.set(6, 1/4, 9, 2);
			probs.add(chance);
			chance.set(11, 1/4, 9, 2);
			probs.add(chance);
			
			chance.set(8, 1/2, 9, 3);
			probs.add(chance);
			chance.set(11, 1/2, 9, 3);
			probs.add(chance);
			
			chance.set(2, 1, 9, 4);
			probs.add(chance);
			
			chance.set(3, 1/2, 10, 1);
			probs.add(chance);
			chance.set(0, 1/4, 10, 1);
			probs.add(chance);
			chance.set(2, 1/4, 10, 1);
			probs.add(chance);
			
			
			chance.set(4, 1/2, 10, 2);
			probs.add(chance);
			chance.set(11, 1/4, 10, 2);
			probs.add(chance);
			chance.set(2, 1/4, 10, 2);
			probs.add(chance);
			
			chance.set(5, 1/2, 10, 3);
			probs.add(chance);
			chance.set(3, 1/4, 10, 3);
			probs.add(chance);
			chance.set(2, 1/4, 10, 3);
			probs.add(chance);
			
			chance.set(6, 1, 10, 4);
			probs.add(chance);
		
		}
		
		else if (playerID == 2)
		{
			
		}
	}

}
