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
		
		JLabel finish = new JLabel(controller.isSolved() > 0 ? "BLACK WINS!" : "WHITE WINS!");
		finish.setFont(new Font("Courier New", Font.CENTER_BASELINE, 30));
		finish.setHorizontalAlignment(JLabel.CENTER);
		northPanel.add(finish);
		add(northPanel, BorderLayout.NORTH);

		JButton newGame = new JButton("NEW GAME");
		newGame.addActionListener(event -> {
			controller.newGame();
			setVisible(false);
		});
		
		JButton close = new JButton("EXIT");
		close.addActionListener(event -> {
			System.exit(0);
		});

		southPanel.add(newGame);
		southPanel.add(close);
		add(southPanel, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(300, 100));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - 150, dim.height/2 - 50);
		
		setResizable(false);
		setUndecorated(true);

		pack();
	}
}