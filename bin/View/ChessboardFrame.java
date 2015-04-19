package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import Controller.ChessboardController;
import Controller.Controller;
import Model.ChessboardConfiguration;
import Model.ChessboardModel;import Model.Configuration;

public class ChessboardFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final ChessboardModel model = new ChessboardModel(new ChessboardConfiguration());
	
	public ChessboardFrame() {
		ChessboardPanel panel = new ChessboardPanel(model, this);
		add(panel, BorderLayout.CENTER);

		Controller controller = new ChessboardController(panel);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 3));
		
		JButton hint = new JButton("Hints Enabled");
		hint.setFont(new Font("title", Font.ITALIC, 20));
		hint.setContentAreaFilled(false);
		hint.setFocusPainted(false);
		hint.setToolTipText("Game illuminates of red all the possible moves of a clicked piece");
		hint.addActionListener(event -> {
			Configuration c = model.getConfiguration();
			if(c.getHint() == true) {
				c.setHint(false);
				hint.setText("Hints Disabled");
				hint.setToolTipText("Game illuminates of gray only the clicked piece");
			}
			else if(c.getHint() == false) {
				c.setHint(true);
				hint.setText("Hints Enabled");
				hint.setToolTipText("Game illuminates of red all the possible moves of a clicked piece");
			}
		});
		
		JButton newGame = new JButton("New Game");
		newGame.setFont(new Font("title", Font.ITALIC, 20));
		newGame.setContentAreaFilled(false);
		newGame.setFocusPainted(false);
		newGame.addActionListener(event -> {
			controller.newGame();
			hint.setText("Hints Enabled");
			hint.setToolTipText("Game illuminates of red all the possible moves of a clicked piece");
		});
		
		JButton quit = new JButton("Quit");
		quit.setFont(new Font("title", Font.ITALIC, 20));
		quit.setContentAreaFilled(false);
		quit.setFocusPainted(false);
		quit.addActionListener(event -> {
			System.exit(0);
		});
		
		northPanel.add(newGame);
		northPanel.add(hint);
		northPanel.add(quit);
		add(northPanel, BorderLayout.NORTH);

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