package server.controller;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;
import server.model.Board;
import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;
import xml.Message;
/**
 * deal with the showgamestate request in server side 
 */
public class ShowGameStateRequestController {

	Model model;
	String content;
	
	public ShowGameStateRequestController(Model model){
		this.model = model;
	}

	public Message process(ClientState client, Message request){

		//select certain game 
		Node showGameStateRequest = request.contents.getFirstChild();
		NamedNodeMap showGame = showGameStateRequest.getAttributes();
		String id = showGame.getNamedItem("gameId").getNodeValue();
		model.selectGame(id);
		
		BoardResponseBuilder builder = new BoardResponseBuilder(model);
		//construct xml response message
		String xmlString = Message.responseHeader(request.id())+builder.build();
		Message message = new Message(xmlString);
		return message;
	}
}
