package Pieces;

public abstract class AbstractPiece {
	public abstract Color getColor();
	
	public abstract boolean equals(Object other);
	public abstract int hashCode();
}