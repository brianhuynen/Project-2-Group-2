package main;

public class Cell {

	private Pieces content;
	//1 = occupied, 0 = free, -1 = unusable
	private int cellState;
	private int[] position;
	private int gcost;
	private int hcost;
	private int fcost;
	
	public Cell(Pieces content, int cellState){
		this.content=content;	
		this.cellState = cellState;
		//System.out.println("original cell state " + cellState );
		position = new int[2];
		fcost = Integer.MAX_VALUE;
	}
	
	public Cell(Cell toClone)
	{
		if (toClone.content == null)
			this.content = null;
		else
			this.content = new Pieces(toClone.content);
		this.cellState = toClone.cellState;
		//System.out.println("toclone cell state = " + toClone.cellState );
		this.position = toClone.position.clone();
		this.gcost = toClone.gcost;
		this.hcost = toClone.hcost;
		this.fcost = toClone.fcost;
	}
	
	public void setPosition(int x, int y)
	{
		position[0] = x;
		position[1] = y;
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
	public void setFcost()
	{
		fcost = hcost + gcost;
	}
	
	public int[] getPosition()
	{
		return position;
	}
	
	public Object clone()
	{
		return new Cell(this);
	}

}
