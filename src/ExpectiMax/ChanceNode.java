package ExpectiMax;

import main.Move2;

public class ChanceNode extends ExpectiNode
{
	
	public Chance prob;
	public int piece;
	
	
	public ChanceNode(ExpectiNode parent, int score, int playerID, Move2 move)
	{
		super(parent, score, playerID, move);
	}
}
