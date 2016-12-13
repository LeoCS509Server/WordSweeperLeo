package server.controller;



import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;

import server.model.Model;

import xml.Message;
/**
 * deal with the showgamestate request in server side 
 */
/**
 * 
 * @author Zetian
 *
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

		
		BoardResponseBuilder builder = new BoardResponseBuilder(model);
		//construct xml response message
		String xmlString = Message.responseHeader(request.id())+builder.build(id);
		Message message = new Message(xmlString);
		return message;
	}
}