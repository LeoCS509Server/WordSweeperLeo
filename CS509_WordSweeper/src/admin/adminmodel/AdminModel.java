package admin.adminmodel;

import java.util.ArrayList;


public class AdminModel {
	public static ArrayList<Game> gamelist= new ArrayList<Game>();
	
	
	public static Game getGame(String GameID){
		for(Game g: gamelist){
			if(g.gameid == GameID){
				return g;
			}			
		}
		return null;		
	}
	
}
