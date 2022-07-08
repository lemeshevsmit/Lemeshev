package com.shpp.p2p.cs.olemeshev.assignment1;


import com.shpp.karel.KarelTheRobot;

/*
    this class clear reactor
 */
public class Assignment1Part5 extends KarelTheRobot {

        /*
        start
         */
        public void run() throws Exception {
            while (frontIsClear()) {
                checkReactor();
                if (frontIsClear()) {
                    move();
                    move();
                }
            }
            checkReactor();
        }

        private void checkReactor() throws Exception {
            if (!beepersPresent()) {
                clearReactor();
            }
        }

        private void clearReactor() throws Exception {
            turnLeft();
            move();
            clear();
            turnAround();
            goToWall();
            clear();
            turnAround();
            move();
            turnRight();
        }

        private void clear() throws Exception {
            while (beepersPresent()) {
                pickBeeper();
            }
        }

        private void turnAround() throws Exception {
            turnLeft();
            turnLeft();
        }

        /*
        move Karel until way is clear
         */
        private void goToWall() throws Exception {
            while (frontIsClear()) {
                move();
            }
        }

        private void turnRight() throws Exception {
            turnLeft();
            turnLeft();
            turnLeft();
        }
    }
