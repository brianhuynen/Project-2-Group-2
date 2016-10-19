package GUILauncher;

//Fire@WILL! game logic and init()of board;
import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import GameBoard.Board;
import GameBoard.Piece;
import GameBoard.Position;
import GameBoard.TurnState;
import Player.Player;

//Should contain all game logic
public class Game implements Runnable {

	public int maxPlayers;
	private Player[] players;
	public Board board;
	boolean flagCaptured = false;

	public Game(Player[] players, Board board) {

		this.players = players;
		this.board = board;
		maxPlayers = 2;

		init();

		System.out.println("\nThe placed pieces are: \n");
		 listPieces();
	}

	// TODO Setup pieces according to graphics
	public void init() {
		try {
			board.resetBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		boolean setup = false;
//
//		while (!setup) {
//			while (!players[currentPlayer].done()) {
//				Scanner s = new Scanner(System.in);
//				System.out.println(players[currentPlayer].getName()
//						+ ", set your piece (Rank xCoord yCoord):");
//				int r = s.nextInt();
//				int x = s.nextInt();
//				int y = s.nextInt();
//				players[currentPlayer].add(
//						new Piece(players[currentPlayer], r),
//						new Position(x, y));
//				s.close();
//			}
//			if (currentPlayer == maxPlayers - 1)
//				setup = true;
//			else
//				currentPlayer++;
//		}
	}

	public void listPieces() {
		for (int i = 0; i < players.length; i++) {
			//System.out.println(players[i].listPieces());
		}
	}

	public void setTurn() {

	}

	public void run() { /* Game logic */
		for (int i = 0; i < players.length; i++) {
			setPieces();
		}
   while (!flagCaptured){
	 //  handleTurns();
   }
   

		ArrayList<TurnState> turnStates = new ArrayList<TurnState>();

		TurnState t = new TurnState();
		
		t.setBoard(board);

		board.setTurnState(t);

		System.out.println("move done");
		turnStates.add(t);

	}

	private void setPieces() {

	}

	private void handleTurns() {
		for (int i = 0; i < players.length; i++) {
			players[i].isTurn();
			while (players[i].getTurn()) {
				board.setCurrentPlayer(players[i]);
				System.out.println(players[i].getName() + "'s turn");
				board.handleMoves();
				}
				players[i].setTurn(false);

			}

	
	}

}
