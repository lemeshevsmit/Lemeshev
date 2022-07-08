package com.shpp.p2p.cs.olemeshev.assignment7;


import com.shpp.cs.a.simple.SimpleProgram;

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
public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    // name text field
    private final JTextField nameField = new JTextField(NUM_COLUMNS);
    //graphics
    private final NameSurferGraph graph = new NameSurferGraph();
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
        add(new JButton("Clear"), "North");
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
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Graph":
            case "EnterPressed":
                if (nameField.getText().equals("")) getDialog().showErrorMessage("No input name.");
                else {
                    NameSurferEntry entry = dataBase.findEntry(nameField.getText().toLowerCase());
                    if (entry != null) graph.addEntry(entry);
                    else getDialog().showErrorMessage("Name not found.");
                }
                break;
            case "Clear":
                graph.clear();
                break;
        }
    }
}
