package astar;
import main.Cell;

public class ANode 
{
	private Cell cell;
	private ANode parent;
	
	public ANode (Cell cell)
	{
		
		this.cell = cell;
		parent = null;
	}
	
	public void setParent(ANode parent)
	{
		this.parent = parent;
	}
	
	public ANode getParent()
	{
		return parent;
	}

	public Cell getContent()
	{
		return cell;
	}
}
