
public class HumanPlayer extends Player{
	
	private int[] piecesData = new int[12];
	private int index = 0;
	private Piece[] pieces; // Stores the STARTING positions of each piece.
	private String name;
	
	public HumanPlayer(String name){
		this.name = name;
		piecesData = data;
		pieces = new Piece[dataLength()];
	}
	//adds a piece to the database
	public void add(Piece p, Position pos){
		if(piecesData[p.getRank()] > 0){
			if(index <= pieces.length){
				pieces[index] = p;
				pieces[index].setPosition(pos);
				index++;
				piecesData[p.getRank()]--;				
			}
		} else {
			System.out.println("You cannot add any more pieces of that kind!");
		}
	}
	
	public boolean done(){
		for (int i = 0; i<data.length; i++){
			if (data[i] != 0){
				return false;
			}
		}
		return true;
	}
	
	//removes the last placed piece
	public void remove(){
		if(index > 0){
			piecesData[pieces[index].getRank()]++;
			pieces[index] = null;
			index--;

//			System.out.println(listData());
//			System.out.println(listPieces());
			
		} else {
			System.out.println("No pieces have been added!");
		}
	}
	
	public void move(Piece p, Position to){
		
	}
	
	public String getName(){
		return name;
	}
	public Piece[] getPieces(){
		return pieces;
	}
	
	private int dataLength(){
		int n = 0;
		for (int i = 0; i<data.length; i++){
			n += piecesData[i];
		}
		return n;
	}
	// Mainly debugging
	public String listData(){
		String s = "";
		
		s += "\nData: [";
		for (int i = 0; i<(piecesData.length - 1); i++){
			if(i == 0)
				s += "Rank F (" + piecesData[i] + "), ";
			else if(i == 1)
				s += "Rank S (" + piecesData[i] + "), ";
			else if(i == 10)
				s += "Rank M (" + piecesData[i] + "), ";
			else
				s += "Rank " + (i) + "(" + piecesData[i] + "), ";
		}
		s += "Rank B (" + piecesData[piecesData.length-1] + ")]";
		
		return s;
	}
	public String listPieces(){
		String s = getName() + ": \n";
		for (int i = 0; i<index; i++){
			s += "Piece with rank " + pieces[i].getRank() + " at position (" 
					+ pieces[i].getPosition().getX() + "," + pieces[i].getPosition().getY() + ")\n";
		}
		return s;
	}
}
