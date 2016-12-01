package server.controller;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class RespositionBoardRequestController {

	Model model;
	Game game;
	public RespositionBoardRequestController (Model model, Game game) {
		this.model = model;
		this.game = game;
	}
	public Message process(ClientState client, Message request) {
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManagingUser() +"' bonus = '" + game.getBonus() +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
