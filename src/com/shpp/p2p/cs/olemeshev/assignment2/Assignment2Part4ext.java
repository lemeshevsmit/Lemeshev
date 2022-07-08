package com.shpp.p2p.cs.olemeshev.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Arrays;

/**
 * This class build flag of country
 *
 * @author Aleksandr Lemeshev
 * @since 23.05.2022
 */
public class Assignment2Part4ext extends WindowProgram {


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

    /**
     * Start of program. In this task i build flag to one of database country.
     * In comment i do animation of flags country's. 
	 * This method have three runnable methods:
     * 1) buildBackground() - build background of window and recliner after
     * 2) displayFlagOfCountry() - build flag of country
     * 3) displayNameOfCountry() - build label with name of country
     */
    public void run() {
        Arrays.stream(Country.values()).forEach(flag -> {
            buildBackground();
            displayFlagOfCountry(flag);
            displayNameOfCountry(flag);
            try {
                // sleep 2 sec
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        Country flag = Country.GERMANY;
//        displayFlagOfCountry(flag);
//        displayNameOfCountry(flag);
    }

    /**
     * This method clear window from all elements. I create rectangle with
     * position from start window [0;0]
     * to end of window [getWidth(); getHeight()]
     */
    private void buildBackground() {
        GRect clear = new GRect(0, 0, getWidth(), getHeight());
        clear.setColor(new Color(230, 230, 230));
        clear.setFillColor(new Color(230, 230, 230));
        clear.setFilled(true);
        add(clear);
    }

    /**
     * This method build label with name of country. In this method i use
     * Glabel with font (SourceSans Pro) and size: 24.
     *
     * @param country country from enum
     */
    private void displayNameOfCountry(Country country) {
        GLabel label = new GLabel("Flag of " + country.getName());
        label.setFont("SourceSans Pro-24");
		//use getDescent because fonts have descent
        add(label, getWidth() - label.getWidth(), getHeight() - label.getDescent());
    }

    /**
     * This method build flag of country.
     *
     * @param country country from enum
     */
    private void displayFlagOfCountry(Country country) {
        //find start position of flag
        double startPointX = getWidth() / 2.0 - FLAG_WIDTH / 2.0;
        double startPointY = getHeight() / 2.0 - FLAG_HEIGHT / 2.0;

        /* Find size elements of flag. I use orientation of flag.
		 * If orientation is horizontal i must change height to three parts
		 * If orientation is vertical i must change width to three parts
		 */
        double flagWidth, flagHeight;
        if (country.getOrientation()) {
            flagWidth = FLAG_WIDTH;
            flagHeight = FLAG_HEIGHT / 3.0;
        } else {
            flagWidth = FLAG_WIDTH / 3.0;
            flagHeight = FLAG_HEIGHT;
        }

        //build flag
        buildFlagOfCountry(country, startPointX, startPointY, flagWidth, flagHeight);
    }

    /**
     * This method build flag of country. I use cycle 'for' with 3 steps
     * because all flags have 3 colors. After I build first color rectangle
     * I must change coordinate for build next.
     *
     * @param country country from database
     * @param x       start coordinate x
     * @param y       start coordinate y
     * @param width   width of flag
     * @param height  height of flag
     */
    private void buildFlagOfCountry(Country country, double x, double y, double width, double height) {
        GRect flagRectangle;
        for (int i = 0; i < 3; i++) {
            flagRectangle = new GRect(x, y, width, height);
            flagRectangle.setColor(country.getColor(i));
            flagRectangle.setFillColor(country.getColor(i));
            flagRectangle.setFilled(true);

            //change coordinate
            if (country.getOrientation()) {
                y += height;
            } else {
                x += width;
            }
            add(flagRectangle);
        }
    }
}

/**
 * Database of country's
 */
enum Country {
    AUSTRIA("Austria", true, new Color[]{Color.RED, Color.WHITE, Color.RED}),
    ARMENIA("Armenia", true, new Color[]{Color.RED, Color.BLUE, Color.ORANGE}),
    BELGIUM("Belgium", false, new Color[]{Color.BLACK, Color.YELLOW, Color.RED}),
    BULGARIA("Bulgaria", true, new Color[]{Color.WHITE, new Color(0, 200, 50), Color.RED}),
    CHAD("Chad", false, new Color[]{Color.BLUE, Color.YELLOW, Color.RED}),
    FRANCE("France", false, new Color[]{Color.BLUE, Color.WHITE, Color.RED}),
    GABON("Gabon", true, new Color[]{new Color(0, 200, 50), Color.YELLOW, Color.BLUE}),
    GERMANY("Germany", true, new Color[]{Color.BLACK, Color.RED, Color.ORANGE}),
    IRELAND("Ireland", false, new Color[]{new Color(0, 200, 50), Color.WHITE, Color.ORANGE}),
    ITALY("Italy", false, new Color[]{new Color(0, 200, 50), Color.WHITE, Color.RED}),
    LITHUANIA("Lithuania", true, new Color[]{Color.ORANGE, new Color(0, 200, 50), Color.RED}),
    LUXEMBOURG("Luxembourg", true, new Color[]{Color.RED, Color.WHITE, Color.BLUE}),
    MALI("Mali", false, new Color[]{new Color(0, 200, 50), Color.ORANGE, Color.RED}),
    NETHERLANDS("Netherlands", true, new Color[]{Color.RED, Color.WHITE, new Color(43, 187, 255)}),
    NIGERIA("Nigeria", false, new Color[]{new Color(0, 200, 50), Color.WHITE, new Color(0, 200, 50)}),
    PERU("Peru", false, new Color[]{Color.RED, Color.WHITE, Color.RED}),
    ROMANIA("Romania", false, new Color[]{Color.BLUE, Color.ORANGE, Color.RED}),
    YEMEN("Yemen", true, new Color[]{Color.RED, Color.WHITE, Color.BLACK});

    //orientation of flag
    private final boolean orientation;
    //name of country
    private final String name;
    //massive of colors in flag
    private final Color[] colors;

    /**
     * Constructor with parameters
     *
     * @param name        name of country
     * @param orientation true - horizontal; false - vertical
     * @param colors      massive colors in flag
     */
    Country(String name, boolean orientation, Color[] colors) {
        this.name = name;
        this.orientation = orientation;
        this.colors = colors;
    }

    /**
     * Method get orientation true - horizontal; false - vertical
     *
     * @return true or false
     */
    boolean getOrientation() {
        return orientation;
    }

    /**
     * Method get name of country
     *
     * @return String name
     */
    String getName() {
        return name;
    }

    /**
     * Method get color
     *
     * @param i number of color (0-2)
     * @return color in flag
     */
    Color getColor(int i) {
        return colors[i];
    }
}