package com.shpp.p2p.cs.olemeshev.assignment3;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * this class build tank
 */
public class Tank extends GCompound {

    // color of tank
    private static final Color TANK_COLOR = new Color(51, 104, 15);

    /**
     * This method build body, barrel and tower of tank.
     * Barrel be on middle of height of tank and see right.
     * Tower be om middle of body.
     *
     * @param width  width of tank
     * @param height height of tank
     */
    public Tank(double width, double height) {
        // build body of tank
        GRect body = new GRect(width, height);
        body.setFilled(true);
        body.setFillColor(TANK_COLOR);
        add(body);

        //build barrel of tank
        double barrelSizeX = width / 1.5;
        double barrelSizeY = height / 10.0;
        GRect barrel = new GRect(barrelSizeX, barrelSizeY);
        barrel.setFilled(true);
        barrel.setFillColor(TANK_COLOR);
        add(barrel, width / 2.0, (height - barrelSizeY) / 2.0);

        // build tower of tank
        double towerDiameter = height / 1.5;
        GOval tower = new GOval(towerDiameter, towerDiameter);
        tower.setFilled(true);
        tower.setFillColor(TANK_COLOR);
        add(tower, (width - towerDiameter) / 2.0, (height - towerDiameter) / 2.0);
    }

}
