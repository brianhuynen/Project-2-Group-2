import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;



public class MCTS 
{
	private Node root;
	private RandomAlg randomalg;
	private Random random;
	private boolean trackTime; // display thinking time used
	private boolean scoreBounds;
	
	public MCTS() {
		random = new Random();
	}
	
//	public Move runMCTS(Game startingBoard, int runs, boolean bounds) {
//		scoreBounds = bounds;
//		randomalg = new RandomAlg(startingBoard, startingBoard.currentPlayer);
//		root = new Node(startingBoard);
//
//		long startTime = System.nanoTime();
//
//		for (int i = 0; i < runs; i++) {
//			select(startingBoard.duplicate(startingBoard.currentPlayer), root);
//		}
//
//		long endTime = System.nanoTime();
//
//		if (this.trackTime) {
//			System.out.println("Making choice for player: " + root.player);
//			System.out.println("Thinking time per move in milliseconds: " + (endTime - startTime) / 1000000);
//		}
//
//		return finalMoveSelection(root);
//		//return null;
//	}
//	
//	private void select(Game currentBoard, Node currentNode){
//		// Begin tree policy. Traverse down the tree and expand. Return
//		// the new node or the deepest node it could reach. Return too
//		// a board matching the returned node.
//		Map.Entry<Game, Node> boardNodePair = treePolicy(currentBoard, currentNode);
//		
//		// Run a random playout until the end of the game.
//		//double[] score = playout(boardNodePair.getValue(), boardNodePair.getKey());
//		
//		// Backpropagate results of playout.
//		Node n = boardNodePair.getValue();
//		n.backPropagateScore(score);
//		if (scoreBounds) {
//			n.backPropagateBounds(score);
//		}
//	}
//	
//	private Map.Entry<Game, Node> treePolicy(Game b, Node node) {
//		while(!b.gameOver) {
//				if (node.unvisitedChildren == null) {
//					node.expandNode(b); 
//				}
//				
//				if (!node.unvisitedChildren.isEmpty()) {
//					Node temp = node.unvisitedChildren.remove(random.nextInt(node.unvisitedChildren.size()));
//					node.children.add(temp);
//					b.makeMove(temp.move);
//					return new AbstractMap.SimpleEntry<>(b, temp);
//				} else {
//					ArrayList<Node> bestNodes = findChildren(node, b, optimisticBias, pessimisticBias, explorationConstant);
//					
//					if (bestNodes.size() == 0){
//						// We have failed to find a single child to visit
//						// from a non-terminal node, so we conclude that
//						// all children must have been PRUNED, and that 
//						// therefore there is no reason to continue.
//						return new AbstractMap.SimpleEntry<>(b, node);						
//					}
//					
//					Node finalNode = bestNodes.get(random.nextInt(bestNodes.size()));
//					node = finalNode;
//					b.makeMove(finalNode.move);
//				}
//		}
//		
//		return new AbstractMap.SimpleEntry<>(b, node);
//	}
//	
//	public int playout(Node state, Game game, RandomAlg rand)
//	{
//		Move mv;
//		while(!game.gameOver)
//		{
//			mv = rand.generateMovement();
//			game.movePiece(mv.piece.getPosition()[0], mv.piece.getPosition()[1], mv.newCoords[0], mv.newCoords[1]);
//		}
//		return game.currentPlayer.offBoard;
//	}
//	
//	public ArrayList<Node> findChildren(Node n, Game b, double optimisticBias, double pessimisticBias, double explorationConstant){
//		double bestValue = Double.NEGATIVE_INFINITY;
//		ArrayList<Node> bestNodes = new ArrayList<Node>();
//		for (Node s : n.children) {
//			// Pruned is only ever true if a branch has been pruned 
//			// from the tree and that can only happen if bounds 
//			// propagation mode is enabled.
//			if (s.pruned == false) {
//				double tempBest = s.upperConfidenceBound(explorationConstant)
//						+optimisticBias * s.opti[n.player]
//						+pessimisticBias * s.pess[n.player];
//
//				if (heuristic != null){
//					tempBest += heuristic.h(b);
//				}
//				
//				if (tempBest > bestValue) {
//					// If we found a better node
//					bestNodes.clear();
//					bestNodes.add(s);
//					bestValue = tempBest;
//				} else if (tempBest == bestValue) {
//					// If we found an equal node
//					bestNodes.add(s);
//				}
//			}
//		}
//		
//		return bestNodes;
//	}	
//	
//	/**
//	 * Sets the exploration constant for the algorithm. You will need to find
//	 * the optimal value through testing. This can have a big impact on
//	 * performance. Default value is sqrt(2)
//	 * 
//	 * @param exp
//	 */
//	public void setExplorationConstant(double exp) {
//		explorationConstant = exp;
//	}
//
//	public void setMoveSelectionPolicy(FinalSelectionPolicy policy){
//		finalSelectionPolicy = policy;
//	}
//	
//	public void setHeuristicFunction(HeuristicFunction h){
//		heuristic = h;
//	}
//	
//	/**
//	 * This is multiplied by the pessimistic bounds of any
//	 * considered move during selection.	 
//	 * @param b
//	 */
//	public void setPessimisticBias(double b) {
//		pessimisticBias = b;
//	}
//
//	/**
//	 * This is multiplied by the optimistic bounds of any
//	 * considered move during selection.
//	 * @param b
//	 */
//	public void setOptimisticBias(double b) {
//		optimisticBias = b;
//	}
//
//	public void setTimeDisplay(boolean displayTime) {
//		this.trackTime = displayTime;
//	}
//}


}
