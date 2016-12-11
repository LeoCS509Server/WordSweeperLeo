package server.controller;

import junit.framework.TestCase;
import server.model.Game;
import server.model.Model;


public class BoardResponseBuilderTest extends TestCase {

	Model model;
	Game game;
	String content;
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
		BoardResponseBuilder builder = new BoardResponseBuilder(model);
		String xml = builder.build();
		String ct = builder.BoardContent(game.getBoard());
		assertEquals(ct,builder.content);
		System.out.println(xml);
	}

	public void testBoardContent() {
		content = new BoardResponseBuilder(model).BoardContent(game.getBoard());
		int size = game.getBoard().getSize()*game.getBoard().getSize()*2;
		assertEquals(size,content.length());
	}


}


