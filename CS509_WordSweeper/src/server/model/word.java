package server.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import util.Location;


/**
 * Helper function for checking if a word sent by use is legal.
 * 
 * @author Tongge Zhu
 */

public class word {
	
	/** Text represent the content of the word. */
	String text;
	
	/** Cells store the location of each letter of word */
	ArrayList<Location> loc;
	

		
	
	/**This function check if the location of each letter of the word is consistent, yes return true, otherwise return false*/
	public static boolean isConsistent(ArrayList<Location> location){
		
		Set<Location> set = new HashSet<Location>();
		
		for(int i=1; i < location.size(); i++){
			
			if (!set.add(location.get(i))) return false;//check if exist duplicate cells
			
			int a = Math.abs(location.get(i).getColumn()-location.get(i-1).getColumn());
			int b = Math.abs(location.get(i).getRow()-location.get(i-1).getRow());
			if(a!= 1 && b!= 1 ){
				return false;
			}		
		}	
		return true;
	}

}
