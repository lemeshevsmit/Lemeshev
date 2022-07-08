package com.shpp.p2p.cs.olemeshev.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


/**
 * This class build game Breakout
 *
 * @author Alexandr Lemeshev
 * @version 0.1.3
 * @since 10.06.2022
 */
public class BreakoutExt extends WindowProgram {
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
    private GOval powerBall = null;
    private int numberOfPower = 0;

    /* Number of turn */
    private int turn = 1;

    /* Count of brick in game (when we start it's be 100) */
    private int countOfBrick = 0;

    /* Speed coordinate of ball min[0.1] max [3.0]*/
    private double vx;
    private double vy = 3.0;

    /**
     * Start method. In this method we build all elements and start game.
     * We play game 'NTURNS' rounds. We win when break all bricks.
     */
    public void run() {
        setBackground(Color.BLACK);
        //List with Heart object == lives == turn
        ArrayList<Heart> lives = new ArrayList<>(NTURNS);
        //add heart in screen
        for (int i = 0; i < NTURNS; i++) {
            lives.add(new Heart(WIDTH / 30.0, WIDTH / 30.0));
            add(lives.get(i), getWidth() - (i + 1) * WIDTH / 20.0, getHeight() - WIDTH / 25.0);
        }
        printScoreLabel();
        //display base elements
        buildPaddle();
        buildBall();
        buildBricks();
        waitForClick();
        addMouseListeners();
        waitForMove();
        moveBall(lives);
        while (turn <= NTURNS) {
            //delete old ball
            remove(ball);
            waitForClick();
            start = false;
            if (countOfBrick != 0) {
                buildBall();
            }
            waitForMove();
            moveBall(lives);
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
     * This method display score point label
     */
    private void printScoreLabel() {
        GLabel score = new GLabel("SCORE: 0");
        score.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 14));
        score.setColor(Color.WHITE);
        add(score, BRICK_SEP, getHeight() - BRICK_SEP - score.getDescent());
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
     * This method build filled white ball in center of application
     */
    private void buildBall() {
        double x = (getWidth() - BALL_DIAMETER) / 2.0;
        double y = getHeight() / 2.0;
        ball = new GOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setColor(Color.WHITE);
        ball.setFillColor(Color.WHITE);
        add(ball);
    }

    /**
     * This method build paddle in center and fill in white color
     */
    private void buildPaddle() {
        double x = (getWidth() - PADDLE_WIDTH) / 2.0;
        double y = getHeight() - PADDLE_Y_OFFSET;
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(Color.WHITE);
        paddle.setFillColor(Color.WHITE);
        add(paddle);
    }

    /**
     * In this method we check all 3 board of game, and do correct animation
     * when we have contact with paddle. If we contact left(25% of paddle)
     * or right(25% of paddle) side of we change vx coordinate. If we be in
     * the paddle I move ball up. If object is not paddle I delete him.
     */
    private void moveBall(ArrayList<Heart> lives) {
        LocalDateTime startTime = LocalDateTime.now();
        //start speed of ball
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        //motion animation of ball
        while (countOfBrick != 0) {
            ball.move(vx, vy);
            startTime = powerBallAnimation(startTime);
            //floor - we lost one life or lose your game.
            if (floorContact(lives, rgen)) {
                break;
            }
            //others contact
            checkBallContact();
            pause(PAUSE_TIME);
        }
        if (countOfBrick == 0) {
            /* Name of audio file*/
            new MyAudioPlayer("win.wav").start();
            printText("YOU WIN!!");
        }

    }

    /**
     * This method do power-ball animation, check contact and refresh power
     *
     * @param startTime start time of power up
     * @return time of power up
     */
    private LocalDateTime powerBallAnimation(LocalDateTime startTime) {
        if (powerBall != null) {
            movePowerBall();
            checkPowerBallContact();
            startTime = LocalDateTime.now();
        }
        if ((ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()) >= 5)) {
            if (numberOfPower % 2 == 0) {
                getPower(numberOfPower - 1);
            } else {
                getPower(numberOfPower + 1);
            }
            numberOfPower = 0;
        }
        return startTime;
    }

    /**
     * This method check power-ball contact with floor or with paddle
     */
    private void checkPowerBallContact() {
        if (powerBall.getY() + powerBall.getHeight() >= getHeight()) {
            remove(powerBall);
            powerBall = null;
        } else {
            if (Objects.equals(getCollidingObject(powerBall), paddle)) {
                remove(powerBall);
                powerBall = null;
                int power = RandomGenerator.getInstance().nextInt(1, 4);
                getPower(power);
            }
        }
    }

    /**
     * This method add random power (positive or negative)
     *
     * @param power number of power
     */
    private void getPower(int power) {
        switch (power) {
            case 1 -> paddle.scale(2, 1);
            case 2 -> paddle.scale(0.5, 1);
            case 3 -> {
                vy *= 2;
                vx *= 2;
            }
            case 4 -> {
                vy /= 2;
                vx /= 2;
            }
        }
        numberOfPower = power;
    }

    /**
     * In this method we check all 3 board of game, and do correct animation
     */
    private void checkBallContact() {
        //left wall contact
        if ((ball.getX() <= 0)) {
            vx = -vx;
        }
        //right wall contact
        if ((ball.getX() + ball.getWidth() >= getWidth())) {
            vx = -vx;
        }
        //roof contact
        if (ball.getY() <= 0) {
            vy = -vy;
        }
        //object contact
        if (getCollidingObject(ball) != null) {
            //brick contact
            if (!(Objects.equals(getCollidingObject(ball), paddle))) {
                brickContact();
            } else paddleContact(); // paddle contact
        }
    }

    /**
     * This method check paddle contact. Start new thread with audio and
     * change vx direction if we contact left or right side of paddle
     */
    private void paddleContact() {
        int deadLine = PADDLE_Y_OFFSET + PADDLE_HEIGHT + BALL_DIAMETER;
        new MyAudioPlayer("paddle.wav").start();
        if (ball.getY() > getHeight() - deadLine) {
            ball.setLocation(ball.getX(), getHeight() - deadLine);
            if (ball.getX() + BALL_DIAMETER - paddle.getX() <= paddle.getWidth() / 4 && vx > 0) {
                vx = -vx;
            }
            if (ball.getX() - paddle.getX() >= 3 * paddle.getWidth() / 4 && vx < 0) {
                vx = -vx;
            }
        }
        vy = -vy;
    }

    /**
     * This method build power-ball when certain bricks are broken
     * that drops from the brick
     */
    public void buildPowerBall() {
        powerBall = new GOval(ball.getX(), ball.getY(), BALL_DIAMETER, BALL_DIAMETER);
        powerBall.setFillColor(Color.YELLOW);
        powerBall.setColor(Color.YELLOW);
        powerBall.setFilled(true);
        add(powerBall);
    }

    /**
     * This method move power-ball downward with a velocity,
     * independent of the changing slowness of quickly variable colors
     */
    public void movePowerBall() {
        Color[] color = new Color[]{
                Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE,
                Color.MAGENTA, Color.PINK, Color.GRAY};
        Color ballColor = color[RandomGenerator.getInstance().nextInt(0, color.length - 1)];
        powerBall.setFillColor(ballColor);
        powerBall.setColor(ballColor);
        powerBall.move(0, vy * 2);
    }

    /**
     * This method check brick contact. Start new thread with audio and do speed up.
     * If we contact with GRect I delete this object
     */
    private void brickContact() {
        if (Objects.equals(Objects.requireNonNull(
                getCollidingObject(ball)).getClass().getName(),
                "acm.graphics.GRect")) {
            if (countOfBrick > 1) {
                new MyAudioPlayer("brick.wav").start();
            }
            remove(getCollidingObject(ball));
            countOfBrick--;
            //change score
            changeScore();
            // speed up
            vx *= 1.01;
            vy *= 1.01;
            vy = -vy;
            if (new Random().nextBoolean() && powerBall == null && numberOfPower == 0) {
                buildPowerBall();
            }
        }
        //// other object contact
    }

    /**
     * This method get position of score label and modified it. This method is specific,
     * because I don't have variable with score. I get element from screen and modified
     * this object. I parse from String number of score and add to int variable. Then I
     * display new value.
     */
    private int changeScore() {
        //+1 because I need 100% get GLabel Object
        GLabel text = (GLabel) getElementAt(BRICK_SEP + 1, getHeight() - BRICK_SEP - 1);
        String[] old = text.getLabel().split("\\D+");
        int oldScore = Integer.parseInt(String.join("", old));
        int newScore = oldScore + (NBRICK_ROWS * NBRICKS_PER_ROW - countOfBrick) * 10 / turn;
        text.setLabel("SCORE: " + newScore);
        return newScore;
    }

    /**
     * This method check contact with low side of application.
     * If we lost a round we restart speed and play again.
     * If we lise game I create new thread with audio and display result
     *
     * @param rgen random speed x coordinate
     * @return true - contact floor; false - no contact
     */
    private boolean floorContact(ArrayList<Heart> lives, RandomGenerator rgen) {
        if (ball.getY() + ball.getHeight() >= getHeight()) {
            remove(lives.get(NTURNS - turn));
            //restart speed
            vx = rgen.nextDouble(1.0, 3.0);
            vy = 3.0;
            turn++;
            if (turn > NTURNS) {
                new MyAudioPlayer("lose.wav").start();
                printText("YOU LOSE.");
            }
            return true;
        }
        return false;
    }

    /**
     * This method display text in center of screen and if we win I display
     * total score in lower side of application
     *
     * @param text String text
     */
    private void printText(String text) {
        GLabel score = new GLabel("");
        int finalScore = changeScore();
        score.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 30));
        removeAll();
        score.setLabel("Total score: " + finalScore);
        score.setColor(Color.WHITE);
        add(score,
                (getWidth() - score.getWidth()) / 2.0,
                getHeight() * 0.9 - score.getDescent());
        buildTurtle(finalScore);
        GLabel finalText = new GLabel(text);
        finalText.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 44));
        finalText.setColor(Color.WHITE);
        add(finalText,
                (getWidth() - finalText.getWidth()) / 2.0,
                getHeight() / 2.0 - finalText.getDescent());

    }

    /**
     * This method build prize score stars == turtle =)) have fun
     *
     * @param finalScore total score
     */
    private void buildTurtle(int finalScore) {
        if (finalScore >= 40000) {
            add(new GTurtle(), getWidth() * 0.5, getHeight() * 0.1);
            add(new GTurtle(), getWidth() * 0.75, getHeight() * 0.15);
            add(new GTurtle(), getWidth() * 0.25, getHeight() * 0.15);
        } else {
            if (finalScore < 20000) {
                add(new GTurtle(), getWidth() * 0.5, getHeight() * 0.1);
            } else {
                add(new GTurtle(), getWidth() * 0.75, getHeight() * 0.15);
                add(new GTurtle(), getWidth() * 0.25, getHeight() * 0.15);
            }
        }
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
        double position = Math.max(Math.min(mouseEvent.getX() - (paddle.getWidth() / 2.0), getWidth() - paddle.getWidth()), 0.0);
        paddle.setLocation(position, getHeight() - PADDLE_Y_OFFSET - paddle.getHeight());
    }

    /**
     * This method check massive coordinates around the circle (circle coordinates)
     * and return GObject if ours coordinate contact with elements in application
     *
     * @return GObject if we contact with element or null
     */
    private GObject getCollidingObject(GOval ball) {
        //2 px around the ball
        int around = 2;
        double circleDegree = 360.0;
        double degree = 10.0;
        double ballXCenter = ball.getX() + BALL_RADIUS;
        double ballYCenter = ball.getY() + BALL_RADIUS;
        ArrayList<GPoint> coordinates = new ArrayList<>();
        GPoint point;
        for (double i = 0.0; i < circleDegree; i += degree) {
            point = new GPoint(ballXCenter + (BALL_RADIUS + around) * Math.cos(Math.toRadians(i)),
                    ballYCenter + (BALL_RADIUS + around) * Math.sin(Math.toRadians(i)));
            coordinates.add(point);
        }
        for (GPoint p : coordinates) {
            if (getElementAt(p) != null) {
                return getElementAt(p);
            }
        }
        return null;
    }
}