package ExpectiMax;

import java.util.ArrayList;

import main.Game;
import main.Move2;
import main.Pieces;
import main.Player;

public class ExpectiNode
{
	public ExpectiNode parent;
	public ArrayList<ExpectiNode> children;
	public double score;
	public int playerID;
	public Move2 move;
	public Game game;
	public boolean chanceGenerated;
	public int sumRank1;
	public int sumRank2;
	public int nParents;

	public ExpectiNode(double score, int playerID, Game game) {
		parent = null;
		children = new ArrayList<ExpectiNode>();
		this.game = game.duplicateG();
		this.score = score;
		this.playerID = playerID;
		this.nParents = 0;
	}

	public ExpectiNode(ExpectiNode parent, double score, int playerID, Move2 move, Game game) {
		this.parent = parent;
		this.nParents++;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
		this.move = move;
	}

	public void assignMove(Move2 move) {
		this.move = move;
	}

	public Move2 getMove() {
		return move;
	}

	public void assignScore(int player_ID)
	{
		//System.out.println(game == null);
		
//		for (int i = 0; i < game.nPieces(1); i++) {
//			sumRank1 ++;
//		}
//
//		for (int i = 0; i < game.nPieces(2); i++) {
//			sumRank2 ++;
//		}
//
//		if (player_ID == 1)
//		{
//			score = sumRank1/sumRank2;
//		}
//
//		if (player_ID == 2)
//		{
//			score = sumRank2/sumRank1;
//		}
//		
//		sumRank1=0;
//		sumRank2=0;
		if (player_ID == 1)
		{
			score = -sumRank2;
		}
		else if(player_ID == 2)
		{
			score = -sumRank1;
		}
	}
	
	public void assignSum(int rank, int playerID)
	{
		if(playerID == 1)
		{
			sumRank1+=rank;
		}
		else if(playerID == 2)
		{
			sumRank2+=rank;
		}
	}
}
