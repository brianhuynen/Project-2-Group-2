import java.util.ArrayList;

public class Move {
	
	public Pieces piece;
	public int[] newCoords;
	
	public Move(Pieces piece)
	{
		this.piece = piece;
	}
	
	public Move(Pieces piece, int[] newCoords)
	{
		this.piece = piece;
		this.newCoords = newCoords;
	}
	
	public void setNewCoords(int[] newCoords)
	{
		this.newCoords = newCoords;
	}
	
	public void printMove()
	{
		System.out.println("origin x = " + piece.position[0] + " y = " + piece.position[1] 
				+ "; destination x = " + newCoords[0] + " y = " + newCoords[1]); 
	}
	
	public void checkBattle(Cell[][] board)
	{
		if (board[newCoords[0]][newCoords[1]].getContent() != null){
		Pieces opponent = board[newCoords[0]][newCoords[1]].getContent();
		
		//System.out.println("Battle between rank " + piece.getRank() + "(" + piece.getPlayer_ID() + ") and rank " + opponent.getRank() + "(" + opponent.getPlayer_ID() + ")");
		
		if(opponent.getRank()==0){
			//System.out.println(piece.getPlayer_ID() + "wins the game.");;
		}
		if((piece.getRank() != 1 && opponent.getRank() != 10) || 
			(piece.getRank()!=3 && opponent.getRank()!= 11)) {
			//attack wins
			if (piece.getRank() > opponent.getRank()) {
				
				System.out.println("WIN");
			}

			//draw
			else if (piece.getRank() == opponent.getRank()) {
				
				System.out.println("DRAW");
			}
			//defense wins
			else {
				
				System.out.println("LOSS");
			}
		}

		}
	}
	
		
}

