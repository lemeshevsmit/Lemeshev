package com.shpp.p2p.cs.olemeshev.assignment8;

import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * This class draw animation of snowfall
 *
 * @author Aleksandr Lemeshev
 * @since 12.07.2022
 */
public class Snowfall extends WindowProgram {
    // approximately size of application
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 400;

    //start count of Snowflake
    public static final int START_COUNT = 10;
    // The amount of time to pause between frames (50fps).
    private static final double PAUSE_TIME = 1000.0 / 50;

    // start position of Snowflake
    public static final int START_POSITION = -20;

    //  time to change wind in sec
    public static final int TIME_OF_CHANGE_WIND = 3;

    // speed of Snowflake ( we have random value in [min;max])
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 5;

    // size of Snowflake ( we have random value in [min;max])
    public static final int MIN_SIZE = 5;
    public static final int MAX_SIZE = 30;

    // amplitude of Snowflake ( we have random value in [min;max])
    public static final int MIN_AMPLITUDE = 1;
    public static final int MAX_AMPLITUDE = 5;

    // speed of wind( we have random value in [min;max])
    public static final int MIN_WIND = -4;
    public static final int MAX_WIND = 4;

    /**
     * Start method. In this method I add start snowflake and start play animation.
     */
    public void run() {
        ArrayList<Snowflake> snowflakes = new ArrayList<>(START_COUNT);
        createSnowflake(snowflakes, Math.random() * getHeight());
        doAnimation(snowflakes);
    }

    /**
     * This method create animation of snowfall. All snowflake have
     * speed, amplitude of fallen size and color. I create random wind
     * physics.
     *
     * @param snowflakes list with snowflakes
     */
    private void doAnimation(ArrayList<Snowflake> snowflakes) {
        LocalDateTime startTime = LocalDateTime.now();
        int windSpeed = 0; //start without wind
        while (true) {

            for (Snowflake s : snowflakes) {
                //animation of rotate
                s.rotate(s.getCoordinateX(), s.getCoordinateY(), Math.random() * 30);
                //animation of move
                s.move(s.getAmplitude() * Math.sin(s.getY() * Math.PI * 2 / getHeight()) + windSpeed
                        , s.getSpeed());
            }
            if (ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()) >= TIME_OF_CHANGE_WIND) {
                // every TIME_OF_CHANGE_WIND == 3 sec I change wind speed (-4;4)
                //windSpeed = (int) (Math.random() * (MAX_WIND - MIN_WIND)) + MIN_WIND;
                createSnowflake(snowflakes, Math.random() * START_POSITION);
                startTime = LocalDateTime.now();
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * this method create start count of snowflake and add them to screen
     * I use random parameter It's looks better.
     *
     * @param snowflakes list with Snowflake
     */
    private void createSnowflake(ArrayList<Snowflake> snowflakes, double coordinateY) {
        //start angle
        double angle = 0.0;
        for (int i = 0; i < START_COUNT; i++) {
            //random x position
            double x = Math.random() * getWidth();
            //speed (1;5)
            int speed = 1;
            //size (5;30)
            int size = (int) (Math.random() * (MAX_SIZE - MIN_SIZE)) + MIN_SIZE;
            //amplitude (1;5)
            int amplitude = 1;
            //random blue color  or use RandomGenerator.getInstance().nextColor()
            Color color = new Color(0, 0, (int) (Math.random() * (205)) + 50);
            Snowflake s = new Snowflake(x, coordinateY, size, speed, amplitude, color, angle);
            snowflakes.add(s);
            add(s);
        }
    }
}
