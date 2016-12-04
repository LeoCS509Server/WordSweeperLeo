package util.hashtable;

import util.Location;

public class Key {
	int row, col;
	
	public Key (Location l){ 
		this.row = l.getRow();
		this.col = l.getColumn();
	}
	
	@Override
	public int hashCode() {
		return row*12387 + col;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Key) {
			Key other = (Key) o;
			return row == other.row && col == other.col;
		}
		
		return false; // nope
	}
}
