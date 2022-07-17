package com.shpp.p2p.cs.olemeshev.Figures;

import acm.graphics.GOval;

import java.awt.*;

public class Circle extends GOval {
    public Circle(int centerX, int centerY, int radius) {
        super(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    public Circle(int centerX, int centerY, int radius, Color color) {
        this(centerX, centerY, radius);
        super.setFilled(true);
        super.setColor(color);
        super.setFillColor(color);
    }
}
