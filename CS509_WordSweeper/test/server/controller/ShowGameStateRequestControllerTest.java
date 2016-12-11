package server.controller;

import org.w3c.dom.NamedNodeMap;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class ShowGameStateRequestControllerTest extends TestCase {

	MockClient client;
	Model model;
	Game game;
	protected void setUp() throws Exception {
		super.setUp();
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		this.client = new MockClient(); 
		Server.register("c", client);
		String clientid = client.id();

		this.model = new Model();
		this.game = new Game("manager",clientid);
		game.addPlayer("ali",clientid);
		game.addPlayer("bird",clientid);
		model.addGame(game);
		Game game1 = new Game("test1",clientid);
		model.addGame(game1);
		model.selectGame(game.getGameID());
	}

	protected void tearDown() throws Exception{
		Server.unregister("c");
	}

	public void testProcess() {
		String id = game.getGameID();
		String xmlString = Message.requestHeader() + "<showGameStateRequest gameId='"
				+ id
				+ "'/></request>";
		Message request = new Message(xmlString);
		
		Message response = new ShowGameStateRequestController(model).process(client, request);
		// get attributes of 'boardResponse' (firstChild) 
		NamedNodeMap map = response.contents.getFirstChild().getAttributes();
		assertEquals (id, map.getNamedItem("gameId").getNodeValue());
		String content = map.getNamedItem("contents").getNodeValue();
		System.out.println(content);
		NamedNodeMap player = response.contents.getFirstChild().getFirstChild().getAttributes();
		assertEquals ("manager",player.getNamedItem("name").getNodeValue());
	}

}
