package server.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.IProtocolHandler;
import server.model.ServerModel;
import xml.Message;
import server.model.Game;
import java.util.Random;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {
	Game game;
	ServerModel model;
	
	public CreateGameRequestController (ServerModel model, Game game) {
		this.model = model;
		this.game = game;
	}
	
	public Message process(ClientState client, Message request) {
		
		model.joinGame();  // HACK.
		
		// note you can retrieve information from the request...
		Node createRequest = request.contents.getFirstChild();
		NamedNodeMap map = createRequest.getAttributes();
		
		String pname = map.getNamedItem("name").getNodeValue();
		Random random=new java.util.Random();
		//String [][] strArray = new String [7][7];
		String temp =new String("");
		String content =new String("");
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				temp = String.valueOf((char)(random.nextInt(26)+65));
				model.setstrArray(i, j, temp);
			}
			
		}
		
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				content = content + model.getstrArray(i, j);
			}
		}
		int x = random.nextInt(3);
		int y = random.nextInt(3);
		String player_content =new String("");
		for(int i= y;i<y+4;i++)
		{
			for(int j=x;j<x+4;j++)
			{
				player_content += model.getstrArray(i, j);
			}
		}
		// Construct message reflecting state
		String xmlString = Message.responseHeader(request.id()) +
				"<boardResponse gameId='"+ game.getGameID() +"' managingUser='" + pname + "' bonus='7,7' contents='" + content + "'>" +
			      "<player name='" + pname + "' score='0' position='"+ x +","+ y +"' board='"+ player_content +"'/>" +
			  "</boardResponse>" +
			"</response>";
		
		// send this response back to the client which sent us the request.
		return new Message (xmlString);
	}
}
