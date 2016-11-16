package server.model;

/** HACK: Replace with actual functionality! */

public class ServerModel {
	int numPlayers = 0;
	String [][] strArray = new String [7][7];
	public void joinGame() {
		numPlayers++;
	}
	public void exitGame() {
		numPlayers--;
	}
	public void setstrArray(int i, int j, String str)
	{
		strArray[i][j] = str;
	}
	public String getstrArray(int i, int j)
	{
		return strArray[i][j];
	}
	public int getNumPlayers() {
		return numPlayers;
	}
}
