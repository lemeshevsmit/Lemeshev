package com.shpp.p2p.cs.olemeshev.assignment3;

import com.shpp.cs.a.console.TextProgram;


/**
 * In this task we raised to the power base number to exponent
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part3 extends TextProgram {

    /**
     * Start of program. Returns the value of the first number (base)
     * raised to the power of the second number (exponent).
     */
    public void run() {
        double base = readDouble("Enter a base number: ");
        int exponent = readInt("Enter a integer exponent number: ");
        println("Result: " + raiseToPower(base, exponent));
        //It's for test
        //println("POW: " + Math.pow(base, exponent));
    }

    /**
     * This method returns the value of the first number (base)
     * raised to the power of the second number (exponent).
     * If exponent == 0 then returns 1.0. If exponent > 0 then
     * we call method myRaiseToPow(), else we 1.0/ result of
     * method myRaiseToPow()
     *
     * @param base     value of number
     * @param exponent exponent number
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) return 1.0;
        if (exponent >= 0) return myRaiseToPow(base, exponent);
        else return 1.0 / myRaiseToPow(base, -exponent);
    }

    /**
     * This method is recursive. Recursion exit condition is
     * when our exponent == 1, we will start came back.
     * Mechanism of recursion is multiply base to our method
     * but without 1 in exponent.
     *
     * @param base     value of number
     * @param exponent exponent number, it's be only positive
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    private double myRaiseToPow(double base, int exponent) {
        if (exponent == 1) {
            return base;
        } else {
            return base * myRaiseToPow(base, exponent - 1);
        }
    }
}
