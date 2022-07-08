package com.shpp.p2p.cs.olemeshev.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/**
 * This class build caterpillar
 *
 * @author Aleksandr Lemeshev
 * @since 23.05.2022
 */
public class Assignment2Part6 extends WindowProgram {


    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 400;

    // Color of caterpillar
    private static final Color CATERPILLAR_GREEN = new Color(50, 100, 0);

    // Diameter of caterpillar
    private static final double DIAMETER = 100.0;

    // Count segments of caterpillar
    private static final int SEGMENT_COUNT = 7;

    // Start position of caterpillar
    private static final double START_X = 0.0;
    private static final double START_Y = 0.0;

    /**
     * This method build caterpillar. In this method second segment
     * must be upper then first. I create key and if key = 0 then I
     * build upper, else build normal. I generate offset of coordinate
     * x and y because I think with this offset it's look better.
     */
    public void run() {
        GOval caterpillarSegment;

        //calculate offset of coordinate x and y in next segments
        double deltaX = DIAMETER / 1.5;
        double deltaY = DIAMETER / 5.0;

        int key = 0;
        for (int i = 0; i < SEGMENT_COUNT; i++) {
            key = (i % 2 == 0) ? 1 : 0;
            caterpillarSegment = new GOval(START_X + i * deltaX,
                    START_Y + key * deltaY, DIAMETER, DIAMETER);
            caterpillarSegment.setFillColor(CATERPILLAR_GREEN);
            caterpillarSegment.setColor(Color.RED);
            caterpillarSegment.setFilled(true);
            add(caterpillarSegment);
        }
    }
}
