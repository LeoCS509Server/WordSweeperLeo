package server.model;

import java.util.Hashtable;
import java.util.List;


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
                Key key = new Key(r,c);
				Cell cell = new Cell(loc);
				//cell.getLetter();
				table.put(key, cell);	
			}
		}
		BonusCell();
	}
	
	public Board(int size) {
		super();
		this.size = size;
		table = new Hashtable<Key, Cell>();
		for(int c = 1; c<=size;c++){
			for(int r =1; r<=size; r++){
				Location loc = new Location(c,r);
				Key key = new Key(r,c);
                Cell cell = new Cell(loc);
				cell.getLetter();
				table.put(key, cell);
			}
		}
		BonusCell();
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
		int row = l.getRow();
		int col = l.getColumn();
		Key key = new Key(row,col);
		return table.get(key).letter;
	}
	
	protected void BonusCell(){
		int c = (int) (Math.random()*size)+1;
		int r = (int) (Math.random()*size)+1;
		Key key = new Key(r,c);
		table.get(key).setSeleted();
		Bonus = table.get(key);
	}
	
	public Cell getBonusCell(){
		return Bonus;
	}
	

	public void removeWord(List<Cell> Cells){
		for(Cell c : Cells){
			c.removeLetter();
		}	
	}
	/**
	public void refreshBoard(){
		for(int c = 1; c<=size;c++){
			int count = 0;
			for(int r =1; r<=size; r++){
				Location l = new Location(c,r);
				Key key = new Key(r,c);
				Key keyBelow = new Key(r+1,c);
				if(!table.get(key).hasLetter()){
					while (!table.get(keyBelow).hasLetter())
					table.get(l).letter = table.get(new Location(c,r)).getLetter();
					table.get(new Location(c,r)).removeLetter();
					
					count++;	
				}			
			}
			for(int x =0; x<count; x++){
				Location r = new Location(c, size-x);
				table.get(r).setLetter();
			}
		}	
	}*/
	public void refreshBoard(){
		for(int c=1; c<=size;c++){
			for(int r=1; r<=size; r++){
				Location l = new Location(c,r);
				Key key = new Key(r,c);
				if(!table.get(key).hasLetter()){
					int count = 0;
					int row = r;
					while(!table.get(key).hasLetter() && r+count < size){
						row++;
						key = new Key(row,c);
						count++;
					}
					if((r+count)==size){
						for(int i = 0; i<=count; i++){
							Key k = new Key(r+i,c);
							table.get(k).setLetter();
							//break;
						}
					}
					else{
						for(int i =0; i<=size-r-count; i++){
							Key k = new Key(r+i,c);
							Key kbelow = new Key(r+count+i,c);
							String tmp = table.get(kbelow).letter;
							table.get(kbelow).setLetter();
							table.get(k).letter=tmp;
							//break;
						}
					}
				}
				
			}
		}
	}


	 
}
