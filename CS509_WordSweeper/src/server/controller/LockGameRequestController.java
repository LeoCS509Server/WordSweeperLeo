package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class LockGameRequestController {

	Model model;
	Game game;
	public LockGameRequestController (Model model) {
		this.model = model;
	}
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
