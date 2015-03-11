package Controller;

import java.awt.Point;
import java.util.ArrayList;

import Moves.Mover;
import Pieces.AbstractPiece;
import Pieces.Color;
import View.View;

public class ChessboardController implements Controller {
	private final Mover mover;
	private final View view;
	
	public ChessboardController (View view) {
		this.view = view;
		this.mover = new Mover(view.getModel());
		
		view.setController(this);
	}

	public void light(int x, int y) {
		mover.light(x, y);
	}

	public void move(int x, int y, ArrayList<Point> list) {
		mover.move(x, y, list);	
		if(isSolved() != 0)
			view.showSolvedDialog();
		if(promotion() != 0)
			view.showPromotionDialog();
		
		//Change turn
		view.getModel().getConfiguration().setTurn(view.getModel().getConfiguration().getFlagTurn() == false ? Color.BLACK : Color.WHITE);
	}

	public boolean checkClickConditions(int x, int y) {
		return mover.checkClickConditions(x, y);
	}

	public int isSolved() {
		return mover.isSolved();
	}

	public void newGame() {
		mover.newGame();
	}

	public int promotion() {
		return mover.promotion();
	}

	public void promotePawn(AbstractPiece piece) {
		mover.promotePawn(piece);
	}
}