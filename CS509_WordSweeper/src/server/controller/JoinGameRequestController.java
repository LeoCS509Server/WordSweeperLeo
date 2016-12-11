package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.Server;
import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;
import xml.Message;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class JoinGameRequestController implements IProtocolHandler {

	Model model;
	public JoinGameRequestController (Model model) {
		this.model = model;
	}
	
	public Message process(ClientState client, Message request) {
		String xmlString = new String();
		
		Node joinGameRequest = request.contents.getFirstChild();
		NamedNodeMap map = joinGameRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		String password = map.getNamedItem("password").getNodeValue();
		String name = map.getNamedItem("name").getNodeValue();
		
		Game game = model.getGame(ID);
		game.addPlayer(name);
		
		String player = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player = player + "<player name='" + p.getName() + "' position = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		// Construct message reflecting state
		 Location location = game.getPlayer(name).getPlayerLocation();
		if(!game.getGameID().equals(ID)){
			xmlString = Message.responseHeader(request.id(),"game is not exist") +"<joinGameResponse gameId='"+ game.getGameID() +"'>" +
					  "</joinGameResponse>" +
					"</response>";
		}	
		else if(game.checkisLocked())
		{
			xmlString = Message.responseHeader(request.id(),"game is locked") +"<joinGameResponse gameId='"+ game.getGameID() +"'>" +
					  "</joinGameResponse>" +
					"</response>";
		}
		else if(!model.isPasswordCorrect(ID, password)){
			xmlString = Message.responseHeader(request.id(),"password is incorrect") +"<joinGameResponse gameId='"+ game.getGameID() +"'>" +
			  "</joinGameResponse>" +
			"</response>";
		
		}
		else{
			
			 xmlString = Message.responseHeader(request.id()) +
					"<boardResponse  gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus = '" +game.getBoard().getBonusCell().getColumn()+","+ game.getBoard().getBonusCell().getRow()+"' >" +
				  player+
					"</boardResponse>" +
				"</response>";
		}
		Message message = new Message (xmlString);
		// all other players on game (excepting this particular client) need to be told of this
		// same response. Note this is inefficient and should be replaced by more elegant functioning
		// hint: rely on your game to store player names...
		for (String id : Server.ids()) {
			if (!id.equals(client.id())) {
				Server.getState(id).sendMessage(message);
			}
		}

		// send this response back to the client which sent us the request.
		return message;
	}
}
