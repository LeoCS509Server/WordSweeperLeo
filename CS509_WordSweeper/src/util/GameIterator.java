package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import server.model.Game;

public class GameIterator implements Iterator<Game>{

	Scanner sc;
	
	public GameIterator(File f) throws FileNotFoundException{
		sc = new Scanner(f);
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Game next() {
		// TODO Auto-generated method stub
		return null;
	}

}
