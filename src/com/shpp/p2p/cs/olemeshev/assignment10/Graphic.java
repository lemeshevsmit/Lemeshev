package com.shpp.p2p.cs.olemeshev.assignment10;

import acm.graphics.*;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.ArrayList;


public class Graphic extends GCanvas implements ComponentListener {
    public int scale;
    private int centerX;
    private int centerY;
    private final int OFFSET = 2;
    private int gridX;
    private int gridY;

    public Graphic(int value) {
        scale = value;
        update();
        addComponentListener(this);
    }

    public void update() {
        removeAll();
        //init parameters
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        gridX = getWidth() / scale;
        gridY = getHeight() / scale;

        //draw x,y line and label
        drawLines();

        //draw grid
        drawGrid(gridX, true);
        drawGrid(gridY, false);
    }


    private void drawLines() {
        GLine x = new GLine(0, centerY, getWidth(), centerY);
        add(x);
        GLine y = new GLine(centerX, 0, centerX, getHeight());
        add(y);
        GLabel textX = new GLabel("x");
        add(textX, getWidth() - textX.getWidth() - OFFSET,
                centerY - OFFSET + textX.getHeight());
        GLabel textY = new GLabel("y");
        add(textY, centerX - textX.getWidth() - OFFSET,
                -OFFSET + textY.getHeight());
    }


    private void drawGrid(int grid, boolean flag) {
        int kayX, kayY, absY = 1;
        for (int i = -(grid / 2); i <= grid / 2; i++) {
            if (flag) {
                kayX = i * scale;
                kayY = 0;
            } else {
                kayY = i * scale;
                kayX = 0;
                absY = -1;
            }
            GRect point = new GRect(centerX - OFFSET / 2 + kayX,
                    centerY - OFFSET / 2 + kayY, OFFSET, OFFSET);
            add(point);
            GLabel text = new GLabel(Integer.toString(i * absY));
            add(text, centerX + kayX - text.getWidth() - OFFSET,
                    centerY + kayY - OFFSET + text.getHeight());
        }
    }

    public void drawGraphics(String text) {
        GPolygon polygon = new GPolygon();
        // ???????????? ?????????? ?????? ???????????? ?? ????????
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("result.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //???????????? ??????????
        BufferedOutputStream buffer = new BufferedOutputStream(file);
        // ???????????? ?????????????? ?????? ????????????
        PrintStream console = new PrintStream(buffer, true);
        // ?????????? ?????????????? ???? ?????????? ?????? ???????????? ?? ????????.
        System.setOut(console);
        for (int i = -getWidth(); i < getWidth(); i++) {
            Assignment10Part1.main(new String[]{text, "x=" + i});
        }
        FileOpener fileOpener = new FileOpener();
        ArrayList<String> result = fileOpener.openFile("result.txt");
        for (int i = 0; i < result.size(); i++) {

            polygon.addVertex((-getWidth() + i + centerX),
                    (-1 * Double.parseDouble(result.get(i)) + centerY));
        }
        polygon.setColor(Color.RED);
        add(polygon);
    }

    /**
     * Invoked when the component's size changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {
        update();
    }

    /**
     * Invoked when the component's position changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentMoved(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made invisible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }
}