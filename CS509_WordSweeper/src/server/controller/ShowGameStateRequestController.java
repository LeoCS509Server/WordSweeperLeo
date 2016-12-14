package server.controller;



import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.ClientState;

import server.model.Model;

import xml.Message;
 
/**
 * When server receive 'showGameStateRequest' from admin, send back 'boardResponse'.
 * This class construct response message by calling the BoardResponseBuilder.
 * @author Zetian
 *
 */
public class ShowGameStateRequestController {

	Model model;
	String content;
	/**
	 * ShowGameStateRequestController constructor. 
	 * @param model
	 */
	public ShowGameStateRequestController(Model model){
		this.model = model;
	}
	/**
	 * Process request.
	 * @param client
	 * @param request
	 * @return boardResponse Message
	 */
	public Message process(ClientState client, Message request){

		//select a certain game 
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