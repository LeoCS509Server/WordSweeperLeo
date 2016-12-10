package util;

import java.util.HashMap;

import junit.framework.TestCase;

public class LettersTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}


	public void testLetters() {
		HashMap<String,Integer> h = new HashMap<>();
		h.put("E", 1);
		h.put("T", 1);
		h.put("A", 2);
		
		assertEquals(h.get("E"), Letters.LetterScore.get("E"));		
	}

	public void testChooseLetter() {		
		assertNotNull(Letters.chooseLetter());
	}

}
