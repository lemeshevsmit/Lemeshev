package com.shpp.p2p.cs.olemeshev.assignment10;


import acm.graphics.GPoint;
import com.shpp.cs.a.simple.SimpleProgram;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

public class Assignment10Part2 extends SimpleProgram {

    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 600;

    private static final int NUM_COLUMNS = 30;
    private final JTextField inputText = new JTextField(NUM_COLUMNS);

    private JSlider scale;

    private Graphic graph;


    public void init() {
        scale = new JSlider(20, 200, 200);
        add(new JLabel("Scale: "), NORTH);
        add(scale, NORTH);
        JButton button = new JButton("Graph");
        add(button, NORTH);
        add(inputText, NORTH);
        graph = new Graphic(scale.getValue());
        add(graph);
        addActionListeners();
        scale.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                graph.scale = scale.getValue();
                graph.update();
                graph.drawGraphics(inputText.getText());
            }
        });
        inputText.setActionCommand("EnterPressed");
        inputText.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Graph".equals(actionCommand) || "EnterPressed".equals(actionCommand)) {
            graph.update();
            if (inputText.getText().equals("")) getDialog().showErrorMessage("No input graphic.");
            else {
                graph.drawGraphics(inputText.getText());
            }
        }
    }
}
