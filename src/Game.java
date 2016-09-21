
public class Game {

public static void main(String[] args) {
	Field field = new Field(3,4,6,4);
	int [] coord = {0,0};
	Pieces a = new Pieces(2,"horse",coord);
	field.addPiece(coord,a);
	int [] newCoord = {0,1};
	field.printField();
	System.out.println();
	field.movePiece(coord,newCoord);
	field.printField();
	Pieces b = new Pieces(3,"this",coord);
	field.addPiece(coord, b);
	System.out.println();
	field.printField();
	field.movePiece(coord, newCoord);
	System.out.println();
	field.printField();
	
	
	
	
}
	
}
