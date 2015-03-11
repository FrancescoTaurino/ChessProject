package Controller;

import java.awt.Point;
import java.util.ArrayList;

public interface Controller {
	void light(int x, int y);
	void move(int x, int y, ArrayList<Point> list);
}
