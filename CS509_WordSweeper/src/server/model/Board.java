package server.model;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import util.Location;
import util.hashtable.Key;

public class Board {
	
	int size;
	Hashtable<Key, Cell> table; 
	Cell Bonus;
	
	Board() {
	
		size = 7;
		table = new Hashtable<Key, Cell>();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
                Location loc = new Location(c,r);
                Key key = new Key(loc);
                Cell cell = new Cell(loc);
				cell.getLetter();
				table.put(key, cell);	
			}
		}
	}
	
	public Board(int size) {
		super();
		table = new Hashtable<Key, Cell>();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
				Location loc = new Location(c,r);
				Key key = new Key(loc);
                Cell cell = new Cell(loc);
				cell.getLetter();
				table.put(key, cell);
			}
		}
	}
	
	public int getSize(){
		return this.size;
	}
	
	
	
	
	public void resetBoard(){
		Board board = new Board();	
	}
	
	public void resetBoard(int size){
		Board board = new Board(size);	
	}
	
	public String getCellContains(Location l){
		Key key = new Key(l);
		return table.get(key).letter;
	}
	
	public Location BonusCell(){
		int c = (int) (Math.random()*size)+1;
		int r = (int) (Math.random()*size)+1;
		Location l = new Location(c,r);
		Key key = new Key(l);
		table.get(key).setSeleted();
		return new Location(c, r);
	} 
	
	
	public void removeWord(List<Cell> Cells){
		for(Cell c : Cells){
			c.removeLetter();
		}	
	}
	//waiting for further change   
	public void refreshBoard(){
		for(int c = 1; c<=size;c++){
			int count = 0;
			for(int r =1; r<=size; r++){
				Location l = new Location(c,r);
				Key key = new Key(l);
				if(!table.get(key).hasLetter()){
					while (!table.get(new Location(c,++r).hashCode()).hasLetter())
					table.get(l.hashCode()).letter = table.get(new Location(c,r).hashCode()).getLetter();
					table.get(new Location(c,r).hashCode()).removeLetter();
					
					count++;	
				}			
			}
			for(int x =0; x<count; x++){
				Location r = new Location(c, size-x);
				table.get(r.hashCode()).setLetter();
			}
		}	
	}
	
	
	
	
	
	






	
	

}
