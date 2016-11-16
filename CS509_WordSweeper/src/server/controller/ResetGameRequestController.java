package server.controller;

import server.ClientState;
import server.model.Game;
import server.model.ServerModel;
import xml.Message;

public class ResetGameRequestController {

	ServerModel model;
	Game game;
	public ResetGameRequestController (ServerModel model, Game game) {
		this.model = model;
		this.game = game;
	}
	public Message process(ClientState client, Message request) {
		game.resetBoard();
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
