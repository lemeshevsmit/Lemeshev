package com.shpp.p2p.cs.olemeshev.assignment5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class open file
 *
 * @author Alexandr Lemeshev
 * @version 1.0
 * @since 17.06.2022
 */
public class FileOpener {

    /**
     * This method open file from input path, If file exist we read
     * him and return ArrayList of String with information in file,
     * else return NULL.
     *
     * @param filename path to file
     * @return ArrayList of String with input data
     */
    public ArrayList<String> openFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(filename);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else return null;
        return list;
    }
}
