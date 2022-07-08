package com.shpp.p2p.cs.olemeshev.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parse CSV format file and display all elements from input column
 *
 * @author Alexandr Lemeshev
 * @since 13.06.2022
 */
public class Assignment5Part4 extends TextProgram {
    /**
     * Start of application. This method parse CSV format file
     * and display all elements from input column
     */
    public void run() {
        ArrayList<String> columnData = extractColumn("test.csv", 1);
        if (columnData != null) {
            for (String s:columnData) {
                println(s);
            }
            //printcol(columnData);
        }
    }

    /**
     * This method print all collection arraylist to one line
     *
     * @param columnData ArrayList to print
     */
    private void printcol(ArrayList<String> columnData) {
        print("[");
        for (int i = 0; i < columnData.size(); i++) {
            print("\"" + columnData.get(i) + "\"");
            if (i < columnData.size() - 1) print(", ");
        }
        print("]");
    }


    /**
     * This method open file from input path, if file not exist we return null.
     * else we read all line and parse information. We check is have line
     * symbol '"', because this symbol tell us that there is a coma in this line
     * or not. Then I split line to massive use regex ','. When I check all input
     * data then I return arraylist with information in columnIndex number.
     *
     * @param filename    path to file
     * @param columnIndex number of column
     * @return arraylist with data
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> inputData;
        FileOpener myFileOpener = new FileOpener();
        inputData = myFileOpener.openFile(filename);
        if (inputData == null) return null;
        else {
            for (int i = 0; i < inputData.size(); i++) {
                Pattern pattern = Pattern.compile("[a-zA-Z],[ ]");
                Matcher matcher = pattern.matcher(inputData.get(i));
                //save symbol ','
                if (matcher.find()) {
                    inputData.set(i, inputData.get(i).replaceAll(", ", "#%@# "));
                }
                String[] words = inputData.get(i).split(",");
                for (int j = 0; j < words.length; j++) {
                    //return back symbol ','
                    words[j] = words[j]
                            .replaceAll("#%@# ", ", ")
                            .replaceAll("\"\"","\"");
                    //delete symbol '"' in start and end of string value
                    if (words[j].startsWith("\"") && (words[j].endsWith("\""))) {
                        words[j] = words[j].substring(1, words[j].length() - 1);
                    }
                }
                inputData.set(i, words[columnIndex]);
            }
        }
        return inputData;
    }
}
