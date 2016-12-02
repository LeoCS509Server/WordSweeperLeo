package adminmodel;

import java.util.Hashtable;
import java.util.List;

import util.Location;

public class Board {
	
	public static String[][] globalboard;
	public static int size;
	int k=0;

	
	public Board() {
		super();
		size = 7;		
		}
      	
	public Board(int size) {
		super();	
	}
	

	
	public void getBoard(String string,int size){
		this.size=size;
		globalboard=new String[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				globalboard[i][j]=string.substring(k,k+1);
				k++;
			}
		}
	}
	
	
	
	public void refreshBoard(){
		
	}
	
	}
	
	