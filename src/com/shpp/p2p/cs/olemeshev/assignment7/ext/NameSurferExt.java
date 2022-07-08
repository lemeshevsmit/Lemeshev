package com.shpp.p2p.cs.olemeshev.assignment7.ext;


import com.shpp.cs.a.simple.SimpleProgram;
import com.shpp.p2p.cs.olemeshev.assignment7.NameSurferConstants;
import com.shpp.p2p.cs.olemeshev.assignment7.NameSurferDataBase;
import com.shpp.p2p.cs.olemeshev.assignment7.NameSurferEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

/**
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 *
 * @author Aleksandr Lemeshev
 * @since 26.06.2022
 */
public class NameSurferExt extends SimpleProgram implements NameSurferConstants {
    // name text field
    private final JTextField nameField = new JTextField(NUM_COLUMNS);
    //graphics
    private final NameSurferGraphExt graph = new NameSurferGraphExt();
    //database
    private NameSurferDataBase dataBase;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        try {
            dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        } catch (FileNotFoundException e) {
            getDialog().showErrorMessage("File not found.");
            //e.printStackTrace();
            return;
        }
        add(new JLabel("Name: "), "North");
        add(nameField, "North");
        add(new JButton("Graph"), "North");
        add(new JButton("Delete"), "North");
        add(new JButton("Clear"), "North");
        add(new JButton("Top10"), "North");
        add(graph);
        addActionListeners();
        nameField.setActionCommand("EnterPressed");
        nameField.addActionListener(this);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        NameSurferEntry entry = dataBase.findEntry(nameField.getText().toLowerCase());
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Graph":
            case "EnterPressed":
                if (nameField.getText().equals("")) getDialog().showErrorMessage("No input name.");
                else {
                    if (entry != null) graph.addEntry(entry);
                    else getDialog().showErrorMessage("Name not found.");
                }
                break;
            case "Clear":
                graph.clear();
                break;
            case "Top10":
                findTop10InDecade();
                break;
            case "Delete":
                if (entry != null) graph.delete(entry);
                else getDialog().showErrorMessage("Name not found.");
                break;
        }
    }

    private void findTop10InDecade() {
        graph.clear();
        int rank;
        try {
            rank = Integer.parseInt(JOptionPane.showInputDialog("Input decade [0;11]:"));
        } catch (NumberFormatException e) {
            rank = -1;
        }
        if ((rank < 0) || (rank > NDECADES - 1)) {
            getDialog().showErrorMessage("Input correct decade.");
            findTop10InDecade();
            return;
        }
        for (String kay : dataBase.getListOfName()) {
            NameSurferEntry entry = dataBase.findEntry(kay.toLowerCase());
            //number 5 because we have 5 girls and 5 boys names
            if ((entry.getRank(rank) >= 1) && (entry.getRank(rank) <= 5)) {
                graph.addEntry(entry);
            }
        }
    }
}