package server.model;

import junit.framework.TestCase;
import util.Location;

public class TestCell extends TestCase {
	public void testGetPosition() {
		Location loc2 = new Location(2,4);
		
		Cell c =new Cell(loc2);
		assertEquals(loc2,c.getPosition());
	}

	public void testHasLetter() {
		Location loc2 = new Location(2,4);
		
		Cell c =new Cell(loc2);
		assertEquals(true, c.hasLetter());
	}

	public void testSetLetter1() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		
		Location loc3 = new Location(3,4);		
		Cell c2 =new Cell(loc3);
		
		c.setLetter(c2);
		assertEquals(c2.getLetter(), c.getLetter());
		}

	public void testSetLetterCell() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		c.removeLetter();
		c.setLetter();
		assertNotNull(c.hasLetter());	
	}

	public void testGetLetter() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		assertNotNull(c.getLetter());		
	}

	public void testRemoveLetter() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		c.removeLetter();
		assertNull(c.getLetter());
	}

	public void testIsSeleted() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		assertEquals(false, c.isSeleted());
	}

	public void testSetSeleted() {
		Location loc2 = new Location(2,4);		
		Cell c =new Cell(loc2);
		c.setSeleted();
		assertEquals(true, c.isSeleted());
	}

}
