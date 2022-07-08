package com.shpp.p2p.cs.olemeshev.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This class find middle of line and put beeper in this position
 * if the size of the field is an paired number,
 * then we put the beeper in the second part of the middle cells
 * 
 * @author Alexandr Lemeshev
 * @since 17.05.2022
 */
public class Assignment1Part3 extends KarelTheRobot {

    /**
     * Start of program.
     * In this method we put beeper in start position. If world is 1x1 the exit.
     * If world not 1x1 go to end of line put beeper and turn around.
     * In next step Karel put beepers between start and last position and stay
     * in the middle. Pick middle beeper because we are doing the inversion of
     * beepers. Go to start position and replace beepers.
     *
     * @throws Exception
     */
    public void run() throws Exception {
        //put beeper on start position
        putBeeper();

        //if world not 1x1 we go
        if (frontIsClear()) {
            // go Karel to last position
            goToWall();

            //put beepers between start and last position and stay in the middle
            putBeeper();
            turnAround();
            putBeepersBetweenTwoBeeper();
            turnAroundWithOneStep();

            //pick middle beeper because we are doing the inversion of beepers
            pickBeeper();

            // go to start position
            goToWall();
            turnAround();

            //replace beepers
            replaceBeepers();
        }
    }

    /**
     * This method replace beepers on the way
     *
     * @throws Exception
     */
    private void replaceBeepers() throws Exception {
        while (frontIsClear()) {
            replacePosition();
            move();
        }
        replacePosition();
    }

    /**
     * This method replace one beeper in this position
     *
     * @throws Exception
     */
    private void replacePosition() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
        } else {
            putBeeper();
        }
    }

    /**
     * This method put beepers between two cells with beeper
     *
     * @throws Exception
     */
    private void putBeepersBetweenTwoBeeper() throws Exception {
        move();
        while (noBeepersPresent()) {
            goToBeeper();
            turnAroundWithOneStep();
            putBeeper();
            putBeepersBetweenTwoBeeper();
        }
    }

    /**
     * This method turn around Karel and do one step
     *
     * @throws Exception
     */
    private void turnAroundWithOneStep() throws Exception {
        turnAround();
        move();
    }

    /**
     * This method move Karel to beeper
     *
     * @throws Exception
     */
    private void goToBeeper() throws Exception {
        while (noBeepersPresent()) {
            move();
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
