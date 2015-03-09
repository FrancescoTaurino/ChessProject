package Pieces;

public class Bishop extends AbstractPiece {
	private Color color;
	
	public Bishop(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof Bishop && ((Bishop) other).color == color;  
	}
	
	public int hashCode() {
		return color.hashCode() ^ "Bishop".hashCode();
	}
}