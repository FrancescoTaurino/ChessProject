package View;

import java.awt.Point;
import java.util.ArrayList;

import Controller.Controller;
import Model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);
	
	void showSolvedDialog();
	void showPromotionDialog();
	
	void onConfiguration();
	void onConfigurationLight(ArrayList<Point> list);
	void onConfigurationCheck();
}
