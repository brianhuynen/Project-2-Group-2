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
	public int score;
	public int playerID;
	public Move2 move;
	public Game game;

	public ExpectiNode(int score, int playerID) {
		parent = null;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}

	public ExpectiNode(ExpectiNode parent, int score, int playerID, Move2 move) {
		this.parent = parent;
		children = new ArrayList<ExpectiNode>();
		this.score = score;
		this.playerID = playerID;
	}

	public void assignMove(Move2 move) {
		this.move = move;
	}

	public Move2 getMove() {
		return move;
	}

	public void assignScore(int player_ID)
	{
		int sumRank1 = 0;
		int sumRank2 = 0;

		for (int i = 0; i < game.player_1.piecesCoord.size(); i++) {
			sumRank1 += game.player_1.piecesCoord.get(i).getRank();
		}

		for (int i = 0; i < game.player_2.piecesCoord.size(); i++) {
			sumRank2 += game.player_2.piecesCoord.get(i).getRank();
		}

		if (player_ID == 1)
		{
			score = sumRank1/sumRank2;
			
//			if ((sumRank1 / sumRank2) > 0.5)
//			{
//				return 1;
//			}
//			if ((sumRank1 / sumRank2) < 0.5)
//			{
//				return 2;
//			}
		}

		if (player_ID == 2)
		{
			score = sumRank2/sumRank1;
//			if ((sumRank2 / sumRank1) > 0.5)
//			{
//				return 2;
//			}
//			if ((sumRank2 / sumRank1) < 0.5)
//			{
//				return 1;
//			}
		}
//		return 0;
	}
}
