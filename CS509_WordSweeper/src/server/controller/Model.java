package server.controller;

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
			break;
		}
	}
	
	
	/** add new game to arraylist*/
	public void addGame (Game g){
		games.add(g);
	}
	
	public Game getGame(String gameid)
	{
		int i=0;
		while(!gameid.equals(games.get(i).getGameID()))
		{
			i++;
			
		}
		return games.get(i);
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
		int i=0;
		while(!id.equals(games.get(i).getGameID()))
		{
			i++;
			
		}
		games.remove(i);
		
	}
	public boolean isPasswordCorrect(String GameID, String password){
		for(Game g: games){
			if(g.getGameID().equals(GameID) && (g.getPassword().equals(password) || password.isEmpty())){
				return true;
			}
		}
		return false;
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