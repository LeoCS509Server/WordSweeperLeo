package util;


import junit.framework.TestCase;

public class KeyTest extends TestCase {
	
	Key k1;
	Key k2;
	protected void setUp() throws Exception {
		k1 = new Key(2,2);
		
		
	}

	public void testHashCode() {
		assertEquals(24776, k1.hashCode());
	}

	public void testEqualsObject() {
		
		assertFalse(equals(k2));
		
		k2 = new Key(2,2);
		assertTrue(k1.equals(k2));

	}

}
