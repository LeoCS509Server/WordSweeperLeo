package server.model;

import java.util.ArrayList;
import java.util.Hashtable;


import util.Location;

public class Board {
	
	int size;
	Hashtable<Location, Cell> board;
	Location Bonus;
	
	
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
	
	public int getSize(){
		return this.size;
	}
		
	public Board resetBoard(){
		return new Board();	
		
	}
	
	public Board resetBoard(int size){
		return new Board(size);	
	}
	
	public String getCellContains(Location l){		
		return board.get(l).letter;
	}
	
	public String getBoardContains(){
		String s = new String();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
                Location loc = new Location(c,r);
                s += board.get(loc).getLetter();
                }
			}						
		return s;
	}
	
	protected static Location BonusCell(){
		int c = (int) (Math.random()*7)+1;
		int r = (int) (Math.random()*7)+1;
		return new Location(c,r);
	}
	
	
	public Location getBonusCell(){
		return Bonus;
	}
	

	public void removeWord(ArrayList<Location> locations){
		for(Location l : locations){	
			board.get(l).removeLetter();
		}	
	}
		
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
