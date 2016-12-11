package server.model;

import util.Letters;
import util.Location;


/**
 * 
 * A cell is located at a specific (x,y) coordinate in board. It has letter inside. 
 * 
 * This class defines the shared behavior for extensions.
 * 
 * @author Tongge Zhu
 */

public class Cell {
	
	/** The location of Cell in the board. */
	Location location;
	/** The letter within the cell */
	String letter;
	/**The selected cell is the bonus cell. */
	Boolean seleted;
	
	public Cell(Location location) {
		super();
		this.location = location;
		this.letter = Letters.chooseLetter();
		this.seleted = false;
	}

	/** Get the location of Cell in the board. */
	public Location getPosition(){
		return this.location;
	}
	
	/** Check if the cell has letter. */
	public boolean hasLetter(){
		if(this.letter!=null) return true;
		return false;
	}
	
	/** Assign random letter to the cell. */
	public void setLetter(){
		this.letter= Letters.chooseLetter();
	}
	
	/** Assign the letter of another cell to the cell.
	 * @param Cell
	 */
	public void setLetter(Cell c){
		this.letter = c.getLetter();		
	}
	
	/** Return the letter of the cell. */
	public String getLetter(){
		return this.letter;
	}
	
	/** Remover the letter of the cell. */
	public void removeLetter(){
		this.letter = null;	
	}
	
	/** Check if the cell is the Bonus cell. */
	public boolean isSeleted(){
		return this.seleted;
	}
	
	/** Set the cell the Bonus cell. */
	public void setSeleted(){
		this.seleted = true;
	}
	
//	/** Assign the letter of another cell to the cell.
//	 * @param Cell
//	 */
//	public void changeLocation(int c, int r){
//		this.location.setColumn(c);
//		this.location.setRow(r);
//	}

	
}
