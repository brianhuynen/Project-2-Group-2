package main;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by esther on 11/23/16.
 */
public class AIPlayer extends Player {
    //1 = randomAlg, 2 = MCTS, 3 = blah
    private int algID;

    public AIPlayer(int i, Color color, int algID){
        super(i, color);
        this.algID = algID;
    }

    public Move generateMovement(Game game){
        if (this.getAlgID() == 1){
            RandomAlg alg = new RandomAlg(game, this);
            return alg.generateMovement();
        }
//        if (this.getAlgID() == 2){
//            MCTS mcts= new MCTS(game, this);
//           
//    		mcts.setExplorationConstant(0.4);
//    		mcts.setTimeDisplay(true);
//    		Move move = null;
//    		mcts.setOptimisticBias(0.0d);
//    		mcts.setPessimisticBias(0.0d);
//    		mcts.setMoveSelectionPolicy(FinalSelectionPolicy.robustChild);
//    		int []scores = new int[3];
//
//    				move = mcts.runMCTS(game, 100, false);
//    				
//    			
//    			
//    			
//    			double []scr = game.getScore();
//    			if (scr[0] == 1.0) {
//    				scores[0]++; // player 1
//    			} else if (scr[1]==1.0) {
//    				scores[1]++; // player 2
//    			} else
//    				scores[2]++; // draw
//    			
//    			System.out.println(Arrays.toString(scr));
//    			System.out.println(Arrays.toString(scores));
//            return move;
//        }
        return null;
    }

    public int getAlgID(){
        return algID;
    }

}