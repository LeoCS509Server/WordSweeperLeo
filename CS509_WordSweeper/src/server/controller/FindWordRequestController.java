package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.Server;
import server.model.Game;
import server.model.Model;
import server.model.Player;
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
		model.selectGame(ID);
		Player player = game.getPlayer(name);
		

		ArrayList<Location> loc = new ArrayList<Location>();
		Node pos = findwordRequest.getFirstChild();
		while (pos != null){
			NamedNodeMap mappos = pos.getAttributes();
			String position = mappos.getNamedItem("position").getNodeValue();
			
			Location l = new Location(position);
			loc.add(l);
			pos = pos.getNextSibling();
		}
				
		int sc = game.calculateScore(word,loc);
		model.getGame(ID).getPlayer(name).addScore(sc);

		if(sc!=0){
			player.addScore(sc);
			game.getBoard().removeWord(loc);
			game.getBoard().refreshBoard();
		}
		// send this response back to the client which sent us the request.
		String xmlString = Message.responseHeader(request.id()) +
				"<findWordResponse gameId='"+ ID +"' name='"+ name +"' score='"+sc+"'>" +
			  "</findWordResponse>" +
			"</response>";
	
		//construct xml response message
		String player1 = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player1 = player1 + "<player name='" + p.getName() + "' position = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		
		String xmlString1 = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus ='" + game.getBoard().getBonusCell().getColumn()+","+ game.getBoard().getBonusCell().getRow()+"'>" +
			  player1 +
				"</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		Message message = new Message(xmlString1);
		for (Player p : game.getPlayers()) {
			for (String id : Server.ids()) {
				if (id.equals(p.getClientId())) {
					
						Server.getState(id).sendMessage(message);
					
				}
			}
		}

		return new Message (xmlString);
		
		

	}
}
