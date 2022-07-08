package com.shpp.p2p.cs.olemeshev.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

/**
 * This class find all words who contain input symbols
 *
 * @author Alexandr Lemeshev
 * @since 13.06.2022
 */
public class Assignment5Part3 extends TextProgram {
    private int countOfCombination;

    /**
     * This method open file and create array with words. After we input
     * symbols and create array of symbols. In next step use method checkWord
     * who find all words from dictionary who contain input symbols.
     */
    public void run() {
        ArrayList<String> dictionary;
        FileOpener myFileOpener = new FileOpener();
        dictionary = myFileOpener.openFile("en-dictionary.txt");
        while (true) {
            String[] letters = readLine("Enter car number: ")
                    .replaceAll("[^\\p{L}]+", "")
                    .toLowerCase()
                    .split("");
            if (letters.length != 3) {
                println("Please input correct string!");
            } else {
                countOfCombination = 0;
                for (String word : dictionary) {
                    checkWord(letters, word);
                }
                println("Total count of words: " + dictionary.size());
                println("Total count of combinations: " + countOfCombination);
                println("It's only " + countOfCombination * 100.0 / dictionary.size() + " % of total.");
            }
        }
    }


    /**
     * This method find word from dictionary who contain input symbols.
     *
     * @param letters array of input symbols
     * @param word    checked word
     */
    private void checkWord(String[] letters, String word) {
        String temp = word;
        int key = 0;
        if (temp.contains(letters[key])) {
            //change world and take new symbol
            temp = temp.substring(temp.indexOf(letters[key]) + 1);
            key++;
            if (temp.contains(letters[key])) {
                //change world and take new symbol
                temp = temp.substring(temp.indexOf(letters[key]) + 1);
                key++;
                if (temp.contains(letters[key])) {
                    println(word);
                    countOfCombination++;
                }
            }
        }
    }
}
