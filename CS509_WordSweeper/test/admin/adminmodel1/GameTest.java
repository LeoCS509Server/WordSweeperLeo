package admin.adminmodel1;

import java.util.ArrayList;

import admin.adminmodel.Game;
import junit.framework.TestCase;

public class GameTest extends TestCase {

	Game game1;
	protected void setUp() throws Exception {
		super.setUp();
		this.game1 = new Game("11103",7);
		game1.setPlayerid("ni");
		game1.setPlayerid("hao");
		game1.setBoard("a,a,a,a,a,a,a,s,s,s,s,s,s,s,d,d,d,d,d,d,d,f,f,f,f,f,f,f,w,w,w,w,w,w,w,r,r,r,r,r,r,r,g,g,g,g,g,g,g,", 7);
		game1.setScore(1000);
		game1.setScore(2000);
		int loc1 = 1 + 2*7;//(1,2)
		int loc2 = 3 + 4*7;//(3,4)
		game1.setPlayerlocation(loc1);
		game1.setPlayerlocation(loc2);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testGetPlayerlocation() {
		ArrayList<Integer> positionlist = new ArrayList<Integer>();
		positionlist.add(15);
		positionlist.add(31);
		assertEquals(positionlist,game1.getPlayerlocation());		
	}

	public void testGetPlayerid() {
		ArrayList<String> pid = new ArrayList<String>();
		pid.add("ni");
		pid.add("hao");
		assertEquals(pid,game1.getPlayerid());	
	}

	public void testGetScore() {
		ArrayList<Integer> sc = new ArrayList<Integer>();
		sc.add(1000);
		sc.add(2000);
		assertEquals(sc,game1.getScore());	
	}

	public void testGetSize() {
		assertEquals(7,game1.getSize());
	}

	public void testGetGlobalboard() {
		String[][] gboard = {{"a","a","a","a","a","a","a"},{"s","s","s","s","s","s","s"},{"d","d","d","d","d","d","d"},
				{"f","f","f","f","f","f","f"},{"w","w","w","w","w","w","w"},{"r","r","r","r","r","r","r"},{"g","g","g","g","g","g","g"}};
		String s1 ="";
		for(String[] i:gboard){
			for(String ii:i){
				s1=s1+ii;
			}
		}
		String s2 ="";
		for(String[] j:game1.getGlobalboard()){
			for(String jj:j){
				s2 = s2+jj;
			}
		}

		assertEquals(s1,s2);
	}

	public void testGetGameid() {
		assertEquals("11103",game1.getGameid());		
	}

}
