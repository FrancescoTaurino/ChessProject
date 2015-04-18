package Moves;

import org.junit.Assert;
import org.junit.Test;

import Model.ChessboardConfiguration;
import Model.Configuration;
import Pieces.Bishop;
import Pieces.Color;
import Pieces.King;
import Pieces.Knight;
import Pieces.NullPiece;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class RulesTest {
	Configuration c;
	Rules r;
	
	@Test
	public void newGameTest() {
		c = new ChessboardConfiguration();
		r = new Rules(c);
		
		Assert.assertTrue(r.newGame().equals(c));
		
		c.set(0, 0, new Pawn(Color.WHITE));
		
		Assert.assertFalse(r.newGame().equals(c));
	}
	
	@Test
	public void promotePawnTest() {
		c = new ChessboardConfiguration();
		c.set(0, 0, new Pawn(Color.WHITE));
		r = new Rules(c);
		
		c = r.promotePawn(new Queen(Color.WHITE));
		
		Assert.assertTrue(c.at(0, 0) instanceof Queen);	
	}
	
	@Test
	public void promotionTest() {
		c = new ChessboardConfiguration();
		r = new Rules(c);
		Assert.assertEquals(r.promotion(), 0);
		
		c = new ChessboardConfiguration();
		c.set(0, 2, new Pawn(Color.WHITE));
		r = new Rules(c);
		Assert.assertEquals(r.promotion(), -1);
		
		c = new ChessboardConfiguration();
		c.set(7, 3, new Pawn(Color.BLACK));
		r = new Rules(c);
		Assert.assertEquals(r.promotion(), 1);
	}
	
	@Test
	public void staleMateTest() {
		c = new ChessboardConfiguration();
		r = new Rules(c);
		Assert.assertNotEquals(r.staleMate(), 1);
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				c.set(i, j, new NullPiece(Color.NULL));
		
		//Set a stalemate situation 
		c.set(0, 5, new Bishop(Color.BLACK));
		c.set(0, 6, new Knight(Color.BLACK));
		c.set(0, 7, new Rook(Color.BLACK));
		c.set(1, 4, new Pawn(Color.BLACK));
		c.set(1, 6, new Pawn(Color.BLACK));
		c.set(1, 7, new Queen(Color.BLACK));
		c.set(2, 4, new Queen(Color.WHITE));
		c.set(2, 5, new Pawn(Color.BLACK));
		c.set(2, 6, new King(Color.BLACK));
		c.set(2, 7, new Rook(Color.BLACK));
		c.set(3, 7, new Pawn(Color.BLACK));
		c.set(4, 7, new Pawn(Color.WHITE));
		c.set(5, 4, new Pawn(Color.WHITE));
		c.set(6, 0, new Pawn(Color.WHITE));
		c.set(6, 1, new Pawn(Color.WHITE));
		c.set(6, 2, new Pawn(Color.WHITE));
		c.set(6, 3, new Pawn(Color.WHITE));
		c.set(6, 5, new Pawn(Color.WHITE));
		c.set(6, 6, new Pawn(Color.WHITE));
		c.set(7, 0, new Rook(Color.WHITE));
		c.set(7, 1, new Knight(Color.WHITE));
		c.set(7, 2, new Bishop(Color.WHITE));
		c.set(7, 4, new King(Color.WHITE));
		c.set(7, 5, new Bishop(Color.WHITE));
		c.set(7, 6, new Knight(Color.WHITE));
		c.set(7, 7, new Rook(Color.WHITE));
		
		r = new Rules(c);
		Assert.assertEquals(r.staleMate(), 1);
	}
	
	@Test
	public void checkTest() {
		//No check
		c = new ChessboardConfiguration();
		r = new Rules(c);
		r.check();
		Assert.assertEquals(c.getCheck(0), false);
		Assert.assertEquals(c.getCheck(1), false);
		
		//Black King in check...
		c.set(1, 4, new NullPiece(Color.NULL));
		c.set(3, 4, new Queen(Color.WHITE));
		c.set(4, 4, new Pawn(Color.WHITE));
		c.set(6, 4, new NullPiece(Color.NULL));
		c.set(7, 3, new NullPiece(Color.NULL));
		c.setTurn(Color.WHITE);
		c.setFlagTurn(false);
		r = new Rules(c);
		r.check();
		
		Assert.assertEquals(c.getCheck(1), true);
		Assert.assertEquals(c.getCheck(0), false);
		
		//...but not in checkmate
		Assert.assertEquals(r.checkMate(), 0);
		
		//White King in check
		c = new ChessboardConfiguration();
		c.set(0, 3, new NullPiece(Color.NULL));
		c.set(1, 4, new NullPiece(Color.NULL));
		c.set(3, 4, new Pawn(Color.BLACK));
		c.set(4, 4, new Pawn(Color.WHITE));
		c.set(4, 7, new Queen(Color.BLACK));
		c.set(5, 5, new Pawn(Color.WHITE));
		c.set(6, 4, new NullPiece(Color.NULL));
		c.set(6, 5, new NullPiece(Color.NULL));
		c.setTurn(Color.BLACK);
		c.setFlagTurn(true);
		r = new Rules(c);
		r.check();
		
		Assert.assertEquals(c.getCheck(0), true);
		Assert.assertEquals(c.getCheck(1), false);
		
		//...but not in checkmate
		Assert.assertEquals(r.checkMate(), 0);
	}
	
	@Test
	public void checkMateTest() {
		c = new ChessboardConfiguration();
		r = new Rules(c);
		Assert.assertEquals(r.checkMate(), 0);
		
		//Black King in checkmate
		c.set(0, 4, new NullPiece(Color.NULL));
		c.set(1, 4, new King(Color.BLACK));
		c.set(3, 4, new Queen(Color.WHITE));
		c.set(4, 4, new Pawn(Color.WHITE));
		c.set(6, 4, new NullPiece(Color.NULL));
		c.set(7, 3, new NullPiece(Color.NULL));
		c.setCheck(1, true);
		
		r = new Rules(c);
		Assert.assertEquals(r.checkMate(), 1);
		
		c = new ChessboardConfiguration();
		
		//White King in checkmate
		c.set(0, 3, new NullPiece(Color.NULL));
		c.set(1, 4, new NullPiece(Color.NULL));
		c.set(3, 4, new Pawn(Color.BLACK));
		c.set(4, 5, new Pawn(Color.WHITE));
		c.set(4, 7, new Queen(Color.BLACK));
		c.set(6, 5, new NullPiece(Color.NULL));
		c.set(6, 6, new NullPiece(Color.NULL));
		c.setCheck(0, true);
		
		r = new Rules(c);
		Assert.assertEquals(r.checkMate(), -1);
	}
}