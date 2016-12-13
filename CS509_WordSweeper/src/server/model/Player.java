package server.model;

import java.util.ArrayList;
import java.util.List;

import util.Location;

/**
 * Player entity class, which contains all the info and functions about the player.  
 * 
 * @author Tongge Zhu, Zhengyu Hu
 *
 */

public class Player {
	
	/** The name of player. */
	private String Name;	
	/** The score of player. */
	private int Score;
	/** The upper left location of the cell of player. */
	private Location location;
	/** The mangingUser status of the player */
	private boolean isManagingUser;
	/** The clientid of the player */
	private String clientid;
	
	/** set the upper left location of the cell of the player. */
	public void setPlayerLocation(Location loc){
		this.location = loc;
		}

	/** get the upper left location of the cell of the player. 
	 * @return
	 */
	public Location getPlayerLocation(){
		return this.location;
	}
	
	/**player constructor*/
	public Player(String name, Location loc, String clientid) {
		super();
		this.Name = name;
		this.Score = 0;
		this.location = loc;
		this.isManagingUser = false;
		this.clientid = clientid;
	}
	
	/** get the clientid of the player 
	 * @return
	 */
	public String getClientId()
	{
		return this.clientid;
	}
	
	/** get the ID of the player 
	 * @return
	 */
	public String getName(){
		return this.Name;
	}
	
	/** get the score of the player 
	 * @return
	 */
	public int getScore(){
		return this.Score;
	}
	
	/** add score 
	 * @param num
	 */
	public void addScore(int num){
		this.Score += num;
	}
	
	/** reset the score of the player */
	public void resetScore(){
		this.Score = 0;
	}
	
	/** set the player to ManagingUser */
	public void setManagingUser(){
		this.isManagingUser = true;
	}
	
	/** check if the player is the ManagingUser */
	public boolean isManagingUser(){
		return isManagingUser;
	}
	
	/** get Location list of the player
	 * @return
	 */
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
