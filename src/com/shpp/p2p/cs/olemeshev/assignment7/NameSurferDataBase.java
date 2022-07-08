package com.shpp.p2p.cs.olemeshev.assignment7;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 *
 * @author Aleksandr Lemeshev
 * @since 26.06.2022
 */
public class NameSurferDataBase implements NameSurferConstants {
    //database values
    private final HashMap<String, NameSurferEntry> database = new HashMap<>();
    private final ArrayList<String> listOfName = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     *
     * @param filename path to file of database
     * @throws FileNotFoundException if file don't exist
     */
    public NameSurferDataBase(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String key = line
                            .replaceAll("[^\\p{L}]+", "")
                            .toLowerCase();
                    this.database.put(key, new NameSurferEntry(line));
                    listOfName.add(key);
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else throw new FileNotFoundException();
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     *
     * @param name human name
     * @return the NameSurferEntry associated with this name
     */
    public NameSurferEntry findEntry(String name) {
        return this.database.getOrDefault(name, null);
    }

    /**
     * Getter list of keys
     *
     * @return arraylist with name in database
     */
    public ArrayList<String> getListOfName() {
        return listOfName;
    }
}

