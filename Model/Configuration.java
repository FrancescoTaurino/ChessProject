package Model;

import java.awt.Point;

import Pieces.AbstractPiece;
import Pieces.Color;

public interface Configuration {
	//Return the piece in x y
	AbstractPiece at(int x, int y);
	
	//Return the configuration after a piece movement
	Configuration swap(int fromX, int fromY, int toX, int toY);
	
	//Set a piece in x y position
	public void set(int x, int y, AbstractPiece p);
	
	//Return color king location
	public Point getKingLocation(Color color);
	
	//Menage white or black turn
	public void setFlagTurn(boolean turn);
	public boolean getFlagTurn();
	public void setTurn(Color turn);
	public Color getTurn();
	
	public void setCheck(int pos, boolean check);
	public boolean getCheck(int pos);
}