package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import util.Location;
import xml.Message;
public class RespositionBoardRequestController {

	Model model;
	public RespositionBoardRequestController (Model model) {
		this.model = model;
	}
	public Message process(ClientState client, Message request) {
		Node exitRequest = request.contents.getFirstChild();
		NamedNodeMap map = exitRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		Location location = game.getBoard().BonusCell();
		
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername() +"' bonus = '" + location.getColumn() +","+ location.getRow() +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
