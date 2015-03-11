package Model;

import Pieces.AbstractPiece;

public interface Configuration {
	//Return the piece in x y
	AbstractPiece at(int x, int y);
	
	//Return the configuration after a piece movement
	Configuration swap(int fromX, int fromY, int toX, int toY);
	
	//Set a piece in x y position
	public void set(int x, int y, AbstractPiece p);
	
	//Menage white or black turn
	public void setTurn(boolean turn);
	public boolean getTurn();
}