
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String names = "";
	private int[] amount = new int[NDECADES];

	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 */
	public NameSurferEntry(String line) {
		int place = 0;
		StringTokenizer tkn = new StringTokenizer(line);
		names = tkn.nextToken().toUpperCase();
		while (tkn.hasMoreTokens()) {
			String number = tkn.nextToken();
			amount[place] = Integer.parseInt(number);
			place++;
		}

	}

	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return names;
	}

	/**
	 * Returns the rank associated with an entry for a particular decade. The decade
	 * value is an integer indicating how many decades have passed since the first
	 * year in the database, which is given by the constant START_DECADE. If a name
	 * does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {

		return amount[decade];
	}

	/**
	 * Returns a string that makes it easy to see the value of a NameSurferEntry.
	 */
	public String toString() {

		String info = names + " - [ ";

		for (int i : amount) {
			info += i + ", ";
		}
		info += info.substring(0, info.length() - 2) + " ]";

		return info;
	}
}
