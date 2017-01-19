package ExpectiMax;

import java.util.ArrayList;

import MCTS.Node;
import main.Cell;
import main.Game;
import main.Move;

/**
 * Created by esther on 1/16/17.
 */
public class Expectimax {
    //Additional source: https://courses.cs.washington.edu/courses/cse473/11au/slides/cse473au11-adversarial-search.pdf
    public ExpectiNode root;
    public Game game;
    public Cell[][] board;
    public ArrayList<MaxNode> maxNodes;
    public ArrayList<MinNode> minNodes;
    public ArrayList<ChanceNode> chances1;
    public ArrayList<ChanceNode> chances2;
    
    
    /**
     * 
     * @param root root of the tree
     * @param depth depth until which we want to build the tree
     * @param game game containing board 
     * @param firstPlayer which player makes the first move
     * @param max which player is the max node
     */
    public Expectimax(ExpectiNode root, int depth, Game game, int firstPlayer, int max)
    {
    	this.root = root;
    	this.game = game.DuplicateG();
    	
    	buildTree(depth, max);
    }
    
    public void buildTree(int depth, int max)
    {
    	
    }

    /**
     * creating one layer of the tree + choosing the best max node
     * first max nodes, then chance nodes if needed, then min nodes, then chance nodes if needed
     * @param root last action on previous layer
     * @return best move
     */
    public Move buildLayer(ExpectiNode root)
    {
    	
    	generateMaxNodes(root);
    	/*
    	 * if(one of the max nodes creates battle)
    	 * {
    	 * 		generateChanceNodes()
    	 * 		generateMinNodes()
    	 * 		if(one of the min nodes creates battle)
    	 * 		{	
    	 * 			generateChanceNodes()
    	 * 			pick best node ->go back to parent max node and return move
    	 * 		}
    	 * 		pick best node -> go back to parent max node and return move
    	 * }
    	 * generateMinNodes()
    	 * if(one of the min nodes creates battle)
    	 * {
    	 * 		generate chance nodes
    	 * 		pick best node -> go back to parent max node and return move
    	 * }
    	 * pick best node -> go back to parent max node and return move
    	 */
    	return null;
    }
    
    /**
     * builds max node list after a specific move, from available moves
     * @param root last action
     * @return list of max nodes
     */
    public void generateMaxNodes(ExpectiNode parent)
    {
    	
    }
    
    /**
     * builds min node list after a specific move/board situation 
     * @param root
     * @param playerID
     * @return
     */
    public void generateMinNodes(ExpectiNode parent, int playerID)
    {
    	
    }
    
    /**
     * generates chance nodes if there is a conflict
     * @param parent the parent node from which we generate the nodes
     * @param n player id
     */
    public void generateChanceNodes(ExpectiNode parent, int n)
    {
    	
    }
    
//    public Expectimax()
//    {
//        children = new ArrayList<ENode>();
//        buildTree(rootNode);
//        findValues(rootNode);
//    }
//
//    public void buildTree(ENode rootNode){
//        if(rootNode == null){
//            rootNode = new ENode(0);
//        }
//    }
//
//    /**
//     * May wanna use this instead:
//     * (Source: https://www.rose-hulman.edu/Users/faculty/young/OldFiles/CS-Classes/csse413/schedule/day7/expectimax.pdf)
//     * def exp-value(state):
//     *      initialize v = 0
//     *      for each successor of state:
//     *          p = probability(successor)
//     *          v += p * value(successor)
//     *      return v
//     */
//
//    //TODO determine return statement.
//    //Source: https://web.uvic.ca/~maryam/AISpring94/Slides/06_ExpectimaxSearch.pdf)
//    public void findValues(ENode n){
//        /**if(!n.isExternal()){
//         *      if(n.isMaxNode()){
//         *          return maxValue(n);
//         *      }
//         *      else if(n.isExpNode(){
//         *          return expValue(n);
//         *      }
//         * }
//         * else return expectation(n); //Should return evaluation of state (i.e. win, draw or loss).
//         */
//    }
//
//    //TODO idem.
//    public void maxValue(ENode n){
//        Node nextNode;
//        /**
//         * for each node p in next Layer
//         *      values.add(findValues(p));
//         *      return max(values);
//         */
//    }
//    //TODO idem.
//    public void expValue(ENode n){
//        /**
//         * for each node p in next Layer
//         *      values.add(findValues(p);
//         *      weights.add(probability(n,p)); //probability (n,p) for p in successors of n.
//         *      return expectation(values, weights);
//         */
//    }
//
}
