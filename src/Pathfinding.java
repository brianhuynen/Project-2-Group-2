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
	
	public void aStar(Cell start, Cell goal)
	{
		Stack<Cell> path = lookPath(start, goal);
		System.out.println("lookPath finished working");
		/*while(!path.isEmpty())
		{
			System.out.println(path.pop().getPosition() + " to "); 
		}*/
	}
	
	/**
	 * the main part of the A* algorithm
	 * @param start starting cell
	 * @param goal goal cell
	 * @return a list ??????
	 */
	public Stack<Cell> lookPath(Cell start, Cell goal)
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
		Stack<Cell> path = null;
	
		mainLoop: while( !openSet.isEmpty() )
		{
			System.out.println("open set is not empty");
			Cell current = null;
			ANode node = new ANode(current);
			for ( int i = 0; i < openSet.size(); i++ )
			{
				//System.out.println("for loop for going through open set started iteration = " + i);
				if(node.getContent() == null)
				{
					node = openSet.get(i);
					System.out.println("set node to be first one in the list");
					openSet.remove(i);
				}
				else if(openSet.get(i).getContent().getFcost() < node.getContent().getFcost())
				{
					openSet.add(node);
					System.out.println("added " + node.getContent().getPosition()[0] + node.getContent().getPosition()[1]);
					node = openSet.get(i);
					System.out.println("set node to be the one in the open set with lowest f");
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
					path = recreatePath(successors.get(i), start);
					break mainLoop;
				}
				successors.get(i).getContent().setGcost(node.getContent().getGcost()+1);
				successors.get(i).getContent().setHcost(calculateHcost(successors.get(i).getContent().getPosition(), goal.getPosition()));
				successors.get(i).getContent().setFcost();
				System.out.println("set f costs of successor = " + i + "fcost = " + successors.get(i).getContent().getFcost());
				
				for( int j = 0; j<openSet.size(); j++ )
				{
					if(openSet.get(j).getContent().getPosition() == successors.get(i).getContent().getPosition()
							&& openSet.get(j).getContent().getFcost() < successors.get(i).getContent().getFcost())
					{
						System.out.println("skipping succesor n = " + j);
						continue outerLoop;
					}
				}
				for ( int j = 0; j<closedSet.size(); j++ )
				{
					if(closedSet.get(j).getContent().getPosition() == successors.get(i).getContent().getPosition()
							&& closedSet.get(j).getContent().getFcost() < successors.get(i).getContent().getFcost())
					{
						System.out.println("skipping succesor n = " + j);
						continue outerLoop;
					}
				}
				openSet.add(successors.get(i));
				System.out.println("added " + successors.get(i).getContent().getPosition()[0] + successors.get(i).getContent().getPosition()[1]);
				System.out.println("forloop ending iteration = " + i);
			}
			
			closedSet.add(node);
			System.out.println("added explored node to closed set");
		}
		return path;
	}
	
	public Stack<Cell> recreatePath(ANode last, Cell first)
	{
		Stack<Cell> path = new Stack<Cell>();
		path.push(last.getContent());
		boolean done = false;
		ANode node = last.getParent();
		while(!done)
		{
			if(node.getContent() != first)
			{
				path.push(node.getContent());
				node = node.getParent();
			}
			else
			{
				done = true;
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
		System.out.println("in successors method");
		//up
		if(pos[0]-1 > 1 && board[pos[0]-1][pos[1]].getContent() == null )
		{
			System.out.println("up");
			ANode child = new ANode(board[pos[0]-1][pos[1]]);
			child.setParent(node);
			list.add(child);
		}
		//right
		if(pos[1]+1 < 11 && board[pos[0]][pos[1]+1].getContent() == null)
		{
			System.out.println("right");
			ANode child = new ANode(board[pos[0]][pos[1]+1]);
			child.setParent(node);
			list.add(child);
		}
		//down
		if(pos[0]+1 < 11 && board[pos[0]+1][pos[1]].getContent() == null)
		{
			System.out.println("down");
			ANode child = new ANode(board[pos[0]+1][pos[1]]);
			child.setParent(node);
			list.add(child);
		}
		//left
		if(pos[1]-1 > 1 && board[pos[0]][pos[1]-1].getContent() == null)
		{
			System.out.println("left");
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
		int h = 0;
		int d = 1;
		int dx = start[0] - goal[0];
		int dy = start[1] - goal[1];
		h = d * ( dx + dy );
		return h;
	}
	
}

/*public void findPath(int x1, int y1, int x2, int y2)
{
	//base cases
	int[] move = new int[2];
	
	if(path.isEmpty())
	{
		move[0]=x1;
		move[1]=y1;
		path.add(move);
	}
	
	if(x1 + 1 == x2 && y1 == y2)
	{
		move[0]=x2;
		move[1]=y2;
		path.add(move);
		//movePiece(x1,y1,x2,y2);
	}
	else if(x1 - 1 == x2 && y1 == y2)
	{
		move[0]=x2;
		move[1]=y2;
		path.add(move);
		//movePiece(x1,y1,x2,y2);
	}
	else if(x1 == x2 && y1 + 1 == y2)
	{
		move[0]=x2;
		move[1]=y2;
		path.add(move);
		//movePiece(x1,y1,x2,y2);
	}
	else if(x1 == x2 && y1 - 1 == y2)
	{
		move[0]=x2;
		move[1]=y2;
		path.add(move);
		//movePiece(x1,y1,x2,y2);
	}
	
	//recursive part, if piece 1 is located above piece 2
	
	/*else if(board[x1][y1+1].getCellState()!=0 && board[x1][y1-1].getCellState()!=0
			&& board[x1+1][y1].getCellState()!=0 && board[x1-1][y1].getCellState()!=0)
	{
		if(y2>y1)
		{
			if( board[x1][y1+2].getCellState() == 0 )
			{
				movePiece(x1,y1+1,x1,y1+2);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
			else if( board[x1+1][y1+1].getCellState() == 0 )
			{
				movePiece(x1,y1+1,x1+1,y1+1);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
			else if( board[x1-1][y1+1].getCellState() == 0 )
			{
				movePiece(x1,y1+1,x1-1,y1+1);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
		}
		if(y2<y1)
		{
			if( board[x1][y1-2].getCellState() == 0 )
			{
				movePiece(x1,y1-1,x1,y1-2);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
			else if( board[x1+1][y1-1].getCellState() == 0 )
			{
				movePiece(x1,y1-1,x1+1,y1-1);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
			else if( board[x1-1][y1-1].getCellState() == 0 )
			{
				movePiece(x1,y1-1,x1-1,y1-1);
				changeTurnH();
				findPath(x1,y1,x2,y2);
			}
		}
	}*/
	
	//checks if the next move is surrounded by three pieces and tries to go around
	//for down
	/*else if(y2>y1 && board[x1][y1+1].getCellState()==0 &&
			board[x1-1][y1+1].getCellState()!=0 && board[x1+1][y1+1].getCellState() != 0
			&& board[x1][y1+2].getCellState() != 0)
	{
		if(x2>x1 && board[x1+1][y1].getCellState() == 0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if(x2<x1 && board[x1-1][y1].getCellState() == 0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
		else if (board[x1+1][y1].getCellState() == 0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if (board[x1-1][y1].getCellState() == 0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
		else if (board[x1][y1-1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
	}
	
	//up
	else if(y2<y1 && board[x1][y1-1].getCellState()==0 &&
			board[x1-1][y1-1].getCellState()!=0 && board[x1+1][y1-1].getCellState() != 0
			&& board[x1][y1-2].getCellState() != 0)
	{
		if(x2>x1 && board[x1+1][y1].getCellState() == 0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if(x2<x1 && board[x1-1][y1].getCellState() == 0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
		else if (board[x1+1][y1].getCellState() == 0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if (board[x1-1][y1].getCellState() == 0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
		else if (board[x1][y1+1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
	}
	
	//right
	else if(x2>x1 && board[x1+1][y1].getCellState()==0 &&
			board[x1+1][y1+1].getCellState()!=0 && board[x1+1][y1-1].getCellState() != 0
			&& board[x1+2][y1].getCellState() != 0)
	{
		if(y2>y1 && board[x1][y1+1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if(y2<y1 && board[x1][y1-1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
		else if (board[x1][y1+1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if (board[x1][y1-1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
		else if (board[x1-1][y1].getCellState() == 0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
	}
	
	//left
	else if(x2<x1 && board[x1-1][y1].getCellState()==0 &&
			board[x1-1][y1+1].getCellState()!=0 && board[x1-1][y1-1].getCellState() != 0
			&& board[x1-2][y1].getCellState() != 0)
	{
		if(y2>y1 && board[x1][y1+1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if(y2<y1 && board[x1][y1-1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
		else if (board[x1][y1+1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if (board[x1][y1-1].getCellState() == 0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
		else if (board[x1+1][y1].getCellState() == 0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
	}
	
	else if(y2>y1 && board[x1][y1+1].getCellState()==0)
	{
		move[0]=x1;
		move[1]=y1+1;
		path.add(move);
		//movePiece(x1,y1,x1,y1+1);
		findPath(x1,y1+1,x2,y2);
	}//if piece 1 is located below piece 2
	else if(y2<y1 && board[x1][y1-1].getCellState()==0)
	{
		move[0]=x1;
		move[1]=y1-1;
		path.add(move);
		//movePiece(x1,y1,x1,y1-1);
		findPath(x1,y1-1,x2,y2);
	}//if piece 1 is located to the left of piece 2
	else if(x2>x1 && board[x1+1][y1].getCellState()==0)
	{
		move[0]=x1+1;
		move[1]=y1;
		path.add(move);
		//movePiece(x1,y1,x1+1,y1);
		findPath(x1+1,y1,x2,y2);
	}//if piece 1 is located to the right of piece 2
	else if(x2<x1 && board[x1-1][y1].getCellState()==0)
	{
		move[0]=x1-1;
		move[1]=y1;
		path.add(move);
		//movePiece(x1,y1,x1-1,y1);
		findPath(x1-1,y1,x2,y2);
	}
	
	//if there are obstacles in its path, move one to the right, if not possible, move to the left.
	else if(y2>y1 && board[x1][y1+1].getCellState()!=0)//block down
	{
		if(board[x1+1][y1].getCellState()==0)//move right
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if(board[x1-1][y1].getCellState()==0)//move left
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
	}
	else if(y2<y1 && board[x1][y1-1].getCellState()!=0)//block up
	{
		if(board[x1+1][y1].getCellState()==0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}
		else if(board[x1-1][y1].getCellState()==0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
	}//if there are obstacles in its path, move one downwards, if not possible, move upwards.
	else if(x2>x1 && board[x1+1][y1].getCellState()!=0)//block right
	{
		if(board[x1][y1+1].getCellState()==0)//move down
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if(board[x1][y1-1].getCellState()==0)//move up
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
	}
	else if(x2<x1 && board[x1-1][y1].getCellState()!=0)//block left
	{
		if(board[x1][y1+1].getCellState()==0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}
		else if(board[x1][y1-1].getCellState()==0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}
	}
}
*/