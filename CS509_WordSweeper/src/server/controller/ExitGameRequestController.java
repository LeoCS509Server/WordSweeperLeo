package server.controller;
import server.ClientState;

import server.model.ServerModel;
import server.model.Game;
import xml.Message;
public class ExitGameRequestController {

	ServerModel model;
	Game game;
	public ExitGameRequestController (ServerModel model, Game game) {
		this.model = model;
		this.game = game;
	}
	public Message process(ClientState client, Message request) {
		model.exitGame();
		
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
