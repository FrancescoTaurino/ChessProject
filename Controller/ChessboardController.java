package Controller;

import java.awt.Point;
import java.util.ArrayList;

import Model.Configuration;
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
		if(promotion() != 0)
			view.showPromotionDialog();
		
		mover.check();
		if(checkMate() != 0)
			view.showSolvedDialog();
		
		Configuration configuration = view.getModel().getConfiguration();
		configuration.setTurn(configuration.getFlagTurn() == true ? Color.WHITE : Color.BLACK);
	}

	public boolean checkClickConditions(int x, int y) {
		return mover.checkClickConditions(x, y);
	}

	public int checkMate() {
		return mover.checkMate();
	}
	
	public int promotion() {
		return mover.promotion();
	}
	
	public void newGame() {
		mover.newGame();
	}

	public void promotePawn(AbstractPiece piece) {
		mover.promotePawn(piece);
	}
}