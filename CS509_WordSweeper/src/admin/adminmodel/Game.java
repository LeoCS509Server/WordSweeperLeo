package admin.adminmodel;

import java.util.ArrayList;
import java.util.Iterator;
import util.ContentsIterator;

/**
 *Game entity class, which contains all the info and functions about the game.  
 * 
 * @author Weihao Li,Zetian Wang (Authors contribute equally)
 *
 */

public class Game {
	String gameid;
	String[][] globalboard;
	int size;
	int k=0;
	
	
	 ArrayList<Integer> playerlocation;
	 ArrayList<String> playerid;
	 ArrayList<Integer> score;
	
	/**Game constructor*/
	public Game(String id, int size){  
		this.gameid = id;
		this.size = size;
		globalboard=new String[size][size];
		playerlocation = new ArrayList<Integer>();
		playerid =new ArrayList<String>();
		score = new ArrayList<Integer>();		
	}
	
	/**
	 * the setter for playerlocation attribute.
	 * @param playerlocation
	 */
	public void setPlayerlocation(int location){
		this.playerlocation.add(location);
	}
	
	/**
	 * the setter for playerid attribute.
	 * @param playerid
	 */
	public void setPlayerid(String id){
		this.playerid.add(id);
	}
	
	/**
	 * the setter for score attribute.
	 * @param score
	 */
	public void setScore(int score){
		this.score.add(score);
	}
	
	/**
	 * the setter for board attribute.
	 * @param letters
	 * @param board size
	 */
	public void setBoard(String string,int size){
		String[] boardc = string.split(",");
		this.size=size;
		int max = boardc.length;
		int i = 0;
		for(Iterator<String> it = new ContentsIterator(boardc,max);it.hasNext();){
			globalboard[i/size][i%size] = (String) it.next();
			i++;
		}
	}
	
	/**
	 * the getter for playerlocation attribute.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getPlayerlocation(){
		return playerlocation;
	}
	
	/**
	 * the getter for playerid attribute.
	 * 
	 * @return
	 */
	public ArrayList<String> getPlayerid(){
		return playerid;
	}
	
	/**
	 * the getter for score attribute.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getScore(){
		return score;
	}
	
	/**
	 * the getter for size attribute.
	 * 
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * the getter for globalboard attribute.
	 * 
	 * @return
	 */
	public String[][] getGlobalboard(){
		return globalboard;
	}
	
	/**
	 * the getter for gameid attribute.
	 * 
	 * @return
	 */
	public String getGameid(){
		return gameid;
	}
}


