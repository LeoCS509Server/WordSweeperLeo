package util;

public class Location {
	int column;
	int row;
	
	
	public Location(int column, int row) {
		super();
		this.column = column;
		this.row = row;
	}
	
	public Location(String s){
		super();
		this.column =Integer.valueOf(s.substring(0,1)).intValue();
		this.row =Integer.valueOf(s.substring(2)).intValue();
		
	}


	public int getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}
	

	public int[] getLocation(){
		int[] s = new int[2];
		s[0]= this.column;
		s[1] = this.row;
		return s;
	}

	public void setColumn(int column) {
		this.column = column;
	}


	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public int hashCode() {
		return row*12387 + column;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Location) {
			Location other = (Location) o;
			return row == other.row && column == other.column;
		}
		
		return false; // nope
	}
	

}
