package com.shpp.p2p.cs.olemeshev.assignment7;

import java.awt.*;

/**
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 *
 * @author Aleksandr Lemeshev
 * @since 26.06.2022
 */
public class NameSurferEntry implements NameSurferConstants {
    //name of entry
    private final String nameOfEntry;
    //values of ranks
    private final int[] ranks = new int[NDECADES];
    //color of graphic
    private Color color;

    /**
     * Getter of field color
     *
     * @return color of graphic
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter of field color
     *
     * @param color color to graphic
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     *
     * @param line data line from file
     */
    public NameSurferEntry(String line) {
        String[] values = line.split(" ");
        this.nameOfEntry = values[0];
        for (int i = 1; i < values.length; i++)
            this.ranks[i - 1] = Integer.parseInt(values[i]);
    }

    /**
     * Returns the name associated with this entry.
     *
     * @return name associated with this entry
     */
    public String getName() {
        return this.nameOfEntry;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     *
     * @param decade number of decade from 1900
     * @return Return the rank associated with an entry in input decade
     */
    public int getRank(int decade) {
        return this.ranks[decade];
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     *
     * @return String interpretation of NameSurferEntry
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nameOfEntry);
        sb.append(" [");
        for (int i = 0; i < this.ranks.length; i++) {
            sb.append(this.ranks[i]);
            if (i < this.ranks.length - 1) sb.append(" ");
            else sb.append("]");
        }
        return sb.toString();
    }
}

