package server.controller;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import server.model.Game;
import server.model.Model;


public class BoardResponseBuilderTest extends TestCase {

	Model model;
	Game game;
	protected void setUp() throws Exception {
		super.setUp();
		model = new Model();
		game = new Game("manager");
		game.addPlayer("ali");
		game.addPlayer("bird");
		model.addGame(game);
		model.selectGame(game.getGameID());
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBuild() {

		String xml = new BoardResponseBuilder(model).build();
		System.out.println(xml);
		
	}

}
