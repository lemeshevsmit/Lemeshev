package com.shpp.p2p.cs.olemeshev.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;


/**
 * This class build game Breakout
 *
 * @author Alexandr Lemeshev
 * @since 8.06.2022
 */
public class Breakout extends WindowProgram {
    /* Approximately width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /* Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /*Dimensions of the paddle*/
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /* Offset of the paddle up from the bottom*/
    private static final int PADDLE_Y_OFFSET = 30;

    /* Number of bricks per row (min:1 ) */
    private static final int NBRICKS_PER_ROW = 10;

    /* Number of rows of bricks (min:1 ; max:10) */
    private static final int NBRICK_ROWS = 10;

    /* Separation between bricks */
    private static final int BRICK_SEP = 4;

    /* Start animation flag*/
    private static boolean start = false;

    /* Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /* Height of a brick*/
    private static final int BRICK_HEIGHT = 8;

    /*Radius and Diameter of the ball in pixels*/
    private static final int BALL_RADIUS = 10;
    private static final int BALL_DIAMETER = 2 * BALL_RADIUS;


    /*Offset of the top brick row from the top*/
    private static final int BRICK_Y_OFFSET = 70;

    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 48;

    /*Number of turns(life's)*/
    private static final int NTURNS = 3;

    /* Graphics object ball and paddle*/
    private GRect paddle;
    private GOval ball;

    /* Number of turn*/
    private int turn = 1;

    /* Count of brick in game (when we start it's be 100*/
    private int countOfBrick = 0;

    /* Speed coordinate of ball*/
    private double vx;
    private double vy = 3.0;

    /**
     * Start method. In this method we build all elements and start game.
     * We play game 'NTURNS' rounds. We win when break all bricks.
     */
    public void run() {
        buildPaddle();
        buildBall();
        buildBricks();
        waitForClick();
        addMouseListeners();
        waitForMove();
        moveBall();
        while (turn <= NTURNS) {
            //delete old ball
            remove(ball);
            waitForClick();
            if (countOfBrick != 0) buildBall();
            start = false;
            waitForMove();
            moveBall();
        }
    }

    /**
     * This method wait because we don't move mouse
     */
    private void waitForMove() {
        while (!start) {
            pause(1);
        }
    }

    /**
     * This method do motion animation of ball. We move ball and check contact coordinate.
     */
    private void moveBall() {
        //start speed of ball
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx;
        //motion animation of ball
        while (countOfBrick != 0) {
            ball.move(vx, vy);
            //floor - we lost one life or lose your game.
            if (ball.getY() + ball.getHeight() >= getHeight()) {
                turn++;
                if (turn > NTURNS) printText("YOU LOSE.");
                break;
            }
            checkBallContact();
            pause(PAUSE_TIME);
        }
        if (countOfBrick == 0) printText("YOU WIN!!");
    }

    /**
     * In this method we check all 3 board of game, and do correct animation
     * when we have contact with paddle. If we contact left(25% of paddle)
     * or right(25% of paddle) side of we change vx coordinate. If we be in
     * the paddle I move ball up. If object is not paddle I delete him.
     */
    private void checkBallContact() {
        int deadLine = PADDLE_Y_OFFSET + PADDLE_HEIGHT + BALL_DIAMETER;
        //left wall contact
        if ((ball.getX() <= 0)) vx = -vx;
        //right wall contact
        if ((ball.getX() + ball.getWidth() >= getWidth())) vx = -vx;
        //roof contact
        if (ball.getY() <= 0) vy = -vy;
        //object contact
        if (getCollidingObject() != null) {
            vy = -vy;
            //brick contact
            if (!(Objects.equals(getCollidingObject(), paddle))) {
                remove(getCollidingObject());
                countOfBrick--;
            } else {
                //paddle contact
                if (ball.getY() > getHeight() - deadLine) {
                    ball.setLocation(ball.getX(), getHeight() - deadLine);
                }
            }
        }
    }

    /**
     * This method display text in center of screen
     *
     * @param text String text
     */
    private void printText(String text) {
        removeAll();
        GLabel finalText = new GLabel(text);
        finalText.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 44));
        add(finalText,
                (getWidth() - finalText.getWidth()) / 2.0,
                getHeight() / 2.0 - finalText.getDescent());
    }

    /**
     * This method move rectangle (paddle) with center in mouse position
     * but we take into consideration the left and right board of application.
     * Right maximum position of paddle: getWidth() - paddle.getWidth();
     * Left minimum position of paddle: 0.0;
     * If 'mouseEvent.getX()-paddle.getWidth()/2.0' have coordinate between
     * right or left zone we move paddle to this position, else we must take
     * relevant coordinate (getWidth()-paddle.getWidth() or 0.0)
     *
     * @param mouseEvent mouse parameters on screen
     */
    public void mouseMoved(MouseEvent mouseEvent) {
        start = true;
        double position = Math.max(
                Math.min(
                        mouseEvent.getX() - (paddle.getWidth() / 2.0),
                        getWidth() - paddle.getWidth()),
                0.0);
        paddle.setLocation(position, getHeight() - PADDLE_Y_OFFSET - paddle.getHeight());
    }

    /**
     * This method build bricks. In first step I find start coordinate X and Y.
     * After build rectangle and change coordinate. We change color to next after
     * we build two rows.
     */
    private void buildBricks() {
        //massive of colors
        Color[] color = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
        int startX = BRICK_SEP / 2;
        int startY = BRICK_Y_OFFSET;
        GRect rect;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                rect = new GRect(startX, startY, BRICK_WIDTH, BRICK_HEIGHT);
                rect.setFillColor(color[i / 2]);
                rect.setColor(color[i / 2]);
                rect.setFilled(true);
                add(rect);
                countOfBrick++;
                startX += (BRICK_WIDTH + BRICK_SEP);
            }
            //change coordinate of start new row
            startX = BRICK_SEP / 2;
            startY += (BRICK_HEIGHT + BRICK_SEP);
        }
    }

    /**
     * This method build paddle in center and fill in black color
     */
    private void buildPaddle() {
        double x = (getWidth() - PADDLE_WIDTH) / 2.0;
        double y = getHeight() - PADDLE_Y_OFFSET;
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    /**
     * This method build filled black ball in center of application
     */
    private void buildBall() {
        double x = (getWidth() - BALL_DIAMETER) / 2.0;
        double y = getHeight() / 2.0;
        ball = new GOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        add(ball);
    }

    /**
     * This method check 4 coordinate around the circle (rectangle coordinates)
     * and return GObject if ours coordinate contact with elements in application
     *
     * @return GObject if we contact with element or null
     */
    private GObject getCollidingObject() {
        GPoint[] coordinates = new GPoint[]{
                new GPoint(ball.getX(), ball.getY()),
                new GPoint(ball.getX(), ball.getY() + BALL_DIAMETER),
                new GPoint(ball.getX() + BALL_DIAMETER, ball.getY()),
                new GPoint(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER)};
        for (GPoint point : coordinates) {
            if (getElementAt(point) != null) return getElementAt(point);
        }
        return null;
    }
}
