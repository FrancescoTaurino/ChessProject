package Model;

import Pieces.AbstractPiece;
import View.View;

public class ChessboardModel implements Model {
	private Configuration configuration;
	private View view;
	
	public ChessboardModel(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public AbstractPiece at(int x, int y) {
		return configuration.at(x, y);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration newConfiguration) {
		if(this.configuration != newConfiguration)
			this.configuration = newConfiguration;
			if (view != null)
				view.onConfiguration();
	}
	
	public void setView(View listener) {
		this.view = listener;
	}
}