package MCTS;


import java.util.ArrayList;
import java.util.Set;

import main.Game;
import main.Move;

public class Node {
	public double[] score;
	int turns=0;
	
	public double games;
	public Move move;
	public ArrayList<Node> unvisitedChildren;
	public ArrayList<Node> children;
	public Set<Integer> rVisited;
	public Node parent;
	public int player;
	public double[] pess;
	public double[] opti;
	public boolean pruned;
	
	/**
	 * This creates the root node
	 * 
	 * @param b
	 */
	public Node(Game g) {
		children = new ArrayList<Node>();
		
		player = g.currentPlayer.getPlayer_ID()-1;
		score = new double[ g.getQuantityOfPlayers()];
		pess = new double[ g.getQuantityOfPlayers()];
		opti = new double[ g.getQuantityOfPlayers()];
		for (int i = 0; i < g.getQuantityOfPlayers(); i++)
			opti[i] = 1;
	}

	/**
	 * This creates non-root nodes
	 * 
	 * @param b
	 * @param m
	 * @param prnt
	 */
	public Node(Game g, Move m, Node prnt) {
		children = new ArrayList<Node>();
		parent = prnt;
		move = m;
		Game tempGame = g.duplicateG();
		tempGame.makeMove(m);
		player = tempGame.currentPlayer.getPlayer_ID()-1;
		score = new double[ g.getQuantityOfPlayers()];
		pess = new double[ g.getQuantityOfPlayers()];
		opti = new double[ g.getQuantityOfPlayers()];
		for (int i = 0; i < g.getQuantityOfPlayers(); i++)
			opti[i] = 1;
	}

	/**
	 * Return the upper confidence bound of this state
	 * 
	 * @param c
	 *            typically sqrt(2). Increase to emphasize exploration. Decrease
	 *            to incr. exploitation
	 * @param t
	 * @return
	 */
	public double upperConfidenceBound(double c) {
		return score[parent.player] / games  + c
				* Math.sqrt(Math.log(parent.games + 1) / games);
	}

	/**
	 * Update the tree with the new score.
	 * @param scr
	 */
	public void backPropagateScore(double[] scr) {
		this.games++;
		for (int i = 0; i < scr.length; i++)
			this.score[i] += scr[i];

		if (parent != null)
			parent.backPropagateScore(scr);
	}

	/**
	 * Expand this node by populating its list of
	 * unvisited child nodes.
	 * @param currentBoard
	 */
	public void expandNode(Game currentGame){
		ArrayList<Move> legalMoves = currentGame.getMoves(CallLocation.treePolicy);
		unvisitedChildren = new ArrayList<Node>();
		for (int i = 0; i < legalMoves.size(); i++) {
			Node tempState = new Node(currentGame, legalMoves.get(i), this);
			unvisitedChildren.add(tempState);
		}
	}

	/**
	 * Set the bounds in the given node and propagate the values 
	 * back up the tree. When bounds are first created they are
	 * both equivalent to a player's score.
	 * 
	 * @param optimistic
	 * @param pessimistic
	 */
	public void backPropagateBounds(double[] score) {
		for (int i = 0; i < score.length; i++) {
			opti[i] = score[i];
			pess[i] = score[i];
		}

		if (parent != null)
			parent.backPropagateBoundsHelper();
	}

	private void backPropagateBoundsHelper() {
		for (int i = 0; i < opti.length; i++) {
			if (i == player) {
				opti[i] = 0;
				pess[i] = 0;
			} else {
				opti[i] = 1;
				pess[i] = 1;
			}
		}

		for (int i = 0; i < opti.length; i++) {
			for (Node c : children) {
				if (i == player) {
					if (opti[i] < c.opti[i])
						opti[i] = c.opti[i];
					if (pess[i] < c.pess[i])
						pess[i] = c.pess[i];
				} else {
					if (opti[i] > c.opti[i])
						opti[i] = c.opti[i];
					if (pess[i] > c.pess[i])
						pess[i] = c.pess[i];
				}
			}
		}

		// This compares against a dummy node with bounds 1 0
		// if not all children have been explored
		if (!unvisitedChildren.isEmpty()) {
			for (int i = 0; i < opti.length; i++) {
				if (i ==player) {
					opti[i] = 1;
				} else {
					pess[i] = 0;
				}
			}
		}

		// TODO: This causes redundant pruning. Fix it
		//pruneBranches();
		if (parent != null)
			parent.backPropagateBoundsHelper();
	}

	public void pruneBranches() {
		for (Node s : children) {
			if (pess[player] >= s.opti[player]) {
				s.pruned = true;
			}
		}

		if (parent != null)
			parent.pruneBranches();
	}

	/**
	 * Select a child node at random and return it.
	 * @param board
	 * @return
	 */
	public int randomSelect(Game game ){
		double []weights = game.getMoveWeights();
		
		double totalWeight = 0.0d;
		for (int i = 0; i < weights.length; i++)
		{
		    totalWeight += weights[i];
		}
		
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 0; i < weights.length; ++i)
		{
		    random -= weights[i];
		    if (random <= 0.0d)
		    {
		        randomIndex = i;
		        break;
		    }
		}
		
		//Node rNode = unvisitedChildren.get(randomIndex);
		return randomIndex;
	}
}
