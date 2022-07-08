package com.shpp.p2p.cs.olemeshev.assignment7.ext;


import acm.graphics.*;
import com.shpp.p2p.cs.olemeshev.assignment7.NameSurferConstants;
import com.shpp.p2p.cs.olemeshev.assignment7.NameSurferEntry;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;

/**
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 *
 * @author Aleksandr Lemeshev
 * @since 26.06.2022
 */
public class NameSurferGraphExt extends GCanvas
        implements NameSurferConstants, ComponentListener {
    //added graphics list
    private final HashMap<String, NameSurferEntry> listOfAddedNames = new HashMap<>();
    //massive of colors
    private final Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
    //actual color position
    private int actualIndex = 0;


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraphExt() {
        addComponentListener(this);
        update();
    }

    /**
     *
     */
    public void delete(NameSurferEntry entry) {
        if (listOfAddedNames.containsKey(entry.getName().toLowerCase())) {
            listOfAddedNames.remove(entry.getName().toLowerCase());
            update();
        }
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        listOfAddedNames.clear();
        update();
    }


    /**
     * This method draw graphics and text with rank with
     * actual color and value in this position
     *
     * @param entry class NameSurferEntry entry
     */
    private void drawGraphic(NameSurferEntry entry) {
        if (actualIndex == 4) actualIndex = 0;
        if (entry.getColor() == null) entry.setColor(colors[actualIndex]);
        drawLines(entry);
        drawText(entry);
        drawFigure(entry, actualIndex);
        actualIndex++;
    }

    /**
     * This method draw figures from class Figure on line of graphics
     *
     * @param entry class NameSurferEntry entry
     * @param index index of figure
     */
    private void drawFigure(NameSurferEntry entry, int index) {
        int widthOfBlock = getWidth() / NDECADES;
        int countFigureInInterval = 5;
        int sizeOfFigure = 6;
        double heightOfGraph = (getHeight() - 2.0 * GRAPH_MARGIN_SIZE) / MAX_RANK;

        for (int i = 0; i < NDECADES - 1; i++) {
            int startY = (entry.getRank(i) != 0)
                    ? GRAPH_MARGIN_SIZE + (int) (entry.getRank(i) * heightOfGraph)
                    : getHeight() - GRAPH_MARGIN_SIZE;
            int endY = (entry.getRank(i + 1) != 0)
                    ? GRAPH_MARGIN_SIZE + (int) (entry.getRank(i + 1) * heightOfGraph)
                    : getHeight() - GRAPH_MARGIN_SIZE;
            for (int j = 0; j < countFigureInInterval; j++) {
                int coordinateX = i * widthOfBlock + j * widthOfBlock / countFigureInInterval;
                int coordinateY = startY + j * (endY - startY) / countFigureInInterval;
                Figure figure = new Figure(coordinateX, coordinateY, sizeOfFigure, entry.getColor());
                add(figure.drawFigure(index));
            }
            Figure figure = new Figure((i + 1) * widthOfBlock, endY, sizeOfFigure, entry.getColor());
            add(figure.drawFigure(index));
        }
    }

    /**
     * This method draw text in coordinate of rank position
     *
     * @param entry class NameSurferEntry entry
     */
    private void drawText(NameSurferEntry entry) {
        int fontSize = 12;
        int scaleFont = 3;
        int widthOfBlock = getWidth() / NDECADES;
        double heightOfGraph = (getHeight() - 2.0 * GRAPH_MARGIN_SIZE) / MAX_RANK;
        int offset = GRAPH_MARGIN_SIZE / 4;
        for (int i = 0; i < NDECADES; i++) {
            int coordinateY = (entry.getRank(i) != 0)
                    ? GRAPH_MARGIN_SIZE + (int) (entry.getRank(i) * heightOfGraph) - offset
                    : getHeight() - GRAPH_MARGIN_SIZE - offset;
            GLabel text = new GLabel(entry.getName() + " "
                    + ((entry.getRank(i) != 0) ? entry.getRank(i) : "*"));
            text.setColor(entry.getColor());
            text.setFont("Source Sans Pro-" + Math.min(fontSize, getWidth() / (scaleFont * NDECADES)));
            if ((i < NDECADES - 1)
                    && (entry.getRank(i) > entry.getRank(i + 1))
                    && (entry.getRank(i + 1) != 0)) {
                coordinateY += GRAPH_MARGIN_SIZE ;
            }
            add(text, i * widthOfBlock, coordinateY);

        }
    }

    /**
     * This method draw line from one point (rank) to next
     *
     * @param entry class NameSurferEntry entry
     */
    private void drawLines(NameSurferEntry entry) {
        int widthOfBlock = getWidth() / NDECADES;
        double heightOfGraph = (getHeight() - 2.0 * GRAPH_MARGIN_SIZE) / MAX_RANK;

        for (int i = 0; i < NDECADES - 1; i++) {
            int startY = (entry.getRank(i) != 0)
                    ? GRAPH_MARGIN_SIZE + (int) (entry.getRank(i) * heightOfGraph)
                    : getHeight() - GRAPH_MARGIN_SIZE;
            int endY = (entry.getRank(i + 1) != 0)
                    ? GRAPH_MARGIN_SIZE + (int) (entry.getRank(i + 1) * heightOfGraph)
                    : getHeight() - GRAPH_MARGIN_SIZE;
            GLine line = new GLine(i * widthOfBlock, startY, (i + 1) * widthOfBlock, endY);
            line.setColor(entry.getColor());
            add(line);
        }
    }


    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     *
     * @param entry class NameSurferEntry entry
     */
    public void addEntry(NameSurferEntry entry) {
        if (!listOfAddedNames.containsKey(entry.getName().toLowerCase())) {
            listOfAddedNames.put(entry.getName().toLowerCase(), entry);
            drawGraphic(entry);
            System.out.println(entry);
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        int decade = 10;
        int fontSize = 24;
        int scaleFont = 2;
        int widthOfBlock = getWidth() / NDECADES;
        GLine line = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(line);

        for (int i = 0; i < NDECADES; i++) {
            line = new GLine(i * widthOfBlock, 0, i * widthOfBlock, getHeight());
            add(line);
            GLabel text = new GLabel(String.valueOf(START_DECADE + i * decade));
            text.setFont("Source Sans Pro-" + Math.min(fontSize, getWidth() / (scaleFont * NDECADES)));
            add(text, i * widthOfBlock, getHeight());
        }

        line = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(line);
        updateGraphic();
    }

    /**
     * Redraw all graphics from list listOfAddedNames
     */
    public void updateGraphic() {
        for (Map.Entry<String, NameSurferEntry> entry : listOfAddedNames.entrySet()) {
            drawGraphic(entry.getValue());
        }
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}