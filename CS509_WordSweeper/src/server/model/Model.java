package server.model;

import java.util.*;
/** top level object of entiy objects*/
public class Model {

	ArrayList<Game> games = new ArrayList<Game>();
	Game selectedGame = null;		
	
	/** select certain game to show its state*/
	public void selectGame (Game g){
		selectedGame = g;
	}
	
	/** add new game to arraylist*/
	public void addGame (Game g){
		games.add(g);
	}
	
	
	
	/** return all the games*/
	public ArrayList<Game> getGames(){
		return games;
	}
	
	/** return selected game*/
	public Game getSelected(){
		return selectedGame;
	}
	
	
	// find(gameid)
}
