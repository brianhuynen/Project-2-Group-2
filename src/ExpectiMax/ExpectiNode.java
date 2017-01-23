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

	public ExpectiNode(double score, int playerID, Game game) {
		parent = null;
		children = new ArrayList<ExpectiNode>();
		this.game = game;
		this.score = score;
		this.playerID = playerID;
	}

	public ExpectiNode(ExpectiNode parent, double score, int playerID, Move2 move, Game game) {
		this.parent = parent;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.game = game;
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
		
		for (int i = 0; i < game.player_1.piecesCoord.size(); i++) {
			sumRank1 += game.player_1.piecesCoord.get(i).getRank();
		}

		for (int i = 0; i < game.player_2.piecesCoord.size(); i++) {
			sumRank2 += game.player_2.piecesCoord.get(i).getRank();
		}

		if (player_ID == 1)
		{
			score = sumRank1/sumRank2;
		}

		if (player_ID == 2)
		{
			score = sumRank2/sumRank1;
		}
		
		sumRank1=0;
		sumRank2=0;
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
