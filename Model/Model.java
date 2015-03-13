package Model;

import java.awt.Point;
import java.util.ArrayList;

import Pieces.AbstractPiece;
import View.View;

public interface Model {
	//Get state information
	public AbstractPiece at(int x, int y);
	public Configuration getConfiguration();
		
	//Set state changes
	public void setConfiguration(Configuration configuration);
	public void setConfigurationLight(ArrayList<Point> list);
	public void setConfigurationCheck();
	public void setView(View listener);
}