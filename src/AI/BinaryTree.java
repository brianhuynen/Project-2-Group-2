package AI;

public class BinaryTree {
	
	Node root;
	
	public BinaryTree(Node root){
		this.root  = root;
	}
	
	public void addNode(Node newNode, Node parent){
		if(parent.getLeftChild()== null){
			parent.setLeftChild(newNode);
		}
		else{
			if(parent.getRightChild() == null){
				parent.setRightChild(newNode);
			}
		}
	}
	

}
