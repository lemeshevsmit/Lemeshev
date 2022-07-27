package com.shpp.p2p.cs.olemeshev.assignment11;

/**
 * class for my exception in calculator
 *
 * @author Aleksandr Lemeshev
 * @since 25.07.2022
 */
public class CalculatorException extends Throwable {

    /**
     * constructor without parameters
     */
    public CalculatorException() {
        System.err.println("Please, input correct value!");
    }

    /**
     * constructor with parameters
     *
     * @param parameter incorrect string value
     * @param kay       kay of exception
     */
    public CalculatorException(String parameter, int kay) {
        switch (kay) {
            case 0 -> System.err.println("Formula is null or empty: " + parameter);
            case 1 -> System.err.println("Negative number="
                    + parameter +
                    " in function [log]. Please, check input value.");
            case 2 -> System.err.println("Negative number="
                    + parameter +
                    " in function [sqrt]. Please, check input value.");
            case 3 -> System.err.println("Function [tan] doesn't exist in number="
                    + "PI/2 in peroid PI." + " Please, check input value.");
            case 4 -> System.err.println("Duplicate [=] in input parameters.");
            case 5 -> System.err.println("Divide by zero in formula.");
            case 6 -> System.err.println("Incorrect value: " + parameter);
            case 7 -> System.err.println("Incorrect operators: " + parameter);
            case 8 -> System.err.println("Cannot find: " + parameter +
                    ". Please, check input value.");
            case 9 -> System.err.println("Unknown argument: " + parameter);

            case 10 -> System.err.println("In function [" + parameter +
                    ". Please, check input arguments.");
            case 11 -> System.err.println("Incorrect lexeme sequence: " + parameter);
        }
    }
}
