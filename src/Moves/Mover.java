package Moves;

import java.awt.Point;
import java.util.ArrayList;

import Model.Model;
import Pieces.AbstractPiece;

public class Mover {
	private final Model model;
	
	public Mover(Model model) {
		this.model = model;
	}

	public void light(int x, int y) {
		model.setConfigurationLight(new Rules(model.getConfiguration()).light(x, y));
	}

	public void move(int x, int y, ArrayList<Point> list) {
		model.setConfiguration(new Rules(model.getConfiguration()).move(x, y, list));
	}
	
	public void newGame() {
		model.setConfiguration(new Rules(model.getConfiguration()).newGame());
	}
	
	public void promotePawn(AbstractPiece piece) {
		model.setConfiguration(new Rules(model.getConfiguration()).promotePawn(piece));	
	}

	public boolean checkClickConditions(int x, int y) {
		return new Rules(model.getConfiguration()).checkClickConditions(x, y);
	}
	
	public int promotion() {
		return new Rules(model.getConfiguration()).promotion();
	}
	
	public void check() {
		new Rules(model.getConfiguration()).check();
		model.setConfigurationCheck();
	}

	public int checkMate() {
		return new Rules(model.getConfiguration()).checkMate();
	}
	
	public int staleMate() {
		return new Rules(model.getConfiguration()).staleMate();
	}
}