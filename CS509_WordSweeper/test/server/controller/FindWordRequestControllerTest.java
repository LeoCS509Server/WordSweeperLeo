package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import junit.framework.TestCase;
import server.MockClient;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class FindWordRequestControllerTest extends TestCase {

	MockClient client;
	Model model;
	Game game;
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		this.client = new MockClient(); 
		Server.register("c", client);
		this.model = new Model();
		this.game = new Game("qaq");
		model.addGame(game);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Server.unregister("c");
	}

	public void testProcess() {
		String id = game.getGameID();
		//game.getBoard().
		String xmlString = Message.requestHeader() +"<findWordRequest name='qaq' word='ACE' gameId='"
				+ id
				+ "'><cell position='1,1' letter='A'/>"
				+ "<cell position='1,2' letter='C'/>"
				+ "<cell position='1,3' letter='E'/>"
				+ "</findWordRequest></request>";
		Message request = new Message(xmlString);
		System.out.println(request);
		Message response = new FindWordRequestController(model).process(client, request);
		System.out.println(response);
		Node find = response.contents.getFirstChild();
		NamedNodeMap map = find.getAttributes();
		String gid = map.getNamedItem("gameId").getNodeValue();
		assertEquals(gid,id);
		String name = map.getNamedItem("name").getNodeValue();
		assertEquals(name,"qaq");
	}

}
