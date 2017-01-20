package main;

public class Move2 
{
	public int[] from;
	public int[] to;
	
	public Move2(int x1, int y1, int x2, int y2)
	{
		from = new int[2];
		to = new int[2];
		from[0] = x1;
		from[1] = y1;
		to[0] = x2;
		to[1] = y2;
	}
	
	public void setFrom(int x, int y)
	{
		from[0] = x;
		from[1] = y;
	}
	
	public void setTo(int x, int y)
	{
		to[0] = x;
		to[1] = y;
	}

}
