package server.controller;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;


public class BoardResponseBuilderTest extends TestCase {
	MockClient client;
	Model model;
	Game game;
	String content;
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		this.client = new MockClient(); 
		Server.register("c", client);
		String cid = client.id();
		model = new Model();
		game = new Game("manager",cid);
		game.addPlayer("ali",cid);
		game.addPlayer("bird",cid);
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


