import java.util.ArrayList;

public class Pathfinding 
{
	Cell[][] board;
	int playerID;
	
	public Pathfinding(Cell[][] board, int playerID)
	{
		this.board = board;
		this.playerID = playerID;
	}
	
	/**
	 * the main part of the A* algorithm
	 * @param start starting cell
	 * @param goal goal cell
	 * @return a list ??????
	 */
	public ArrayList<Cell> aStar(Cell start, Cell goal)
	{
		
		ArrayList<Cell> closedSet = new ArrayList<Cell>();
		ArrayList<Cell> openSet = new ArrayList<Cell>();
		start.setGcost(0);
		start.setHcost(calculateHcost(start.getPosition(), goal.getPosition()));
		start.setFcost();
		openSet.add(start);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(start);
		
		while( !openSet.isEmpty() )
		{
			Cell node = null;
			for ( int i = 0; i < openSet.size(); i++ )
			{
				if(node == null)
				{
					node = openSet.get(i);
					openSet.remove(i);
				}
				else if(openSet.get(i).getFcost() < node.getFcost())
				{
					openSet.add(node);
					node = openSet.get(i);
					openSet.remove(i);
				}
			}
			
			ArrayList<Cell> successors = generateSuccessors(node, goal.getPosition());
			
			outerLoop: for( int i = 0; i < successors.size(); i++ )
			{
				if(successors.get(i) == goal)
				{
					path.add(successors.get(i));
					return path;
				}
				successors.get(i).setGcost(node.getGcost()+1);
				successors.get(i).setHcost(calculateHcost(successors.get(i).getPosition(), goal.getPosition()));
				successors.get(i).setFcost();
				
				for( int j = 0; j<openSet.size(); j++ )
				{
					if(openSet.get(j).getPosition() == successors.get(i).getPosition()
							&& openSet.get(j).getFcost() < successors.get(i).getFcost())
					{
						continue outerLoop;
					}
				}
				for ( int j = 0; j<closedSet.size(); j++ )
				{
					if(closedSet.get(j).getPosition() == successors.get(i).getPosition()
							&& closedSet.get(j).getFcost() < successors.get(i).getFcost())
					{
						continue outerLoop;
					}
				}
				openSet.add(successors.get(i));
			}
			closedSet.add(node);
		}
		
		return null;
	}
	
	/**
	 * finding the cells around a chosen cell for the four different directions
	 * after checking if the cell is occupied or not (so we have only the possible moves)
	 * @param node the chosen cell
	 * @return a list of the nodes
	 */
	public ArrayList<Cell> generateSuccessors(Cell node, int[] goal)
	{
		ArrayList<Cell> list = new ArrayList<Cell>();
		int[] pos = node.getPosition();
		
		//up
		if(board[pos[0]-1][pos[1]].getContent() == null)
		{
			list.add(board[pos[0]-1][pos[1]]);
		}
		//right
		if(board[pos[0]][pos[1]+1].getContent() == null)
		{
			list.add(board[pos[0]][pos[1]+1]);
		}
		//down
		if(board[pos[0]+1][pos[1]].getContent() == null)
		{
			list.add(board[pos[0]+1][pos[1]]);
		}
		//left
		if(board[pos[0]][pos[1]-1].getContent() == null)
		{
			list.add(board[pos[0]][pos[1]-1]);
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