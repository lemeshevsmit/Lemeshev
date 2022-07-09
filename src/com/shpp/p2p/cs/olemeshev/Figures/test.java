package com.shpp.p2p.cs.olemeshev.Figures;

import acm.graphics.GOval;
import acm.graphics.GPolygon;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class test extends WindowProgram {

    public void run() {
        Circle c = new Circle(50, 50, 50, Color.BLUE);
        add(c);


        GPolygon p = new GPolygon();
        p.addVertex(300,300);
        p.addVertex(300,250);
        p.addVertex(200,300);
        add(p);

    }
}
