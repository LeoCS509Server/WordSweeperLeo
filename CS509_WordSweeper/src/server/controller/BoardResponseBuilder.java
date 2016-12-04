package server.controller;

import java.util.ArrayList;

import server.model.Board;
import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;

public class BoardResponseBuilder {
	Model model;
	
	public BoardResponseBuilder(Model m){
		this.model = m;
	}

	public String build(){
		//get needed information
		Game selectedGame = model.getSelected();
		String gameId = selectedGame.getGameID();
		int size = selectedGame.checkBoardSize();
		String managingUser = selectedGame.getManageUsername();
		Board board = selectedGame.getBoard();
		String content = BoardContent(board);
		String bonus = "'7,7'";
		ArrayList<Player> players = selectedGame.getPlayers();
		String playerInfo = "";
		for (Player p : players){
			playerInfo = playerInfo + playerContent(p, content, size);
		}
		
		
		
		
		//construct xml response message
		String xmlString =
				"<boardResponse " + "gameId='" +gameId+"' size='"+ size +"' managingUser='"+managingUser+"' contents='"
				+ content +"' bonus='"+bonus+"'>"+
				playerInfo+
				"</boardResponse>"+
				"</response>";
		return xmlString;
	}
	/**construct the content of name="content"*/
	private String BoardContent(Board g){
		String content = "";
		for (int i = 1; i <= g.getSize(); i++){
			for (int j = 1; j <= g.getSize(); j++){
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
