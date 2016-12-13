package server.model;

import java.util.ArrayList;
import java.util.Hashtable;
import util.Location;

/**
 * Board entity class, which contains all the info and functions about the board.  
 * 
 * @author Tongge Zhu
 */

public class Board {
	
	/** Size attribute of board, a board is size * size. */
	int size;
	
	/** Cells being maintained. */
	Hashtable<Location, Cell> board;
	
	/** Bonus cell being maintained. */
	Location Bonus;
	
	/**Board constructor*/
	Board() {		
		size = 7;
		board = new Hashtable<Location, Cell>();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
                Location loc = new Location(c,r);
				Cell cell = new Cell(loc);
				board.put(loc, cell);	
			}
		}
		Bonus=BonusCell();
	}
	
	/**Board constructor, when size of players larger than 3
	 *  @param size  int is calculated by game.checkBoardSize()
	 */
	public Board(int size) {
		super();
		this.size = size;
		board = new Hashtable<Location, Cell>();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
				Location loc = new Location(c,r);
                Cell cell = new Cell(loc);
				board.put(loc, cell);
			}
		}
		Bonus=BonusCell();
	}
	
	/**return the size attribute of the board
	 * @return 
	 */
	public int getSize(){
		return this.size;
	}
	
	/** 
	 * refresh board with a assigned size
	 * 
	 */
	public Board resetBoard(){
		return new Board();	
		
	}
	
	/** 
	 * refresh board with a assigned size
	 * 
	 * @param size
	 */
	public Board resetBoard(int size){
		return new Board(size);	
	}
	
	/**
	 * Return Letter within the Cell of Location l.
	 * 
	 *@param location
	 */
	public String getCellContains(Location location){		
		return board.get(location).letter;
	}
	
	/**
	 * Return Letters within the board line-by-line.
	 * 
	 *@return
	 */
	public String getBoardContains(){
		String s = new String();
		for(int r = 1; r<=size;r++){
			for(int c =1; c<=size; c++){
                Location loc = new Location(c,r);
                s += board.get(loc).getLetter();
                }
			}						
		return s;
	}
	
	/**
	 * Set Location of the bonus cell 
	 * 
	 *@return
	 */
	public static Location BonusCell(){
		int c = (int) (Math.random()*7)+1;
		int r = (int) (Math.random()*7)+1;
		return new Location(c,r);
	}
	
	/**
	 * Return Location of the bonus cell 
	 * 
	 *@return
	 */
	public Location getBonusCell(){
		return Bonus;
	}
	
	/**
	 * Clear the cell contain letter at a given locations list
	 * 
	 */
	public void removeWord(ArrayList<Location> locations){
		for(Location l : locations){	
			board.get(l).removeLetter();
		}	
	}
	
	/**
	 * perform right after removeWord，refill the empty cell
	 */
	public void refreshBoard(){
		for(int c = 1; c<=size;c++){
			int count = 0;
			ArrayList<String> arr = new ArrayList<>();
			for(int r =1; r<=size; r++){
				Location l = new Location(c,r);				
				if(board.get(l).hasLetter()){
					arr.add(board.get(l).getLetter());
				}
				count = arr.size();
			}
			for(int r =1; r<=count; r++){
				Location l1 = new Location(c,r);
				board.get(l1).setLetter(arr.get(r-1));
			}
			for(int x =count+1; x<=size; x++){
				Location r = new Location(c, x);
				board.get(r).setLetter();
			}			
		}						
	}	
}
