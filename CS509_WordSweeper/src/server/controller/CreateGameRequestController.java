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

/**
 * when server receive the createGameRequest from client, then the server will create a game 
 * and send the boardResponse to the client.
 * 
 * 
 * The {@link #process()} makes a boardResponse in XML format, create a game and send it
 * to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {
	Model model;

	
	public CreateGameRequestController (Model model) {
	
		this.model = model;
	}
	/** Make a boardResponse in XML format, create a game and send it to the client. */
	public Message process(ClientState client, Message request) {
		Node createGameRequest = request.contents.getFirstChild();
		NamedNodeMap map = createGameRequest.getAttributes();
		String name = map.getNamedItem("name").getNodeValue();
		String password = map.getNamedItem("password").getNodeValue();
		Game game = new Game(name,client.id());
		String ggid = game.getGameID();
		model.addGame(game);
		game.setPassword(password);
		model.selectGame(ggid);
		
//		BoardResponseBuilder builder = new BoardResponseBuilder(model);
//		//construct xml response message
//		String xmlString = Message.responseHeader(request.id())+builder.build();
//		Message message = new Message(xmlString);
//		return message;
		
		
		String player = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player = player + "<player name='" + p.getName() + "' position = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		// Construct message reflecting state

	    String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus = '" +game.getBoard().getBonusCell().getColumn()+","+ game.getBoard().getBonusCell().getRow() +"' >" +
			  player +
				"</boardResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		return new Message (xmlString);

	}
}
