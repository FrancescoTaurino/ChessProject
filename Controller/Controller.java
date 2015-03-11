package Controller;

import java.awt.Point;
import java.util.ArrayList;

public interface Controller {
	//Checks if coordinates x y clicked are valid, if valid then light and move
	boolean checkClickConditions(int x, int y);

	//Menages piece moves
	void light(int x, int y);
	void move(int x, int y, ArrayList<Point> list);
	
	//Starts a new game
	void newGame();
	
	//Checks if game is solved: int > 0 Black wins, int < 0 White wins
	int isSolved();
}
