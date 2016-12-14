package server.controller;

import java.util.ArrayList;

import server.model.Board;

import server.model.Game;
import server.model.Model;
import server.model.Player;
import util.Location;
/**
 * Since many request need 'boardResponse', separate the xml message builder.
 * This class may not be used.  
 * @author Zetian
 *
 */
public class BoardResponseBuilder {
	Model model;
	String content;
	/**
	 * Constructor
	 * @param m
	 */
	public BoardResponseBuilder(Model m){
		this.model = m;
	}
	/**
	 * Obtain information from entity classed and construct xmlString,
	 * which is the content of a message except the header.  
	 * @param ID
	 * @return xmlString
	 */
	public String build(String ID){
		Game selectedGame = model.getGame(ID);
		String gameId = ID;
		int size = selectedGame.getBoard().getSize();
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
	/**
	 * This function is an internal function to make up the 'contents' node of 'boardResponse'
	 * @param b
	 * @return boardcontent
	 */
		String BoardContent(Board b){
		String boardcontent = "";
		for (int r = 1; r <= b.getSize(); r++){
			for (int c = 1; c <= b.getSize(); c++){
				Location l = new Location(c,r);
				boardcontent = boardcontent + b.getCellContains(l)+",";
			}
		}
		return boardcontent;
	}
		/**
		 * This function is a internal function to make up 'player' nodes of 'boardResponse'
		 * @param p
		 * @param bContent
		 * @param size
		 * @return playercontent
		 */
		String playerContent(Player p, String bContent, int size){
			String[] bcontents = bContent.split(",");
			int row = p.getPlayerLocation().getRow();
			int column = p.getPlayerLocation().getColumn();
			int startPoint= (row-1)*size+column-1;
			String name = p.getName();
			String position = column + "," + row;
			String board = "";
			for(int i = 0; i<4;i++){
				for(int j = 0; j<4; j++){
					int s = startPoint+i*size;
					board = board+bcontents[s+j]+",";
				}
			}
			int score = p.getScore();
			return "<player name='"+name+"' position='"+position+"' board='"+board+"' score='"+score+"'/>";
	}
}
