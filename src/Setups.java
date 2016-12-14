import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Husky on 7-12-2016.
 */
public class Setups {

    private ArrayList<int[][]> setups1, setups2;

    // Setup id format: sxx, x1 = setup number, x2 = player id
    /* NOTE: Seems like the setups are coded like this:
            x.length = setup.length;
            y.length = setup[0].length;

            o ----> y
            | x x x
            | x x x
            v x x x
            x
     */
    public Setups(){
//        //Setups for player1, stored in arraylist setups1
//        int[][] s11 = {{ 2, 3,11, 2, 3,11, 0,11, 3, 3},
//                       { 4,11, 4, 7, 8, 5,11, 5, 6, 4},
//                       { 5, 4,11, 1, 9, 2, 7, 7, 8, 2},
//                       { 6, 2, 2, 5, 2, 6, 3,10, 2, 6}};
//
//        int[][] s21 = {{ 7, 3, 3, 3, 4,11, 0,11,11, 3},
//                       { 7, 2, 7, 1, 6, 5,11, 4, 5, 2},
//                       { 4, 2, 8, 8, 9, 2, 4,11,11, 5},
//                       {10, 6, 5, 3, 2, 6, 2, 2, 2, 6}};
//
//        int[][] s31 = {{ 3,11, 4,11, 4, 2, 3, 3,11, 0},
//                       { 4, 8, 1, 3,11, 2, 6, 5, 5,11},
//                       { 5, 2, 7, 5,11, 2, 7, 7, 8, 3},
//                       { 6, 2, 4, 9, 6, 2, 2,10, 2, 6}};
//
//        int[][] s41 = {{ 3, 2, 3, 3, 4,11, 0,11, 3, 2},
//                       { 6, 4, 7, 1, 7, 5,11, 4,11, 4},
//                       {10, 2, 7, 8, 2, 6,11, 5,11, 5},
//                       { 2, 8, 5, 2, 6, 2, 9, 3, 2, 6}};
//
        int[][] s11 = {{ 4, 4,11, 0,11,-1,-1,-1,-1,-1},
                       { 4,-1,-1,11,-1,-1,-1,-1,-1,-1},
                       {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                       {-1,-1,-1,-1,-1, 3,-1,-1,-1,-1}};
        setups1 = new ArrayList<int[][]>();
        setups1.add(s11);
//        setups1.add(s21);
//        setups1.add(s31);
//        setups1.add(s41);
//
//        //Mirrored setups for player 2, stored in arraylist setups2
//        int[][] s12 = {{ 6, 2, 2, 5, 2, 6, 3,10, 2, 6},
//                       { 5, 4,11, 1, 9, 2, 7, 7, 8, 2},
//                       { 4,11, 4, 7, 8, 5,11, 5, 6, 4},
//                       { 2, 3,11, 2, 3,11, 0,11, 3, 3}};
//
//        int[][] s22 = {{10, 6, 5, 3, 2, 6, 2, 2, 2, 6},
//                       { 4, 2, 8, 8, 9, 2, 4,11,11, 5},
//                       { 7, 2, 7, 1, 6, 5,11, 4, 5, 2},
//                       { 7, 3, 3, 3, 4,11, 0,11,11, 3}};
//
//        int[][] s32 = {{ 6, 2, 4, 9, 6, 2, 2,10, 2, 6},
//                       { 5, 2, 7, 5,11, 2, 7, 7, 8, 3},
//                       { 4, 8, 1, 3,11, 2, 6, 5, 5,11},
//                       { 3,11, 4,11, 4, 2, 3, 3,11, 0}};
//
//        int[][] s42 = {{ 2, 8, 5, 2, 6, 2, 9, 3, 2, 6},
//                       {10, 2, 7, 8, 2, 6,11, 5,11, 5},
//                       { 6, 4, 7, 1, 7, 5,11, 4,11, 4},
//                       { 3, 2, 3, 3, 4,11, 0,11, 3, 2}};
//
        int[][] s12 = {{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                       {-1,-1,-1,-1, 4,-1, 4,-1,-1,-1},
                       {-1,-1,-1,-1,-1, 4,-1,11,-1,-1},
                       {-1,-1,-1,-1,-1,-1,11, 0,11, 3}};
        setups2 = new ArrayList<int[][]>();
        setups2.add(s12);
//        setups2.add(s22);
//        setups2.add(s32);
//        setups2.add(s42);

    }
    //Returns a random setup for a player.
    public int[][] randomPreset(int playerID){
        Random rand = new Random();
        if(playerID == 1){
            int i = rand.nextInt(setups1.size());
            printSetup(setups1.get(i));
            return setups1.get(i);
        }
        else if(playerID == 2){
            int i = rand.nextInt(setups1.size());
            printSetup(setups2.get(i));
            return setups2.get(i);
        }
        return null;
    }
    //Prints a setup (Just for testing purposes, please remove later!)
    public void printSetup(int[][] setup){
        System.out.println("Player setup:");
        for (int x = 0; x<setup.length; x++) {
            System.out.print("{");
            for (int y = 0; y<setup[0].length; y++) {
                if(y < setup[0].length-1) {
                    if (setup[x][y] < 10)
                        System.out.print(" " + setup[x][y] + ",");
                    else
                        System.out.print(setup[x][y] + ",");
                } else {
                    if (setup[x][y] < 10)
                        System.out.print(" " + setup[x][y]);
                    else
                        System.out.print(setup[x][y]);
                }
            }
            System.out.print("}");
            System.out.println();
        }
    }
}
