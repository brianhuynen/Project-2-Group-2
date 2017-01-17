import java.util.ArrayList;
import java.util.Stack;

public class Pathfinding 
{
	Cell[][] board;
	int playerID;
	
	public Pathfinding(Cell[][] board, int playerID)
	{
		this.board = board;
		this.playerID = playerID;
	}
	
	public ArrayList<Cell> aStar(Cell start, Cell goal)
	{
		ArrayList<Cell> path = lookPath(start, goal);
		System.out.println("lookPath finished working start = " + start.getPosition()[0] + start.getPosition()[1]
				+ " goal = " + goal.getPosition()[0] + goal.getPosition()[1]);
		for(int i = 0; i<path.size(); i++)
		{
			System.out.println("new coords = " + path.get(i).getPosition()[0] + path.get(i).getPosition()[1]);
		}
		
		return path;
	}
	
	/**
	 * the main part of the A* algorithm
	 * @param start starting cell
	 * @param goal goal cell
	 * @return a list ??????
	 */
	public ArrayList<Cell> lookPath(Cell start, Cell goal)
	{
		//open set = nodes to be explored
		//closed set = nodes already explored
		ArrayList<ANode> closedSet = new ArrayList<ANode>();
		ArrayList<ANode> openSet = new ArrayList<ANode>();
		//setting the costs of the starting cell and adding it to the open set
		start.setGcost(0);
		start.setHcost(calculateHcost(start.getPosition(), goal.getPosition()));
		start.setFcost();
		ANode first = new ANode(start);
		openSet.add(first);
		System.out.println("added " + first.getContent().getPosition()[0] + first.getContent().getPosition()[1]);
		ArrayList<Cell> path = null;
	
		mainLoop: while( !openSet.isEmpty() )
		{
			Cell current = null;
			ANode node = new ANode(current);
			for ( int i = 0; i < openSet.size(); i++ )
			{
				if(node.getContent() == null)
				{
					node = openSet.get(i);
					openSet.remove(i);
				}
				else if(openSet.get(i).getContent().getFcost() < node.getContent().getFcost())
				{
					openSet.add(node);
					node = openSet.get(i);
					openSet.remove(i);
				}
			}
			
			
			ArrayList<ANode> successors = generateSuccessors(node, goal.getPosition());
			
			outerLoop: for( int i = 0; i < successors.size(); i++ )
			{
				System.out.println("for loop for going through successors started iteration = " + i);
				if(successors.get(i).getContent() == goal)
				{
					System.out.println("found goal in successors");
					System.out.println("start = " + start.getPosition()[0] + start.getPosition()[1]);
					path = recreatePath(successors.get(i), start);
					break mainLoop;
				}
				successors.get(i).getContent().setGcost(node.getContent().getGcost()+1);
				successors.get(i).getContent().setHcost(calculateHcost(successors.get(i).getContent().getPosition(), goal.getPosition()));
				successors.get(i).getContent().setFcost();
				System.out.println("set f costs of successor = " + i + " fcost = " + successors.get(i).getContent().getFcost()
						+ " coordinates = " + successors.get(i).getContent().getPosition()[0] + 
						successors.get(i).getContent().getPosition()[1]);
				
				for( int j = 0; j<openSet.size(); j++ )
				{
					if(openSet.get(j).getContent().getPosition() == successors.get(i).getContent().getPosition()
							&& openSet.get(j).getContent().getFcost() < successors.get(i).getContent().getFcost())
					{
						continue outerLoop;
					}
				}
				for ( int j = 0; j<closedSet.size(); j++ )
				{
					if(closedSet.get(j).getContent().getPosition() == successors.get(i).getContent().getPosition()
							&& closedSet.get(j).getContent().getFcost() < successors.get(i).getContent().getFcost())
					{
						continue outerLoop;
					}
				}
				openSet.add(successors.get(i));
				System.out.println("forloop ending iteration = " + i);
			}
			
			closedSet.add(node);
			System.out.println("added explored node to closed set");
		}
		return path;
	}
	
	public ArrayList<Cell> recreatePath(ANode last, Cell first)
	{
		System.out.println("recreatepath start = " + first.getPosition()[0] + first.getPosition()[1]);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(last.getContent());
		boolean done = false;
		ANode node = last.getParent();
		System.out.println("last coords = " + last.getContent().getPosition()[0] + last.getContent().getPosition()[1]);
		while(!done)
		{
			if(node.getContent() == first)
			{
				path.add(first);
				done = true;
			}
			else if(node.getContent() != first)
			{
				path.add(node.getContent());
				System.out.println("node coords = " + node.getContent().getPosition()[0] + node.getContent().getPosition()[1]);
				ANode parent = node.getParent();
				node = parent;
			}
			
		}
		return path;
	}
	
	/**
	 * finding the cells around a chosen cell for the four different directions
	 * after checking if the cell is occupied or not (so we have only the possible moves)
	 * @param node the chosen cell
	 * @return a list of the nodes
	 */
	public ArrayList<ANode> generateSuccessors(ANode node, int[] goal)
	{
		ArrayList<ANode> list = new ArrayList<ANode>();
		int[] pos = node.getContent().getPosition();
		//up
		if(pos[0]-1 > 1 && board[pos[0]-1][pos[1]].getContent() == null )
		{
			ANode child = new ANode(board[pos[0]-1][pos[1]]);
			child.setParent(node);
			list.add(child);
		}
		//right
		if(pos[1]+1 < 11 && board[pos[0]][pos[1]+1].getContent() == null)
		{
			ANode child = new ANode(board[pos[0]][pos[1]+1]);
			child.setParent(node);
			list.add(child);
		}
		//down
		if(pos[0]+1 < 11 && board[pos[0]+1][pos[1]].getContent() == null)
		{
			ANode child = new ANode(board[pos[0]+1][pos[1]]);
			child.setParent(node);
			list.add(child);
		}
		//left
		if(pos[1]-1 > 1 && board[pos[0]][pos[1]-1].getContent() == null)
		{
			ANode child = new ANode(board[pos[0]][pos[1]-1]);
			child.setParent(node);
			list.add(child);
		}
		
		return list;
	}
	
	/**
	 * calculating the estimated cost used in a star;
	 * currently using manhattan distance with a scaler of 1
	 */
	public int calculateHcost(int[] start, int[] goal)
	{
		int d = 1;
		int dx = Math.abs(start[0] - goal[0]);
		int dy = Math.abs(start[1] - goal[1]);
		return d * ( dx + dy );
	}
	
}