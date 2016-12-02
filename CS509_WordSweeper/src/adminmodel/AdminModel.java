package adminmodel;

import java.util.ArrayList;
import java.util.Hashtable;


public class AdminModel {
	ArrayList<Game> gamelist= new ArrayList<Game>();
	Game currentGame;
	
	public AdminModel(){
		this.currentGame=null;
	}
	
	public void clearlist(){
		gamelist.clear();
	}
	
	public void refresh(Game GameID){  ///////
		gamelist.add(GameID);	
	}
	
	
	
	
	public void setGame(Game game){    ////////
		currentGame=game;
	}
	
	public Game getGame(){
		return currentGame;
	}
	


	

   
}
