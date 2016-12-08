package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class ResetGameRequestController {

	Model model;
	Game game;
	public ResetGameRequestController (Model model) {
		this.model = model;
		
	}
	public Message process(ClientState client, Message request) {
		Node resetRequest = request.contents.getFirstChild();
		NamedNodeMap map = resetRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		game.resetBoard();
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
