package com.shpp.p2p.cs.olemeshev.assignment1;


import com.shpp.karel.KarelTheRobot;

/**
 * This class build chess board with black in start position.
 *
 * @author Alexandr Lemeshev
 * @since 17.05.2022
 */
public class Assignment1Part4 extends KarelTheRobot {

    /**
     * Start of program.
     * In this method we put beeper in start position if world is 1*1 and exit.
     * If world is X*1 turn left and build chess line and if world X*X or 1*X
     * build chess board with black in start position.
     *
     * @throws Exception
     */
    public void run() throws Exception {
        //if world is X*X  or 1*X we go
        if (frontIsClear()) {
            //put beeper on start position
            buildChess();
        } else {
            // if world x*1 then put beepers up and exit
            turnLeft();
            if (frontIsClear()) {
                //put beeper on start position
                putBeeper();
                buildFirstChessLine();
            } else {
                //if world 1*1 put beeper and exit
                putBeeper();
            }
        }
    }

    /**
     * This method build chess board with black in start position.
     * @throws Exception
     */
    private void buildChess() throws Exception{
        while (frontIsClear()) {
            //build first line
            putBeeper();
            buildFirstChessLine();
            goBackToStart();

            //Karel see north and must check wall
            if (frontIsClear()) {
                //build second line
                moveWithTurnRight();
                buildSecondChessLine();
                goBackToStart();

                //Karel see north and must check wall
                //After Karel can build first line again
                if (frontIsClear()) {
                    moveWithTurnRight();
                }
            }
        }
    }

    /**
     * This method move Karel with turn right
     *
     * @throws Exception
     */
    private void moveWithTurnRight() throws Exception {
        move();
        turnRight();
    }

    /**
     * This method return Karel to star position and go face to north
     *
     * @throws Exception
     */
    private void goBackToStart() throws Exception {
        turnAround();
        goToWall();
        turnRight();
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
     * This method build chess line with beeper in the start
     *
     * @throws Exception
     */
    private void buildFirstChessLine() throws Exception {
        if (frontIsClear()) {
            move();
            while (frontIsClear()) {
                move();
                putBeeper();
                buildFirstChessLine();
            }
        }
    }

    /**
     * This method build chess line without beeper in the start
     *
     * @throws Exception
     */
    private void buildSecondChessLine() throws Exception {
        if (frontIsClear()) {
            move();
            putBeeper();
            if (frontIsClear()) {
                move();
            }
            while (frontIsClear()) {
                buildSecondChessLine();
            }
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

    /**
     * This method move Karel until way is clear
     *
     * @throws Exception
     */
    private void goToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}
