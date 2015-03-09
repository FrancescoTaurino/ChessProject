package Pieces;

public class NullPiece extends AbstractPiece {
	private Color color;
	
	public NullPiece(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object other) {
		return other instanceof NullPiece;  
	}
	
	public int hashCode() {
		return hashCode() ^ "NullPiece".hashCode();
	}
}