package server.controller;

import java.util.Random;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class JoinGameRequestController implements IProtocolHandler {

	Model model;
	Game game;
	public JoinGameRequestController (Model model) {
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		 
		Node exitRequest = request.contents.getFirstChild();
		NamedNodeMap map = exitRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		String password = map.getNamedItem("password").getNodeValue();
		String name = map.getNamedItem("name").getNodeValue();
		
		Game game = model.getGame(ID);
		game.addPlayer(name);
		// Construct message reflecting state
		
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"'>" +
			  "</boardResponse>" +
			"</response>";
		Message message = new Message (xmlString);
		
		// all other players on game (excepting this particular client) need to be told of this
		// same response. Note this is inefficient and should be replaced by more elegant functioning
		// hint: rely on your game to store player names...
		for (String id : Server.ids()) {
			if (!id.equals(client.id())) {
				Server.getState(id).sendMessage(message);
			}
		}

		// send this response back to the client which sent us the request.
		return message;
	}
}
