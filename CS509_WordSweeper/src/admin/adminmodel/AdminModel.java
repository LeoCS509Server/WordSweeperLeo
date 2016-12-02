package admin.adminmodel;

import java.util.ArrayList;
import java.util.Hashtable;


public class AdminModel {
	ArrayList<Game> gamelist= new ArrayList<Game>();
	Game game;
	
	
	
	
	
	public void refresh(Game GameID){  ///////
		gamelist.add(GameID);	
	}
	
	
	
	
	
	public Game getGame(){
		return game;
	}
	


	

   
}
