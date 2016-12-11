package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * when server receive the lockGameRequest from client, the server will lock the game
 * and send a lockGameResponse to the client.
 * 
 * 
 * The {@link #process()} makes a boardResponse in XML format, lock the game and send it
 * to the client.
 */

public class LockGameRequestController {

	Model model;
	Game game;
	public LockGameRequestController (Model model) {
		this.model = model;
	}
	
	/** Make a lockGameResponse in XML format, lock the game and send it to the client. */
	
	public Message process(ClientState client, Message request) {
		Node lockGameRequest = request.contents.getFirstChild();
		NamedNodeMap map = lockGameRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		game.setLocked();
		String xmlString = Message.responseHeader(request.id()) +
				"<lockGameResponse gameId='"+ game.getGameID() +"'>" +
			  "</lockGameResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
