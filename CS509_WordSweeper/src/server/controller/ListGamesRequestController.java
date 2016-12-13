package server.controller;

import java.util.ArrayList;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * deal with listGames request in server
 */
public class ListGamesRequestController {
	
	Model model;
	
	public ListGamesRequestController(Model model){
		this.model = model;
	}
	
	public Message process(ClientState client, Message request){
		
//		String gameBrief = new String("");
//		ArrayList<Game> games = model.getGames();
//		for (Game g : games){
//			String gameID = g.getGameID();
//			int players = g.getNumPlayers();
//			gameBrief = gameBrief + "<gameBrief gameId='" + gameID + "' players='" + players +"'/>" ;
//		}
		String gameBrief = new String("");
		model.refreshGameList();
		ArrayList<Game> games = model.getGames();
		for (Game g : games){
			String gameID = g.getGameID();
			int players = g.getNumPlayers();
			gameBrief = gameBrief + "<gameBrief gameId='" + gameID + "' players='" + players +"'/>" ;
		}
		
		/** construct response message for listGame*/
		String xmlString = Message.responseHeader(request.id()) +
			"<listGamesResponse>" +	
			gameBrief+
			"</listGamesResponse>"+
			"</response>";
		
		Message message = new Message (xmlString);
		return message;
	}
	

}
