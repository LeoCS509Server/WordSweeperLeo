package util;

public class Location {
	int column;
	int row;
	
	
	public Location(int column, int row) {
		super();
		this.column = column;
		this.row = row;
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
	

	
	
	
	

}
