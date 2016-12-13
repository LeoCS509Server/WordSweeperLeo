package server.model;

import java.util.*;
/** top level object of entiy objects*/
public class Model {


	ArrayList<Game> games;
	Game selectedGame;		
	public Model(){
		this.selectedGame = null;
		this.games = new ArrayList<Game>();
	}
	
	/** select certain game to show its state*/
	public void selectGame (String id){

		for(Game g : games){
			if(g.getGameID().equals(id))
				selectedGame = g;
		}
	}
	
	
	/** add new game to arraylist*/
	public void addGame (Game g){
		games.add(g);
	}
	
	public Game getGame(String gameid)
	{
		for(Game g : games){
			if(g.getGameID().equals(gameid))
				return g;
		}
		return null;
	}
	
	/** return all the games*/
	public ArrayList<Game> getGames(){
		return games;
	}
	
	/** return selected game*/
	public Game getSelected(){
		return selectedGame;
	}
	/**remove game from list*/
	public void removeGame(String id){
		for(Game g : games){
			if(g.getGameID().equals(id))
				games.remove(g);
			break;
		}		
	}
	
	public boolean isPasswordCorrect(String GameID, String password){
		boolean istrue = false;
		for(Game g: games){
			boolean b1 = g.getGameID().equals(GameID);
			boolean b2 = g.getPassword().equals(password);
			boolean b3 = password.isEmpty();
			if(( b2||b3) &&b1){
				istrue = true;
				break;
			}
		}
		return istrue;
	}
	
	
	public void refreshGameList(){
		ArrayList<Game> NewGameList = new ArrayList<>();
		for(Game g: games){
			if(g.isActived){
				NewGameList.add(g);			
			}
		}
		this.games = NewGameList;	
	}
	
	// find(gameid)
}
