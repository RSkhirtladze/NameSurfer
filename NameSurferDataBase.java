import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			BufferedReader Entr = new BufferedReader(new FileReader(filename));

			while (true) {
				String line = Entr.readLine();
				if (line == null)
					break;
				NameSurferEntry NewEntry = new NameSurferEntry(line);
				Entries.add(NewEntry);
			}

			Entr.close();

		} catch (IOException e) {
			System.out.println("Try another file");
		}

	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		for (int i = 0; i < Entries.size(); i++)
			if (Entries.get(i).getName().toLowerCase().equals(name.toLowerCase()))
				return Entries.get(i);

		return null;
	}

	private ArrayList<NameSurferEntry> Entries = new ArrayList<>();
}
