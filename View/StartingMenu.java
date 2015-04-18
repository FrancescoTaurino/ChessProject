package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartingMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public StartingMenu() {
		JPanel menu = new JPanel();
		menu.setLayout(null);
		
		JButton chess = new JButton();
		chess.setContentAreaFilled(false);
		chess.setBorderPainted(false);
		chess.setIcon(new ImageIcon("src/Images/chess.png"));
		chess.setRolloverIcon(new ImageIcon("src/Images/chess2.png"));
		chess.setBounds(120, 20, 300, 120);
		
		JButton credit = new JButton();
		credit.setContentAreaFilled(false);
		credit.setBorderPainted(false);
		credit.setIcon(new ImageIcon("src/Images/univr.png"));
		credit.setRolloverIcon(new ImageIcon("src/Images/univr1.png"));
		credit.setBounds(195, 150, 150, 150);
		
		JLabel background = new JLabel(new ImageIcon("src/Images/background.png"));
		background.setBounds(0, 0, 550, 350);
		
		chess.addActionListener(event -> {
			ChessboardFrame p = new ChessboardFrame();
			p.setVisible(true);
			setVisible(false);
		});
		
		setPreferredSize(new Dimension(550, 350));
		menu.add(credit);
		menu.add(chess);
		menu.add(background);
		add(menu);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (getPreferredSize().getWidth() / 2);
		int panelHeight = (int) (getPreferredSize().getHeight() / 2);
		setLocation(dim.width/2 - panelWidth, dim.height/2 - panelHeight);
		
		setIconImage(new ImageIcon("src/Images/Icon.png").getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	}	
}