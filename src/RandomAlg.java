import java.util.ArrayList;
import java.util.Random;

/**
 * Created by esther on 11/23/16.
 */
public class RandomAlg {

    private Cell[][] board;
    private Player player;

    public RandomAlg(Game game, Player player){
        this.board = game.board;
        this.player = player;
    }
    public int[] generateMovement(){
        //finds coordinates of movable pieces
        ArrayList<int[]> movables = findMovableCoords(player);
        int[] movementData = new int[3];

        Random rand = new Random();
        int i = rand.nextInt(movables.size());

        int[] coord = movables.get(i);

        ArrayList<Integer> directions = new ArrayList<Integer>();
        if (board[coord[0]+1][coord[1]].getCellState() == 0 || (board[coord[0]+1][coord[1]].getCellState() == 1 && board[coord[0]+1][coord[1]].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
            directions.add(1);
        }
        if (board[coord[0]-1][coord[1]].getCellState() == 0 || (board[coord[0]-1][coord[1]].getCellState() == 1 && board[coord[0]-1][coord[1]].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
            directions.add(2);
        }
        if (board[coord[0]][coord[1]+1].getCellState() == 0 || (board[coord[0]][coord[1]+1].getCellState() == 1 && board[coord[0]][coord[1]+1].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
            directions.add(3);
        }
        if (board[coord[0]][coord[1]-1].getCellState() == 0 || (board[coord[0]][coord[1]-1].getCellState() == 1 && board[coord[0]][coord[1]-1].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
            directions.add(4);
        }
        int j;
        if(directions.size() ==1){
            j = 0;
        }
        else {
            j = rand.nextInt(directions.size());
        }
        //1 = right, 2 = left, 3 = down, 4 = up
        if (directions.get(j) == 1){
            //movePiece(coord[0], coord[1], coord[0]+1, coord[1]);
            movementData[0] = coord[0];
            movementData[1] = coord[1];
            movementData[2] = 1;
            return movementData;
        }
        if (directions.get(j) == 2){
            //movePiece(coord[0], coord[1], coord[0]-1, coord[1]);
            movementData[0] = coord[0];
            movementData[1] = coord[1];
            movementData[2] = 2;
            return movementData;

        }
        if (directions.get(j) == 3){
            //movePiece(coord[0], coord[1], coord[0], coord[1]+1);
            movementData[0] = coord[0];
            movementData[1] = coord[1];
            movementData[2] = 3;
            return movementData;
        }
        if (directions.get(j) == 4){
            //movePiece(coord[0], coord[1], coord[0], coord[1]-1);
            movementData[0] = coord[0];
            movementData[1] = coord[1];
            movementData[2] = 4;
            return movementData;
        }
        return null;
    }
    private ArrayList<int[]> findMovableCoords(Player p){
        ArrayList<int[]> list = new ArrayList<int[]>();
        for (int x=1; x<board.length-1; x++){
            for (int y=1; y<board[0].length-1; y++){
                //looks for piece
                Cell current = board[x][y];
                if(board[x][y].getCellState() == 1){
                    Cell right = board[x+1][y];
                    Cell left = board[x-1][y];
                    Cell up = board[x][y-1];
                    Cell down = board[x][y+1];
                    //checks adjacent cells (if chosen piece is movable)
                    //either a empty cell or opponent piece
                    if((right.getCellState() == 0 || left.getCellState() == 0 || down.getCellState() == 0 || up.getCellState() == 0) ||
                            ((right.getCellState() == 1 && right.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()) ||
                                    (left.getCellState() == 1 && left.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (down.getCellState() == 1 && down.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (up.getCellState() == 1 && up.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()))){
                        if(current.getContent().getPlayer_ID() == p.getPlayer_ID()) {
                            int[] coord = {x, y};
                            list.add(coord);
                        }
                    }
                }
            }
        }
        System.out.print(list.size() + " ");
        return list;
    }
}
