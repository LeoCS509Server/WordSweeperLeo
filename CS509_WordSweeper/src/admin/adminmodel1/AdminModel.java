package admin.adminmodel1;

import java.util.ArrayList;


public class AdminModel {
	public static ArrayList<Game> gamelist;
	String idlist;
	
	
	public AdminModel(){
		AdminModel.gamelist = new ArrayList<Game>();
		this.idlist = null;
	}
	
	public void getIdlist(String s){
		idlist = s;
	}
	public String obtainIdList(){
		return idlist;
	}
	
	public void addGame(Game game){
		gamelist.add(game);		
	}
	
	public void removeGame(String ID){
		for(Game g: gamelist){
			if(g.gameid.equals(ID)){
				gamelist.remove(g);
				break;
			}			
		}
	}
	
	public boolean isExist(String ID){
		boolean ishere = false;
		for(Game g: gamelist){
			if(g.gameid.equals(ID)){
				ishere = true;
			}			
		}
		return ishere;	
		
	}
	public static Game getGame(String GameID){
		for(Game g: gamelist){
			if(g.gameid.equals(GameID)){
				return g;
			}			
		}
		return null;		
	}
	
}
