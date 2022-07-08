package com.shpp.p2p.cs.olemeshev.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.math.BigDecimal;

/**
 * This class do arithmetical operation plus for two number in String format
 * with decimal point symbol '.'
 *
 * @author Alexandr Lemeshev
 * @since 13.06.2022
 */
public class Assignment5Part2 extends TextProgram {
    public void run() {
        int lengthNum1, lengthNum2;
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            lengthNum1 = n1.length();
            lengthNum2 = n2.length();
            n1 = n1.replaceAll("[^\\p{N}|^.]+", "");
            n2 = n2.replaceAll("[^\\p{N}|^.]+", "");
            if (((n1.length() < lengthNum1) || n1.equals("")) &&
                    ((n2.length() < lengthNum2) || n2.equals(""))) {
                println("Please, input correct numbers.");
            } else {
                println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
                println();
            }

        }
    }

    /**
     * This method given two string representations of non negative decimal
     * numbers, adds the numbers and returns sum. I find position of decimal
     * point and count symbols of integer and fractional parts of numbers.
     * In cycle while I go from max count of fractional part to max count of
     * integer part and calculate numbers in this positions. If summa bigger
     * then ten I must save remainder after divide to ten. Result string
     * buffer I reverse because I add symbols to end.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    public String addNumericStrings(String n1, String n2) {
        /* Find position of decimal point in numbers
          it's integer part of number */
        int decPointPosN1 = n1.indexOf(".");
        int decPointPosN2 = n2.indexOf(".");
        //The decimal numeral system
        int decimal = 10;
        /* Max count symbols of fractional part in numbers
          (position decimal point from back in result number) */
        int count = Math.max(decPointPosN1 < 0 ? 0 : n1.length() - decPointPosN1,
                decPointPosN2 < 0 ? 0 : n2.length() - decPointPosN2) - 1;
        /*if we have natural number change decimal point position
          to ends of number */
        decPointPosN1 = decPointPosN1 < 0 ? n1.length() : decPointPosN1;
        decPointPosN2 = decPointPosN2 < 0 ? n2.length() : decPointPosN2;
        int summa = 0;
        StringBuilder sb = new StringBuilder();
        //while size of number not zero we take symbols
        while (count + decPointPosN1 >= 0 || count + decPointPosN2 >= 0) {
            if (count == 0) {
                sb.append('.');
                count--;
                continue;
            }
            //add to summa two numbers if we can
            if (count + decPointPosN1 >= 0 && count + decPointPosN1 < n1.length()) {
                summa += n1.charAt(count + decPointPosN1) - '0';
            }
            if (count + decPointPosN2 >= 0 && count + decPointPosN2 < n2.length()) {
                summa += n2.charAt(count + decPointPosN2) - '0';
            }
            //create final number
            if (summa >= decimal) {
                sb.append(summa % decimal);
                //save remainder
                summa = summa / decimal;
            } else {
                sb.append(summa);
                summa = 0;
            }
            //go to next symbol
            count--;
        }
        //if we have remainder add it's to final result
        if (summa > 0) sb.append(summa);
        return sb.reverse().toString();
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
    private String addNumericStrings2(String n1, String n2) {
        return new BigDecimal(n1).add(new BigDecimal(n2)).toString();
    }


    /**
     * This method return sum of two natural numbers.
     *
     * @param n1 The first natural number.
     * @param n2 The second natural number.
     * @return String representation of n1 + n2
     */
    private String sumNaturalNumbers(String n1, String n2) {
        int symbolN1 = 0, symbolN2 = 0, summa = 0, decimal = 10;
        StringBuilder sb = new StringBuilder();
        //find bigger number
        String firstNumber = n1.length() > n2.length() ? n1 : n2;
        String secondNumber = n1.length() > n2.length() ? n2 : n1;
        int j = secondNumber.length();
        //get last symbol of numbers
        for (int i = firstNumber.length(); i > 0; i--) {
            symbolN1 = Integer.parseInt(firstNumber, i - 1, i, decimal);
            if (firstNumber.length() > secondNumber.length()) {
                if (j > 0) {
                    symbolN2 = Integer.parseInt(secondNumber, j - 1, j, decimal);
                    j--;
                } else symbolN2 = 0;
            } else symbolN2 = Integer.parseInt(secondNumber, i - 1, i, decimal);
            //add two number
            summa = summa + symbolN1 + symbolN2;
            //create final number
            if (summa >= decimal) {
                sb.append(summa % decimal);
                //save remainder
                summa = summa / decimal;
            } else {
                sb.append(summa);
                summa = 0;
            }
        }
        //if we have remainder add it's to final result
        if (summa > 0) sb.append(summa);
        return sb.reverse().toString();
    }
}
