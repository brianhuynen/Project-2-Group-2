package GameBoard;
//fire@WILL!

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import Cell.CardCell;
import Cell.Cell;
import Cell.EmptyCell;
import Cell.ImpassableCell;
import Cell.OccupiedCell;
import Player.HumanPlayer;
import Player.Player;

// TODO Change the board such that it just accepts an array of pieces instead of two arrays (as it is right now).
// Make sure to separate the game logic from the Piece and Board class and put it in the Game class.
/** TODO switch around board graphics at each turn */
public class Board {

	Player currentPlayer;

	boolean cellSelected = false;

	private Cell[][] grid = new Cell[12][12];
	// private Display graphics;

	final int[][] boardData = { { -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ -1, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0, -1 }, { -1, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0, -1 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2 } };

	public Board() {

		resetBoard();

	}

	// review place pieces with buttons
	public void placePlayerPieces(Piece[] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			placePiece(pieces[i].getPosition(), pieces[i]);
		}

	}

	public void placePiece(Position p, Piece piece) {
		if (grid[p.getX()][p.getY()] instanceof EmptyCell) {
			OccupiedCell newState = new OccupiedCell(piece);
			grid[p.getX()][p.getY()] = newState;
			// graphics.update();
		}

	}

	public void removePiece(Position p) {
		EmptyCell newState = new EmptyCell();
		grid[p.getX()][p.getY()] = newState;
		// graphics.update();
	}

	public Player getPID(int x, int y) {
		if (grid[x][y] instanceof OccupiedCell) {

			return grid[x][y].getContent().getPID();
		}
		return null;
	}

	public void movePiece(Position p, Position newP) {
		if (validMove(p, newP)) {
			Piece piece = grid[p.getX()][p.getY()].getContent();
			removePiece(p);
			OccupiedCell newState = new OccupiedCell(piece);
			grid[newP.getX()][newP.getY()] = newState;
			// graphics.update();
		}
	}

	public boolean validMove(Position p, Position newP) {
		if (grid[newP.getX()][newP.getY()] instanceof ImpassableCell
				|| (newP.getX() != p.getX() && newP.getY() != p.getY())) {
			return false;
		}

		else {
			Piece piece = grid[p.getX()][p.getY()].getContent();
			int spaces;
			if (p.getX() == newP.getX()) {
				spaces = Math.abs(p.getY() - newP.getY());
			} else {
				spaces = Math.abs(p.getX() - newP.getX());
			}
			if (!piece.validWalk(spaces)) {
				return false;
			}

			if (grid[newP.getX()][newP.getY()] instanceof EmptyCell) {
				return true;
			}

			else if (grid[newP.getX()][newP.getY()] instanceof OccupiedCell
					&& getPID(p.getX(), p.getY()) != getPID(newP.getX(), newP.getY())) {

				Piece defensePiece = grid[newP.getX()][newP.getY()].getContent();
				if (piece.win(defensePiece) != piece && piece.win(defensePiece) != defensePiece) {
					removePiece(piece.getPosition());
					removePiece(defensePiece.getPosition());
					return false;
				} else {

					if (piece.win(defensePiece) == piece) {
						return true;
					} else {
						removePiece(p);
						return false;
					}
				}
			}

			return false;
		}

	}

	public void resetBoard() {

		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[0].length; j++) {
				// grid[i][j].setOpaque(true);
				// grid[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
				Position pos = new Position(i, j);
				if (boardData[i][j] == -1) {
					grid[i][j] = new ImpassableCell();
					grid[i][j].setPosition(pos);
				} else if (boardData[i][j] == 0) {
					grid[i][j] = new EmptyCell();
					grid[i][j].setPosition(pos);
				}

				// else if(boardData[i][j] == -2)
				// {
				// createPlayerCards(currentPlayer);
				// }

			}
		}

	}

	public void setCurrentPlayer(Player player) {

		this.currentPlayer = player;
	}

	// pieces info to create cards row
	public void createPlayerCards(Player player) {

		int sideP1 = grid.length - 1;
		System.out.println(grid.length - 1);
		int sideP2 = 0;

		for (int i = 0; i < grid.length; i++) {

			int rank = i;

			if (player.getID() == 1) {
				player.setColor(Color.BLUE);
				grid[sideP1][i] = new CardCell(player, i);
				Position pos = new Position(sideP1, i);
				grid[sideP1][i].setPosition(pos);

			}
			if (player.getID() == 2) {
				player.setColor(Color.RED);
				grid[sideP2][i] = new CardCell(player, i);
				Position pos = new Position(sideP2, i);
				grid[sideP2][i].setPosition(pos);

			}

		}

	}

	public Piece getContent(Position p) {
		if (grid[p.getX()][p.getY()] instanceof OccupiedCell) {
			return grid[p.getX()][p.getY()].getContent();
		} else
			return null;
	}

	public Cell[][] getGrid() {

		return grid;
	}

	public void printBoard() {
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[0].length; j++) {
				if (grid[i][j] instanceof ImpassableCell) {

					// grid[i][j].setBackground(Color.black);

					System.out.print("[X]");
				} else if (grid[i][j] instanceof EmptyCell) {
					// System.out.print("E ");
					System.out.print("[ ]");
					grid[i][j].setBackground(Color.WHITE);
				} else if (grid[i][j] instanceof OccupiedCell) {
					Piece p = ((OccupiedCell) grid[i][j]).getContent();
					grid[i][j].setIcon(p.drawPiece(currentPlayer));
					if (0 < p.getRank() && p.getRank() < 10)
						System.out.print(p.getRank() + "[ ]");
					else if (p.getRank() == 0)
						System.out.print("[F]");
					else if (p.getRank() == 10)
						System.out.print("[M]");
					else if (p.getRank() == 11)
						System.out.print("[B]");
				} else if (grid[i][j] instanceof CardCell) {

					Piece p = grid[i][j].getContent();
					CardCell holder = new CardCell(currentPlayer, p.getRank());

					grid[i][j].setIcon(p.drawPiece(currentPlayer));
					if (0 < p.getRank() && p.getRank() < 10)
						System.out.print("[" + p.getRank() + "]" + "(" + ((CardCell) grid[i][j]).getNumP() + ")]");
					else if (p.getRank() == 0)
						System.out.print("[F]");

					else if (p.getRank() == 10)
						System.out.print("[M]");

					else if (p.getRank() == 11)
						System.out.print("[B]");
				}
			}
			System.out.println();
		}
	}

	public int getSize() {

		return grid.length;
	}

	public void setTurnState(TurnState t) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {

				grid[i][j].setTurnState(t);
			}
		}
	}

	public void handleMoves() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j].isEmpty() == false) {

					// if(currentPlayer.getID()==
					// grid[i][j].getContent().getPID()){

				}
			}
		}
	}

	public boolean boardEmpty() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] instanceof OccupiedCell) {
					return false;
				}
			}
		}
		return true;
	}
}

// }
