package Cell;
import java.awt.Color;


import java.util.Stack;

import GameBoard.Piece;
//fire@WILL!
import Player.Player;

// Empty cell state, should be able to add a Piece and change the Empty cell to a Occupied cell
public class CardCell extends Cell {


	
	public static int numberOfCardsStacked=0;
	private Player player;
	private Stack<Piece> stack;
	private Color bcolor;
	private int numP=0;
	public CardCell(Player player,int rank){
		content = new Piece(player,rank);
		//bcolor = player.getColor();
		this.numP=player.data[rank];
		setBackground(bcolor);
		stack = new Stack<Piece>();
		stack.setSize(numP);
		createPieces();
		//toString();
	}
	
//	public Cell setStacked(int numberOfCardsStacked){
//		
//		this.numberOfCardsStacked = numberOfCardsStacked;
//		return this;
//	}
	public int getNumP(){
		return numP;
	}
	public Piece getContent(){
		Piece p = stack.pop();
		numP--;
		return p;
	}
	
	public String toString(){
		String c = Integer.toString(this.content.getRank())+"("+Integer.toString(getNumP())+")";
		return c;
	}
	public void createPieces(){
	for (int i=0;i<numP;i++){
		stack.push(content);
	}

		
	}
	
	
}
