package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class JoinGameRequestControllerTest extends TestCase {

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
		Server.register("c2", client);
		model = new Model();
		game = new Game("player1");
		game.setPassword("1");
		model.addGame(game);
		gameId = game.getGameID();
		password = game.getPassword();
	}

	protected void tearDown() throws Exception {
	
		super.tearDown();
		Server.unregister("c2");
	}

	public void testProcess() {
		String xmlString = Message.requestHeader() + "<joinGameRequest name='player2' gameId = '"+ gameId +"' password='"+ password +"'/></request>";
		Message request = new Message(xmlString);
		
		// get response after processing this request
		Message response = new JoinGameRequestController(model).process(client, request);
		
		// make sure model is well-represented
		
		
		// get attributes of 'boardResponse' (firstChild) 
		Node joinGameResponse= response.contents.getFirstChild();
		NamedNodeMap map = joinGameResponse.getAttributes();
		assertTrue (response.success());
		
			String managingUser = map.getNamedItem("managingUser").getNodeValue();
			String gameId1 = map.getNamedItem("gameId").getNodeValue();
			String bonus = map.getNamedItem("bonus").getNodeValue();
		
			NodeList list = joinGameResponse.getChildNodes();
			String position = new String();
			String board = new String();
			String score = new String();
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				String name = n.getAttributes().getNamedItem("name").getNodeValue();
				if(name.equals("player2"))
				{
					position = n.getAttributes().getNamedItem("position").getNodeValue();
					board = n.getAttributes().getNamedItem("board").getNodeValue();
					score = n.getAttributes().getNamedItem("score").getNodeValue();
					break;
				}
			}
		
			assertEquals ("player1", managingUser);
			assertEquals (gameId1.isEmpty(), false);
			assertEquals (position.isEmpty(), false);
			assertEquals (board.isEmpty(), false);
			assertEquals (score.isEmpty(), false);
			assertEquals (bonus.isEmpty(), false);
		
	}

}
