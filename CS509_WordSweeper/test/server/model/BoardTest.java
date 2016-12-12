
package server.model;

import java.util.ArrayList;

import junit.framework.TestCase;
import util.Location;

public class BoardTest extends TestCase {

	Board board;
	Board board1;
	
	protected void setUp() throws Exception {
		super.setUp();
		board = new Board();
		board1 = new Board(7);
	}


	public void testGetSize() {
		assertEquals(7,board.getSize());
		assertEquals(9,board1.getSize());
	}

	public void testResetBoard() {
		fail("Not yet implemented");
	}

	public void testResetBoardInt() {
		fail("Not yet implemented");
	}

	public void testGetCellContains() {
		for(int c =1; c <= board.getSize(); c++){
			for(int r=1; r <= board.getSize(); r++){
				Location l = new Location(r,c);
				assertNotNull(board.board.get(l));
				String contain = board.getCellContains(l);
				System.out.println(contain);
				assertFalse(contain.isEmpty());
				}
			}
	}

	public void testBonusCell() {
		fail("Not yet implemented");
	}

	public void testGetBonusCell() {
		fail("Not yet implemented");
	}

	public void testRemoveWord() {
		Location l1 = new Location(1,1);
		Location l2 = new Location(1,2);
		String letter = board.board.get(l1).getLetter();
		System.out.println(letter);
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(l1);
		locations.add(l2);
		board.removeWord(locations);
		letter = board.board.get(l1).getLetter();
		System.out.println(letter);
		assertEquals(null,letter);		
	}

	public void testRefreshBoard() {
		Location l1 = new Location(1,1);
		Location l2 = new Location(2,2);
		Location l3 = new Location(3,3);
		Location l4 = new Location(4,4);
		Location l5 = new Location(5,5);
		Location l6 = new Location(6,6);
		Location l7 = new Location(7,7);
		
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(l1);
		locations.add(l2);
		locations.add(l3);
		locations.add(l4);
		locations.add(l5);
		locations.add(l6);
		locations.add(l7);
		
		System.out.println(board1.getBoardContains().length());
		String s1 =board1.getBoardContains();
		System.out.println(s1);
		board1.removeWord(locations);
		board1.refreshBoard();
		System.out.println(board1.getBoardContains().length());
		String s2 =board1.getBoardContains();
		System.out.println(s2);
		assertFalse(s1.equals(s2));	
	}
}
