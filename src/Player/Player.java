package Player;

import java.awt.Color;

import GameBoard.Piece;
import GameBoard.Position;

// TODO add methods.
public abstract class Player {
	
	private Color color = null;


	public final int[] data = {1,1,8,5,4,4,4,3,2,1,1,6};
	
	
	private Piece[] pieces;
	private String name;
	protected static int maxID = 0;
	protected int ID = 0;
	private boolean action = false;

	private boolean turn= false;
	
	public Player(){
		maxID++;
	System.out.println("Total number of players: " + maxID);
	
	ID = maxID;
	}
	
	
	public void add(Piece p, Position pos){}
	public void remove(){}
	public void movePiece(Piece p, Position to){}
	public boolean done(){ return false; }
	
	public void setID(int id){
		this.ID = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getID(){ return ID;}
	
	public String getName(){ return name; }
	public Piece[] getPieces(){ return pieces; }
	public String listPieces(){ return ""; }


	public boolean getAction() {
		// TODO Auto-generated method stub
		return action;
	}


	public void isTurn() {
		turn = true;
		
	}

	public Color getColor(){
		return color;
	}
	public void setColor(Color c) {
		this.color = c;
		
	}


	public void setTurn(boolean b) {
		this.turn=b;
		
	}
	public boolean getTurn() {
		return turn;
		
	}
}
