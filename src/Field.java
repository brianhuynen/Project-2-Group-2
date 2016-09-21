import java.lang.Math;


public class Field {
	
	
	Field board;
	Cell [][] field;
	
	public Field(int x1, int y1, int x2, int y2){
		Cell field[][] = new Cell[10][10];
		this.field = field;
		for(int i =0; i<10; i++){
			for(int j=0; j<10; j++){
				if((i==x1 && j==y1) || (i==x1+1 && j==y1)||(i==x1+1 && j==y1+1)
					|| (i==x1 && j==y1+1)||(i==x2 && j==y2) || (i==x2+1 && j==y2)
					||(i==x2+1 && j==y2+1)|| (i==x2 && j==y2+1)){
					Cell cell = new Cell(null, -1);
					field[i][j]= cell;
				}
				else{
					Cell cell = new Cell(null,0);
					field[i][j]= cell;
				}
			}
		}
	}
	

	public void addPiece(int [] coord, Pieces piece){
		if(field[coord[0]][coord[1]].getCellState()==0){
			field[coord[0]][coord[1]].setContent(piece);
			field[coord[0]][coord[1]].setCellState(1);
		}
	}
	
	public void removePiece(int[] coord){
		field[coord[0]][coord[1]].setCellState(0);
		field[coord[0]][coord[1]].setContent(null);
	}
	
	public void movePiece(int [] coord, int[] newCoord){	
		if(validMove(coord,newCoord)){
			Pieces piece = field[coord[0]][coord[1]].getContent();
			removePiece(coord);
			field[newCoord[0]][newCoord[1]].setCellState(1);
			field[newCoord[0]][newCoord[1]].setContent(piece);
		}
	}
	
	public boolean validMove(int [] coord, int[] newCoord){
		if(field[newCoord[0]][newCoord[1]].getCellState()== -1 ||
				(newCoord[0]!= coord[0] && newCoord[1]!= coord[1])){
			return false;
		}
		
		
		else{
			Pieces piece = field[coord[0]][coord[1]].getContent();
			int spaces;
			if(coord[0] == newCoord[0]){
				spaces = Math.abs(coord[1]-newCoord[1]);
			}
			else{
				spaces = Math.abs(coord[0]-newCoord[0]);				
			}
			
			if(field[newCoord[0]][newCoord[1]].getCellState()==0 && piece.validWalk(spaces)){
				
				return true;
			}
			else if(field[newCoord[0]][newCoord[1]].getCellState()==1 && piece.validWalk(spaces)){
				Pieces defensePiece = field[newCoord[0]][newCoord[1]].getContent();
				if(piece.win(defensePiece) == piece){
					
					return true;
				}
				else{
					removePiece(coord);
					return false;
				}
			}
			
			return false;
		}
	}
 
	//Method prints the board. Prints the rank of the pieces. If a cell is empty or
	// unusable it prints the cell state. (0 or 1), just to test.
	public void printField(){
		for(int i=0; i<10; i++){
			System.out.println();
			for(int j=0; j<10; j++){
				if(field[i][j].getCellState()==1){
					System.out.print(field[i][j].getContent().getRank());
				}
				else{
				int t = field[i][j].getCellState();
				System.out.print(t);
				}
			}
		}
	}
 }
