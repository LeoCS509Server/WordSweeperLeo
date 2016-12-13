package admin.adminmodel;

import java.util.ArrayList;

/**
 * adminmodel entity class, which contains all the info and functions about the adminmodel.  
 * 
 * @author Weihao Li (Authors contribute equally)
 *
 */
public class AdministratorModel {
	public static ArrayList<Game> gamelist;
	public static ArrayList<String> idlist;
	
	/**Adminmodel constructor*/
	public AdministratorModel(){
		AdministratorModel.gamelist = new ArrayList<Game>();
		AdministratorModel.idlist=new ArrayList<String>();
	}
	/**set the idlist attribute 
	 * @param idlist
	 */
	public void setIdlist(String s){
		idlist.add(s) ;
	}
	
	/**
	 * the getter for idlist attribute.
	 * 
	 * @return
	 */
	public ArrayList<String> obtainIdList(){
		return idlist;
	}
	
	/**
	 * add game object in gamelist
	 * 
	 */
	public void addGame(Game game){
		gamelist.add(game);		
	}
	
	/**
	 * remove game object from gamelist
	 */
	public void removeGame(String ID){
		for(Game g: gamelist){
			if(g.gameid.equals(ID)){
				gamelist.remove(g);
				break;
			}			
		}
	}
	
	/**
	 * check the gameid is already in gamelist
	 *@return
	 */
	public boolean isExist(String ID){
		boolean ishere = false;
		for(Game g: gamelist){
			if(g.gameid.equals(ID)){
				ishere = true;
			}			
		}
		return ishere;	
		
	}
	
	/**
	 * check the whether gameid is already in gamelist or not
	 *@return
	 */
	public static Game getGame(String GameID){
		for(Game g: gamelist){
			if(g.gameid.equals(GameID)){
				return g;
			}			
		}
		return null;		
	}

}
