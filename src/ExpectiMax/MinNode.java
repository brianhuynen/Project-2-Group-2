package ExpectiMax;

import main.Move;

public class MinNode extends ExpectiNode
{

	public MinNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MinNode(ExpectiNode parent, int score, int playerID, Move move) 
	{
		super(parent, score, playerID, move);
	}
	
}
