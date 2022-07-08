package com.shpp.p2p.cs.olemeshev.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

/**
 * In this task we build The St. Petersburg Game
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part5 extends TextProgram {

    /**
     * Start of program. I use random.nextBoolean because i need random
     * true/false value. Then I use cycle 'while' because i don't know
     * number of iterations, and we play until cash < 20$. Eagle == true
     * Tail == false. In this game we always win ( 1$ or more) .
     */
    public void run() {
        println("The St. Petersburg Game.");
        Random random = new Random();
        int countOfGames = 0;
        int cash = 0;
        int prize = 1;
        while (cash < 20) {
            //if we have eagle == true, multiply prize
            while (random.nextBoolean()) { prize *= 2; }
            // this block if we have tail == false, take cash
            println("This game, you earned $" + prize);
            cash += prize;
            println("Your total is $" + cash);
            countOfGames++;
        }
        println("It took " + countOfGames + " games to earn $20");
    }
}
