package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.Server;
import server.model.Game;
import server.model.Model;
import server.model.Player;
import xml.Message;

/**
 * when server receives the resetGameRequest from client, then the server will reset the game 
 * and send the boardResponse to the client.
 * 
 * 
 * The {@link #process()} makes a boardResponse and a exitGameResponse in XML format, 
 * sends the boardResponse to all clients which in the game and sends exitGameResponse
 * to the client which sends the resetGameRequest.
 * 
 * @author Zhenyu Hu
 */

public class ResetGameRequestController {

	Model model;
	Game game;
	public ResetGameRequestController (Model model) {
		this.model = model;
		
	}
	public Message process(ClientState client, Message request) {
		Node resetRequest = request.contents.getFirstChild();
		NamedNodeMap map = resetRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		Game game = model.getGame(ID);
		game.resetBoard();
		String xmlString = Message.responseHeader(request.id()) +
				"<resetGameResponse gameId='"+ game.getGameID() +"'>" +
			  "</resetGameResponse>" +
			"</response>";
		// send this response back to the client which sent us the request.
		
		String player = new String();
		ArrayList<Player> Players = game.getPlayers();
		for (Player p : Players){
			player = player + "<player name='" + p.getName() + "' position = '"+p.getPlayerLocation().getColumn()+","+ p.getPlayerLocation().getRow() +"' board = '"+ game.getPlayerboard(p) +"' score='" + p.getScore() +"'/>" ;
		}
		
		String xmlString1 = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser = '"+ game.getManageUsername()+"' bonus ='" + game.getBoard().getBonusCell().getColumn()+","+ game.getBoard().getBonusCell().getRow()+"'>" +
			  player +
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
