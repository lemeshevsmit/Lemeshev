package com.shpp.p2p.cs.olemeshev.Figures;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GPolygon;
import acm.util.RandomGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class draw snowflake
 *
 * @author Alexandr Lemeshev
 * @since 12.07.2022
 */
public class Snowflake extends GCompound {
    private static final int NUM_OF_ANGLE = 6;
    private final Color color;
    private final int size;
    private final int speed;
    private final int amplitude;
    private final int centerX;
    private final int centerY;
    private GPolygon p;

    /**
     * Constructor with parameters
     *
     * @param x         coordinate center x
     * @param y         coordinate center y
     * @param size      size of snowflake
     * @param speed     speed of fall snowflake
     * @param amplitude amplitude of sin wave of snowflake
     * @param color     color of snowflake
     */
    Snowflake(int x, int y, int size, int speed,
              int amplitude, Color color) {
        this.size = size;
        this.speed = speed;
        this.amplitude = amplitude;
        this.color = color;
        this.centerX = x;
        this.centerY = y;
        drawSnowflake(x, y, size);
    }

    private void drawSnowflake(int centerX, int centerY, int size) {
        GPolygon snowflake = new GPolygon();
        Random random = new Random();
        int countOfBranch = random.nextInt(NUM_OF_ANGLE);
        int sizeOfBranch = random.nextInt(size / NUM_OF_ANGLE);
        for (int i = 0; i < 2 * NUM_OF_ANGLE; i++) {
            if (i % 2 == 0) snowflake.addVertex(centerX, centerY);
            else {
                snowflake.addVertex(
                        (size / 2) * Math.cos(Math.toRadians(i * 30)) + centerX,
                        (size / 2) * Math.sin(Math.toRadians(i * 30)) + centerY);
            }
        }
        snowflake.setFilled(true);
        p = snowflake;
        add(snowflake);
    }

    public void rotate() {
        p.rotate(1);
    }
//    /**
//     * Setter of color
//     *
//     * @param color new color
//     */
//    public void setColorOf(Color color) {
//        this.color = color;
//    }
//
//    /**
//     * Setter of size
//     *
//     * @param size new size
//     */
//    public void setSize(double size) {
//        this.size = size;
//    }
//
//    /**
//     * Seter of speed
//     *
//     * @param speed new speed
//     */
//    public void setSpeed(double speed) {
//        this.speed = speed;
//    }
//
//    /**
//     * Getter of speed
//     *
//     * @return speed of snowflake
//     */
//    public double getSpeed() {
//        return speed;
//    }
//
//    /**
//     * Setter of amplitude
//     *
//     * @param amplitude new amplitude
//     */
//    public void setAmplitude(double amplitude) {
//        this.amplitude = amplitude;
//    }
//
//    /**
//     * Getter of amplitude
//     *
//     * @return amplitude of snowflake
//     */
//    public double getAmplitude() {
//        return amplitude;
//    }
//
//    /**
//     * Getter of coordinate x
//     *
//     * @return coordinate x of snowflake
//     */
//    public double getCoordinateX() {
//        return coordinateX;
//    }
//
//    /**
//     * Getter of coordinate y
//     *
//     * @return coordinate y of snowflake
//     */
//    public double getCoordinateY() {
//        return coordinateY;
//    }
//
//    /**
//     * Setter of coordinate x
//     *
//     * @param coordinateX new coordinate x
//     */
//    public void setCoordinateX(double coordinateX) {
//        this.coordinateX = coordinateX;
//    }
//
//    /**
//     * Setter of coordinate y
//     *
//     * @param coordinateY new coordinate y
//     */
//    public void setCoordinateY(double coordinateY) {
//        this.coordinateY = coordinateY;
//    }
}
