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

public class CreateGameRequestControllerTest extends TestCase {

	MockClient client;
	
	Model model;
	Game game;
	
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
				
		client = new MockClient();
		Server.register("c1", client);
		model = new Model();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Server.unregister("c1");
	}

	public void testProcess() {
		// client1 creates game
				String xmlString = Message.requestHeader() + "<createGameRequest name='player1' password=''/></request>";
				Message request = new Message(xmlString);
				
				// get response after processing this request
				Message response = new CreateGameRequestController(model).process(client, request);
				
				// make sure model is well-represented
				assertTrue (response.success());
				
				// get attributes of 'boardResponse' (firstChild) 
				Node createGameResponse= response.contents.getFirstChild();
				NamedNodeMap map = createGameResponse.getAttributes();
				
				String managingUser = map.getNamedItem("managingUser").getNodeValue();
				String gameId = map.getNamedItem("gameId").getNodeValue();
				String bonus = map.getNamedItem("bonus").getNodeValue();
				
				NodeList list = createGameResponse.getChildNodes();
			
				
				Node n = list.item(0);

				String name = n.getAttributes().getNamedItem("name").getNodeValue();	

				
				assertEquals (gameId.isEmpty(), false);
				assertEquals (bonus.isEmpty(), false);
				assertEquals ("player1", managingUser);
				assertEquals ("player1", name);
				
				
				

	}

}
