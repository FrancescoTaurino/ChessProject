package Model;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

import Pieces.Color;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Rook;

public class ChessboardConfigurationTest {
	@Test
	public void atAndSetTest() {
		Configuration c = new ChessboardConfiguration();
		
		Assert.assertTrue(c.at(0, 0) instanceof Rook);
		Assert.assertFalse(c.at(1, 0) instanceof King);
		
		c.set(1, 0, new King(Color.WHITE));
		
		Assert.assertTrue(c.at(1, 0) instanceof King);
		
	}

	@Test
	public void swapTest() {
		Configuration c = new ChessboardConfiguration();
		
		Assert.assertTrue(c.at(0, 0) instanceof Rook);
		Assert.assertTrue(c.at(1, 0) instanceof Pawn);
		
		c.swap(0, 0, 1, 0);
		
		Assert.assertTrue(c.at(0, 0) instanceof Pawn);
		Assert.assertTrue(c.at(1, 0) instanceof Rook);
	}
	
	@Test
	public void equalsTest() {
		Configuration c = new ChessboardConfiguration();
		Configuration o = new ChessboardConfiguration();
		
		Assert.assertTrue(c.equals(o));
		
		c.set(1, 0, new King(Color.WHITE));
		
		Assert.assertFalse(c.equals(o));
	}
	
	@Test
	public void hashCodeTest() {
		Configuration c = new ChessboardConfiguration();
		Configuration o = new ChessboardConfiguration();
		
		Assert.assertEquals(c.hashCode(), o.hashCode());
		
		c.set(1, 0, new King(Color.WHITE));
		
		Assert.assertNotEquals(c.hashCode(), o.hashCode());
	}
	
	@Test
	public void getKingLocation() {
		Configuration c = new ChessboardConfiguration();
		Point p; 
		
		p = c.getKingLocation(Color.BLACK);
		Assert.assertEquals((int)p.getX(), 0);
		Assert.assertEquals((int)p.getY(), 4);
		
		p = c.getKingLocation(Color.WHITE);
		Assert.assertEquals((int)p.getX(), 7);
		Assert.assertEquals((int)p.getY(), 4);
	}
}