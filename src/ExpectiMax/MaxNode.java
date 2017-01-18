package ExpectiMax;

public class MaxNode extends ExpectiNode
{

	public MaxNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MaxNode(ExpectiNode parent, int score, int playerID) {
		super(parent, score, playerID);
	}
	
}
