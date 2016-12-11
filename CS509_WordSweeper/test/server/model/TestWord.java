package server.model;

import java.util.ArrayList;

import junit.framework.TestCase;
import util.Location;

public class TestWord extends TestCase {
		

	ArrayList<Location> loc = new ArrayList<>();
	Location loc2 = new Location(2,3);
	Location loc4 = new Location(3,3);
	
	
	protected void setUp() throws Exception {
		super.setUp();
		loc.add(loc2);
		loc.add(loc2);
		
		
	}
	public void testIsConsistent() {
	
				
		assertTrue(!word.isConsistent(loc));
	}
	
	public void testIsConsistent2() {
		Location loc4 = new Location(3,3);
		loc.add(loc4);
		
				
		assertFalse(word.isConsistent(loc));
	}

}
