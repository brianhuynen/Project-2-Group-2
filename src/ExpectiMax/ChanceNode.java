package ExpectiMax;

import main.Game;
import main.Move2;

public class ChanceNode extends ExpectiNode
{
	
	public Chance prob;
	public int piece;
	
	
	public ChanceNode(ExpectiNode parent, double score, int playerID, Move2 move, int piece, Game game)
	{
		super(parent, score, playerID, move, game);
		this.piece = piece;
	}
}
