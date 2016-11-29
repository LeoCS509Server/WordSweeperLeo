package server.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import util.Letters;
import util.Location;
import util.WordTable;


public class Game {
	
	static LinkedList<Game> games;
	String GameID;
	ArrayList<Player> Players;
	Board board;
	Player managingUser;
	boolean isLocked;
	String password;
	//need an attribute bonus
	
	public Game(String GameID, Player managingUser ) {
		super();
		GameID = GameID;
		Players.add(managingUser);
		this.managingUser = managingUser;
		this.board = new Board();
		this.isLocked = false;
	}
	public Game(){
	
	}

	public Game(String gameID, Player managingUser, String password) {
		super();
		GameID = gameID;
		Players.add(managingUser);
		this.managingUser = managingUser;
		this.board = new Board();
		this.isLocked = true;
		this.password = password;
	}
	
	public void addPlayer(Player player){
		Players.add(player);
	}
	
	public String getGameID(){
		return GameID;
	}

	public int getBonus(){
		return 1;                  //need bonus
	}
	
	public void removePlayer(Player player){
		if(this.Players.size()>1&&(player!=managingUser)){
			this.Players.remove(player);
		}else if(this.Players.size()>1 &&(player==managingUser)){
			this.Players.remove(player);
			this.setManagingUser(this.Players);
		}else{
			this.Players.remove(player); //
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
		int i = r.nextInt(Players.size())+1;
		this.managingUser=Players.get(i);
	}
	
	public String getManagingUser(){
		return managingUser.getName();
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
	
	public int CellOverlapNum(Cell cell){
		int column = cell.location.getColumn();
		int row = cell.location.getRow();
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
	
	public int calculateScore(String text, ArrayList<Cell> cells){
		int score = 0;
		int cellsSum = 0;
		int Bonus = 1;
		if(WordTable.isWord(text)&&word.isConsistent(cells)){
			for(int i = 0; i< cells.size(); i++){
				if(cells.get(i).isSeleted()){
					Bonus = 10;
				}	
				cellsSum+= Letters.LetterScore.get(cells.get(i))*Math.pow(2, CellOverlapNum(cells.get(i)));				
			}		
			score = (int) Math.pow(2, cells.size())*10*cellsSum*Bonus;
		}	
		return score;
	}
	
	public Location rePosition(Player p, int c, int r){
		int x = p.getPlayerLocation().getColumn() + c;
		int y = p.getPlayerLocation().getRow() + r;
		Boolean isOverColumn = x>board.getSize()-3 || x<1;
		Boolean isOverRow = y>board.getSize()-3 || y<1;
		if(!(isOverColumn || isOverRow)){
			Location loc = new Location(x,y); 
			p.setPlayerLocation(loc);
			}
		return p.getPlayerLocation();
	}

	/** return game id*/
	public String getGameId(){
		return GameID;
	}
	
	/** return the number of players*/
	public int getNumPlayers(){
		return Players.size();
	}
	
	/**return all the players in a game*/
	public ArrayList<Player> getPlayers(){
		return Players;
	}
}
	
	

