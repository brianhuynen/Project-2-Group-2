package main;


public class Pieces {
	
	int rank;
	String name;
	Pieces piece;
	int player_ID;
	public int [] position;
	public boolean known;
	
	
	public Pieces(int rank, String name, int player_ID){
		this.rank = rank;
		this.name = name;
		this.player_ID = player_ID;
		this.known = false;
	}

	public Pieces(Pieces toClone)
	{
		if (toClone == null)
			System.out.println("Michael should listen to Taghi more");
		this.rank = toClone.rank;
		this.name = toClone.name;
		this.player_ID = toClone.player_ID;
		this.known = toClone.known;
		this.position = toClone.position.clone();
	}
	
	public int getPlayer_ID(){
		return player_ID;
	}
	public Pieces getPiece(){
		return piece;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRank(){
		return rank;
	}
	/**
	 * Checks if piece can walk. bombs and flag cannot move
	 * @return if piece is bomb/ flag return false else returns true
	 */
	public boolean isMovable(){
		if(getRank()==0|| getRank()==11){
			return false;
		}
		return true;
	}
	
	public void setPosition(int x, int y){
		int pos [] = new int [2];
		pos[0] = x; pos[1] = y;
		this.position = pos;
	}
	
	public int [] getPosition(){
		return position;
	}
	
	public boolean known(){
		return known;
	}
	
	public Object clone()
	{
		return new Pieces(this);
	}

}
