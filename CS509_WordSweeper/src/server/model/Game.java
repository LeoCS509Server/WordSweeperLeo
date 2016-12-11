package server.model;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Random;

import util.Letters;
import util.Location;
import util.WordTable;


public class Game {
	
	String GameID;
	ArrayList<Player> Players;
	Board board;
	boolean isLocked;
	String password;
	boolean isActived;

	
	public String setGameID(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		String s=String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
		return s;
	}
	
	public Location randomLocation(int size){
		int x =(int) (Math.random()*(size-3)+1);
		int y =(int) (Math.random()*(size-3)+1);
		return new Location(x,y);
	}
	
	public Game(String UserID) {
		GameID = setGameID();
		this.board = new Board();
		Location loc = randomLocation(board.getSize());
		Player managingUser = new Player(UserID, loc);
		managingUser.setManagingUser();
		Players = new ArrayList<Player>();
		Players.add(managingUser);
		this.isLocked = false;
		password = "";
		//boolean isActived = true;
	}
	
	
	public void addPlayer(String id){
		
		if(Players.size()>8){
			int size = (int) Math.sqrt(16*(Players.size()+1)/3);
			Board board = new Board(size);
			Location loc = randomLocation(board.getSize());
			Player p = new Player(id, loc); 
			Players.add(p);
			for(int i = 0; i<Players.size()-1; i++){
				Players.get(i).resetScore();
				Location loc1 = randomLocation(board.getSize());
				Players.get(i).setPlayerLocation(loc1);			
			}			
		}else{	
			Location loc = randomLocation(board.getSize());
			Player p = new Player(id, loc); 
			Players.add(p);
		}
	}
	
	
	public String getGameID(){
		return GameID;
	}
	
	public String getManageUsername(){
		String Username = "";
		for(Player p : Players){
			if(p.isManagingUser())
				Username = p.getName();
		}
		return Username;
	}
	
	

	
	
	public void removePlayer(Player player){
		if(this.Players.size()>1&&(!player.isManagingUser())){
			this.Players.remove(player);
		}else if(this.Players.size()>1 &&(player.isManagingUser())){
			this.Players.remove(player);
			this.setManagingUser(this.Players);
		}else{
			this.Players.remove(player); 
			this.isActived = false;
		}
	}
	
	public void removePlayer(String playerID){
		int index = 0;
		for(int i = 0; i<Players.size(); i++){
			if(Players.get(i).getName().equals(playerID)){
				index = i;
			}
		}
		
		if(this.Players.size()>1&&(!Players.get(index).isManagingUser())){
			this.Players.remove(index);
		}else if(this.Players.size()>1 &&(Players.get(index).isManagingUser())){
			this.Players.remove(index);
			this.setManagingUser(this.Players);
		}else{
			this.Players.remove(index);
			this.isActived = false;
		}
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	public void resetBoard(){
		this.board = new Board();
		for(Player player: Players){
			player.resetScore();
		}
	}
	
	
	public int checkBoardSize(){
		int i = 7;
		if(Players.size() >3){
			i = (int)Math.sqrt(16*Players.size())+1;
		}
		return i;	
	}
	
	public void setManagingUser(ArrayList<Player> Players){
		//int i = (int)Math.random()*Players.size();
		Random r =new Random();
		int i = r.nextInt(Players.size());
		Players.get(i).setManagingUser();;
	}
	
	
	public boolean checkisLocked(){
		return isLocked; 
	}
	
	public void setLocked(){
		this.isLocked = true;
	}
	
	public void setPassword(String pw){
		this.password = pw;
	}
	
	public String getPassword(){
		return password;
	}
	
	public int CellOverlapNum(Location loc){
		
		int column = loc.getColumn();
		int row = loc.getRow();
		int OverlapNum = 0;
		for(Player p : Players){
			int deltaC = Math.abs(p.getPlayerLocation().getColumn()-column);
			int deltaR = Math.abs(p.getPlayerLocation().getRow()-row);
			if(deltaC <4 || deltaR <4){
				OverlapNum++;
			}
		}
		return OverlapNum;
	}
	
	public int calculateScore(String text, ArrayList<Location> loc){
		int score = 0;
		int cellsSum = 0;
		int Bonus = 1;
		if(WordTable.isWord(text)&&word.isConsistent(loc)){
			for(int i = 0; i< loc.size(); i++){
				if(board.getBonusCell().equals(loc.get(i))){
					Bonus = 10;
				}
				String s = board.getCellContains(loc.get(i));
				cellsSum+= Letters.LetterScore.get(s)*Math.pow(2, CellOverlapNum(loc.get(i)));				
			}		
			score = (int) Math.pow(2, loc.size())*10*cellsSum*Bonus;
		}	
		return score;
	}
	
	public void rePosition(Player p, int c, int r){
		int x = p.getPlayerLocation().getColumn() + c;
		int y = p.getPlayerLocation().getRow() + r;
		Boolean isOverColumn = x>board.getSize()-3 || x<1;
		Boolean isOverRow = y>board.getSize()-3 || y<1;
		if(!(isOverColumn || isOverRow)){
			Location loc = new Location(x,y); 
			p.setPlayerLocation(loc);
			}
		//return p.getPlayerLocation();
	}
	
	public Player getPlayer(String Playerid){
		for(Player p: Players){
			if(p.getName().equals(Playerid)){
				return p;
			}
		}
		return null;
	}

	
	/** return the number of players*/
	public int getNumPlayers(){
		return Players.size();
	}
	
	/**return all the players in a game*/
	public ArrayList<Player> getPlayers(){
		return Players;
	}
	
	public String getPlayerboard(Player p){
		String s = new String();	
		for(Location l : p.getCellsLocation()){
			s += board.getCellContains(l);
		}
		return s;		
	}
	
}
	
	

