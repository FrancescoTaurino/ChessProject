package Pieces;

public class King extends AbstractPiece {
	private Color color;
	
	public King(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof King && ((King) other).color == color;  
	}
	
	public int hashCode() {
		return color.hashCode() ^ "King".hashCode();
	}
}