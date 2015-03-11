package Moves;

import java.awt.Point;
import java.util.ArrayList;

import Model.ChessboardConfiguration;
import Model.Configuration;
import Pieces.AbstractPiece;
import Pieces.Bishop;
import Pieces.Color;
import Pieces.King;
import Pieces.Knight;
import Pieces.NullPiece;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class Rules {
	private final Configuration configuration;
	
	public Rules (Configuration configuration) {
		this.configuration = configuration;
	}
	
	public boolean checkClickConditions(int x, int y) {
		return configuration.at(x, y).getColor() != Color.NULL && 
				((configuration.getTurn() == Color.WHITE && configuration.at(x, y).getColor() == Color.WHITE) || 
				(configuration.getTurn() == Color.BLACK && configuration.at(x, y).getColor() == Color.BLACK));
	}
	
	public int isSolved() {
		AbstractPiece piece;
		int flag = 0;
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				piece = configuration.at(x, y);
				if(piece instanceof King && piece.getColor() == Color.BLACK)
					flag++;
				else if(piece instanceof King && piece.getColor() == Color.WHITE)
					flag--;
			}
		}
		
		return flag;
	}
	
	private Point getCooPromotePawn() {
		Point result = null;
		
		for(int y = 0; y < 8; y++) 
			if(configuration.at(0, y) instanceof Pawn && configuration.at(0, y).getColor() == Color.WHITE) 
				return new Point(0, y);
		for(int y = 0; y < 8; y++) 
			if(configuration.at(7, y) instanceof Pawn && configuration.at(7, y).getColor() == Color.BLACK) 
				return new Point(7, y);
		
		return result;
	}
	
	public Configuration promotePawn(AbstractPiece piece) {
		Point cooPromotePawn = getCooPromotePawn();
		
		int x = (int) cooPromotePawn.getX();
		int y = (int) cooPromotePawn.getY();
		
		configuration.set(x, y, piece);
		
		return configuration;
	}
	
	//If white pawn crosses chessboard return -1, if black pawn crosses chessoboard return 1
	public int promotion() {
		int flag = 0;
		
		for(int y = 0; y < 8; y++) 
			if(configuration.at(0, y) instanceof Pawn && (configuration.at(0, y).getColor() == Color.WHITE))
				flag = -1;
		for(int y = 0; y < 8; y++) 
			if(configuration.at(7, y) instanceof Pawn && (configuration.at(0, y).getColor() == Color.BLACK))
				flag = +1;
		
		return flag;
	}
	
	public Configuration move(int x, int y, ArrayList<Point> list) {
		int X = (int) list.get(0).getX();
		int Y = (int) list.get(0).getY();
				
		//Remove clicked piece
		list.remove(0);
			
		//If list contains the clicked location then move
		if(list.contains(new Point(x, y)))
			capture(X, Y, x, y);
				
		return configuration;
	}
			
	private void capture(int X, int Y, int x, int y) {
		configuration.swap(X, Y, x, y);
					
		//If swapped pieces are opposite color then eat
		if(configuration.at(X, Y).getColor() == Color.BLACK && configuration.at(x, y).getColor() == Color.WHITE ||
			configuration.at(X, Y).getColor() == Color.WHITE && configuration.at(x, y).getColor() == Color.BLACK) {
					
		configuration.set(X, Y, new NullPiece(Color.NULL));
		}
					
		//Change turn
		configuration.setFlagTurn(configuration.getFlagTurn() == true ? false : true);
	}

	public ArrayList<Point> light(int x, int y) {
		ArrayList<Point> result = new ArrayList<>();
		
		//Add clicked piece in first position of result
		result.add(new Point(x, y));
		
		AbstractPiece piece = configuration.at(x, y);
		Color pieceColor = piece.getColor();
		
		if(piece instanceof Pawn) 
			tryPawn(pieceColor, x, y, result);
		else if(piece instanceof Rook)
			tryRook(pieceColor, x, y, result);
		else if(piece instanceof Knight)
			tryKnight(pieceColor, x, y, result);
		else if(piece instanceof Bishop)
			tryBishop(pieceColor, x, y, result);
		else if(piece instanceof Queen)
			tryQueen(pieceColor, x, y, result);
		else if(piece instanceof King)
			tryKing(pieceColor, x, y, result);
		
		return result;
	}
	
	private void tryKing(Color color, int x, int y, ArrayList<Point> result) {
		if(color == Color.WHITE) {
			if(y + 1 < 8)
				if(configuration.at(x, y + 1).getColor() == Color.NULL || configuration.at(x, y + 1).getColor() == Color.BLACK)
					result.add(new Point(x, y + 1));
			if(x - 1 >= 0 && y + 1 < 8)
				if(configuration.at(x - 1, y + 1).getColor() == Color.NULL || configuration.at(x - 1, y + 1).getColor() == Color.BLACK)
					result.add(new Point(x - 1, y + 1));
			if(x - 1 >= 0)
				if(configuration.at(x - 1, y).getColor() == Color.NULL || configuration.at(x - 1, y).getColor() == Color.BLACK)
					result.add(new Point(x - 1, y));
			if(x - 1 >= 0 && y - 1 >= 0)
				if(configuration.at(x - 1, y - 1).getColor() == Color.NULL || configuration.at(x - 1, y - 1).getColor() == Color.BLACK)
					result.add(new Point(x - 1, y - 1));
			if(y - 1 >= 0)
				if(configuration.at(x, y - 1).getColor() == Color.NULL || configuration.at(x, y - 1).getColor() == Color.BLACK)
					result.add(new Point(x, y - 1));
			if(x + 1 < 8 && y - 1 >= 0)
				if(configuration.at(x + 1, y - 1).getColor() == Color.NULL || configuration.at(x + 1, y - 1).getColor() == Color.BLACK)
					result.add(new Point(x + 1, y - 1));
			if(x + 1 < 8)
				if(configuration.at(x + 1, y).getColor() == Color.NULL || configuration.at(x + 1, y).getColor() == Color.BLACK)
					result.add(new Point(x + 1, y));
			if(x + 1 < 8 && y + 1 < 8)
				if(configuration.at(x + 1, y + 1).getColor() == Color.NULL || configuration.at(x + 1, y + 1).getColor() == Color.BLACK)
					result.add(new Point(x + 1, y + 1));
		}
		else {
			if(y + 1 < 8)
				if(configuration.at(x, y + 1).getColor() == Color.NULL || configuration.at(x, y + 1).getColor() == Color.WHITE)
					result.add(new Point(x, y + 1));
			if(x - 1 >= 0 && y + 1 < 8)
				if(configuration.at(x - 1, y + 1).getColor() == Color.NULL || configuration.at(x - 1, y + 1).getColor() == Color.WHITE)
					result.add(new Point(x - 1, y + 1));
			if(x - 1 >= 0)
				if(configuration.at(x - 1, y).getColor() == Color.NULL || configuration.at(x - 1, y).getColor() == Color.WHITE)
					result.add(new Point(x - 1, y));
			if(x - 1 >= 0 && y - 1 >= 0)
				if(configuration.at(x - 1, y - 1).getColor() == Color.NULL || configuration.at(x - 1, y - 1).getColor() == Color.WHITE)
					result.add(new Point(x - 1, y - 1));
			if(y - 1 >= 0)
				if(configuration.at(x, y - 1).getColor() == Color.NULL || configuration.at(x, y - 1).getColor() == Color.WHITE)
					result.add(new Point(x, y - 1));
			if(x + 1 < 8 && y - 1 >= 0)
				if(configuration.at(x + 1, y - 1).getColor() == Color.NULL || configuration.at(x + 1, y - 1).getColor() == Color.WHITE)
					result.add(new Point(x + 1, y - 1));
			if(x + 1 < 8)
				if(configuration.at(x + 1, y).getColor() == Color.NULL || configuration.at(x + 1, y).getColor() == Color.WHITE)
					result.add(new Point(x + 1, y));
			if(x + 1 < 8 && y + 1 < 8)
				if(configuration.at(x + 1, y + 1).getColor() == Color.NULL || configuration.at(x + 1, y + 1).getColor() == Color.WHITE)
					result.add(new Point(x + 1, y + 1));
		}
	}
	
	private void tryQueen(Color pieceColor, int x, int y, ArrayList<Point> result) {
		if(pieceColor == Color.WHITE) {
			for(int i = x - 1; i >= 0; i--) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(i, y).getColor() == Color.BLACK) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = x + 1; i < 8; i++) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(i, y).getColor() == Color.BLACK) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = y - 1; i >= 0; i--) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.WHITE)
					break;
				else if(configuration.at(x, i).getColor() == Color.BLACK) {
					result.add(new Point(x, i));
					break;
				}
			}
			
			for(int i = y + 1; i < 8; i++) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.WHITE)
					break;
				else if(configuration.at(x, i).getColor() == Color.BLACK) {
					result.add(new Point(x, i));
					break;
				}
			}
			
			int X = x - 1;
			int Y = y + 1;
			while(X >= 0 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y++;
			}
			
			X = x - 1;
			Y = y - 1;
			while(X >= 0 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y--;
			}
			
			X = x + 1;
			Y = y + 1;
			while(X < 8 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y++;
			}
			
			X = x + 1;
			Y = y - 1;
			while(X < 8 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y--;
			}
		}
		else {
			for(int i = x - 1; i >= 0; i--) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(i, y).getColor() == Color.WHITE) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = x + 1; i < 8; i++) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(i, y).getColor() == Color.WHITE) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = y - 1; i >= 0; i--) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.BLACK)
					break;
				else if(configuration.at(x, i).getColor() == Color.WHITE) {
					result.add(new Point(x, i));
					break;
				}
			}
			
			for(int i = y + 1; i < 8; i++) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.BLACK)
					break;
				else if(configuration.at(x, i).getColor() == Color.WHITE) {
					result.add(new Point(x, i));
					break;
				}
			}
			
			int X = x - 1;
			int Y = y + 1;
			while(X >= 0 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y++;
			}
			
			X = x - 1;
			Y = y - 1;
			while(X >= 0 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y--;
			}
			
			X = x + 1;
			Y = y + 1;
			while(X < 8 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y++;
			}
			
			X = x + 1;
			Y = y - 1;
			while(X < 8 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y--;
			}
		}
	}
	
	private void tryBishop(Color pieceColor, int x, int y, ArrayList<Point> result) {
		if(pieceColor == Color.WHITE) {
			int X = x - 1;
			int Y = y + 1;
			while(X >= 0 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y++;
			}
			
			X = x - 1;
			Y = y - 1;
			while(X >= 0 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y--;
			}
			
			X = x + 1;
			Y = y + 1;
			while(X < 8 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y++;
			}
			
			X = x + 1;
			Y = y - 1;
			while(X < 8 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(X, Y).getColor() == Color.BLACK) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y--;
			}
		}
		else {
			int X = x - 1;
			int Y = y + 1;
			while(X >= 0 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y++;
			}
			
			X = x - 1;
			Y = y - 1;
			while(X >= 0 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X--;
				Y--;
			}
			
			X = x + 1;
			Y = y + 1;
			while(X < 8 && Y < 8) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y++;
			}
			
			X = x + 1;
			Y = y - 1;
			while(X < 8 && Y >= 0) {
				if(configuration.at(X, Y).getColor() == Color.NULL)
					result.add(new Point(X, Y));
				else if(configuration.at(X, Y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(X, Y).getColor() == Color.WHITE) {
					result.add(new Point(X, Y));
					break;
				}	
				X++;
				Y--;
			}
		}		
	}

	private void tryKnight(Color color, int x, int y, ArrayList<Point> result) {
		if(color == Color.WHITE) {
			if(x - 2 >= 0 && y + 1 < 8 && !(configuration.at(x - 2, y + 1).getColor() == Color.WHITE))
				result.add(new Point(x - 2, y + 1));
			if(x - 2 >= 0 && y - 1 >= 0 && !(configuration.at(x - 2, y - 1).getColor() == Color.WHITE))
				result.add(new Point(x - 2, y - 1));
			if(x + 2 < 8 && y + 1 < 8 && !(configuration.at(x + 2, y + 1).getColor() == Color.WHITE))
				result.add(new Point(x + 2, y + 1));
			if(x + 2 < 8 && y - 1 >= 0 && !(configuration.at(x + 2, y - 1).getColor() == Color.WHITE))
				result.add(new Point(x + 2, y - 1));
			if(x - 1 >= 0 && y + 2 < 8 && !(configuration.at(x - 1, y + 2).getColor() == Color.WHITE))
				result.add(new Point(x - 1, y + 2));
			if(x - 1 >= 0 && y - 2 >= 0 && !(configuration.at(x - 1, y - 2).getColor() == Color.WHITE))
				result.add(new Point(x - 1, y - 2));
			if(x + 1 < 8 && y - 2 >= 0 && !(configuration.at(x + 1, y - 2).getColor() == Color.WHITE))
				result.add(new Point(x + 1, y - 2));
			if(x + 1 < 8 && y + 2 < 8 && !(configuration.at(x + 1, y + 2).getColor() == Color.WHITE))
				result.add(new Point(x + 1, y + 2));
		}
		else {
			if(x - 2 >= 0 && y + 1 < 8 && !(configuration.at(x - 2, y + 1).getColor() == Color.BLACK))
				result.add(new Point(x - 2, y + 1));
			if(x - 2 >= 0 && y - 1 >= 0 && !(configuration.at(x - 2, y - 1).getColor() == Color.BLACK))
				result.add(new Point(x - 2, y - 1));
			if(x + 2 < 8 && y + 1 < 8 && !(configuration.at(x + 2, y + 1).getColor() == Color.BLACK))
				result.add(new Point(x + 2, y + 1));
			if(x + 2 < 8 && y - 1 >= 0 && !(configuration.at(x + 2, y - 1).getColor() == Color.BLACK))
				result.add(new Point(x + 2, y - 1));
			if(x - 1 >= 0 && y + 2 < 8 && !(configuration.at(x - 1, y + 2).getColor() == Color.BLACK))
				result.add(new Point(x - 1, y + 2));
			if(x - 1 >= 0 && y - 2 >= 0 && !(configuration.at(x - 1, y - 2).getColor() == Color.BLACK))
				result.add(new Point(x - 1, y - 2));
			if(x + 1 < 8 && y - 2 >= 0 && !(configuration.at(x + 1, y - 2).getColor() == Color.BLACK))
				result.add(new Point(x + 1, y - 2));
			if(x + 1 < 8 && y + 2 < 8 && !(configuration.at(x + 1, y + 2).getColor() == Color.BLACK))
				result.add(new Point(x + 1, y + 2));
		}
	}
	
	private void tryRook(Color pieceColor, int x, int y, ArrayList<Point> result) {
		if(pieceColor == Color.WHITE) {
			for(int i = x - 1; i >= 0; i--) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(i, y).getColor() == Color.BLACK) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = x + 1; i < 8; i++) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.WHITE)
					break;
				else if(configuration.at(i, y).getColor() == Color.BLACK) {
					result.add(new Point(i, y));
					break;
				}
			}
			
			for(int i = y - 1; i >= 0; i--) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.WHITE)
					break;
				else if(configuration.at(x, i).getColor() == Color.BLACK) {
					result.add(new Point(x, i));
					break;
				}
			}
			
			for(int i = y + 1; i < 8; i++) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.WHITE)
					break;
				else if(configuration.at(x, i).getColor() == Color.BLACK) {
					result.add(new Point(x, i));
					break;
				}
			}
		}
		else {
			for(int i = x - 1; i >= 0; i--) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(i, y).getColor() == Color.WHITE) {
					result.add(new Point(i, y));
					break;
				}
			}
				
			for(int i = x + 1; i < 8; i++) {
				if(configuration.at(i, y).getColor() == Color.NULL)
					result.add(new Point(i, y));
				else if(configuration.at(i, y).getColor() == Color.BLACK)
					break;
				else if(configuration.at(i, y).getColor() == Color.WHITE) {
					result.add(new Point(i, y));
					break;
				}
			}
				
			for(int i = y - 1; i >= 0; i--) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.BLACK)
					break;
				else if(configuration.at(x, i).getColor() == Color.WHITE) {
					result.add(new Point(x, i));
					break;
				}
			}
				
			for(int i = y + 1; i < 8; i++) {
				if(configuration.at(x, i).getColor() == Color.NULL)
					result.add(new Point(x, i));
				else if(configuration.at(x, i).getColor() == Color.BLACK)
					break;
				else if(configuration.at(x, i).getColor() == Color.WHITE) {
					result.add(new Point(x, i));
					break;
				}
			}
		}
	}

	private void tryPawn(Color color, int x, int y, ArrayList<Point> result) {
		if(color == Color.WHITE) {
			if(x == 6) {
				if(configuration.at(x - 1, y).getColor() == Color.NULL && configuration.at(x - 2, y).getColor() == Color.NULL) {
					result.add(new Point(x - 1, y));
					result.add(new Point(x - 2, y));
				}
			}
			if(x - 1 >= 0)
				if(configuration.at(x - 1, y).getColor() == Color.NULL)
					result.add(new Point(x - 1, y));
			if(x - 1 >= 0 && y + 1 < 8)
				if(configuration.at(x - 1, y + 1).getColor() == Color.BLACK) 
					result.add(new Point(x - 1, y + 1));
			if(x - 1 >= 0 && y - 1 >= 0)
				if (configuration.at(x - 1, y - 1).getColor() == Color.BLACK)
					result.add(new Point(x - 1, y - 1));
		}
		else {
			if(x == 1) {
				if(configuration.at(x + 1, y).getColor() == Color.NULL) {
					result.add(new Point(x + 1, y));
					result.add(new Point(x + 2, y));
				}
			}
			if(x + 1 < 8)
				if(configuration.at(x + 1, y).getColor() == Color.NULL)
					result.add(new Point(x + 1, y));
			if(x + 1 < 8 && y + 1 < 8)
				if(configuration.at(x + 1, y + 1).getColor() == Color.WHITE) 
					result.add(new Point(x + 1, y + 1));
			if(x + 1 < 8 && y - 1 >= 0)
				if (configuration.at(x + 1, y - 1).getColor() == Color.WHITE)
					result.add(new Point(x + 1, y - 1));
		}
	}

	public Configuration newGame() {
		return new ChessboardConfiguration();
	}
}