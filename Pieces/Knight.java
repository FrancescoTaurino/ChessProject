package Pieces;

public class Knight extends AbstractPiece {
	private Color color;
	
	public Knight(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof Knight && ((Knight) other).color == color;  
	}
	
	public int hashCode() {
		return hashCode();
	}
}