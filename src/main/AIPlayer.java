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

    public int getAlgID(){
        return algID;
    }

}
