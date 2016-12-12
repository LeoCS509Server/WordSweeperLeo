package admin.adminmodel;

import java.util.ArrayList;
import java.util.Iterator;

import util.ContentsIterator;

public class Game {
	String gameid;
	String[][] globalboard;
	int size;
	int k=0;
	
	
	 ArrayList<Integer> playerlocation;
	 ArrayList<String> playerid;
	 ArrayList<Integer> score;
	
	public Game(String id, int size){  
		this.gameid = id;
		this.size = size;
		globalboard=new String[size][size];
		playerlocation = new ArrayList<Integer>();
		playerid =new ArrayList<String>();
		score = new ArrayList<Integer>();		
	}

	public void setPlayerlocation(int location){
		this.playerlocation.add(location);
	}
	
	public void setPlayerid(String id){
		this.playerid.add(id);
	}
	
	public void setScore(int score){
		this.score.add(score);
	}
	
	public void setBoard(String string,int size){
		String[] boardc = string.split(",");
		this.size=size;
		int max = boardc.length;
		int i = 0;
		for(Iterator<String> it = new ContentsIterator(boardc,max);it.hasNext();){
			globalboard[i/size][i%size] = (String) it.next();
			i++;
		}
		/**
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				globalboard[i][j]=string.substring(k,k+1);
				k++;
			}
		}**/
	
	
	}
	
	public ArrayList<Integer> getPlayerlocation(){
		return playerlocation;
	}
	
	public ArrayList<String> getPlayerid(){
		return playerid;
	}
	
	public ArrayList<Integer> getScore(){
		return score;
	}
	
	public int getSize(){
		return size;
	}
	
	public String[][] getGlobalboard(){
		return globalboard;
	}

	public String getGameid(){
		return gameid;
	}
}


