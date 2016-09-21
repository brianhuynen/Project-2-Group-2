
public class Pieces {
	
	int rank;
	String name;
	Pieces piece;
	int [] coordinates;
	
	public Pieces(int rank, String name, int [] coordinates){
		this.rank = rank;
		this.name = name;
		this.coordinates = coordinates;
	}
	
	public void setCoordinates(int [] coord){
		coordinates = coord;
	}
	
	public int[] getCoordinates(){
		return coordinates;
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
	
	public Pieces win(Pieces defenseP){
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
