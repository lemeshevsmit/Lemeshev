package com.shpp.p2p.cs.olemeshev.assignment10;

import java.util.*;

public class Assignment10Part1 {
    private static final String[] OPERATORS = new String[]{"^", "/", "*", "-", "+"};

    public static void main(String[] args) {
        if (args == null || Arrays.equals(args, new String[]{}) || args[0].equals("")) {
            System.out.println("Formula is null or empty: " + Arrays.toString(args));
            return;
        }
        String startFormula = args[0];
        HashMap<String, String> parameters = getParameters(args);
        try {
            System.out.println(calculate(startFormula, parameters));
        } catch (NullPointerException e) {
            return;
        } catch (ArithmeticException e) {
            System.out.println("Incorrect operators: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect formula: " + e.getMessage());
        }
    }

    static double calculate(String line, HashMap<String, String> variables) throws NullPointerException {
        String[] formula = getFormula(line, variables);
        for (String operator : OPERATORS) {
            formula = findOperation(formula, operator);
            if (formula == null) throw new NullPointerException();
        }
        if (formula.length > 1) throw new ArithmeticException(formula[1]);
        else return Double.parseDouble(formula[0]);
    }

    /**
     * This method create final view of formula without parameters
     *
     * @param line      start formula
     * @param variables list with parameters
     * @return final formula
     */
    private static String[] getFormula(String line, HashMap<String, String> variables) {
        String[] formula = line.trim().split(" ");
        if (variables.size() > 0) {
            LinkedList<String> newFormula = new LinkedList<>();
            for (String s : formula) {
                newFormula.add(s);
                for (String kay : variables.keySet()) {
                    if (s.equals(kay)) {
                        newFormula.removeLast();
                        String[] values = variables.get(kay).split(" ");
                        newFormula.addAll(Arrays.asList(values));
                    }
                }
            }
            formula = newFormula.toArray(new String[0]);
        }
        return formula;
    }

    private static String[] findOperation(String[] formula, String o) {
        LinkedList<String> temp = new LinkedList<>();
        for (int i = 0; i < formula.length; i++) {
            if (formula[i].equals(o)) {
                try {
                    double value1 = Double.parseDouble(temp.getLast());
                    double value2 = Double.parseDouble(formula[i + 1]);
                    temp.removeLast();
                    temp.add(Double.toString(operator(value1, value2, o)));
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("Unknown value in formula: " + e.getMessage());
                    return null;
                }
            } else temp.add(formula[i]);
        }
        return temp.toArray(new String[0]);
    }

    /**
     * This method do arithmetical operation with two double variable
     *
     * @param value1 first value
     * @param value2 second value
     * @param o      String association of math operation
     * @return result of arithmetical operation
     */
    static double operator(double value1, double value2, String o) {
        switch (o) {
            case "+" -> {
                return value1 + value2;
            }
            case "-" -> {
                return value1 - value2;
            }
            case "*" -> {
                return value1 * value2;
            }
            case "/" -> {
                return value1 / value2;
            }
            case "^" -> {
                return Math.pow(value1, value2);
            }
        }
        return 0.0;
    }


    /**
     * This method return list with input parameters. If we
     * don't input parameters return empty list.
     *
     * @param parameters parameters from console
     * @return list with all input parameters
     */
    static HashMap<String, String> getParameters(String[] parameters) {
        HashMap<String, String> list = new HashMap<>();
        if (parameters.length > 1) {
            for (int i = 1; i < parameters.length; i++) {
                String[] value = parameters[i].split(" = ");
                if (!list.containsKey(value[0])) {
                    try {
                        list.put(value[0], value[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Incorrect parameter: " +
                                Arrays.toString(value));
                    }
                }
            }
        }
        return list;
    }
}