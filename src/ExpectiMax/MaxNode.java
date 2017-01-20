package ExpectiMax;

import main.Move2;

public class MaxNode extends ExpectiNode
{

	public MaxNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MaxNode(ExpectiNode parent, int score, int playerID, Move2 move) {
		super(parent, score, playerID, move);
	}
	
}
