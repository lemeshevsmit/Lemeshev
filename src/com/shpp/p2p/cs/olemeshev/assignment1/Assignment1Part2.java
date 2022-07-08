package com.shpp.p2p.cs.olemeshev.assignment1;


import com.shpp.karel.KarelTheRobot;

/**
 * In this task Karel build columns.
 * Each column must be integral and contain all beepers.
 * The final position and direction of Karel is not important.
 * You can not put beepers where they are already.
 *
 * @author Alexandr Lemeshev
 * @since 17.05.2022
 */
public class Assignment1Part2 extends KarelTheRobot {

    /**
     * Start of program.
     * In this method we have 3 step:
     * 1. Build column
     * 2. Go back to start position
     * 3. Make sure that the next column exists and
     * repeat the previous actions.
     *
     * @throws Exception
     */
    public void run() throws Exception {
        buildColumn();
        returnBackToStartPosition();

        //check if we can go to next column and build columns again
        while (frontIsClear()) {
            goNextColumn();
            buildColumn();
            returnBackToStartPosition();
        }
    }

    /**
     * This method move up Karel and put in empty cell beeper
     *
     * @throws Exception
     */
    private void buildColumn() throws Exception {
        // must turn left because we need go up
        turnLeft();
        goUpToWall();
    }

    /**
     * This method move Karel to the next column,
     * the distance between them is 4 cells
     *
     * @throws Exception
     */
    private void goNextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
//        //this block must be if last column have different position
//        move();
//        if (frontIsClear()) {
//            move();
//        }
//        if (frontIsClear()) {
//            move();
//        }
//        if (frontIsClear()) {
//            move();
//        }
    }

    /**
     * This method return Karel to start position
     *
     * @throws Exception
     */
    private void returnBackToStartPosition() throws Exception {
        turnAround();
        goToWall();
        turnLeft();
    }

    /**
     * This method move Karel up to end of column and put beepers
     *
     * @throws Exception
     */
    private void goUpToWall() throws Exception {
        checkPosition();
        while (frontIsClear()) {
            move();
            checkPosition();
        }

    }

    /**
     * This method check position and put beeper if position is empty
     *
     * @throws Exception
     */
    private void checkPosition() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }


    /**
     * This method move Karel to wall
     *
     * @throws Exception
     */
    private void goToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /**
     * This method turn around Karel
     *
     * @throws Exception
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}