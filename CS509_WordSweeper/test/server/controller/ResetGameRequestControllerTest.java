package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class ResetGameRequestControllerTest extends TestCase {

	MockClient client;
	
	Model model;
	Game game;
	String gameId = new String();
	String password = new String();
	
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
				
		client = new MockClient();
		Server.register("c1", client);
		model = new Model();
		game = new Game("player1");
		game.setPassword("1");
		model.addGame(game);
		gameId = game.getGameID();
		password = game.getPassword();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Server.unregister("c1");
	}

	public void testProcess() {
		String xmlString = Message.requestHeader() + "<resetGameRequest gameId='"+ gameId +"'/></request>";
		Message request = new Message(xmlString);
		
		// get response after processing this request
		Message response = new ResetGameRequestController(model).process(client, request);
		
		// make sure model is well-represented
		assertTrue (response.success());
		
		// get attributes of 'boardResponse' (firstChild) 
		Node resetGameResponse= response.contents.getFirstChild();
		NamedNodeMap map = resetGameResponse.getAttributes();
		
		
		String gameId1 = map.getNamedItem("gameId").getNodeValue();	

		
		assertEquals (gameId1,gameId);
		
	}

}
