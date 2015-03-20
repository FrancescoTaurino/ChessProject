package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
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
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		
		JLabel cornice = new JLabel(new ImageIcon("src/Images/Cornice.png"));
		cornice.setBounds(0, 0, 420, 100);
		
		JLabel text = new JLabel();
		if(controller.checkMate() != 0) 
			text.setText(controller.checkMate() > 0 ? "CHECKMATE, WHITE WINS!" : "CHECKMATE, BLACK WINS!");
		else if(controller.staleMate() != 0)
			text.setText("STALEMATE!");
		text.setFont(new Font("", Font.CENTER_BASELINE, 20));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(0, 10, 420, 30);
		
		JButton look = new JButton("TAKE A LOOK AT CHESSBOARD!");
		look.setFont(new Font("", Font.CENTER_BASELINE, 15));
		look.setContentAreaFilled(false);
		look.setFocusPainted(false);
		look.setBounds(61, 50, 300, 35);
		look.addActionListener(event -> {
			setVisible(false);
		});
		
		setPreferredSize(new Dimension(420, 100));
		menu.add(cornice);
		menu.add(text);
		menu.add(look);
		add(menu);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (getPreferredSize().getWidth() / 2);
		int panelHeight = (int) (getPreferredSize().getHeight() / 2);
		setLocation(dim.width/2 - panelWidth, dim.height/2 - panelHeight);
		
		setResizable(false);
		setUndecorated(true);

		pack();
	}
}