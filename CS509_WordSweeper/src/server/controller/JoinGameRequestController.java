package server.controller;

import java.util.Random;

import server.ClientState;
import server.IProtocolHandler;
import server.Server;
import server.model.Game;
import server.model.Model;
import xml.Message;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class JoinGameRequestController implements IProtocolHandler {

	Model model;
	Game game;
	public JoinGameRequestController (Model model, Game game) {
		this.model = model;
		this.game = game;
	}
	
	public Message process(ClientState client, Message request) {
		
		model.joinGame(); 
		Random random=new java.util.Random();
		String otherPlayers = "";
		String content =new String("");
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				content = content + model.getstrArray(i, j);
			}
		}
		for (int i = 0; i < model.getNumPlayers(); i++) {
			int x = random.nextInt(3);
			int y = random.nextInt(3);
			String player_content =new String("");
			for(int m= y;m<y+4;m++)
			{
				for(int n=x;n<x+4;n++)
				{
					player_content += model.getstrArray(m, n);
				}
			}
			otherPlayers += "<player name='player" + i + "' score='0' position='"+ x +","+ y +"' board='"+ player_content +"'/>";
		}
		
		// Construct message reflecting state
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='hg12jhd' managingUser='player0' bonus='4,3' contents='"+ content +"'>" +
			      otherPlayers +
			  "</boardResponse>" +
			"</response>";
		
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
