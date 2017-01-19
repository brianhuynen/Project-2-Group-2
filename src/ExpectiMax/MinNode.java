package ExpectiMax;

import main.Move2;

public class MinNode extends ExpectiNode
{

	public MinNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MinNode(ExpectiNode parent, int score, int playerID, Move2 move) 
	{
		super(parent, score, playerID, move);
	}
	
}
