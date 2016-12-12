package server.model;



import java.util.ArrayList;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import util.Location;

public class GameTest extends TestCase {
	
	Game g1;
	Game g2;
	MockClient client1;
	MockClient client2;
	MockClient client3;
	MockClient client4;
	MockClient client5;
	MockClient client6;
	MockClient client7;
	MockClient client8;

	protected void setUp() throws Exception {
		super.setUp();
		
		client1 = new MockClient();
		Server.register("c1", client1);
		client2 = new MockClient();
		Server.register("c2", client2);
		client3 = new MockClient();
		Server.register("c3", client3);
		client4 = new MockClient();
		Server.register("c4", client4);
		client5 = new MockClient();
		Server.register("c5", client5);
		client6 = new MockClient();
		Server.register("c6", client6);
		client7 = new MockClient();
		Server.register("c7", client7);
		client8 = new MockClient();
		Server.register("c8", client8);
		
		g1 = new Game("Tom",client1.id());
		g2 = new Game("Sam",client2.id());
		
		g1.addPlayer("Tim",client3.id());
		g1.addPlayer("Jim",client4.id());
		g2.addPlayer("Echo",client5.id());
		
		g1.setPassword("12345");
		
		
	}


	public void testSetGameID() {
		String s1 =g1.setGameID();
		assertNotNull(s1);
		
	}

	public void testRandomLocation() {
		Location s1 = g1.randomLocation(7);
		assertNotNull(s1);
		Location s2 = g1.randomLocation(7);
		assertNotNull(s2);
		if(s1.hashCode()==s2.hashCode()){
			fail("Not yet implemented");
		}	
	}


	public void testAddPlayer() {
		g1.addPlayer("Sam",client6.id());
		assertEquals(4,g1.getNumPlayers());
	}

	public void testGetGameID() {
		assertNotNull(g1.getGameID());
	}

	public void testGetManageUsername() {
		assertEquals("Tom",g1.getManageUsername());
	}

	public void testRemovePlayerPlayer() {	
		g1.removePlayer(g1.getPlayer("Tim"));
		assertEquals(2,g1.getNumPlayers());
	}

	public void testRemovePlayerString() {
		g1.removePlayer("Tim");
		assertEquals(2,g1.getNumPlayers());
	}

	public void testGetBoard() {
		assertEquals(7,g1.getBoard().getSize());
	}

	public void testResetBoard() {
		Board b1 = g1.getBoard();
		g1.resetBoard();
		Board b2 = g1.getBoard();
		assertFalse(b1.equals(b2));
	}

	public void testCheckBoardSize() {
		g1.addPlayer("Red",client7.id());
		g1.addPlayer("Blue",client8.id());	
		assertEquals(9,g1.checkBoardSize());
		
	}

	public void testSetManagingUser() {
		g2.removePlayer("Same");
		g2.setManagingUser(g2.getPlayers());
		assertEquals("Echo",g2.getManageUsername());
		
	}

	public void testCheckisLocked() {
		assertFalse(g1.checkisLocked());
	}

	public void testSetLocked() {
		g1.setLocked();
		assertTrue(g1.checkisLocked());
		
	}

	public void testSetPassword() {
		g1.setPassword("23456");
		assertEquals("23456",g1.getPassword());
	}

	public void testGetPassword() {
		assertEquals("12345",g1.getPassword());
	}

//	public void testCellOverlapNum() {
//		fail("Not yet implemented");
//	}



	public void testRePosition() {
		int i = g1.getPlayer("Tom").getCellsLocation().get(1).getRow();
		g1.rePosition(g1.getPlayer("Tom"), 0, 0);
		int i1 = g1.getPlayer("Tom").getCellsLocation().get(1).getRow();
		assertEquals(i,i1);
		
	}
	
	public void testCalculateScore(){
		String s1 ="WORD";
		ArrayList<Location> loc = new ArrayList<>();
		Location l1 = new Location(1,3);
		Location l2 = new Location(2,3);
		Location l3 = new Location(3,3);
		Location l4 = new Location(4,4);
		loc.add(l1);
		loc.add(l2);
		loc.add(l3);
		loc.add(l4);
//		System.out.println(word.isConsistent(loc));
//		System.out.println(g1.calculateScore(s1, loc));
		assertFalse(g1.calculateScore(s1, loc)==0);		
		
	}

	public void testGetPlayer() {
		assertEquals("Tom",g1.getPlayer("Tom").getName());
	}

	public void testGetNumPlayers() {
		assertEquals(3,g1.getNumPlayers());
	}

	public void testGetPlayers() {
		assertEquals(3,g1.getPlayers().size());
	}

	public void testGetPlayerboard() {
		assertEquals(32, g1.getPlayerboard(g1.getPlayer("Tom")).length());
	}

}
