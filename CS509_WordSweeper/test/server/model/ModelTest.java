package server.model;

import junit.framework.TestCase;

public class ModelTest extends TestCase {
	Model model;
	Game game1;
	Game game2;
	
	protected void setUp() throws Exception {
		super.setUp();	
		model = new Model();
		game1 = new Game("test1");
		game2 = new Game("test2");
		String id2 = game2.getGameID();
		model.addGame(game1);
		model.addGame(game2);
	}

	

	public void testSelectGame() {
		String id1 = game1.getGameID();
		model.selectGame(id1);
		assertEquals(game1,model.selectedGame);
	}


/**
	public void testGetGame() {
		fail("Not yet implemented");
	}

	public void testGetGames() {
		fail("Not yet implemented");
	}

	public void testGetSelected() {
		fail("Not yet implemented");
	}

	public void testRemoveGame() {
		fail("Not yet implemented");
	}

	public void testIsPasswordCorrect() {
		fail("Not yet implemented");
	}

	public void testRefreshGameList() {
		fail("Not yet implemented");
	}*/

}
