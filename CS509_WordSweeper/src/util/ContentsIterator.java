package util;

import java.util.Iterator;

public class ContentsIterator implements Iterator<String>{
	/** Store string of contents reference. */
	String[] contents;
	/** State of iteration. */
	int idx;
	/** max. */
	final int max;
	/** Construct iterator object. */
	public ContentsIterator (String[] c, int max) {
		this.contents = c;
		idx = 0; // start at 0th element
		this.max = max; 
	}
	
	@Override
	public boolean hasNext() {
		return (idx < max);
	}

	@Override
	public String next() {

		String letter = contents[idx++];
		return letter;
	}
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Unable to remove values from underlying array.");
	}

}
