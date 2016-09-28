//Should contain all game logic

public class Game implements Runnable{
	
	public Player[] players = new Player[2];
	public Board board;
	
	public Game(Board board, Player[] players){
		this.board = board;
		this.players = players;
		
		init();
	}
	
	public void init(){ /*TODO Setup pieces*/ }
	
	public void run(){ /*Game logic*/ }
}
