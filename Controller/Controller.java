package Controller;

import java.awt.Point;
import java.util.ArrayList;

public interface Controller {
	//Check if coordinates x y clicked are valid, if valid then light and move
	boolean checkClickConditions(int x, int y);

	//Menage piece moves
	void light(int x, int y);
	void move(int x, int y, ArrayList<Point> list);
}
