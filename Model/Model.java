package Model;

import Pieces.AbstractPiece;
import View.View;

public interface Model {
	//Get state information
	public AbstractPiece at(int x, int y);
	public Configuration getConfiguration();
		
	//Set state changes
	public void setConfiguration(Configuration configuration);
	public void setView(View listener);
}