package com.shpp.p2p.cs.olemeshev.assignment8;

import acm.graphics.GCompound;
import acm.graphics.GLine;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class draw snowflake
 *
 * @author Alexandr Lemeshev
 * @since 12.07.2022
 */
public class Snowflake extends GCompound {
    private Color color;
    private double size;
    private double speed;
    private double amplitude;
    private double coordinateX;
    private double coordinateY;
    private static final double DEGREE_STEP = 30.0;
    private final ArrayList<GLine> list;

    /**
     * Constructor with parameters
     *
     * @param x         coordinate x
     * @param y         coordinate y
     * @param size      size of snowflake
     * @param speed     speed of fall snowflake
     * @param amplitude amplitude of sin wave of snowflake
     * @param color     color of snowflake
     * @param angle     angle of start rotate
     */
    Snowflake(double x, double y, double size, double speed,
              double amplitude, Color color, double angle) {
        this.size = size;
        this.speed = speed;
        this.amplitude = amplitude;
        this.color = color;
        this.coordinateX = x;
        this.coordinateY = y;
        this.list = new ArrayList<>();
        init(x, y, angle);
    }

    /**
     * This method draw snowflake from GLine object with parameter angle
     *
     * @param x     coordinate x
     * @param y     coordinate y
     * @param angle start angle of rotate
     */
    private void init(double x, double y, double angle) {
        for (double i = angle; i < angle + 360.0; i += DEGREE_STEP) {
            GLine line = new GLine(x, y,
                    this.size * Math.cos(Math.toRadians(i)) + x,
                    this.size * Math.sin(Math.toRadians(i)) + y);
            line.setColor(this.color);
            this.list.add(line);
            add(line);
            line.sendToBack();
        }
    }

    /**
     * This method re-draw snowflake with new angle
     *
     * @param x     coordinate x
     * @param y     coordinate y
     * @param angle angle of rotation
     */
    public void rotate(double x, double y, double angle) {
        for (GLine line : list) {
            remove(line);
        }
        list.clear();
        init(x, y, angle);
    }

    /**
     * Setter of color
     *
     * @param color new color
     */
    public void setColorOf(Color color) {
        this.color = color;
    }

    /**
     * Setter of size
     *
     * @param size new size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Seter of speed
     *
     * @param speed new speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Getter of speed
     *
     * @return speed of snowflake
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Setter of amplitude
     *
     * @param amplitude new amplitude
     */
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    /**
     * Getter of amplitude
     *
     * @return amplitude of snowflake
     */
    public double getAmplitude() {
        return amplitude;
    }

    /**
     * Getter of coordinate x
     *
     * @return coordinate x of snowflake
     */
    public double getCoordinateX() {
        return coordinateX;
    }

    /**
     * Getter of coordinate y
     *
     * @return coordinate y of snowflake
     */
    public double getCoordinateY() {
        return coordinateY;
    }

    /**
     * Setter of coordinate x
     *
     * @param coordinateX new coordinate x
     */
    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Setter of coordinate y
     *
     * @param coordinateY new coordinate y
     */
    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }
}
