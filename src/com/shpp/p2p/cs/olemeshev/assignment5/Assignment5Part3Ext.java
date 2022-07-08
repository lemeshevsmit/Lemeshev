package com.shpp.p2p.cs.olemeshev.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

/**
 * This class find all words who contain input symbols
 *
 * @author Alexandr Lemeshev
 * @since 13.06.2022
 */
public class Assignment5Part3Ext extends TextProgram {

    /**
     * This method find all combinations of symbols who don't have
     * words in dictionary.
     */
    public void run() {
        ArrayList<String> dictionary;
        FileOpener myFileOpener = new FileOpener();
        dictionary = myFileOpener.openFile( "en-dictionary.txt");
        int countOfWords = 0;
        int countOfCombinations = 0;
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char i : alphabet) {
            for (char j : alphabet) {
                for (char k : alphabet) {
                    for (String word : dictionary) {
                        if (word.contains(Character.toString(i))) {
                            word = word.substring(word.indexOf(Character.toString(i)) + 1);
                            if (word.contains(Character.toString(j))) {
                                word = word.substring(word.indexOf(Character.toString(j)) + 1);
                                if (word.contains(Character.toString(k))) {
                                    countOfWords++;
                                    break;
                                }
                            }
                        }
                    }
                    if (countOfWords == 0) {
                        println(Character.toString(i) + j + k);
                        countOfCombinations++;
                    } else countOfWords = 0;
                }
            }
        }
        println("Total count of combinations: " + (int) Math.pow(alphabet.length, 3));
        println("Total empty combinations: " + countOfCombinations);
        println("It's only " + countOfCombinations * 100 / Math.pow(alphabet.length, 3) + " % of total.");
    }
}
