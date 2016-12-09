package util;

import java.util.HashMap;

public class Letters {
	
	
	public static String letters[] = new String[]{"E","T","A","O","I","N","S","H","R","D","L",
			"C","U","M","W","F","G","Y","P","B","V","K","J","X","Qu","Z"};
	public static Integer values[] = new Integer[]{1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,5,5,7,7,11,8};
		
	public static HashMap<String,Integer> LetterScore;
	
	
	
	
	public Letters() {
		super();
		
		String letters[] = new String[]{"E","T","A","O","I","N","S","H","R","D","L",
				"C","U","M","W","F","G","Y","P","B","V","K","J","X","Qu","Z"};

		Integer values[] = new Integer[]{1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,5,5,7,7,11,8};
		
		for(int i = 0; i < 27; i++ ){
			LetterScore.put(letters[i], values[i]);
		}	
	}
	
	public static String chooseLetter(){
		String l = null;
		Double randomIndex = Math.random();
		Double[] LetterFreSum= new Double[]{12.70, 21.76, 29.93, 37.43, 44.40, 51.15, 57.47, 63.57, 
				69.56, 73.81, 77.83, 80.62, 83.37, 85.78, 88.14, 90.37, 92.38, 94.36, 96.29, 97.78,
				98.76, 99.53, 99.68, 99.83, 99.93, 100.0};

		if (randomIndex*100 < 12.70)
			l = "X";
		for(int i = 0; i< LetterFreSum.length-1; i++){
			if(randomIndex*100 >= LetterFreSum[i] && (randomIndex*100 <LetterFreSum[i+1])){
				l = Letters.letters[i];
			}
		}
	    return l;
	}
	
	
}
	