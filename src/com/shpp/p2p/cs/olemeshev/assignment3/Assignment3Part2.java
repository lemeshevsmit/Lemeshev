package com.shpp.p2p.cs.olemeshev.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * In this task we build Collatz conjecture
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part2 extends TextProgram {

    /**
     * Start of program. This method build Collatz conjecture. I use cycle
     * 'while' because we don't know number of iterations but we know end of
     * conjecture - it's 'number=1'. In next step I check if the number is
     * paired then decrease by 2 otherwise increase to 3*n+1.
     */
    public void run() {
        print("Enter a natural number: ");
        int number = readInt();
        while (number != 1) {
            if (number % 2 == 0) {
                print(number + " is even so I take half: ");
                number = number / 2;
            } else {
                print(number + " is odd so I make 3n + 1: ");
                number = number * 3 + 1;
            }
            println(number);
        }
        println("The end.");
    }
}
