
public class HumanPlayer extends Player{
	
	private int[] data = {1,0,0,0,0,0,0,0,0,0,0,0}; // For testing purposes, remove later.
	private int[] piecesData = new int[12];
	private int index = 0;
	private Piece[] pieces = new Piece[40]; // Stores the STARTING positions of each piece.
	private String name;
	
	public HumanPlayer(String name){
		this.name = name;
		piecesData = data;
	}
	//adds a piece to the database
	public void add(Piece p, Position pos){
		if(piecesData[p.getRank()] > 0){
			if(index <= pieces.length){
				pieces[index] = p;
				pieces[index].setPosition(pos);
				index++;
				piecesData[p.getRank()]--;
				
				System.out.println(listData());
				
			}
		} else {
			System.out.println("You cannot place any more pieces of that kind!");
			
			System.out.println(listData());
			System.out.println(listPieces());
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

			System.out.println(listData());
			System.out.println(listPieces());
			
		} else {
			System.out.println("There are no placed pieces!");
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
	// Mainly debugging
	private String listData(){
		String s = "";
		
		s += "\nData: [";
		for (int i = 0; i<piecesData.length-1; i++){
			s += piecesData[i] + " ";
		}
		s += piecesData[piecesData.length-1] + "]";
		
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
