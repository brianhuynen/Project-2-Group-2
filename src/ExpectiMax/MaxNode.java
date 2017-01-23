package ExpectiMax;

import main.Game;
import main.Move2;

public class MaxNode extends ExpectiNode
{
	
	public MaxNode(double score, int playerID, Game game)
	{
		super(score, playerID, game);
	}
	
	public MaxNode(ExpectiNode parent, double score, int playerID, Move2 move, Game game)
	{
		super(parent, score, playerID, move, game);
	}
	
	
	
}
