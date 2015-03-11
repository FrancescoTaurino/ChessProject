package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Controller.ChessboardController;
import Model.ChessboardConfiguration;
import Model.ChessboardModel;

public class ChessboardFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final ChessboardModel model = new ChessboardModel(new ChessboardConfiguration());
	
	public ChessboardFrame() {
		ChessboardPanel panel = new ChessboardPanel(model, this);
		add(panel, BorderLayout.CENTER);
		
		new ChessboardController(panel);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (getPreferredSize().getWidth() / 2);
		int panelHeight = (int) (getPreferredSize().getHeight() / 2);
		setLocation(dim.width/2 - panelWidth - 20, dim.height/2 - panelHeight - 20);
		
		setResizable(false);
		setIconImage(new ImageIcon("src/Images/Icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	}
}