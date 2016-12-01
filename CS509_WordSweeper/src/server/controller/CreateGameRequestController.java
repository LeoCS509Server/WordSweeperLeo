package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.Model;
import xml.Message;
import server.model.Game;
import java.util.Random;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {
	Game game;

	
	public CreateGameRequestController (Game game) {
	
		this.game = game;
	}
	
	public Message process(ClientState client, Message request) {
		

		// Construct message reflecting state
		
		
		// send this response back to the client which sent us the request.
		return new Message ("");
	}
}
