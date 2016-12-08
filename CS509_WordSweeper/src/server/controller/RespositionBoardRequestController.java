package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;
import xml.Message;
public class RespositionBoardRequestController {

	Model model;
	public RespositionBoardRequestController (Model model) {
		this.model = model;
	}
	public Message process(ClientState client, Message request) {
		Node repositionBoardRequest = request.contents.getFirstChild();
		NamedNodeMap map = repositionBoardRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		String name = map.getNamedItem("name").getNodeValue();
		
		String rc = map.getNamedItem("rowChange").getNodeValue();
		String cc = map.getNamedItem("colChange").getNodeValue();
		int rowchange = Integer.valueOf(rc).intValue();
		int colchange = Integer.valueOf(cc).intValue();
		Location location = game.rePosition(game.getPlayer(name), colchange, rowchange);
		
		String player = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player = player + "<player name='" + p.getName() + "' lcoation = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse "+ player +" gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus = '" +location.getColumn() +","+ location.getRow() +"' >" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
