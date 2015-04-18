package Pieces;

import org.junit.Assert;
import org.junit.Test;

public class KnightTest {
	AbstractPiece t1 = new Knight(Color.WHITE);
	AbstractPiece t2 = new Knight(Color.BLACK);
	
	@Test
	public void equalsTest() {
		AbstractPiece[] allPieces = {new Pawn(Color.WHITE), new Pawn(Color.BLACK),
                					 new Rook(Color.WHITE), new Rook(Color.BLACK),
                					 new Bishop(Color.WHITE), new Bishop(Color.BLACK), 
                					 new Queen(Color.WHITE), new Queen(Color.BLACK), 
                					 new King(Color.WHITE), new King(Color.BLACK),
                					 new NullPiece(Color.NULL)};
		
		Assert.assertTrue(t1.equals(t1));
		Assert.assertTrue(t2.equals(t2));
		Assert.assertFalse(t1.equals(t2));
		
		for(int i = 0; i < 11; i++) {
			Assert.assertFalse(t1.equals(allPieces[i]));
			Assert.assertFalse(t2.equals(allPieces[i]));
		}	
	}
	
	@Test
	public void getColorTest() {
		Assert.assertEquals(t1.getColor(), Color.WHITE);
		Assert.assertEquals(t2.getColor(), Color.BLACK);
	}
}