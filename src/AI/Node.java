package AI;

public class Node {
	
	Node leftChild;
	Node rightChild;
	
	public Node(Node leftChild, Node rightChild){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node getRightChild(){
		return rightChild;
	}
	
	public Node getLeftChild(){
		return leftChild;
	}
	
	public void setLeftChild(Node n){
		this.leftChild = n;
	}
	
	public void setRightChild(Node n){
		this.rightChild = n;
	}

}
