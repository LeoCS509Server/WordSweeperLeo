package admin.adminmodel;

import java.util.ArrayList;


public class Game {

	
	String gameid;
	String[][] globalboard;
	int size;
	int k=0;
	
	
	 ArrayList<Integer> playerlocation= new ArrayList<>();
	 ArrayList<String> playerid= new ArrayList<>();
	 ArrayList<Integer> score= new ArrayList<>();
	

	
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
