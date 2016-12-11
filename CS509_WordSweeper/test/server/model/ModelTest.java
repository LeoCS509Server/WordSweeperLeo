package server.model;

import junit.framework.TestCase;

public class ModelTest extends TestCase {
	Model model;
	Game game1;
	Game game2;
	String id2;
	
	protected void setUp() throws Exception {
		super.setUp();	
		model = new Model();
		game1 = new Game("test1");
		game2 = new Game("test2");
		id2 = game1.getGameID();
		model.addGame(game1);
	}

	

	public void testSelectGame() {
		String id1 = game1.getGameID();
		model.selectGame(id1);
		assertEquals(game1,model.selectedGame);
	}
	
	public void testIsPasswordCorrect() {
		game1.setPassword("123");
		game2.setPassword("122");
		String id1 = game1.getGameID();
		assertFalse(model.isPasswordCorrect(id1, "344"));
	}
	
	
	public void testGetGame() {
		
		assertNotNull(model.getGame(id2));
		
	}

	public void testGetSelected() {
		String id1 = game1.getGameID();
		model.selectGame(id1);
		assertEquals(game1,model.getSelected());
		
	}

	public void testRemoveGame() {
		model.removeGame(id2);
		assertNull(model.getGame(id2));
		
	}

	public void testRefreshGameList() {
		
		model.addGame(game2);
		System.out.println(model.games.size());
		
		game1.isActived = false;
		model.refreshGameList();
		assertEquals(1,model.games.size());
	}

}
