package com.shpp.p2p.cs.olemeshev.assignment3;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

import java.awt.*;

public class Helicopter extends GCompound {

    //color of helicopter
    private static final Color HELICOPTER_COLOR = new Color(51, 104, 15);

    /**
     * This method build helicopter. I build tail in left of body,
     * body - it's oval, and two propellers - it's polygon.
     *
     * @param width width of helicopter
     * @param height height of helicopter
     */
    public Helicopter(double width, double height) {
        //build tail of helicopter
        double tailSizeX = width / 1.5;
        double tailSizeY = height / 7.0;
        GRect tail = new GRect(tailSizeX, tailSizeY);
        tail.setFilled(true);
        tail.setFillColor(HELICOPTER_COLOR);
        add(tail, -width / 2.0, (height - tailSizeY) / 2.0);

        // build body of helicopter
        GOval body = new GOval(width, height);
        body.setFilled(true);
        body.setFillColor(HELICOPTER_COLOR);
        add(body);

        // build first propeller of helicopter
        double propellerSizeX = width * 1.5;
        double propellerSizeY = height / 10.0;
        GPolygon propeller1 = new GPolygon();
        propeller1.addVertex(0, 0);
        propeller1.addVertex(propellerSizeX,0 );
        propeller1.addVertex(propellerSizeX, propellerSizeY);
        propeller1.addVertex(0, propellerSizeY);
        propeller1.rotate(45);
        propeller1.setFilled(true);
        propeller1.setFillColor(Color.BLACK);
        propeller1.move(0,(width+height)/2.0);
        add(propeller1);

        // build second propeller of helicopter
        GPolygon propeller2 = new GPolygon();
        propeller2.addVertex(0, 0);
        propeller2.addVertex(propellerSizeX,0 );
        propeller2.addVertex(propellerSizeX, propellerSizeY);
        propeller2.addVertex(0, propellerSizeY);
        propeller2.rotate(135);
        propeller2.setFilled(true);
        propeller2.setFillColor(Color.BLACK);
        propeller2.move(width,(width+height)/2.0);
        add(propeller2);

    }

}
