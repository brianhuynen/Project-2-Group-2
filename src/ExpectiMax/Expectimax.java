package ExpectiMax;

import java.util.ArrayList;

import MCTS.Node;
import main.Cell;
import main.Game;
import main.Move;
import main.Move2;
import main.Player;

/**
 * Created by esther on 1/16/17.
 */
public class Expectimax {
    //Additional source: https://courses.cs.washington.edu/courses/cse473/11au/slides/cse473au11-adversarial-search.pdf
    public ExpectiNode root;
    public Game game;
    public Player maxPlayer;
    public Cell[][] board;
    public Probabilities probsP1;
    public Probabilities probsP2;
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
    public Expectimax(ExpectiNode root, int depth, Game game, Player maxPlayer, int max)
    {
    	probsP1 = new Probabilities(1);
    	probsP2 = new Probabilities(2);
    	this.root = root;
    	//this.game = game.duplicateG();
    	this.maxPlayer = maxPlayer;
    	buildTree(depth, max, game);
    }
    
    public void buildTree(int depth, int max, Game game)
    {
    	game = game.duplicateG();
    	ExpectiNode root = new ExpectiNode(0, 1);
    	for( int i = 0; i<depth; i++)
    	{
    		buildLayer(root);
    	}
    }

    /**
     * creating one layer of the tree + choosing the best max node
     * first max nodes, then chance nodes if needed, then min nodes, then chance nodes if needed
     * @param root last action on previous layer
     * @return best move
     */
    public Move buildLayer(ExpectiNode root)
    {
    	
    	maxNodes.clear();
    	minNodes.clear();
    	chances1.clear();
    	chances2.clear();
    	
    	generateMaxNodes(root, maxPlayer);
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
    public void generateMaxNodes(ExpectiNode parent, Player player)
    {
    	//idk if score should be 0, maybe should be assigned from the method
    	int score = 0;
    	ArrayList<Move> movables = game.movesAvailable();
    	for(int i = 0; i<movables.size(); i++)
    	{
    		MaxNode max = new MaxNode(parent, score, player.player_ID, movables.get(i));
    		maxNodes.add(max);
    	}
    }
    
    /**
     * builds min node list after a specific move/board situation 
     * @param root
     * @param playerID
     * @return
     */
    public void generateMinNodes(ExpectiNode parent, Player player)
    {
    	//idk if score should be 0, maybe should be assigned from the method

    	int score = 0;
    	ArrayList<Move> movables = game.movesAvailable();
    	for(int i = 0; i<movables.size(); i++)
    	{
    		MinNode min = new MinNode(parent, score, player.player_ID, movables.get(i));
    		minNodes.add(min);
    	}
    }
    
    /**
     * generates chance nodes if there is a conflict
     * @param parent the parent node from which we generate the nodes
     * @param n player id
     */
    public void generateChanceNodes(ArrayList<ExpectiNode> parents, Player player)
    {
    	//checking which
    	if( parents.get(0) instanceof MaxNode )
    	{
    		for( int i=0; i<parents.size(); i++ )
    		{
    			Move2 move = parents.get(i).move;
    			game.movePiece(move.from[0], move.from[1], move.to[0], move.to[1]);
    			/**
    			 * run through the probabilities for your player 
    			 * get the ones with move.to coordinates
    			 * create a chance node for each of them with the rank of the piece
    			 */
    			if( game.battled == true)
    			{
    				//kudeto e 0 tr da se naznachi shansa ot node-cheto v koeto sme
    				game.handleBattleEM(game.board[move.from[0]][move.from[1]].getContent().getRank(), 
    						0);
    			}
    			
    		}
    	}
    	else if( parents.get(0) instanceof MinNode )
    	{
    		//sushtoto kato gore purvo
    		/**
			 * run through the probabilities for oponnent player 
			 * get the ones with move.to coordinates
			 * create a chance node for each of them with the rank of the piece
			 */
    		/*
    		 * if ( battle )
    		 * {
    		 * 		check origin 
    		 */
    	}
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
