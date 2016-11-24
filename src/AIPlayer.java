import java.awt.*;

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

    public int[] generateMovement(Game game){
        if (this.getAlgID() == 1){
            RandomAlg alg = new RandomAlg(game, this);
            return alg.generateMovement();
        }
        return null;
    }

    public int getAlgID(){
        return algID;
    }

}
