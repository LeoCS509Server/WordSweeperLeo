package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * when server receive the resetGameRequest from client, then the server will reset the game 
 * and send the resetGameResponse to the client.
 * 
 * 
 * The {@link #process()} makes a resetGameResponse in XML format, reset the game and send it
 * to the client.
 */

public class ResetGameRequestController {

	Model model;
	Game game;
	public ResetGameRequestController (Model model) {
		this.model = model;
		
	}
	
	/** Make a resetGameResponse in XML format, reset the game and send it to the client. */
	
	public Message process(ClientState client, Message request) {
		Node resetRequest = request.contents.getFirstChild();
		NamedNodeMap map = resetRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		game.resetBoard();
		String xmlString = Message.responseHeader(request.id()) +
				"<resetGameResponse gameId='"+ game.getGameID() +"'>" +
			  "</resetGameResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
