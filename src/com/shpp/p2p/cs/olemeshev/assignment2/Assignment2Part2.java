package com.shpp.p2p.cs.olemeshev.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class build optical illusion
 *
 * @author Alexandr Lemeshev
 * @since 22.05.2022
 */
public class Assignment2Part2 extends WindowProgram {

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    /*
     * Set margin size to application.
     * I use it because size of application is 'approximately'.
     * I think with this margin it's look better.
     */
//    public static final int MARGIN_WIDTH = 17;
//    public static final int MARGIN_HEIGHT = 40;

    /*
     * Diameter of circles, I use math.min because I want draw the circle proportionally to
     * application window. I take minimal value and divide by three (33% of application)
     */
    public static final double DIAMETER = 70;

    /**
     * Run the program. I do two methods: one build circles, second - rectangle
     */
    public void run() {
        printCircles();
        printRectangle();
//        setSize(APPLICATION_WIDTH + MARGIN_WIDTH, APPLICATION_HEIGHT + MARGIN_HEIGHT);
    }

    /**
     * This method build rectangle. I calculate center of first circle. It's be start
     * coordinate and calculate width and height of rectangle ( it's be size of
     * application - diameter, because we build rectangle in centers os circles).
     * Change color of border and fill to white.
     */
    private void printRectangle() {
        double startPoint = DIAMETER / 2.0;
        double endPointX = getWidth() - DIAMETER;
        double endPointY = getHeight() - DIAMETER;
        GRect rectangle = new GRect(startPoint, startPoint, endPointX, endPointY);
        rectangle.setFilled(true);
        rectangle.setColor(Color.WHITE);
        rectangle.setFillColor(Color.WHITE);
        add(rectangle);
    }

    /**
     * This method build 4 circle. I use two cycle 'for' with 2 steps ( 0 and 1)
     * Where: 0 - zero coordinate; 1 - size of application minus diameter.
     */
    private void printCircles() {
        GOval circle;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                circle = new GOval(
                        (getWidth() - DIAMETER) * i,
                        (getHeight() - DIAMETER) * j,
                        DIAMETER,
                        DIAMETER);
                circle.setFilled(true);
                circle.setFillColor(Color.BLACK);
                add(circle);
            }
        }

    }
}
