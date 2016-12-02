package admin.adminmodel;

import java.util.ArrayList;

import server.model.Board;

public class Game {

	
	String gameid;
	public static String[][] globalboard;
	public static int size;
	int k=0;
	
	
	public static ArrayList playerlocation= new ArrayList();
	public static ArrayList playerid= new ArrayList();
	public static ArrayList score= new ArrayList();
	

	
	public void getPlayerlocation(String string){
		this.playerlocation.add(Integer.parseInt(string));
	}
	
	public void getplayerid(String id){
		this.playerid.add(id);
	}
	
	public void getscore(int score){
		this.score.add(score);
	}
	
	public void getBoard(String string,int size){
		this.size=size;
		globalboard=new String[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				globalboard[i][j]=string.substring(k,k+1);
				k++;
			}
		}
	
	
	}	
}
