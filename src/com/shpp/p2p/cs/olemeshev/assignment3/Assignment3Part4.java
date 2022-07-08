package com.shpp.p2p.cs.olemeshev.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/**
 * This class build pyramid with block
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part4 extends WindowProgram {

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 350;

    /* The brick is rectangle with height and width*/
    private static final int BRICK_HEIGHT = 30;
    private static final int BRICK_WIDTH = 60;

    /* The horizontal and vertical spacing between the brick. */
    private static final double BRICK_SPACING = 0;

    /* The vertical offset between the brick in start and border of application. */
    private static final double BASE_OFFSET = 0;

    /* Count of bricks in start of pyramid */
    private static final double BRICKS_IN_BASE = 7;

    /* The brick size width and height with spacing(cement) */
    private static final double BLOCK_WIDTH = BRICK_WIDTH + BRICK_SPACING;
    private static final double BLOCK_HEIGHT = BRICK_HEIGHT + BRICK_SPACING;

    // Color of brick
    private static final Color PYRAMID_COLOR = new Color(250, 200, 100);

    /**
     * Start method. This method build pyramid with block. In first step
     * I find start coordinate X and Y. After build rectangles and change
     * coordinate. Rectangle I fill my PYRAMID_COLOR color.
     */
    public void run() {
        // find start coordinate x and y and save coordinate x
        double startPointX = (getWidth() - (BRICKS_IN_BASE * BLOCK_WIDTH - BRICK_SPACING)) / 2.0;
        double startPointY = getHeight() - BLOCK_HEIGHT - BASE_OFFSET;
        double savePointX = startPointX;

        //build rectangles and fill its in my PYRAMID_COLOR color
        GRect rect;
        for (int i = 1; i <= BRICKS_IN_BASE; i++) {
            for (int j = 0; j <= BRICKS_IN_BASE - i; j++) {
                rect = new GRect(startPointX, startPointY, BRICK_WIDTH, BRICK_HEIGHT);
                rect.setFillColor(PYRAMID_COLOR);
                rect.setFilled(true);
                add(rect);
                startPointX += BLOCK_WIDTH;
            }
            //change coordinate of start new row
            startPointX = savePointX + BLOCK_WIDTH * i / 2.0;
            startPointY -= BLOCK_HEIGHT;
        }
    }
}
