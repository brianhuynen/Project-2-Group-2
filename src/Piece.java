public class Piece {
	
	private int rank, playerID;
	private Player pID;
	private Position position;
	
	public Piece(int playerID, int rank){
		this.playerID = playerID;
		this.rank = rank;
	}
	
	public Piece(Player pID, int rank){
		this.pID = pID;
		this.rank = rank;
	}
	
	public void setPosition(Position pos){position = pos;}
	public Position getPosition(){return position;}
	public void setRank(int rank){this.rank = rank;}
	public int getRank(){return rank;}
	public int getPlayer(){return playerID;}
	
	public Piece win(Piece defenseP){
		int attack = this.getRank();
		int defense = defenseP.getRank();
		if(defense == 0){
			//endgame();
			return null;
		}
		else{
			if((attack != 1 && defense != 10) || (attack!=3 && defense!= 11)){
							if(attack > defense){
								
								return this;
							}
							else {
								return defenseP;
							}
			}
			else				
				return this;	
		}
	}
	
	public boolean validWalk(int spaces){
		if(getRank()==0 || getRank()==11){
			return false;
		}
		else{
			if(getRank()!=2 && spaces > 1){
					return false;
				}
			
				return true;
		}
	}
}
