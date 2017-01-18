package ExpectiMax;

import main.Move;

public class MaxNode extends ExpectiNode
{

	public MaxNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MaxNode(ExpectiNode parent, int score, int playerID, Move move) {
		super(parent, score, playerID, move);
	}
	
}
