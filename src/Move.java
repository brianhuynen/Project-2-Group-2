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
		Pieces opponent = board[newCoords[0]][newCoords[1]].getContent();
		
		System.out.println("Battle between rank " + piece.getRank() + "(" + piece.getPlayer_ID() + ") and rank " + opponent.getRank() + "(" + opponent.getPlayer_ID() + ")");
		
		if(opponent.getRank()==0){
			System.out.println(piece.getPlayer_ID() + "wins the game.");;
		}
		if((piece.getRank() != 1 && opponent.getRank() != 10) || 
			(piece.getRank()!=3 && opponent.getRank()!= 11)) {
			//attack wins
			if (piece.getRank() > opponent.getRank()) {
				/*if (!piece.known) {
					makeKnown(attack);
				}*/
				/*if (currentPlayer == player_1) {
					if (defense.known) {
						unknow(defense);
					}
					player_1.offBoard += defense.getRank();
				} else {
					player_2.offBoard += defense.getRank();
				}*/
				System.out.println("Win");
			}

			//draw
			if (piece.getRank() == opponent.getRank()) {
				/*if (currentPlayer == player_1) {
					player_1.offBoard += defense.getRank();
					player_2.offBoard += attack.getRank();
				} else {
					player_1.offBoard += attack.getRank();
					player_2.offBoard += defense.getRank();
				}
				removePiece(x1, y1);
				removePiece(x2, y2);
				if (attack.known) {
					unknow(attack);
				}
				if (defense.known) {
					unknow(defense);
				}*/
				System.out.println("Draw");
			}
			//defense wins
			else {
				/*won = defense;
				lost = attack;
				if (!defense.known) {
					makeKnown(defense);
				}
				if (attack.known) {
					unknow(attack);
				}
				if (currentPlayer == player_1) {
					player_2.offBoard += attack.getRank();
				} else {
					player_1.offBoard += attack.getRank();
				}

				removePiece(x1, y1);*/
				System.out.println("Loss");
			}

		}
	}
	
		
}

