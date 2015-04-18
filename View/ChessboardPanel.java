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
	
	//Draw an image of a chessboard as background
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("src/Images/Chessboard.png").getImage(), 0, 0, null);
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
					if(controller.checkClickConditions(x, y) && list.size() > 1) {
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
		p.setFocusPainted(false);
		
		return p;
	}	
	
	private void iconSetter(JButton p, int x, int y) {
		AbstractPiece piece = model.at(x, y);
		
		if(piece instanceof Pawn) {
			if(piece.getColor() == Color.WHITE)  {
				p.setIcon(new ImageIcon("src/Images/WPawnMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WPawn.png"));
			}
			else if(piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BPawnMIN.png"));
			}
		}
		else if(piece instanceof Rook) {
			if(piece.getColor() == Color.WHITE) {
				p.setIcon(new ImageIcon("src/Images/WRookMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WRook.png"));
			}
			else if (piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BRookMIN.png"));
			}
		}
		else if(piece instanceof Bishop) {
			if(piece.getColor() == Color.WHITE) {
				p.setIcon(new ImageIcon("src/Images/WBishopMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WBishop.png"));
			}
			else if (piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BBishopMIN.png"));
				
			}
		}
		else if(piece instanceof Knight) {
			if(piece.getColor() == Color.WHITE) {
				p.setIcon(new ImageIcon("src/Images/WKnightMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WKnight.png"));
			}
			else if (piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BKnightMIN.png"));
				
			}
		}
		else if(piece instanceof Queen) {
			if(piece.getColor() == Color.WHITE) {
				p.setIcon(new ImageIcon("src/Images/WQueenMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WQueen.png"));
			}
			else if (piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BQueenMIN.png"));
				
			}
		}
		else if(piece instanceof King) {
			if(piece.getColor() == Color.WHITE) {
				p.setIcon(new ImageIcon("src/Images/WKingMIN.png"));
				p.setRolloverIcon(new ImageIcon("src/Images/WKing.png"));
			}
			else if (piece.getColor() == Color.BLACK) {
				p.setIcon(new ImageIcon("src/Images/BKingMIN.png"));
			
			}
		}
		else
			p.setIcon(new ImageIcon("src/Images/NullPiece.png"));
	}
	
	public void onConfiguration() {
		Color turn = model.getConfiguration().getTurn();
		boolean flagTurn = model.getConfiguration().getFlagTurn();
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				iconSetter(buttons[x][y], x, y);
				buttons[x][y].setRolloverIcon(null);
				buttons[x][y].setContentAreaFilled(false);
				if(turn == Color.WHITE && flagTurn == false && model.at(x, y).getColor() == Color.BLACK) {
					if(model.at(x, y) instanceof Pawn)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BPawn.png"));
					else if(model.at(x, y) instanceof Rook)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BRook.png"));
					else if(model.at(x, y) instanceof Knight)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BKnight.png"));
					else if(model.at(x, y) instanceof Bishop)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BBishop.png"));
					else if(model.at(x, y) instanceof Queen)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BQueen.png"));
					else if(model.at(x, y) instanceof King)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BKing.png"));
				}
				if(turn == Color.WHITE && flagTurn == true && model.at(x, y).getColor() == Color.WHITE) {
					if(model.at(x, y) instanceof Pawn)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WPawn.png"));
					else if(model.at(x, y) instanceof Rook)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WRook.png"));
					else if(model.at(x, y) instanceof Knight)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WKnight.png"));
					else if(model.at(x, y) instanceof Bishop)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WBishop.png"));
					else if(model.at(x, y) instanceof Queen)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WQueen.png"));
					else if(model.at(x, y) instanceof King)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WKing.png"));
				}
				if(turn == Color.BLACK && flagTurn == true && model.at(x, y).getColor() == Color.WHITE) {
					if(model.at(x, y) instanceof Pawn)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WPawn.png"));
					else if(model.at(x, y) instanceof Rook)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WRook.png"));
					else if(model.at(x, y) instanceof Knight)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WKnight.png"));
					else if(model.at(x, y) instanceof Bishop)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WBishop.png"));
					else if(model.at(x, y) instanceof Queen)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WQueen.png"));
					else if(model.at(x, y) instanceof King)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/WKing.png"));
				}
				if(turn == Color.BLACK && flagTurn == false && model.at(x, y).getColor() == Color.BLACK) {
					if(model.at(x, y) instanceof Pawn)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BPawn.png"));
					else if(model.at(x, y) instanceof Rook)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BRook.png"));
					else if(model.at(x, y) instanceof Knight)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BKnight.png"));
					else if(model.at(x, y) instanceof Bishop)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BBishop.png"));
					else if(model.at(x, y) instanceof Queen)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BQueen.png"));
					else if(model.at(x, y) instanceof King)
						buttons[x][y].setRolloverIcon(new ImageIcon("src/Images/BKing.png"));
				}
			}
		}	
	}
	
	public void onConfigurationLight(ArrayList<Point> list) {
		//Light up of gray selected pieces 
		Point p1 = list.get(0);
		buttons[(int) p1.getX()][(int) p1.getY()].setContentAreaFilled(true);
		buttons[(int) p1.getX()][(int) p1.getY()].setBackground(java.awt.Color.GRAY);
		
		//Remove clicked piece from list
		list.remove(0);
			
		//Light up of red all the positions on which piece can move
		if(model.getConfiguration().getHint()) {
			for(Point p: list) {
				buttons[(int) p.getX()][(int) p.getY()].setContentAreaFilled(true);
				buttons[(int) p.getX()][(int) p.getY()].setBackground(java.awt.Color.RED);
			}
		}
	}
	
	public void onConfigurationCheck() {
		Point e;
		if(model.getConfiguration().getCheck(1) == true) {
			e = model.getConfiguration().getKingLocation(Color.BLACK);
			buttons[(int) e.getX()][(int) e.getY()].setContentAreaFilled(true);
			buttons[(int) e.getX()][(int) e.getY()].setBackground(java.awt.Color.BLUE);
		}
		else if(model.getConfiguration().getCheck(0) == true) {
			e = model.getConfiguration().getKingLocation(Color.WHITE);
			buttons[(int) e.getX()][(int) e.getY()].setContentAreaFilled(true);
			buttons[(int) e.getX()][(int) e.getY()].setBackground(java.awt.Color.BLUE);
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

	public void showPromotionDialog() {
		new PromotionDialog(frame, controller).setVisible(true);
	}
}