package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

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

		setResizable(false);
		setIconImage(new ImageIcon("src/Images/Icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	}
}