import java.util.ArrayList;
import java.util.Set;

import main.Board;
import main.Move;
import main.Node;


public class Node 
{
	public double[] score;
	public double games;
	//public Move move;
	public ArrayList<Node> unvisitedChildren;
	public ArrayList<Node> children;
	//public Set<Integer> rVisited;
	public Node parent;
	public Player player;
	//public double[] pess;
	//public double[] opti;
	public boolean pruned;
	
	public Node(Game b) {
		children = new ArrayList<Node>();
		player = b.getCurrentPlayer();
		score = new double[2];
		//pess = new double[b.getQuantityOfPlayers()];
		//opti = new double[b.getQuantityOfPlayers()];
		//for (int i = 0; i < b.getQuantityOfPlayers(); i++)
		//	opti[i] = 1;
	}
	
	public Node(Game b, Node parent) {
		children = new ArrayList<Node>();
		this.parent = parent;
		player = b.getCurrentPlayer();
		Cell[][] tempBoard = b.duplicate(player);
		//move = m;
		//TODO add making appropriate move from brian/esther code
		score = new double[2];
		//pess = new double[b.getQuantityOfPlayers()];
		//opti = new double[b.getQuantityOfPlayers()];
		//for (int i = 0; i < b.getQuantityOfPlayers(); i++)
		//	opti[i] = 1;
	}

}
