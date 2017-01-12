
public class Cell {

	private Pieces content;
	//1 = occupied, 0 = free, -1 = unusable
	private int cellState;
	private int gcost;
	private int hcost;
	private int fcost;
	
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
	
	public int getGcost()
	{
		return gcost;
	}
	
	public int getHcost()
	{
		return hcost;
	}

	public int getFcost()
	{
		return fcost;
	}
	
	public void setGcost(int gcost)
	{
		this.gcost = gcost;
	}

	public void setHcost(int hcost)
	{
		this.hcost = hcost;
	}
	public void setFcost(int fcost)
	{
		this.fcost = fcost;
	}

}
