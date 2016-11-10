package server.model;

import java.util.LinkedList;
import java.util.List;

import util.Location;



public class Player {
	
	
	private String Name;
	private String idNum;
	private int Score;
	//private Color color;
	private Location location;
	
	
//	public Color randomColor(){
//		Random rand = new Random();
//		float r = rand.nextFloat();
//		float g = rand.nextFloat();
//		float b = rand.nextFloat();
//		Color randomcolor = new Color(r,g,b);
//		return randomcolor.brighter();
//	}
	
	public Location randomLocation(){
		int x =(int) (Math.random()*4+1);// 4 改成board size -3
		int y =(int) (Math.random()*4+1);
		return new Location(x,y);
	}
	
	public void setPlayerLocation(Location loc){
		this.location = loc;
		}

	
	public Location getPlayerLocation(){
		return this.location;
	}
	
	public Player(String name, String idNum) {
		super();
		Name = name;
		this.idNum = idNum;
		Score = 0;
		this.location = randomLocation();
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
	
	public List<Location> getCellsLocation(){
		List<Location> LocationList = new LinkedList<>();
		for(int c = 0; c<4; c++){
			for(int r=0; r<4; r++){
				LocationList.add(new Location(this.location.getColumn()+c,this.location.getRow()+r));
			}
		}
		return LocationList;	
	}

}
