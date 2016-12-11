package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import server.ClientState;
import server.model.Cell;
import server.model.Game;
import server.model.Model;
import util.Location;
import xml.Message;

public class FindWordRequestController {
	Model model;
	public FindWordRequestController (Model model) {
		this.model = model;
	}
	public Message process(ClientState client, Message request) {
		Node findwordRequest = request.contents.getFirstChild();
		NamedNodeMap map = findwordRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		String name = map.getNamedItem("name").getNodeValue();
		String word = map.getNamedItem("word").getNodeValue();
		Game game = model.getGame(ID);
		
// 		NodeList list = findwordRequest.getChildNodes();
// 		ArrayList<Cell> cells = new ArrayList<Cell>();
// 		for (int i = 0; i < list.getLength(); i++) {
// 			Node n = list.item(i);
// 			String position = n.getAttributes().getNamedItem("position").getNodeValue();
// 			Location l = new Location(position);
// 			Cell c = new Cell(l);
// 			cells.add(c);
// 		}
		
		NodeList list = findwordRequest.getChildNodes();
		ArrayList<Location> loc = new ArrayList<Location>();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String position = n.getAttributes().getNamedItem("position").getNodeValue();
			Location l = new Location(position);
			loc.add(l);
		}
		
		
		
		String xmlString = Message.responseHeader(request.id()) +
				"<findWordResponse gameId='"+ game.getGameID() +"' name='"+ name +"' score='"+ game.calculateScore(word,cells) +"'>" +
			  "</findWordResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
