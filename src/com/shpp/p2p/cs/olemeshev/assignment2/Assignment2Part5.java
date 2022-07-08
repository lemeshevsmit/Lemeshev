package com.shpp.p2p.cs.olemeshev.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/**
 * This class build optical illusion with rectangle
 *
 * @author Alexandr Lemeshev
 * @since 22.05.2022
 */
public class Assignment2Part5 extends WindowProgram {

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 350;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;



    /**
     * Start method. This method build optical illusion. In first step
     * I find middle and start coordinate X and Y. After build rectangle
     * and change coordinate.
     */
    public void run() {
        // find middle coordinate x and y
        double midPointX = getWidth() / 2.0;
        double midPointY = getHeight() / 2.0;

        /* Find start coordinate x and y. I dublicate start coordinate x and remumber it 
		 * because I use cycle 'for'. In this cycle I build all rectangles in row and go 
		 * to next rows. If you see I use minus one, it's need because count of indent 
		 * between rectangle less then count of columns to one.
		 */
        double currentX = midPointX - NUM_COLS / 2.0 * BOX_SIZE -
                (NUM_COLS - 1) / 2.0 * BOX_SPACING;
        double currentY = midPointY - NUM_ROWS / 2.0 * BOX_SIZE -
                (NUM_ROWS - 1) / 2.0 * BOX_SPACING;
		double startPointX = currentX;

        //build rectangles and fill its in black color
        GRect rect;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                rect = new GRect(currentX, currentY, BOX_SIZE, BOX_SIZE);
                rect.setFillColor(Color.BLACK);
                rect.setFilled(true);
                add(rect);
                currentX += (BOX_SIZE + BOX_SPACING);
            }

            //change coordinate of start new row
            currentX = startPointX;
            currentY += (BOX_SIZE + BOX_SPACING);
        }
    }

}
