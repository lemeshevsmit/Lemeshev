package com.shpp.p2p.cs.olemeshev.assignment5;
import com.shpp.cs.a.console.TextProgram;

import java.math.BigDecimal;

public class Tack2Test extends TextProgram {
    public void run() {
		/* Automate our testing to make life easier! */
        check("223896236.504932", "454952909.833728",addN("223896236.504932", "454952909.833728"));
        check("394864564.084867", "558235747.441386",addN("394864564.084867", "558235747.441386"));
        check("394864564", "665852623",addN("394864564", "665852623"));
        check("212249089.850321", "494401915",addN("212249089.850321", "494401915"));
        check("45529", "0.47147420239388",addN("45529", "0.47147420239388"));
        check("2493602874287753452342", "0",addN("2493602874287753452342", "0"));
        check("999999999999999", "1",addN("999999999999999", "1"));
        check("00000", "0000","00000");
        check("1000000.99", "999999.01",addN("1000000.99", "999999.01"));
        check("99999999999999999999999999999999999999999999999", "9999999999999999999999999999999999999999999",
                addN("99999999999999999999999999999999999999999999999", "9999999999999999999999999999999999999999999"));
        check("1", "1", addN("1", "1"));
        check("9", "9", addN("9", "9"));
        check("0", "0", addN("0", "0"));
    }

    /**
     * Given a test case and an expected result for that test case, runs the test
     * and reports the result.
     *
     * @param testCase1 The test case to try out.
     * @param testCase2 The test case to try out.
     * @param expectedResult What the result of the test should be.
     */
    private void check(String testCase1,String testCase2, String expectedResult) {
        Assignment5Part2 test = new Assignment5Part2();
        if (test.addNumericStrings(testCase1,testCase2).equals(expectedResult)) {
            println("  Pass: ");
        } else {
            println("! FAIL! Must be:" + expectedResult);
        }
    }

    /**
     * Given two string representations of non negative integers, adds the
     * numbers represented by those strings and returns the result.
     * If we need minus use subtract; multiply - multiply; divide - divide;
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addN(String n1, String n2) {
        return new BigDecimal(n1).add(new BigDecimal(n2)).toString();
    }
}
