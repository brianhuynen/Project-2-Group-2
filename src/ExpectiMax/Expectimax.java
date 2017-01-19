package ExpectiMax;

import java.util.ArrayList;

import MCTS.Node;
import main.Game;
import main.Move;

/**
 * Created by esther on 1/16/17.
 */
public class Expectimax {
    //Additional source: https://courses.cs.washington.edu/courses/cse473/11au/slides/cse473au11-adversarial-search.pdf
    public ExpectiNode root;
    public Game game;
    
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
    	this.game = game;
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
    	return null;
    }
    
    /**
     * builds max node list after a specific move, from available moves
     * @param root last action
     * @return list of max nodes
     */
    public ArrayList<MaxNode> generateMaxNodes(ExpectiNode root)
    {
    	return null;
    }
    
    /**
     * builds min node list after a specific move/board situation 
     * @param root
     * @param playerID
     * @return
     */
    public ArrayList<MinNode> generateMinNodes(ExpectiNode root, int playerID)
    {
    	return null;
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
