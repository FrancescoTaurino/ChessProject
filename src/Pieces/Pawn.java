package Pieces;

public class Pawn extends AbstractPiece {
	private Color color;
	
	public Pawn(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof Pawn && ((Pawn) other).color == color;  
	}
	
	public int hashCode() {
		return color.hashCode() ^ "Pawn".hashCode();
	}
}