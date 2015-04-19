package Pieces;

import org.junit.Assert;
import org.junit.Test;

public class NullPieceTest {
	AbstractPiece t1 = new NullPiece(Color.NULL);
	
	@Test
	public void equalsTest() {
		AbstractPiece[] allPieces = {new Pawn(Color.WHITE), new Pawn(Color.BLACK),
									 new Rook(Color.WHITE), new Rook(Color.BLACK), 
									 new Knight(Color.WHITE), new Knight(Color.BLACK), 
									 new Bishop(Color.WHITE), new Bishop(Color.BLACK), 
									 new Queen(Color.WHITE), new Queen(Color.BLACK), 
									 new King(Color.WHITE), new King(Color.BLACK)};
		
		Assert.assertTrue(t1.equals(t1));
		
		for(int i = 0; i < 12; i++) {
			Assert.assertFalse(t1.equals(allPieces[i]));
		}	
	}
	
	@Test
	public void getColorTest() {
		Assert.assertEquals(t1.getColor(), Color.NULL);
	}
}