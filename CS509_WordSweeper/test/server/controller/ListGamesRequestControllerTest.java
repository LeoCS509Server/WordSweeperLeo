package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class ListGamesRequestControllerTest extends TestCase {
	MockClient client;
	Model model;
	Game game1;
	Game game2;
	
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		this.client = new MockClient(); 
		Server.register("c", client);
		String cid = client.id();
		this.model = new Model();
		game1 = new Game("test1",cid);
		game1.addPlayer("ali",cid);
		game1.addPlayer("bird",cid);
		model.addGame(game1);
		game2 = new Game("test2",cid);
		game2.addPlayer("cath",cid);
		model.addGame(game2);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Server.unregister("c");
	}

	public void testProcess() {
		String id1 = game1.getGameID();
		String id2 = game2.getGameID();
		String xmlString = Message.requestHeader() + "<listGamesRequest/></request>";
		Message request = new Message (xmlString);
		Message response = new ListGamesRequestController(model).process(client, request);
		System.out.println(response);
		Node firstgamebrief = response.contents.getFirstChild().getFirstChild();
		NamedNodeMap map1 = firstgamebrief.getAttributes();
		String gid1 = map1.getNamedItem("gameId").getNodeValue();
		assertEquals(id1,gid1);
		String number1 = map1.getNamedItem("players").getNodeValue();
		assertEquals("3",number1);
		Node secondgamebrief = firstgamebrief.getNextSibling();
		NamedNodeMap map2 = secondgamebrief.getAttributes();
		String gid2 = map2.getNamedItem("gameId").getNodeValue();
		assertEquals(id2,gid2);
		String number2 = map2.getNamedItem("players").getNodeValue();
		assertEquals("2",number2);
		
	}

}
