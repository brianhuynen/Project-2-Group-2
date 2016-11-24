import java.awt.*;

/**
 * Created by esther on 11/23/16.
 */
public class AIPlayer extends Player {
    private int algID;

    public AIPlayer(int i, Color color, int algID){
        super(i, color);
        this.algID = algID;
    }

    public int getAlgID(int algID){
        return algID;
    }

}
