package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.Model;
import server.model.Player;
import util.Location;
import xml.Message;
import server.model.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {
	Model model;

	
	public CreateGameRequestController (Model model) {
	
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		Node createGameRequest = request.contents.getFirstChild();
		NamedNodeMap map = createGameRequest.getAttributes();
		String name = map.getNamedItem("name").getNodeValue();
		String password = map.getNamedItem("password").getNodeValue();
		Game game = new Game(name);
		model.addGame(game);
		game.setPassword(password);
		game.addPlayer(name);
		
		String player = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player = player + "<player name='" + p.getName() + "' lcoation = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		// Construct message reflecting state
	    Location location = game.getPlayer(name).getPlayerLocation();
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse "+ player +" gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus = '" +location.getColumn() +","+ location.getRow() +"' >" +
			  "</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);

	}
}
