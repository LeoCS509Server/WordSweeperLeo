package server.model;

import java.util.ArrayList;
import java.util.List;

import util.Location;



public class Player {
	
	
	private String Name;
	private int Score;
	private Location location;
	private boolean isManagingUser;
	private String clientid;
	
	
	public void setPlayerLocation(Location loc){
		this.location = loc;
		}

	
	public Location getPlayerLocation(){
		return this.location;
	}
	
	public Player(String name, Location loc, String clientid) {
		super();
		this.Name = name;
		this.Score = 0;
		this.location = loc;
		this.isManagingUser = false;
		this.clientid = clientid;
	}
	
	public String getClientId()
	{
		return this.clientid;
	}

	public String getName(){
		return this.Name;
	}
	
	public int getScore(){
		return this.Score;
	}
	
	public void addScore(int num){
		this.Score += num;
	}
	
	public void resetScore(){
		this.Score = 0;
	}
	
	public void setManagingUser(){
		this.isManagingUser = true;
	}
	
	public boolean isManagingUser(){
		return isManagingUser;
	}
	
	public List<Location> getCellsLocation(){
		List<Location> LocationList = new ArrayList<>();
		for(int r = 0; r<4; r++){
			for(int c=0; c<4; c++){
				LocationList.add(new Location(this.location.getColumn()+c,this.location.getRow()+r));
			}
		}
		return LocationList;	
	}

}
