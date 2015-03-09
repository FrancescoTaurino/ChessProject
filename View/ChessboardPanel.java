package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Model.Model;
import Pieces.AbstractPiece;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Color;
import Pieces.Queen;
import Pieces.Rook;

public class ChessboardPanel extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	
	private final Model model;
	private final JFrame frame;
	private Controller controller;
	private final JButton[][] buttons = new JButton[8][8];

	public ChessboardPanel (Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
		
		createButtons();

		model.setView(this);
	}
	
	//Create all the buttons of the chessboard
	private void createButtons() {
		setLayout(new GridLayout(8, 8));
		
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++) 
				add(buttons[x][y] = mkButton(x, y));
	}
	
	private JButton mkButton(int x, int y) {
		JButton p = setButtonProperties(setIconPiece(x, y));
		
		return p;
	}
	
	private JButton setIconPiece(int x, int y) {
		JButton p = new JButton();
		
		iconSetter(p, x, y);
		
		return p;
	}

	//Every button is 75 x 75 and it is made trasparent
	private JButton setButtonProperties(JButton p) {
		p.setPreferredSize(new Dimension(75, 75));
		p.setContentAreaFilled(false);
		p.setBorderPainted(false);
		
		return p;
	}	
	
	//Set the right icon for every piece on the chessboard
	private void iconSetter(JButton p, int x, int y) {
		AbstractPiece piece = model.at(x, y);
		
		if(piece instanceof Pawn)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WPawn.png") : ("src/Images/BPawn.png"))); 
		else if(piece instanceof Rook)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WRook.png") : ("src/Images/BRook.png"))); 
		else if(piece instanceof Knight)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WKnight.png") : ("src/Images/BKnight.png"))); 
		else if(piece instanceof Bishop)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WBishop.png") : ("src/Images/BBishop.png")));
		else if(piece instanceof Queen)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WQueen.png") : ("src/Images/BQueen.png")));
		else if(piece instanceof King)
			p.setIcon(new ImageIcon(piece.getColor() == Color.WHITE ? ("src/Images/WKing.png") : ("src/Images/BKing.png"))); 
		else
			p.setIcon(new ImageIcon("src/Images/NullPiece.png"));
	}
	
	//Draw an image of a chessboard as background
	protected void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("src/Images/Chessboard.png").getImage(), 0, 0, null);
	}
	
	public void onConfiguration() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				iconSetter(buttons[x][y], x, y);
				buttons[x][y].setContentAreaFilled(false);	
			}
		}
	}
	
	public Model getModel() {
		return model;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void showSolvedDialog() {
	}
}