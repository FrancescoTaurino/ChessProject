package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controller.Controller;
import Pieces.Color;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Knight;
import Pieces.Bishop;

public class PromotionDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public PromotionDialog(JFrame frame, Controller controller) {
		super(frame, "Promotion", true);
		
		JPanel northPanel = new JPanel();
		JLabel text = new JLabel("Choose the promotion for your Pawn!");
		text.setFont(new Font("title", Font.BOLD, 25));
		text.setHorizontalAlignment(JLabel.CENTER);
		northPanel.add(text);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton queen = new JRadioButton(controller.promotion() < 0 ? "White Queen" : "Black Queen");
		queen.setFont(new Font("title", Font.ITALIC, 15));
		queen.setFocusPainted(false);
		buttonGroup.add(queen);
		centerPanel.add(queen);
		JRadioButton rook = new JRadioButton(controller.promotion() < 0 ? "White Rook" : "Black Rook");
		rook.setFont(new Font("title", Font.ITALIC, 15));
		rook.setFocusPainted(false);
		buttonGroup.add(rook);
		centerPanel.add(rook);
		JRadioButton knight = new JRadioButton(controller.promotion() < 0 ? "White Knight" : "Black Knight");
		knight.setFont(new Font("title", Font.ITALIC, 15));
		knight.setFocusPainted(false);
		buttonGroup.add(knight);
		centerPanel.add(knight);
		JRadioButton bishop = new JRadioButton(controller.promotion() < 0 ? "White Bishop" : "Black Bishop");
		bishop.setFont(new Font("title", Font.ITALIC, 15));
		bishop.setFocusPainted(false);
		buttonGroup.add(bishop);
		centerPanel.add(bishop);
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		JButton ok = new JButton("OK");
		
		ok.addActionListener(event -> {
			if(queen.isSelected())
				controller.promotePawn(queen.getText().equals("White Queen") ? new Queen(Color.WHITE) : new Queen(Color.BLACK));
			else if(rook.isSelected())
				controller.promotePawn(rook.getText().equals("White Rook") ? new Rook(Color.WHITE) : new Rook(Color.BLACK));
			else if(knight.isSelected())
				controller.promotePawn(knight.getText().equals("White Knight") ? new Knight(Color.WHITE) : new Knight(Color.BLACK));
			else if(bishop.isSelected())
				controller.promotePawn(bishop.getText().equals("White Bishop") ? new Bishop(Color.WHITE) : new Bishop(Color.BLACK));
			
			setVisible(false);
		});
		
		southPanel.add(ok);
		add(southPanel, BorderLayout.SOUTH);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (getPreferredSize().getWidth() / 2);
		int panelHeight = (int) (getPreferredSize().getHeight() / 2);
		setLocation(dim.width/2 - panelWidth, dim.height/2 - panelHeight);
		
		setResizable(false);
		setUndecorated(true);
		
		pack();
	}
}
