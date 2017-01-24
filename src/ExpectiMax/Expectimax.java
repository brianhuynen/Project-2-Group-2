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
    public Player minPlayer;
    public Cell[][] board;
    public Probabilities probs;
    public Probabilities probsP2;
    public ArrayList<ExpectiNode> maxNodes;
    public ArrayList<ExpectiNode> minNodes;
    public ArrayList<ChanceNode> chancenodes1;
    public ArrayList<ChanceNode> chancenodes2;
    
    
    /**
     * @param root root of the tree
     * @param depth depth until which we want to build the tree
     * @param game game containing board 
     * @param firstPlayer which player makes the first move
     * @param max which player is the max node
     */
    public Expectimax(ExpectiNode root, int depth, Game game, Player maxPlayer, Player minPlayer)
    {
    	if(maxPlayer.player_ID == 1)
    		probs = new Probabilities(2);
    	else
    		probs = new Probabilities(1);
    	this.root = root;
    	this.game = game;
    	this.maxPlayer = maxPlayer;
    	this.minPlayer = minPlayer;
    	buildTree(depth, game);
    }
    
    public void buildTree(int depth, Game game)
    {
    	//game = game.duplicateG();
    	ExpectiNode root = new ExpectiNode(0, 1, game);
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
    public Move2 buildLayer(ExpectiNode root)
    {

    	maxNodes = new ArrayList<ExpectiNode>();
    	minNodes = new ArrayList<ExpectiNode>();
    	chancenodes1 = new ArrayList<ChanceNode>();
    	chancenodes2 = new ArrayList<ChanceNode>();
    	System.out.println("arraylists initialized");
    	Move2 move  = null;
    	double score = 0;
    	
    	
    	//generating max nodes from the acailable moves
    	System.out.println("next generate max nodes");
    	generateMaxNodes(root, maxPlayer);
    	//generating chance nodes from the max nodes
    	
    	move = maxNodes.get(0).move;
    	System.out.println(" in buildlayer move 0 from " + move.from[0] + move.from[1] + " to " + move.to[0] + move.to[1]);
    	
    	System.out.println("generating chance nodes after max nodes");
    	generateChanceNodes(maxNodes, maxPlayer);
    	
    	//generating min nodes from max nodes/chance nodes
    	for(int i = 0; i < maxNodes.size(); i++)
    	{
    		if( !maxNodes.get(i).chanceGenerated )
    		{
    			System.out.println("generating min nodes immediately after max nodes");
    			generateMinNodes(maxNodes.get(i), minPlayer);
    		}
    	}
    	if(!chancenodes1.isEmpty())
		{
			for(int i = 0; i<chancenodes1.size(); i++)
			{
				System.out.println("generating min nodes immediately after chance nodes");
				generateMinNodes(chancenodes1.get(i), minPlayer);
			}
		}
    	//generating chance nodes from the min nodes
    	System.out.println("generating chance nodes after min nodes");
    	generateChanceNodes(minNodes, maxPlayer);
    	
    	for(int i = 0; i<minNodes.size(); i++)
    	{
    		if(!minNodes.get(i).chanceGenerated)
    		{
    			System.out.println("finding lowest score");
	    		if(move == null)
	    		{
	    			move = minNodes.get(i).move;
	    			score = minNodes.get(i).score;
	    		}
	    		else if(minNodes.get(i).score > score)
	    		{
	    			move = minNodes.get(i).move;
	    			score = minNodes.get(i).score;
	    		}
    		}
    	}
    	if(!chancenodes2.isEmpty())
    	{
    		for(int i = 0; i < chancenodes2.size(); i++)
    		{
    			if(move == null)
	    		{
	    			move = chancenodes2.get(i).move;
	    			score = chancenodes2.get(i).score;
	    		}
	    		else if(minNodes.get(i).score > score)
	    		{
	    			move = chancenodes2.get(i).move;
	    			score = chancenodes2.get(i).score;
	    		}
    		}
    	}
    	for(int i = 0; i<maxNodes.size(); i++ )	
    	{
    		if(maxNodes.get(i).score == 15 )
    		{
    			move = maxNodes.get(i).move;
    			score = maxNodes.get(i).score;
    		}
    	}
    	//clearing the lists before we make a new layer
    	if(!maxNodes.isEmpty())
    		maxNodes.clear();
    	if(!minNodes.isEmpty())
    		minNodes.clear();
    	if(!chancenodes1.isEmpty())	
    		chancenodes1.clear();
    	if(!chancenodes2.isEmpty())
    		chancenodes2.clear();
    	return move;
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
    	System.out.println("score set to 0");
    	ArrayList<Move2> movables = game.movesAvailable2(player);
    	if (!movables.isEmpty())
    	{
    		System.out.println("movables exists");
    	}
    	for(int i = 0; i<movables.size(); i++)
    	{
    		System.out.println("going through moves to add them to max nodes");
	    	MaxNode max = new MaxNode(parent, score, player.player_ID, movables.get(i), game);
	    	System.out.println("move from " + movables.get(i).from[0] + movables.get(i).from[1] +
	    			" to " + movables.get(i).to[0] + movables.get(i).to[1]);
	    	max.assignScore(player.player_ID);
	    	System.out.println("score assigned = " + max.score);
	    	maxNodes.add(max);
	    	System.out.println("max added to max nodes");
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
    	ArrayList<Move2> movables = game.movesAvailable2(minPlayer);
    	for(int i = 0; i<movables.size(); i++)
    	{
    		
    			MinNode min = new MinNode(parent, score, player.player_ID, movables.get(i), game);
    			min.assignScore(player.player_ID);
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
    		System.out.println("chance node to be generated after max node");
    		for( int i=0; i<parents.size(); i++ )
    		{
    			Move2 move = parents.get(i).move;
    			System.out.println("move from " + move.from[0] + move.from[1] + " to " + move.to[0] + move.to[1]);
    			if(move == null)
    			{
    				System.out.println("move = null");
    			}
    			game.movePiece(move.from[0], move.from[1], move.to[0], move.to[1]);
    			/**
    			 * run through the probabilities for your player 
    			 * get the ones with move.to coordinates
    			 * create a chance node for each of them with the rank of the piece
    			 */
    			if(game.battled == true)
    			{
    				System.out.println("conflict");
    				ArrayList<Chance> chances = new ArrayList<Chance>();
    				for(int j = 0; j < probs.probs.size(); j++)
    				{
    					if( probs.probs.get(j).coords == move.to)
    					{
    						chances.add(probs.probs.get(j));
    					}
    				}
    				for(int j = 0; i < chances.size(); j++)
    				{
    					ChanceNode chanceN = new ChanceNode(parents.get(i), 0.5, player.getPlayer_ID(), move, chances.get(i).piece, game);
    					
    					int handle = game.handleBattleEM(game.board[move.from[0]][move.from[1]].getContent().getRank(), chanceN.piece);
    					if( handle == 0 )
    					{
    						chanceN.score = 15;
    						chanceN.parent.score = 15;
    						//game over our player wins yay
    					}
    					else if (handle == 1 )
    					{
    						//our player loses the battle
    						chanceN.assignSum(-game.board[move.from[0]][move.from[1]].getContent().getRank(), player.player_ID);
    						chanceN.assignScore(player.player_ID);
    					}
    					else if (handle == 2)
    					{
    						//our player wins the battle
    						int id;
    						if(player.player_ID == 1)
    						{
    							id = 2;
    						} else
    						{
    							id = 1;
    						}
    						chanceN.assignSum(-chanceN.piece, id);
    						chanceN.assignScore(player.player_ID);
    					}
    					else if (handle == 3)
    					{
    						int id;
    						if(player.player_ID == 1)
    						{
    							id = 2;
    						} else
    						{
    							id = 1;
    						}
    						chanceN.assignSum(-game.board[move.from[0]][move.from[1]].getContent().getRank(), player.player_ID);
    						chanceN.assignSum(-chanceN.piece, id);
    						chanceN.assignScore(player.player_ID);
    					}
    					chancenodes1.add(chanceN);
    				}
    				//kudeto e 0 tr da se naznachi shansa ot node-cheto v koeto sme
    				
    				parents.get(i).chanceGenerated = true;
    			}
    			game.reverseMove2(parents.get(i).getMove());
    		}
    	}
    	else if( parents.get(0) instanceof MinNode )
    	{
    		bigloop: for( int i=0; i<parents.size(); i++ )
    		{
    			
    				
    			Move2 move = parents.get(i).move;
    			System.out.println("there is move from " + move.from[0] + move.from[1] + 
						" to " + move.to[0] + move.to[1]);
    			if(move.from[0] == 2 && move.from[1] == 10 && move.to[0] == 1 && move.to[1] == 10)
    			{
    				continue bigloop;
    			}
    			Move2 moveparent = parents.get(i).parent.move;
    			game.movePiece(moveparent.from[0], moveparent.from[1], moveparent.to[0], moveparent.to[1]);
    			game.movePiece(move.from[0], move.from[1], move.to[0], move.to[1]);
    			/**
    			 * run through the probabilities for your player 
    			 * get the ones with move.from coordinates
    			 * create a chance node for each of them with the rank of the piece
    			 */
    			if(game.battled == true)
    			{
    				System.out.println("conflict");
    				ArrayList<Chance> chances = new ArrayList<Chance>();
    				for(int j = 0; j < probs.probs.size(); j++)
    				{
    					if( probs.probs.get(j).coords == move.from)
    					{
    						chances.add(probs.probs.get(j));
    					}
    				}
    				for(int j = 0; i < chances.size(); j++)
    				{
    					ChanceNode chanceN = new ChanceNode(parents.get(i), 0.5, player.getPlayer_ID(), move, chances.get(i).piece, game);
    					
    					int handle = game.handleBattleEM(chanceN.piece, game.board[move.to[0]][move.to[1]].getContent().getRank());
    					if( handle == 0 )
    					{
    						chanceN.score = 0;
    						chanceN.parent.score = 0;
    						//game over our player loses :(((((((((((
    					}
    					else if (handle == 1 )
    					{
    						//our player wins the battle
    						int id;
    						if(player.player_ID == 1)
    						{
    							id = 2;
    						} else
    						{
    							id = 1;
    						}
    						chanceN.assignSum(-chanceN.piece, id);
    						chanceN.assignScore(player.player_ID);
    						
    					}
    					else if (handle == 2)
    					{
    						//our player loses the battle
    						chanceN.assignSum(-game.board[move.from[0]][move.from[1]].getContent().getRank(), player.player_ID);
    						chanceN.assignScore(player.player_ID);
    					}
    					else if (handle == 3)
    					{
    						int id;
    						if(player.player_ID == 1)
    						{
    							id = 2;
    						} else
    						{
    							id = 1;
    						}
    						chanceN.assignSum(-game.board[move.from[0]][move.from[1]].getContent().getRank(), player.player_ID);
    						chanceN.assignSum(-chanceN.piece, id);
    						chanceN.assignScore(player.player_ID);
    					}
    					chancenodes2.add(chanceN);
    				}
        			parents.get(i).chanceGenerated = true;
    			}
    			if(move == null)
    			{
    				System.out.println("no move");
    			} else {
    				System.out.println("yes move");
    			}
    			game.reverseMove2(move);
    			game.reverseMove2(moveparent);
    		}
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
