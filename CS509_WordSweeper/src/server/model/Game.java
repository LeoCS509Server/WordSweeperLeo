package server.model;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Random;

import util.Letters;
import util.Location;
import util.WordTable;

/**
 * Game entity class, which contains all the info and functions about the game.  
 * 
 * @author Tongge Zhu
 *
 */
public class Game {
	
	/** The ID of the game. */
	String GameID;	
	/** Players in the game being maintained. */
	ArrayList<Player> Players;
	/** board of the game being maintained. */
	Board board;
	/** if the game is locked */
	boolean isLocked;
	/** password of the game being maintained. */
	String password;
	/** if the game is activate */
	boolean isActived;

	/** generate the gameID
	 * @return 
	 */
	public String setGameID(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		String s=String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
		return s;
	}
	
	/** generate random location on the board
	 * @return
	 */
	public boolean isactived(){
		return isActived;
	}
	
	public Location randomLocation(int size){
		int x =(int) (Math.random()*(size-3)+1);
		int y =(int) (Math.random()*(size-3)+1);
		return new Location(x,y);
	}
	
	/**Game constructor
	 * @param User ID
	 * @param clientID
	 */
	public Game(String UserID, String clientid) {
		GameID = setGameID();
		this.board = new Board();
		Location loc = randomLocation(board.getSize());
		Player managingUser = new Player(UserID, loc, clientid);
		managingUser.setManagingUser();
		Players = new ArrayList<Player>();
		Players.add(managingUser);
		this.isLocked = false;
		password = "";
		isActived = true;
	}
	
	/**Add a new player into the game
	 * @param playerid
	 * @param clientid
	 */
	public void addPlayer(String id, String clientid){
		
		if(Players.size()>=3){
			int size = this.checkBoardSize();
			board = new Board(size);
			Location loc = randomLocation(board.getSize());
			Player p = new Player(id, loc, clientid); 
			Players.add(p);
			for(int i = 0; i<Players.size()-1; i++){
				Players.get(i).resetScore();
				Location loc1 = randomLocation(board.getSize());
				Players.get(i).setPlayerLocation(loc1);			
			}			
		}else{	
			Location loc = randomLocation(board.getSize());
			Player p = new Player(id, loc, clientid); 
			Players.add(p);
		}
	}
	
	/**return the GameID
	 * @return 
	 */
	public String getGameID(){
		return GameID;
	}
	
	/**return managing user ID
	 * @return 
	 */
	public String getManageUsername(){
		String Username = "";
		for(Player p : Players){
			if(p.isManagingUser())
				Username = p.getName();
		}
		return Username;
	}
	
	/**remover player from game 
	 * @param playerID
	 */
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
	
	/**return the board of the game
	 * @return 
	 */
	public Board getBoard(){
		return this.board;
	}
	
	/**reset the board of the game
	 * reset players' score to zero
	 * @return 
	 */
	public void resetBoard(){
		this.board = new Board();
		for(Player player: Players){
			player.resetScore();
		}
	}
	
	/**reset the board of the game
	 * reset players' score to zero
	 * @return 
	 */
	public int checkBoardSize(){
		int i = 7;
		if(Players.size() >= 3){
			i = (int)Math.sqrt(16*Players.size())+1;
		}
		return i;	
	}
	
	/**set the managinguser of the game
	 * @return 
	 */
	public void setManagingUser(ArrayList<Player> Players){
		//int i = (int)Math.random()*Players.size();
		Random r =new Random();
		int i = r.nextInt(Players.size());
		Players.get(i).setManagingUser();;
	}
	
	/** check if the game is locked
	 *@return
	 * */
	public boolean checkisLocked(){
		return isLocked; 
	}
	
	/** lock the game*/
	public void setLocked(){
		this.isLocked = true;
	}
	
	/** set the password of the game*/
	public void setPassword(String pw){
		this.password = pw;
	}
	
	/** return the password of the game
	 * @return
	 */
	public String getPassword(){
		return password;
	}
	
	/** return the overlap time of cell
	 * @param loc
	 * @return
	 */
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
	
	/** return score of found word
	 * @param text
	 * @param loc
	 */
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
	
	/** reset player's position
	 * @param c
	 * @param r
	 */
	public void rePosition(Player p, int c, int r){
		int x = p.getPlayerLocation().getColumn() + c;
		int y = p.getPlayerLocation().getRow() + r;
		Boolean isOverColumn = x>board.getSize()-3 || x<1;
		Boolean isOverRow = y>board.getSize()-3 || y<1;
		if(!(isOverColumn || isOverRow)){
			Location loc = new Location(x,y); 
			p.setPlayerLocation(loc);
			}
	}
	
	/** return player with given id
	 * @param Playerid
	 */
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
	
	/**return the player's board in a game line by lien*/
	public String getPlayerboard(Player p){
		String s = new String();	
		for(Location l : p.getCellsLocation()){
			s += board.getCellContains(l)+",";
		}
		return s;		
	}
	
}
	
	

