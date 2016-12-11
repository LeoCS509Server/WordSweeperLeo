package util;

import junit.framework.TestCase;

public class LocationTest extends TestCase {
	
	Location l1;
	Location l2;

	protected void setUp() throws Exception {
		l1 = new Location(2,3);
		l2 = new Location(4,6);
	}




	public void testLocation() {
		String s = "1,2";
		Location l3 = new Location(s);
		assertEquals(1,l3.getColumn());
		assertEquals(2,l3.getRow());
	}

	public void testGetColumn() {
		assertEquals(2,l1.getColumn());
	}

	public void testGetRow() {
		assertEquals(3,l1.getRow());
	}

	public void testGetLocation() {
		int s[] ={2,3};
		assertEquals(s[0], l1.getLocation()[0]);
		assertEquals(s[1], l1.getLocation()[1]);
		
	}

	public void testSetColumn() {
		l2.setColumn(6);
		assertEquals(6,l2.getColumn());
	}

	public void testSetRow() {
		l2.setRow(6);
		assertEquals(6,l2.getRow());
	}

	
}
