package Model;

import java.awt.Point;

import Pieces.AbstractPiece;
import Pieces.Bishop;
import Pieces.Color;
import Pieces.King;
import Pieces.Knight;
import Pieces.NullPiece;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class ChessboardConfiguration implements Configuration {
	private AbstractPiece[][] chessboard = new AbstractPiece[8][8];
	
	//White starts 
	private Color turn = Color.WHITE;
	private boolean flagTurn = true;
	//Initially no check
	private boolean[] check = {false, false};

	
	public ChessboardConfiguration() {
		//Set black pieces
		Color black = Color.BLACK;
		chessboard[0][0] = new Rook(black);
		chessboard[0][1] = new Knight(black);
		chessboard[0][2] = new Bishop(black);
		chessboard[0][3] = new Queen(black);
		chessboard[0][4] = new King(black);
		chessboard[0][5] = new Bishop(black);
		chessboard[0][6] = new Knight(black);
		chessboard[0][7] = new Rook(black);
			
		//Set white pieces
		Color white = Color.WHITE;
		chessboard[7][0] = new Rook(white);
		chessboard[7][1] = new Knight(white);
		chessboard[7][2] = new Bishop(white);
		chessboard[7][3] = new Queen(white);
		chessboard[7][4] = new King(white);
		chessboard[7][5] = new Bishop(white);
		chessboard[7][6] = new Knight(white);
		chessboard[7][7] = new Rook(white);
		
		//Set null pieces, black and white pawns
		Color nullPiece = Color.NULL;
		for(int x = 1; x < 7; x++) {
			for(int y = 0; y < 8; y++) {
				if(x == 1)
					chessboard[x][y] = new Pawn(black);
				else if(x == 6)
					chessboard[x][y] = new Pawn(white);
				else
					chessboard[x][y] = new NullPiece(nullPiece);
			}
		}
	}
	
	public AbstractPiece at(int x, int y) {
		return chessboard[x][y];
	}
	
	public void set(int x, int y, AbstractPiece p) {
		chessboard[x][y] = p;
	}

	public Configuration swap(int fromX, int fromY, int toX, int toY) {
		ChessboardConfiguration result = this;
		
		AbstractPiece fromPiece = this.at(fromX, fromY);
		AbstractPiece toPiece = this.at(toX, toY);
		
		result.set(toX, toY, fromPiece);
		result.set(fromX, fromY, toPiece);
		
		return result;
	}
	
	public AbstractPiece[][] getChessboard() {
		return chessboard;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Configuration) {
			Configuration otherConfiguration = (Configuration) other;
			for (int y = 0; y < 8; y++)
				for (int x = 0; x < 8; x++)
					if (at(x, y) != (otherConfiguration.at(x, y)))
						return false;

			return true;
		}
		else
			return false;
	}
	
	public int hashCode() {
		int hashCodeResult = 0;
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++)
				hashCodeResult ^= at(x, y).hashCode(); 
		
		return hashCodeResult;				
	}
	
	public Point getKingLocation(Color color) {
		Point[] kingsLocation = new Point[2];
		
		for(int y = 0; y < 8; y++) { 
			for(int x = 0; x < 8; x++) {
				if(chessboard[x][y] instanceof King && chessboard[x][y].getColor() == Color.WHITE)
					kingsLocation[0] = new Point(x, y);
				else if(chessboard[x][y] instanceof King && chessboard[x][y].getColor() == Color.BLACK)
					kingsLocation[1] = new Point(x, y);
			}
		}
						
		return color == Color.WHITE ? kingsLocation[0] : kingsLocation[1];
	}
	
	public void setFlagTurn(boolean flagTurn) {
		this.flagTurn = flagTurn;
	}
	
	public boolean getFlagTurn() {
		return flagTurn;
	}
	
	public void setTurn(Color turn) {
		this.turn = turn;
	}
	
	public Color getTurn() {
		return turn;
	}


	public void setCheck(int pos, boolean check) {
		this.check[pos] = check;
	}
	
	public boolean getCheck(int pos) {
		return check[pos];
	}
}