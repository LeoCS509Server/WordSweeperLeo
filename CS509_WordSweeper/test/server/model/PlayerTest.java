package server.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import util.Location;

public class PlayerTest extends TestCase {

	Player p1;
	Player p2;

	protected void setUp() throws Exception {
		super.setUp();
		String name = "Tom";
		Location l = new Location(2,3);
		p1 = new Player(name, l);
		
		String name2 = "Bob";
		Location l2 = new Location(2,3);
		p2 = new Player(name2, l2);
		
	}

	public void testSetPlayerLocation() {
		Location l3 = new Location(4,5);
		p2.setPlayerLocation(l3);
		assertEquals(l3, p2.getPlayerLocation());
	}

	public void testGetPlayerLocation() {
		Location l1 = new Location(2,3);
		assertEquals(l1.hashCode(), p1.getPlayerLocation().hashCode());
	}


	public void testGetName() {
		assertEquals("Tom", p1.getName());
	}

	public void testGetScore() {
		assertEquals(0, p1.getScore());
	}

	public void testAddScore() {
		p1.addScore(10);
		assertEquals(10, p1.getScore());
		
	}

	public void testResetScore() {
		p1.resetScore();
		assertEquals(0, p1.getScore());
	}

	public void testSetManagingUser() {
		p2.setManagingUser();
		assertTrue(p2.isManagingUser());
	}

	public void testIsManagingUser() {
		assertFalse(p1.isManagingUser());
	}

	public void testGetCellsLocation() {
		
		Location l3 = new Location(2,3);
		List<Location> LocationList2 = new ArrayList<>();
		for(int c = 0; c<4; c++){
			for(int r=0; r<4; r++){
				LocationList2.add(new Location(l3.getColumn()+c,l3.getRow()+r));
			}
		}
		List<Location> LocationList1 = p1.getCellsLocation();
		for(int i= 0 ; i<16; i++){
			assertEquals(LocationList2.get(i).hashCode(), LocationList1.get(i).hashCode());
			
		}
		
	}

}
