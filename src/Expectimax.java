
import java.util.ArrayList;

/**
 * Created by esther on 1/16/17.
 */
public class Expectimax {
    //Additional source: https://courses.cs.washington.edu/courses/cse473/11au/slides/cse473au11-adversarial-search.pdf
    private ENode rootNode;
    private ArrayList<ENode> children;
    private ArrayList<Double> values, weights;

    public Expectimax()
    {
        children = new ArrayList<ENode>();
        buildTree(rootNode);
        findValues(rootNode);
    }

    public void buildTree(ENode rootNode){
        if(rootNode == null){
            rootNode = new ENode(0);
        }
    }

    /**
     * May wanna use this instead:
     * (Source: https://www.rose-hulman.edu/Users/faculty/young/OldFiles/CS-Classes/csse413/schedule/day7/expectimax.pdf)
     * def exp-value(state):
     *      initialize v = 0
     *      for each successor of state:
     *          p = probability(successor)
     *          v += p * value(successor)
     *      return v
     */

    //TODO determine return statement.
    //Source: https://web.uvic.ca/~maryam/AISpring94/Slides/06_ExpectimaxSearch.pdf)
    public void findValues(ENode n){
        /**if(!n.isExternal()){
         *      if(n.isMaxNode()){
         *          return maxValue(n);
         *      }
         *      else if(n.isExpNode(){
         *          return expValue(n);
         *      }
         * }
         * else return expectation(n); //Should return evaluation of state (i.e. win, draw or loss).
         */
    }

    //TODO idem.
    public void maxValue(ENode n){
        Node nextNode;
        /**
         * for each node p in next Layer
         *      values.add(findValues(p));
         *      return max(values);
         */
    }
    //TODO idem.
    public void expValue(ENode n){
        /**
         * for each node p in next Layer
         *      values.add(findValues(p);
         *      weights.add(probability(n,p)); //probability (n,p) for p in successors of n.
         *      return expectation(values, weights);
         */
    }

}
