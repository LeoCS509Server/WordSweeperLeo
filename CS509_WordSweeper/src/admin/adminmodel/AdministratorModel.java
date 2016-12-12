package admin.adminmodel;

import java.util.ArrayList;


public class AdministratorModel {
	public static ArrayList<Game> gamelist;
	public static ArrayList<String> idlist;
	
	
	
	public AdministratorModel(){
		AdministratorModel.gamelist = new ArrayList<Game>();
		AdministratorModel.idlist=new ArrayList<String>();
	}
	
	public void setIdlist(String s){
		idlist.add(s) ;
	}
	public ArrayList<String> obtainIdList(){
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
