import java.util.ArrayList;

public class Node 
{
	public int[] score;
	public double games;
	public Move move;
	public ArrayList<Node> unvisitedChildren;
	public ArrayList<Node> children;
	//public Set<Integer> rVisited;
	public Node parent;
	public Player player;
	//public double[] pess;
	//public double[] opti;
	public boolean pruned;
	RandomAlg random;

	public Node(Game b) {
		children = new ArrayList<Node>();
		player = b.getCurrentPlayer();
		score = new int[2];
		findScore();
		random = new RandomAlg(b, player);
		//pess = new double[b.getQuantityOfPlayers()];
		//opti = new double[b.getQuantityOfPlayers()];
		//for (int i = 0; i < b.getQuantityOfPlayers(); i++)
		//	opti[i] = 1;
		
	}
	
	public Node(Game b, Node parent, Move m) {
		children = new ArrayList<Node>();
		this.parent = parent;
		player = b.getCurrentPlayer();
		random = new RandomAlg(b, player);
		Cell[][] tempBoard = b.duplicate(player);
		move = m;
		score = new int[2];
		findScore();
		//pess = new double[b.getQuantityOfPlayers()];
		//opti = new double[b.getQuantityOfPlayers()];
		//for (int i = 0; i < b.getQuantityOfPlayers(); i++)
		//	opti[i] = 1;
	}
	
	public void findScore()
	{
		if ( player.getPlayer_ID() == 1)
		{
			score[0]=player.offBoard;
		} else if (player.getPlayer_ID() == 2)
		{
			score[1]=player.offBoard;
		}
	}
	
	public void expandNode(Game currentBoard){
		RandomAlg rand = new RandomAlg(currentBoard, currentBoard.currentPlayer);
		ArrayList<Move> legalMoves = rand.movesAvailable(currentBoard.board);
		unvisitedChildren = new ArrayList<Node>();
		for (int i = 0; i < legalMoves.size(); i++) {
			Node tempState = new Node(currentBoard, this, legalMoves.get(i));
			unvisitedChildren.add(tempState);
		}
	}

}
