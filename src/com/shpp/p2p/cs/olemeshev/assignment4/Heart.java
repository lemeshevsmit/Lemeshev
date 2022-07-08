package com.shpp.p2p.cs.olemeshev.assignment4;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GPolygon;

import java.awt.*;

/**
 * This class build approximately heart. I use 2 circles and 1 polygon
 *
 * @author Alexander Lemeshev
 */
public class Heart extends GCompound {

    /**
     * Constructor with parameters
     *
     * @param width  width of heart
     * @param height height of heart
     */
    public Heart(double width, double height) {
        GOval o1 = new GOval(0, 0, width / 2, height / 2);
        o1.setFilled(true);
        o1.setFillColor(Color.RED);
        o1.setColor(Color.RED);
        add(o1);

        GOval o2 = new GOval(width / 2, 0, width / 2, height / 2);
        o2.setFilled(true);
        o2.setFillColor(Color.RED);
        o2.setColor(Color.RED);
        add(o2);

        GPolygon p = new GPolygon();
        p.addVertex(0, height * 0.3);
        p.addVertex(width, height * 0.3);
        p.addVertex(width / 2, height);
        p.setFilled(true);
        p.setFillColor(Color.RED);
        p.setColor(Color.RED);
        add(p);
    }
}
