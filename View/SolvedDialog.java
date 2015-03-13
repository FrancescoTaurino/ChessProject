package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;

public class SolvedDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public SolvedDialog(JFrame frame, Controller controller) {
		super(frame, "Game Solved", true);
		
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JLabel finish = new JLabel(controller.checkMate() > 0 ? "CHECKMATE, WHITE WINS!" : "CHECKMATE, BLACK WINS!");
		finish.setFont(new Font("Courier New", Font.CENTER_BASELINE, 30));
		finish.setHorizontalAlignment(JLabel.CENTER);
		northPanel.add(finish);
		add(northPanel, BorderLayout.NORTH);

		JButton newGame = new JButton("NEW GAME");
		newGame.setFocusPainted(false);
		newGame.addActionListener(event -> {
			controller.newGame();
			setVisible(false);
		});
		
		JButton quit = new JButton("QUIT");
		quit.setFocusPainted(false);
		quit.addActionListener(event -> {
			System.exit(0);
		});

		southPanel.add(newGame);
		southPanel.add(quit);
		add(southPanel, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(420, 100));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (getPreferredSize().getWidth() / 2);
		int panelHeight = (int) (getPreferredSize().getHeight() / 2);
		setLocation(dim.width/2 - panelWidth, dim.height/2 - panelHeight);
		
		setResizable(false);
		setUndecorated(true);

		pack();
	}
}