package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Cell;
import server.model.Game;
import server.model.Model;
import xml.Message;

public class FindWordRequestController {
	Model model;
	public FindWordRequestController (Model model) {
		this.model = model;
	}
	public Message process(ClientState client, Message request) {
		Node exitRequest = request.contents.getFirstChild();
		NamedNodeMap map = exitRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		String name = map.getNamedItem("name").getNodeValue();
		String word = map.getNamedItem("word").getNodeValue();
		
		Node cellrequest = exitRequest.getFirstChild();
		NamedNodeMap cellmap = cellrequest.getAttributes();
		
		
		Game game = model.getGame(ID);
		
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' name='"+ name +"' score='"+ game.calculateScore(word,) +"'>" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
