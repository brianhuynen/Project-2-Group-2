package ExpectiMax;

import main.Move2;

public class MaxNode extends ExpectiNode
{
	
	public MaxNode(double score, int playerID)
	{
		super(score, playerID);
	}
	
	public MaxNode(ExpectiNode parent, double score, int playerID, Move2 move) {
		super(parent, score, playerID, move);
	}
	
	
	
}
