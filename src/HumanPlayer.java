
public class HumanPlayer extends Player{
		
	private int[] piecesData = new int[12];
	private int index = 0;
	private Piece[] pieces = new Piece[40];
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
			} else {
				System.out.println("You cannot place any more pieces!");
			}
		}
	}
	//removes the last placed piece
	public void remove(){
		if(index > 0){
			piecesData[pieces[index].getRank()]++;
			pieces[index] = null;
			index--;
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
}
