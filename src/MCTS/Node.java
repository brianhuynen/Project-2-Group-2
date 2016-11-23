package MCTS;

import java.util.ArrayList;

public class Node {
	
	ArrayList<Node> children;	
	Node parent;
	String content;
	
	//Should store content
	public Node(String cont, Node pos){
		ArrayList<Node> children = new ArrayList<Node>();
		this.children = children;
		this.parent = pos;
		this.content = cont;
	}
	
	public String toString(){
		String s = content;
		return s;
	}

}
