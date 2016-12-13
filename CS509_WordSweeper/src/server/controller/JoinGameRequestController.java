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
import xml.Message;

/**
 * when server receives the joinGameRequest from client, then the server will determine whether the game is exit,
 * the game is locked and the password is correct.
 * If all of it are correct the server will add the client to the game player and sent the boardResponse to all client 
 * else the server will send the joinGameresponse to the client and tell it why fail.
 * 
 * 
 * The {@link #process()} makes a boardResponse or a joinGameResponse in XML format.
 * 
 * @author Zhenyu Hu
 * 
 */
public class JoinGameRequestController implements IProtocolHandler {

	Model model;

	public JoinGameRequestController(Model model) {
		this.model = model;
	}
	/** Make a boardResponse or a joinGameResponse in XML format. */
	public Message process(ClientState client, Message request) {
		String xmlString = new String();

		Node joinGameRequest = request.contents.getFirstChild();
		NamedNodeMap map = joinGameRequest.getAttributes();
		String ID = map.getNamedItem("gameId").getNodeValue();
		String password = map.getNamedItem("password").getNodeValue();
		String name = map.getNamedItem("name").getNodeValue();

		// ArrayList<Game> games=model.getGames();
		// if(model.getGamenames().contains(ID)){
		
		for (Game g : model.getGames()) {
			if (g.getGameID().equals(ID) && g.isactived()) {
				if (!g.checkisLocked()) {
					if (model.isPasswordCorrect(ID, password)) {
						g.addPlayer(name,client.id());
						String player = new String();
						ArrayList<Player> Players = g.getPlayers();
						for (Player p : Players) {
							player = player + "<player name='" + p.getName() + "' position = '"
									+ p.getPlayerLocation().getColumn() + "," + p.getPlayerLocation().getRow()
									+ "' board = '" + g.getPlayerboard(p) + "' score='" + p.getScore() + "'/>";
						}
						// Construct message reflecting state

						xmlString = Message.responseHeader(request.id()) + "<boardResponse  gameId='" + g.getGameID()
								+ "' managingUser = '" + g.getManageUsername() + "' bonus = '"
								+ g.getBoard().getBonusCell().getColumn() + "," + g.getBoard().getBonusCell().getRow()
								+ "' >" + player + "</boardResponse>" + "</response>";
						break;
					} else {
						xmlString = Message.responseHeader(request.id(), "password is incorrect")
								+ "<joinGameResponse gameId='" + g.getGameID() + "'>" + "</joinGameResponse>"
								+ "</response>";
						break;
					}
				} else {
					xmlString = Message.responseHeader(request.id(), "game is locked")
							+ "<joinGameResponse gameId='" + g.getGameID() + "'>" + "</joinGameResponse>"
							+ "</response>";
					break;

				}
			}
			xmlString = Message.responseHeader(request.id(), "game is not exist") + "<joinGameResponse gameId='" + ID + "'>"
					+ "</joinGameResponse>" + "</response>";
		}
		

		
		Message message = new Message(xmlString);
		// all other players on game (excepting this particular client) need to
		// be told of this
		// same response. Note this is inefficient and should be replaced by
		// more elegant functioning
		// hint: rely on your game to store player names...
		Game game=model.getGame(ID);
		for (Player p : game.getPlayers()) {
			for (String id : Server.ids()) {
				if (!id.equals(client.id()) && id.equals(p.getClientId())) {
					
						Server.getState(id).sendMessage(message);
					
				}
			}
		}

		// send this response back to the client which sent us the request.
		return message;
	}
}