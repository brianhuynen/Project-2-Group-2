package ExpectiMax;

import java.util.ArrayList;

import main.Move;

public class ExpectiNode 
{
	public ExpectiNode parent;
	public ArrayList<ExpectiNode> children;
	public int score;
	public int playerID;
	public Move move;
	
	public ExpectiNode(int score, int playerID)
	{
		parent = null;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}
	
	public ExpectiNode(ExpectiNode parent, int score, int playerID, Move move)
	{
		this.parent = parent;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}
	
}


