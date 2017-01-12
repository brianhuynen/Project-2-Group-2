public class Field {
	Cell [][] field;
	
	//Creates the empty field, with impassable cells;
	public Field(){
		Cell field[][] = new Cell[12][12];
		this.field = field;
		for(int i =0; i<12; i++){
			for(int j=0; j<12; j++){
				if((i==0)||(j==0)||(i==11)||(j==11)||
					(i==3 && j==5) || (i==4 && j==5)||(i==4 && j==6)
					|| (i==3 && j==6)||(i==7 && j==5) || (i==8&& j==5)
					||(i==8 && j==6)|| (i==7 && j==6)){
					Cell cell = new Cell(null, -1);
					field[i][j]= cell;
					cell.setPosition(i, j);
				}
				else{
					Cell cell = new Cell(null,0);
					field[i][j]= cell;
				}
			}
		}
	}
	
	public Cell[][] getBoard(){
		return field;
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
