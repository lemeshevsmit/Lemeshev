package com.shpp.p2p.cs.olemeshev.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class build flag of country
 *
 * @author Aleksandr Lemeshev
 * @since 23.05.2022
 */
public class Assignment2Part4 extends WindowProgram {


    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    /* Each flag is rectangle with this width and height. */
    private static final double FLAG_WIDTH = 300;
    private static final double FLAG_HEIGHT = 200;

    /* Massive of colors of flag Germany */
    private static final Color[] COLOR_GERMANY = {
            new Color(10, 10, 10),
            new Color(200, 30, 60),
            new Color(255, 200, 0)};

    /**
     * Start of program. In this task i build flag Germany.
     * This method have two runnable methods:
     * 1) displayFlagOfCountry() - build flag of country
     * 2) displayNameOfCountry() - build label with name of country
     */
    public void run() {
        displayFlagOfCountry();
        displayNameOfCountry();
    }

    /**
     * This method build label with name of country. In this method i use
     * Glabel with font (SourceSans Pro) and size: 24.
     */
    private void displayNameOfCountry() {
        GLabel label = new GLabel("Flag of " + "Germany");
        label.setFont("SourceSans Pro-24");
        //use getDescent because fonts have descent
        add(label, getWidth() - label.getWidth(), getHeight() - label.getDescent());
    }

    /**
     * This method build flag of country.
     */
    private void displayFlagOfCountry() {
        //find start position of flag
        double startPointX = getWidth() / 2.0 - FLAG_WIDTH / 2.0;
        double startPointY = getHeight() / 2.0 - FLAG_HEIGHT / 2.0;

        //Find height og one element of flag.
        double heightOfLine = FLAG_HEIGHT / 3.0;

        //build flag
        GRect flagRectangle;
        for (int i = 0; i < 3; i++) {
            flagRectangle = new GRect(startPointX, startPointY, FLAG_WIDTH, heightOfLine);
            flagRectangle.setColor(COLOR_GERMANY[i]);
            flagRectangle.setFillColor(COLOR_GERMANY[i]);
            flagRectangle.setFilled(true);
            startPointY += heightOfLine;
            add(flagRectangle);
        }
    }

}

