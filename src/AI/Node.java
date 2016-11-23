package AI;

public class Node {
	
	Node leftChild;
	Node parent;
	Node rightChild;
	int content;
	
	public Node(int content, Node parent, Node leftChild, Node rightChild){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
		this.content = content;
	}
	
	public void setContent(int i){
		this.content = i;
	}
	
	public int getContent(){
		return content;
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
	
	public void setParent(Node p){
		this.parent = p;
	}
	
	public Node getParent(){
		return parent;
	}

}
