package com.shpp.p2p.cs.olemeshev.assignment10;

import java.util.HashMap;
import java.util.LinkedList;

public class Assignment10Part1 {
    public static void main(String[] args) {
        String startFormula = args[0];
        HashMap<String, Double> parameters = getParameters(args);

        System.out.println(calculate(startFormula, parameters));

//        for (Map.Entry<String, Double> entry : parameters.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
    }

    static double calculate(String line, HashMap<String, Double> variables) {
        String[] formula = line.split(" ");
        for (int i = 0; i < formula.length; i++) {
            for (String kay : variables.keySet()) {
                if (formula[i].equals(kay)) {
                    formula[i] = String.valueOf(variables.get(kay));
                }
            }
        }
        return findValue(formula);
    }

    private static double findValue(String[] formula) {
        findOperation(formula, "*");

        return 0.0;
    }

    private static void findOperation(String[] formula, String o) {
        LinkedList<String> temp = new LinkedList<>();
        for (int i = 0; i < formula.length; i++) {
            if (formula[i].equals(o)) {
                temp.removeLast();
                double value = Double.parseDouble(formula[i - 1]) *
                        Double.parseDouble(formula[i + 1]);
                temp.add(Double.toString(value));
            }
            temp.add(formula[i]);
        }
    }

    static HashMap<String, Double> getParameters(String[] parameters) {
        HashMap<String, Double> variables = new HashMap<>();
        if (parameters.length > 1) {
            for (int i = 1; i < parameters.length; i++) {
                String[] line = parameters[i].split("=");
                if (!variables.containsKey(line[0])) {
                    try {
                        Double value = Double.valueOf(line[1]);
                        variables.put(line[0], value);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect parameters value\n");
                        e.printStackTrace();
                    }
                }
            }
        }
        return variables;
    }
}
