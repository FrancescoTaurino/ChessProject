package Controller;

import java.awt.Point;
import java.util.ArrayList;

import Moves.Mover;
import Pieces.AbstractPiece;
import View.View;

public class ChessboardController implements Controller {
	private final Mover mover;
	private final View view;
	
	public ChessboardController (View view) {
		this.view = view;
		this.mover = new Mover(view.getModel());
		
		view.setController(this);
	}
}