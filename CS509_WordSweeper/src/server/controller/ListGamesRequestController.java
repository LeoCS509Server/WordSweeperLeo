package server.controller;

import java.util.ArrayList;

import server.ClientState;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * When server receive 'listGamesRequest' from admin, send back 'listGameResponse'.
 * This class construct response based on xml transmission protocol. 
 * @author Zetian
 *
 */
public class ListGamesRequestController {
	
	Model model;
	/**
	 * ListGamesRequestContrllor constructor.
	 * @param model
	 */
	public ListGamesRequestController(Model model){
		this.model = model;
	}
	/**
	 * Process request.
	 * @param client
	 * @param request
	 * @return listGameResponse
	 */
	public Message process(ClientState client, Message request){
		

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
