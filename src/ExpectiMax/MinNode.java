package ExpectiMax;
public class MinNode extends ExpectiNode
{

	public MinNode(int score, int playerID)
	{
		super(score, playerID);
	}
	
	public MinNode(ExpectiNode parent, int score, int playerID) 
	{
		super(parent, score, playerID);
	}
	
}
