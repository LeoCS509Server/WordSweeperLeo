package server.model;

import java.util.ArrayList;

import junit.framework.TestCase;
import util.Location;

public class TestWord extends TestCase {
	public void testIsConsistent() {
		ArrayList<Cell> cells = new ArrayList<>();
		

		Location loc2 = new Location(2,3);
		Location loc4 = new Location(2,3);
		
		
		Cell c2 = new Cell(loc2);
		Cell c4 = new Cell(loc4);
		
		cells.add(c2);
		cells.add(c4);
				
		assertTrue(!word.isConsistent(cells));
	}
	
	public void testIsConsistent2() {
		ArrayList<Cell> cells = new ArrayList<>();
		

		Location loc2 = new Location(2,3);
		Location loc3 = new Location(3,3);
		Location loc4 = new Location(2,4);
		
		
		Cell c2 = new Cell(loc2);
		Cell c3 = new Cell(loc3);
		Cell c4 = new Cell(loc4);
		
		cells.add(c2);
		cells.add(c3);
		cells.add(c4);
				
		assertTrue(word.isConsistent(cells));
	}

}
