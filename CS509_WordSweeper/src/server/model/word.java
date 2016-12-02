package server.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import server.model.Game;
import util.Letters;
import util.WordTable;



public class word {
	String text;
	ArrayList<Cell> cells;
	

		
	
	//check if the cells of the word is consistent, yes return true, otherwise return false
	public static boolean isConsistent(ArrayList<Cell> cells){
		
		Set<Cell> set = new HashSet<Cell>();
		
		for(int i=1; i < cells.size(); i++){
			
			if (!set.add(cells.get(i))) return false;//check if exist duplicate cells
			
			int a = Math.abs(cells.get(i).getPosition().getColumn()-cells.get(i-1).getPosition().getColumn());
			int b = Math.abs(cells.get(i).getPosition().getRow()-cells.get(i-1).getPosition().getRow());
			if(a!= 1 && b!= 1 ){
				return false;
			}		
		}	
		return true;
	}
	

}
