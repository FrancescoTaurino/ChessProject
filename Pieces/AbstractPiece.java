package Pieces;

public abstract class AbstractPiece {
	public abstract int getColor();
	public abstract String getName();
	
	public abstract boolean equals(Object other);
	public abstract int hashCode();
}