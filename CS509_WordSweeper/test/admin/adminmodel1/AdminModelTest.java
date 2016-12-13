package admin.adminmodel1;

import junit.framework.TestCase;
import admin.adminmodel.AdministratorModel;
import admin.adminmodel.Game;


public class AdminModelTest extends TestCase {
	AdministratorModel model;
	Game game1;
	Game game2;
	Game game3;
	protected void setUp() throws Exception {
		super.setUp();
		model = new AdministratorModel();
		
		game1 = new Game("test1",7);
		game2 = new Game("test2",7);
		game3 = new Game("test3",7);
		model.addGame(game1);
		model.addGame(game2);
		model.addGame(game3);
		model.setIdlist("test1,3,test2,2,test3,2,");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testRemoveGame() {
		model.removeGame("test2");
		assertEquals(2,AdministratorModel.gamelist.size());
		assertEquals(game3,AdministratorModel.gamelist.get(1));
	}

	public void testIsExist() {
		assertTrue(model.isExist("test1"));
	}

	public void testGetGame() {
		Game gg = AdministratorModel.getGame("test1");
		assertEquals(game1,gg);
	}

}
