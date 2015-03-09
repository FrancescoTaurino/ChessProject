package View;

import Controller.Controller;
import Model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);
	
	void showSolvedDialog();
	
	void onConfiguration();
}
