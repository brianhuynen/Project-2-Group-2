package ExpectiMax;

import java.util.ArrayList;

public class ExpectiNode 
{
	public ExpectiNode parent;
	public ArrayList<ExpectiNode> children;
	public int score;
	public int playerID;
	
	public ExpectiNode(int score, int playerID)
	{
		parent = null;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}
	
	public ExpectiNode(ExpectiNode parent, int score, int playerID)
	{
		this.parent = parent;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}
	
}


