
public class Cell {

	private Pieces content;
	//1 = occupied, 0 = free, -1 = unusable
	private int cellState;
	
	public Cell(Pieces content, int cellState){
		this.content=content;	
		this.cellState = cellState;
	}
	
	public int getCellState(){
		return cellState;
	}
	
	public void setCellState(int state){
		cellState = state;
	}
	
	public void setContent(Pieces piece){
		content = piece;
	}
	
	public Pieces getContent(){
		return content;
	}
	

}
