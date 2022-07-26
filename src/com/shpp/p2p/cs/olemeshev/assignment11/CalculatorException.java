package com.shpp.p2p.cs.olemeshev.assignment11;

/**
 * class for my exception if have exception
 *
 * @author Aleksandr Lemeshev
 * @since 25.07.2022
 */
public class CalculatorException extends Throwable {

    /**
     * constructor without
     */
    public CalculatorException() {
        System.err.println("Please, input correct value!");
    }

    /**
     * constructor with parameters
     *
     * @param parameter incorrect value
     * @param kay       kay of exception
     */
    public CalculatorException(String parameter, int kay) {
        switch (kay) {
            case 0 -> System.out.println("Formula is null or empty: " + parameter);
            case 1 -> System.out.println("Negative number="
                    + parameter +
                    " in function [log]. Please, check input value.");
            case 2 -> System.out.println("Negative number="
                    + parameter +
                    " in function [sqrt]. Please, check input value.");
            case 3 -> System.out.println("Function [tan] doesn't exist in number="
                    + "PI/2 in peroid PI." + " Please, check input value.");
            case 4 -> System.out.println("Duplicate [=] in input parameters.");
            case 5 -> System.out.println("Divide by zero in formula.");
            case 6 -> System.out.println("Incorrect value: " + parameter);
            case 7 -> System.out.println("Incorrect operators: " + parameter);
            case 8 -> System.out.println("Cannot find: " + parameter +
                    ". Please, check input value.");

        }
    }
}
