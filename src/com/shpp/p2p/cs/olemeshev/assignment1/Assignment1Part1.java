package com.shpp.p2p.cs.olemeshev.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * In this task we move Karel to newspaper, pick up
 * and return back to start position
 * 
 * @author Alexandr Lemeshev
 * @since 17.05.2022
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Start of program.
     * In this method we have 3 step:
     * 1. Go to newspaper
     * 2. Get newspaper
     * 3. Go back to start position
     *
     * @throws Exception
     */
    public void run() throws Exception {
        goToNewspaper();
        pickBeeper();
        goToStartLocation();
    }


    /**
     * This method return Karel to start position
     *
     * @throws Exception
     */
    private void goToStartLocation() throws Exception {
        turnAround();
        goToWall();

        //turn rigth and move 1 row up
        turnRight();
        move();

        //Karel take started position (looks at the east)
        turnRight();
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
     * This method move Karel to newspaper.
     * Route: we go down one step, and go to beeper.
     *
     * @throws Exception
     */
    private void goToNewspaper() throws Exception {
        cameToLowerRow();
        moveToBeeper();
    }

    /**
     * This method move Karel until he see a newspaper(beeper)
     *
     * @throws Exception
     */
    private void moveToBeeper() throws Exception {
        while (noBeepersPresent()) {
            move();
        }
    }

    /**
     * This method move Karel one row down without changing direction
     *
     * @throws Exception
     */
    private void cameToLowerRow() throws Exception {
        turnRight();
        move();
        turnLeft();
    }

    /**
     * This method turn right Karel
     *
     * @throws Exception
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
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
