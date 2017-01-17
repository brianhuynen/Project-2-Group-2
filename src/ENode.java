import java.util.ArrayList;

public class ENode<Double> {

    double element;
    ENode parent;
    ArrayList<ENode> children;

    public ENode(double e){ //Root node
        element = e;
        children = new ArrayList<ENode>();
    }

    public ENode(double e, ENode parent){ //Child node
        this.parent = parent;
        parent.getChildren().add(this);
        children = new ArrayList<ENode>();
    }

    public ArrayList<ENode> getChildren(){
        return children;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isExternal(){
        return (getChildren().size() == 0) && (parent != null);
    }
}
