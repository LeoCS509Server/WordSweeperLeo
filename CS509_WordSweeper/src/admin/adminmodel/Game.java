package admin.adminmodel;

import java.util.ArrayList;

import server.model.Board;

public class Game {

	
	static String gameid;
	static String[][] globalboard;
	static int size;
	int k=0;
	
	
	 static ArrayList playerlocation= new ArrayList();
	 static ArrayList playerid= new ArrayList();
	 static ArrayList score= new ArrayList();
	

	
	public void setPlayerlocation(String string){
		this.playerlocation.add(Integer.parseInt(string));
	}
	
	public void setPlayerid(String id){
		this.playerid.add(id);
	}
	
	public void setScore(int score){
		this.score.add(score);
	}
	
	public void setBoard(String string,int size){
		this.size=size;
		globalboard=new String[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				globalboard[i][j]=string.substring(k,k+1);
				k++;
			}
		}
	
	
	}
	
	public static ArrayList getPlayerlocation(){
		return playerlocation;
	}
	
	public static ArrayList getPlayerid(){
		return playerid;
	}
	
	public static ArrayList getScore(){
		return score;
	}
	
	public static int getSize(){
		return size;
	}
	
	public static String[][] getGlobalboard(){
		return globalboard;
	}

	public static String getGameid(){
		return gameid;
	}	
}
