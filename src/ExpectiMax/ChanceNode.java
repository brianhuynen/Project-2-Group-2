package ExpectiMax;

import main.Move;

public class ChanceNode extends ExpectiNode
{
	
	public Chance prob;
	
	
	public ChanceNode(ExpectiNode parent, int score, int playerID, Move move, Chance prob)
	{
		super(parent, score, playerID, move);
		this.prob = prob;
	}
}
