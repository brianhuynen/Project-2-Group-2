package ExpectiMax;

public class ChanceNode extends ExpectiNode
{
	
	public Probabilities probs;
	
	
	public ChanceNode(ExpectiNode parent, int score, int playerID)
	{
		super(parent, score, playerID);
		probs = new Probabilities(playerID);
	}
}
