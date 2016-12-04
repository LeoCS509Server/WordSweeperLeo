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
public class ShowGameStateRequestController extends ControllerChain {

	Model model;
	String content;
	
	public ShowGameStateRequestController(Model model){
		this.model = model;
	}
	@Override 
	public Message process(ClientState client, Message request){
		String type = request.contents.getFirstChild().getLocalName();
		if (!type.equals ("showGameStateRequest")) {
			return next.process(client,request);
		}
		
		//select certain game 
		Node showGameStateRequest = request.contents.getFirstChild();
		NamedNodeMap showGame = showGameStateRequest.getAttributes();
		String id = showGame.getNamedItem("gameId").getNodeValue();
		model.selectGame(id);
		
		//get needed information
		Game selectedGame = model.getSelected();
		String gameId = selectedGame.getGameId();
		int size = selectedGame.checkBoardSize();
		String managingUser = selectedGame.getManageUsername();
		Board board = selectedGame.getBoard();
		content = BoardContent(board);
		String bonus = "'7,7'";
		ArrayList<Player> players = selectedGame.getPlayers();
		String playerInfo = "";
		for (Player p : players){
			playerInfo = playerInfo + playerContent(p, content, size);
		}
		//construct xml response message
		String xmlString = Message.responseHeader(request.id())+
				"<boardResponse " + "gameId='" +gameId+"' size='"+ size +"' managingUser='"+managingUser+"' contents='"
				+ content +"' bonus='"+bonus+"'>"+
				playerInfo+
				"</boardResponse>"+
				"</response>";
		Message message = new Message(xmlString);
		return message;
	}
	/**construct the content of name="content"*/
	private String BoardContent(Board g){
		String content = "";
		for (int i = 0; i < g.getSize(); i++){
			for (int j = 0; j < g.getSize(); j++){
				Location l = new Location(i,j);
				content += g.getCellContains(l);
			}
		}
		return content;
	}
	/**construct the content of ref="player"*/
	private String playerContent(Player p, String boardContent, int size){
		int row = p.getPlayerLocation().getRow();
		int column = p.getPlayerLocation().getColumn();
		int startPoint= row*size+column;
		String name = p.getName();
		String position = row + "," + column;
		String board = boardContent.substring(startPoint, startPoint+17);
		int score = p.getScore();
		return "<player name='"+name+"' position='"+position+"' board='"+board+"' score='"+score+"'/>";
	}


}
