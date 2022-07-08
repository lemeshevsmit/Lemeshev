package com.shpp.p2p.cs.olemeshev.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Repeatedly prompt the user for a word and print out the estimated
 * number of syllables in that word.
 *
 * @author Alexandr Lemeshev
 * @since 13.06.2022
 */
public class Assignment5Part1 extends TextProgram {
    /**
     * Repeatedly prompt the user for a word and print out the estimated
     * number of syllables in that word.
     */
    public void run() {
        int startLength;
        String[] test = new String[]{"me", "quokka", "springbok", "syllable",
                "Unity", "Unite", "Growth", "Possibilities", "Nimble", "Beautiful", "Manatee",
                "mouse", "I", "language", "are", "energy", "technology", "moustache",
                "every", "area"};
        while (true) {
            String word = readLine("Enter a single word: ");
            startLength = word.length();
            word = word.toLowerCase();
            word = word.replaceAll("[^\\p{L}]+", "");
            if ((word.length() < startLength) || word.equals("")) {
                println("Please, input correct word.");
            } else {
                println("  Syllable count: " + syllablesIn(word));
//                //for test
//                for (String s : test) {
//                    println(s);
//                    println("  Syllable count: " + syllablesIn(s.toLowerCase()));
//                }
            }
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according
     * to the heuristic specified in the handout. I check nex rules:
     * closed syllable; open syllable; vowel combination syllable;
     * silent-e syllable; consonant-le syllable;
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        //String[] syllables = word.split("(?<=[aeiouy])");
        int countOfSyllable = 0;
        boolean isDoubleVowel = false;
//        // Rule: consonant-le syllable;
//        String[] endOfWords = new String[]{"ble", "cle", "dle", "fle", "tle", "gle"};
//        for (String end : endOfWords)
//            if (word.endsWith(end)) countOfSyllable++;
        for (int i = 0; i < word.length(); i++)
            // Rule: closed syllable; open syllable;
            if (isVowel(word.charAt(i))) {
                // Rule: silent-e syllable;
                if (!((word.charAt(i) == 'e') && (i == word.length() - 1)))
                    // Rule: vowel combination syllable;
                    if (!isDoubleVowel) {
                        countOfSyllable++;
                        isDoubleVowel = true;
                    }
            } else isDoubleVowel = false;
        if (countOfSyllable <= 0) return 1;
        else return countOfSyllable;
    }

    /**
     * This method check char and return true if char is vowel
     *
     * @param c char symbol
     * @return true if char is vowel
     */
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }
}
