package Pieces;

public class Queen extends AbstractPiece {
	private Color color;
	
	public Queen(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof Queen && ((Queen) other).color == color;  
	}
	
	public int hashCode() {
		return hashCode();
	}
}