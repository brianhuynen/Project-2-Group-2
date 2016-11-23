package AI;

public class BinaryTree {
	
	Node root;
	
	public BinaryTree(Node root){
		this.root  = root;
	}
	
	public Node root(){
		return root;
	}
	
	public boolean isRoot(Node p){
		if(p!=root){
			return false;
		}
		else
			return true;
	}
	
	public int depth(Node p){
		if(isRoot(p)){
			return 0;
		}
		else{
			return 1+depth(p.getParent());
		}
	}
	
	public boolean isInterenal(Node p){
		if(p.getLeftChild()!= null || p.getRightChild()!= null){
			return true;
		}
		else
			return false;
	}
	
	public boolean isExternal(Node p){
		if(p.getLeftChild()==null && p.getRightChild()==null){
			return true;
		}
		else
			return false;
	}
	
	public void addNode(Node newNode, Node pos){
		if(pos.getLeftChild()== null){
			pos.setLeftChild(newNode);
			newNode.setParent(pos);
		}
		else{
			if(pos.getRightChild() == null){
				pos.setRightChild(newNode);
				newNode.setParent(pos);
			}
		}
	}
	
	public Node sibling(Node p){
		if(!isRoot(p)){
			Node parent = p.getParent();
			if(parent.getLeftChild() == p){
				return parent.getRightChild();
			}
			else{
				return parent.getLeftChild();
			}
		}
		else{
			return null;
		}
	}
	

}
