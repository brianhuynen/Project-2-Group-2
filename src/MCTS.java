import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MCTS {
	private Random random;
	private Node rootNode;
	private double explorationConstant = Math.sqrt(2.0);
	private double pessimisticBias;
	private double optimisticBias;

	private boolean scoreBounds;
	private boolean trackTime; // display thinking time used
	private FinalSelectionPolicy finalSelectionPolicy;

	private HeuristicFunction heuristic;
	
	public MCTS() {
		random = new Random();
	}

	/**
	 * Run a UCT-MCTS simulation for a number of iterations.
	 * 
	 * @param startingBoard starting board
	 * @param runs how many iterations to think
	 * @param bounds enable or disable score bounds.
	 * @return
	 */
	public int[] runMCTS(Game startingGame, int runs, boolean bounds) {
		scoreBounds = bounds;
		rootNode = new Node(startingGame);

		long startTime = System.nanoTime();

		for (int i = 0; i < runs; i++) {
		 select(startingGame.DuplicateG(), rootNode);
		}

		long endTime = System.nanoTime();

		if (this.trackTime) {
			System.out.println("Making choice for player: " + rootNode.player);
			System.out.println("Thinking time per move in milliseconds: " + (endTime - startTime) / 1000000);
		}

		return finalMoveSelection(rootNode);
	}
	
	/**
	 * This represents the select stage, or default policy, of the algorithm.
	 * Traverse down to the bottom of the tree using the selection strategy
	 * until you find an unexpanded child node. Expand it. Run a random playout.
	 * Backpropagate results of the playout.
	 * 
	 * @param node
	 *            Node from which to start selection
	 * @param brd
	 * 			  Board state to work from.
	 */
	private void select(Game currentGame, Node currentNode){
		// Begin tree policy. Traverse down the tree and expand. Return
		// the new node or the deepest node it could reach. Return too
		// a board matching the returned node.
		Map.Entry<Game, Node> boardNodePair = treePolicy(currentGame, currentNode);
		
		// Run a random playout until the end of the game.
		double[] score = playout(boardNodePair.getValue(), boardNodePair.getKey());
		
		// Backpropagate results of playout.
		Node n = boardNodePair.getValue();
		n.backPropagateScore(score);
		if (scoreBounds) {
			n.backPropagateBounds(score);
		}
	}
	
	private Map.Entry<Game, Node> treePolicy(Game g, Node node) {
		while(!g.gameOver()) {
				if (node.unvisitedChildren == null) {
					node.expandNode(g); 
				}
				
				if (!node.unvisitedChildren.isEmpty()) {
					Node temp = node.unvisitedChildren.remove(random.nextInt(node.unvisitedChildren.size()));
					node.children.add(temp);
					g.makeMove(temp.move);
					return new AbstractMap.SimpleEntry<>(g, temp);
				} else {
					ArrayList<Node> bestNodes = findChildren(node, g, optimisticBias, pessimisticBias, explorationConstant);
					
					if (bestNodes.size() == 0){
						// We have failed to find a single child to visit
						// from a non-terminal node, so we conclude that
						// all children must have been PRUNED, and that 
						// therefore there is no reason to continue.
						return new AbstractMap.SimpleEntry<>(g, node);						
					}
					
					Node finalNode = bestNodes.get(random.nextInt(bestNodes.size()));
					node = finalNode;
					g.makeMove(finalNode.move);
				}
		}
		
		return new AbstractMap.SimpleEntry<>(g, node);
	}
	
	
	/**
	 * This is the final step of the algorithm, to pick the best move to
	 * actually make.
	 * 
	 * @param n
	 *            this is the node whose children are considered
	 * @return the best Move the algorithm can find
	 */
	private int[] finalMoveSelection(Node n) {
		Node r = null;
		
		switch (finalSelectionPolicy) {
		case maxChild:
			r = maxChild(n);
			break;
		case robustChild:
			r = robustChild(n);
			break;
		default:
			r = robustChild(n);
			break;
		}

		return r.move;
	}

	/**
	 * Select the most visited child node
	 * @param n
	 * @return
	 */
	private Node robustChild(Node n){
		double bestValue = Double.NEGATIVE_INFINITY;
		double tempBest;
		ArrayList<Node> bestNodes = new ArrayList<Node>();

		for (Node s : n.children) {
			tempBest = s.games;
			//tempBest += s.opti[n.player] * optimisticBias;
			//tempBest += s.pess[n.player] * pessimisticBias;
			if (tempBest > bestValue) {
				bestNodes.clear();
				bestNodes.add(s);
				bestValue = tempBest;
			} else if (tempBest == bestValue) {
				bestNodes.add(s);
			}
		}

		Node finalNode = bestNodes.get(random.nextInt(bestNodes.size()));

		return finalNode;
	}
	
	/**
	 * Select the child node with the highest score
	 * @param n
	 * @return
	 */
	private Node maxChild(Node n){
		double bestValue = Double.NEGATIVE_INFINITY;
		double tempBest;
		ArrayList<Node> bestNodes = new ArrayList<Node>();

		for (Node s : n.children) {
			tempBest = s.score[n.player];
			if (tempBest > bestValue) {
				bestNodes.clear();
				bestNodes.add(s);
				bestValue = tempBest;
			} else if (tempBest == bestValue) {
				bestNodes.add(s);
			}
		}

		Node finalNode = bestNodes.get(random.nextInt(bestNodes.size()));

		return finalNode;
	}
	
	
	
	/**
	 * Playout function for MCTS
	 * 
	 * @param state
	 * @return
	 */
	private double[] playout(Node state, Game game) {
		ArrayList<int[]> moves;
		int[] mv;
		Game gm = game.DuplicateG();

		// Start playing random moves until the game is over
		while (!gm.gameOver()) {
			moves = gm.getMoves(CallLocation.treePolicy);
			if (gm.currentPlayer_ID >= 0) {
				// make random selection normally
				mv = moves.get(random.nextInt(moves.size()));
			} else {
				/*
				 * This situation only occurs when a move
				 * is entirely random, for example a die
				 * roll. We must consider the random weights
				 * of the moves. 
				 */
				
				mv = getRandomMove(gm, moves);
			}
									
			gm.makeMove(mv);
		}
		
		return gm.getScore();
	}

	private int[] getRandomMove(Game game, ArrayList<int[]> moves) {
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
		
		return moves.get(randomIndex);
	}
	
	/**
	 * Produce a list of viable nodes to visit. The actual 
	 * selection is done in runMCTS
	 * @param optimisticBias
	 * @param pessimisticBias
	 * @param explorationConstant
	 * @return
	 */
	public ArrayList<Node> findChildren(Node n, Game g, double optimisticBias, double pessimisticBias, double explorationConstant){
		double bestValue = Double.NEGATIVE_INFINITY;
		ArrayList<Node> bestNodes = new ArrayList<Node>();
		for (Node s : n.children) {
			// Pruned is only ever true if a branch has been pruned 
			// from the tree and that can only happen if bounds 
			// propagation mode is enabled.
			if (s.pruned == false) {
				double tempBest = s.upperConfidenceBound(explorationConstant)
						+optimisticBias * s.opti[n.player]
						+pessimisticBias * s.pess[n.player];

				if (heuristic != null){
					tempBest += heuristic.h(g);
				}
				
				if (tempBest > bestValue) {
					// If we found a better node
					bestNodes.clear();
					bestNodes.add(s);
					bestValue = tempBest;
				} else if (tempBest == bestValue) {
					// If we found an equal node
					bestNodes.add(s);
				}
			}
		}
		
		return bestNodes;
	}	
	
	/**
	 * Sets the exploration constant for the algorithm. You will need to find
	 * the optimal value through testing. This can have a big impact on
	 * performance. Default value is sqrt(2)
	 * 
	 * @param exp
	 */
	public void setExplorationConstant(double exp) {
		explorationConstant = exp;
	}

	public void setMoveSelectionPolicy(FinalSelectionPolicy policy){
		finalSelectionPolicy = policy;
	}
	
	public void setHeuristicFunction(HeuristicFunction h){
		heuristic = h;
	}
	
	/**
	 * This is multiplied by the pessimistic bounds of any
	 * considered move during selection.	 
	 * @param b
	 */
	public void setPessimisticBias(double b) {
		pessimisticBias = b;
	}

	/**
	 * This is multiplied by the optimistic bounds of any
	 * considered move during selection.
	 * @param b
	 */
	public void setOptimisticBias(double b) {
		optimisticBias = b;
	}

	public void setTimeDisplay(boolean displayTime) {
		this.trackTime = displayTime;
	}
}


