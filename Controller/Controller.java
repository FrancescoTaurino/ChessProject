package Controller;

import java.awt.Point;
import java.util.ArrayList;

import Pieces.AbstractPiece;

public interface Controller {
	//Checks if coordinates x y clicked are valid, if valid then light and move
	boolean checkClickConditions(int x, int y);

	//Menages piece moves
	void light(int x, int y);
	void move(int x, int y, ArrayList<Point> list);
	
	//Starts a new game
	void newGame();
	
	//Check checkmate
	public int checkMate();
	
	//Check stalemate
	public int staleMate();
	
	//Menages a pawn promotion
	int promotion();
	void promotePawn(AbstractPiece piece);	
}