package MCTS;

import java.util.ArrayList;

public class Tree {

	Node root;
	int size;
	
	public Tree(Node root){
		this.root = root;
		this.size = 1;
	}
	
	public boolean isRoot(Node p){
		if( p == root){
			return true;
		}
		else
			return false;
	}
	
	public Node root(){
		return root;
	}
	
	public Node parent(Node p){
		if(!isRoot(p)){
			return p.parent;
		}
		else
			return null;
	}
	
	public ArrayList<Node> children(Node p){
		return p.children;
	}
	
	public void insertNode(Node p, Node parent){
		if(parent != null){
			parent.children.add(parent);
		}
		size++;
	}
	
	public int numChildren(Node p){
		return p.children.size();
	}
	
	public boolean isInternal(Node p){
		if(p.children.isEmpty()){
			return false;
		}
		else
			return true;
	}
	
	public boolean isExternal(Node p){
		if(p.children.isEmpty()){
			return true;
		}
		else
			return false;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		else
			return false;
	}
	
	public void removeNode(Node pos){
		Node parent = parent(pos);
		ArrayList<Node> children = children(parent);
		for(int i = 0; i<children.size(); i++){
			if(pos == children.get(i)){
				parent.children.remove(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Node a = new Node("a",null);
		
		Node b = new Node("b", a);
		Node c = new Node("c", a);
		Node d = new Node("d", a);
		Node e = new Node("e", a);
		Node f = new Node("f", a);
		
		Node g = new Node("g", b);
		Node h = new Node("h", b);
		
		Node i = new Node("i", c);
		Node j = new Node("j", c);
		Node k = new Node("k", c);
		
		Tree t = new Tree(a);
		t.insertNode(b, b.parent);
		t.insertNode(c, c.parent);
		t.insertNode(d, d.parent);
		t.insertNode(e, e.parent);
		t.insertNode(f, f.parent);
		t.insertNode(g, g.parent);
		t.insertNode(h, h.parent);
		t.insertNode(i, i.parent);
		t.insertNode(j, j.parent);
		t.insertNode(k, k.parent);
		
		
		
	}
 
}
