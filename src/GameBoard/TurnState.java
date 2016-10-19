package GameBoard;

import javax.swing.Icon;

public class TurnState {

	private Board board;
	private Position target, source;
	public boolean moveDone = false;

	public TurnState() {
	}

	public static void movePiece(Cell.Cell button1, Cell.Cell button2) {
		Icon tempButton = button1.getIcon();
		button1.setIcon(button2.getIcon());
		button2.setIcon(tempButton);
		button1.setBackground(null);
		button2.setBackground(null);

		button1.getContent();

		return;
	}

	static int x = 0;
	static Cell.Cell button22 = null;
	static Cell.Cell button11 = null;

	// Determine if it's the first or the second button pressed
	public static void buttonPress(Cell.Cell input) {
		if (x == 0) {
			x = 1;
			System.out.println("first");

		} else {
			button22 = input;
			movePiece(button11, button22);
			x = 0;
			System.out.println("second");

		}
	}

	public void setTarget(Position target) {

	}

	public void setSource(Position source) {
	}

	public Position getTarget() {
		return target;
	}

	public Position getSource() {

		return source;
	}

	public void updateGrid() {

	}

	public void setBoard(Board b) {
		this.board = b;
	}

	public void handle(Position pos) {

		if (source == null) {
			source = pos;
		} else {
			target = pos;

			System.out.println("source " + source.toString());
			System.out.println("target " + target.toString());

			board.movePiece(source, target);
			source = null;
			target = null;
		}

	}
}
