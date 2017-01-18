package ExpectiMax;
import java.util.ArrayList;

public class ENode {

    double element;
    ENode parent;
    ArrayList<ENode> children;
    boolean rootNode = false;

    public ENode(double e){ //Root node
        element = e;
        children = new ArrayList<ENode>();
        rootNode = true;
    }

    public ENode(double e, ENode parent){ //Child node
        this.parent = parent;
        parent.getChildren().add(this);
        children = new ArrayList<ENode>();
    }

    public ArrayList<ENode> getChildren(){
        return children;
    }

    public boolean hasRoot(){
        return rootNode;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isExternal(){
        return (getChildren().size() == 0) && (parent != null);
    }
}
