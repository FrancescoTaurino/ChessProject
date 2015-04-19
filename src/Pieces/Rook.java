package Pieces;

public class Rook extends AbstractPiece {
	private Color color;
	
	public Rook(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof Rook && ((Rook) other).color == color;  
	}
	
	public int hashCode() {
		return color.hashCode() ^ "Rook".hashCode();
	}
}