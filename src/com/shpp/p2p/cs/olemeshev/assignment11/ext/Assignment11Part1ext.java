package com.shpp.p2p.cs.olemeshev.assignment11.ext;


import com.shpp.cs.a.simple.SimpleProgram;
import com.shpp.p2p.cs.olemeshev.assignment11.CalculatorException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

public class Assignment11Part1ext extends SimpleProgram {

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
                try {
                    graph.drawGraphics(inputText.getText());
                } catch (CalculatorException ignored) {}
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
                try {
                    graph.drawGraphics(inputText.getText());
                } catch (CalculatorException ignored) {
                }
            }
        }
    }
}
