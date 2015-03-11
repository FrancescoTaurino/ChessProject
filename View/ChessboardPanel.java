package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Model.Model;
import Moves.Rules;
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
	ArrayList<Point> list;
	
	private boolean lightOrMove = true;

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

		p.addActionListener(event -> {
			if(controller != null) {
				if(lightOrMove) {
					list = new Rules(model.getConfiguration()).light(x, y);
					if(controller.checkClickConditions(x, y)) {
						controller.light(x, y);
						lightOrMove = false;
					}
				}
				else {
					controller.move(x, y, list);
					lightOrMove = true;
				}
			}
		});
		
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
		
		if(piece instanceof Pawn) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WPawn.png" : "src/Images/WPawnMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BPawn.png" : "src/Images/BPawnMIN.png"));
		}
		else if(piece instanceof Rook) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WRook.png" : "src/Images/WRookMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BRook.png" : "src/Images/BRookMIN.png"));
		}
		else if(piece instanceof Bishop) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WBishop.png" : "src/Images/WBishopMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BBishop.png" : "src/Images/BBishopMIN.png"));
		}
		else if(piece instanceof Knight) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WKnight.png" : "src/Images/WKnightMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BKnight.png" : "src/Images/BKnightMIN.png"));
		}
		else if(piece instanceof Queen) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WQueen.png" : "src/Images/WQueenMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BQueen.png" : "src/Images/BQueenMIN.png"));
		}
		else if(piece instanceof King) {
			if(piece.getColor() == Color.WHITE)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == true ? "src/Images/WKing.png" : "src/Images/WKingMIN.png"));
			else if (piece.getColor() == Color.BLACK)
				p.setIcon(new ImageIcon(model.getConfiguration().getFlagTurn() == false ? "src/Images/BKing.png" : "src/Images/BKingMIN.png"));
		}
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
	
	public void onConfigurationLight(ArrayList<Point> list) {
		//Light up of yellow clicked piece
		buttons[(int) list.get(0).getX()][(int) list.get(0).getY()].setContentAreaFilled(true);
		buttons[(int) list.get(0).getX()][(int) list.get(0).getY()].setBorderPainted(false); 
		buttons[(int) list.get(0).getX()][(int) list.get(0).getY()].setBackground(java.awt.Color.GRAY);

		//Remove clicked piece from list
		list.remove(0);
		
		//Light up of red all the positions on which piece can move
		for(Point p: list) {
			buttons[(int) p.getX()][(int) p.getY()].setContentAreaFilled(true);
			buttons[(int) p.getX()][(int) p.getY()].setBorderPainted(false); 
			buttons[(int) p.getX()][(int) p.getY()].setBackground(java.awt.Color.RED);
		}
	}
	
	public Model getModel() {
		return model;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void showSolvedDialog() {
		new SolvedDialog(frame, controller).setVisible(true);
	}
}