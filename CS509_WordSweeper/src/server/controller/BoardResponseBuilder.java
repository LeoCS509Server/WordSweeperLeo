package server.controller;

import java.util.ArrayList;

import server.model.Board;

import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;

public class BoardResponseBuilder {
	Model model;
	String content;
	ArrayList<String> bcontent;
	
	public BoardResponseBuilder(Model m){
		this.model = m;
		this.bcontent = new ArrayList<String>();
	}

	public String build(){
		//get needed information
		Game selectedGame = model.getSelected();
		String gameId = selectedGame.getGameID();
		int size = selectedGame.checkBoardSize();
		String managingUser = selectedGame.getManageUsername();
		Board board = selectedGame.getBoard();
		content = BoardContent(board);
		Location bonusCell = board.getBonusCell();
		int bonusr = bonusCell.getRow();
		int bonusc = bonusCell.getColumn();
		String bonus = bonusc+","+bonusr;
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
		String BoardContent(Board b){
		String boardcontent = "";
		for (int r = 1; r <= b.getSize(); r++){
			for (int c = 1; c <= b.getSize(); c++){
				Location l = new Location(c,r);
				boardcontent = boardcontent + b.getCellContains(l);
				bcontent.add(b.getCellContains(l));
			}
		}
		return boardcontent;
	}
	/**construct the content of ref="player"*/
		String playerContent(Player p, String boardContent, int size){
		int row = p.getPlayerLocation().getRow();
		int column = p.getPlayerLocation().getColumn();
		int startPoint= (row-1)*size+column-1;
		String name = p.getName();
		String position = column + "," + row;
		String board = "";
		for(int i = 0; i<4;i++){
			for(int j = 0; j<4; j++){
				int s = startPoint+i*size;
				board = board+bcontent.get(s+j);
			}
		}
		int score = p.getScore();
		return "<player name='"+name+"' position='"+position+"' board='"+board+"' score='"+score+"'/>";
	}
}
