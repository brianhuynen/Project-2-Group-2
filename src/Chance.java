
public class Chance 
{
	public int piece;
	public double prob;
	public int[] coords;

	public Chance(int piece, double prob, int x, int y)
	{
		this.piece = piece;
		this.prob = prob;
		coords = new int[2];
		coords[0] = x;
		coords[1] = y;
	}
	
	public void set(int piece, double prob, int x, int y)
	{
		this.piece = piece;
		this.prob = prob;
		coords[0] = x;
		coords[1] = y;
	}
	
}
