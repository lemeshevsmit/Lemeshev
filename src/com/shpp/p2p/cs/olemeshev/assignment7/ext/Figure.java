package com.shpp.p2p.cs.olemeshev.assignment7.ext;

import acm.graphics.*;

import java.awt.*;

/**
 * This class draw figure
 *
 * @author Aleksandr Lemeshev
 * @since 29.06.2022
 */
public class Figure extends GCompound {
    private final int centerX;
    private final int centerY;
    private final int size;
    private final Color color;

    /**
     * Constructor init parameters
     *
     * @param x     x coordinate center of figure
     * @param y     y coordinate center of figure
     * @param size  width and height of figure
     * @param color fill color
     */
    Figure(int x, int y, int size, Color color) {
        centerX = x;
        centerY = y;
        this.size = size;
        this.color = color;
    }

    /**
     * This method draw circle
     *
     * @return figure
     */
    private GObject drawCircle() {
        GOval circle = new GOval(centerX - size / 2.0, centerY - size / 2.0, size, size);
        circle.setFilled(true);
        circle.setFillColor(color);
        circle.setColor(color);
        return (circle);
    }

    /**
     * This method draw square
     *
     * @return figure
     */
    private GObject drawSquare() {
        GRect square = new GRect(centerX - size / 2.0, centerY - size / 2.0, size, size);
        square.setFilled(true);
        square.setFillColor(color);
        square.setColor(color);
        return (square);
    }

    /**
     * This method draw triangle
     *
     * @return figure
     */
    private GObject drawTriangle() {
        GPolygon triangle = new GPolygon();
        double y = centerY + size / (2 * Math.sqrt(3));
        triangle.addVertex(centerX - size / 2.0, y);
        triangle.addVertex(centerX + size / 2.0, y);
        triangle.addVertex(centerX, centerY - size / Math.sqrt(3));
        triangle.setFilled(true);
        triangle.setFillColor(color);
        triangle.setColor(color);
        return (triangle);
    }

    /**
     * This method draw rhomb
     *
     * @return figure
     */
    private GObject drawRhomb() {
        GPolygon rhomb = new GPolygon();
        rhomb.addVertex(centerX - size / 2.0, centerY);
        rhomb.addVertex(centerX, centerY + size / 2.0);
        rhomb.addVertex(centerX + size / 2.0, centerY);
        rhomb.addVertex(centerX, centerY - size / 2.0);
        rhomb.setFilled(true);
        rhomb.setFillColor(color);
        rhomb.setColor(color);
        return (rhomb);
    }

    /**
     * This method return figure. I create index from zero to three and
     * call method who draw figure
     *
     * @return figure
     */
    public GObject drawFigure(int i) {
        switch (i) {
            case 0 -> {
                return drawCircle();
            }
            case 1 -> {
                return drawSquare();
            }
            case 2 -> {
                return drawTriangle();
            }
            case 3 -> {
                return drawRhomb();
            }
        }
        return null;
    }
}
