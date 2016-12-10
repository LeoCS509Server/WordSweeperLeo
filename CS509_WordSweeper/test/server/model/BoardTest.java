package server.model;

import java.util.List;
import java.util.ArrayList;

import junit.framework.TestCase;
import util.Location;
import util.Key;

public class BoardTest extends TestCase {

	Board board;
	Board board1;
	protected void setUp() throws Exception {
		super.setUp();
		board = new Board();
		board1 = new Board(9);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}



	public void testResetBoardInt() {
		board = board.resetBoard();
		board1 = board1.resetBoard(9);
	}
	
	public void testGetSize() {
		assertEquals(7,board.getSize());
		assertEquals(9,board1.getSize());
	}
	public void testGetCellContains() {
		for(int c =1; c <= board.getSize(); c++){
			for(int r=1; r <= board.getSize(); r++){
				Key key = new Key(r,c);
				assertNotNull(board.table.get(key));
				Location l = new Location(c,r);
				String contain = board.getCellContains(l);
				System.out.println(contain);
				assertFalse(contain.isEmpty());
			}
		}
	}
	public void testGetBonusCell() {
		assertNotNull(board.getBonusCell());
		Location l = board.getBonusCell().location;
		System.out.println(l.getRow()+","+l.getColumn());
	}	
	
	public void testRemoveWord(){
		Key key = new Key(1,1);
		Cell c = board.table.get(key);
		List<Cell> cells = new ArrayList<Cell>();
		cells.add(c);
		board.removeWord(cells);
		String letter = board.table.get(key).letter;
		System.out.println(letter);
		assertEquals(null,letter);		
	}
	
	public void testRefreshBoard() {
		Key k2 = new Key(2,1);
		Key k3 = new Key(3,1);
		Key k4 = new Key(4,1);
		Key k5 = new Key(5,1);
		Key k6 = new Key(6,1);
		Key k7 = new Key(7,1);
		board.table.get(k2).removeLetter();
		board.table.get(k3).removeLetter();
		String letter4 = board.table.get(k4).letter;
		String letter5 = board.table.get(k5).letter;
		String letter6 = board.table.get(k6).letter;
		String letter7 = board.table.get(k7).letter;
		System.out.println(letter4+letter5+letter6+letter7);
		board.refreshBoard();
		assertEquals(letter4,board.table.get(k2).letter);
		assertEquals(letter5,board.table.get(k3).letter);
		assertEquals(letter6,board.table.get(k4).letter);
		assertEquals(letter7,board.table.get(k5).letter);
		assertFalse(board.table.get(k6).letter.isEmpty());
		assertFalse(board.table.get(k7).letter.isEmpty());
		System.out.println(board.table.get(k2).letter+board.table.get(k3).letter+board.table.get(k4).letter+board.table.get(k5).letter
				+board.table.get(k6).letter+board.table.get(k7).letter);
	}


}
