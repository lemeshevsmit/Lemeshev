package com.shpp.p2p.cs.olemeshev.assignment10;

import java.util.*;

/**
 * This class calculate formula with parameters
 *
 * @author Alexandr Lemeshev
 * @since 19.07.2022
 */
public class Assignment10Part1 {
    //array with mathematics operators
    private static final String[] OPERATORS = new String[]{"^", "/", "*", "-", "+"};

    /**
     * This is main method. In this method I check start parameters and
     * load other methods for calculate result of start formula
     *
     * @param args start parameters (formula and parameters)
     */
    public static void main(String[] args) {
        if (args == null || Arrays.equals(args, new String[]{}) || args[0].equals("")) {
            System.out.println("Formula is null or empty: " + Arrays.toString(args));
            return;
        }
        String startFormula = args[0];
        HashMap<String, String[]> parameters = getParameters(args);
        try {
            System.out.println(calculate(startFormula, parameters));
        } catch (NullPointerException ignored) {
        } catch (ArithmeticException e) {
            System.out.println("Incorrect operators: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect formula: " + e.getMessage());
        }
    }

    /**
     * This method create final view of formula, find all
     * mathematics operators and calculate result value.
     *
     * @param line      Start formula
     * @param variables start parameters
     * @return result of calculate formula
     * @throws NullPointerException           incorrect formula
     * @throws ArithmeticException            incorrect operators
     * @throws ArrayIndexOutOfBoundsException incorrect formula
     */
    static double calculate(String line, HashMap<String, String[]> variables)
            throws NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException {
        String[] formula = getFormula(line, variables);
        for (String operator : OPERATORS) {
            formula = findOperation(formula, operator);
            Objects.requireNonNull(formula); //throw exception if null (exit)
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
     * @throws ArrayIndexOutOfBoundsException incorrect formula
     */
    private static String[] getFormula(String line, HashMap<String, String[]> variables)
            throws ArrayIndexOutOfBoundsException {
        String[] formula = parseFormula(line);
        if (variables.size() > 0) {
            LinkedList<String> newFormula = new LinkedList<>();
            for (String s : formula) {
                newFormula.add(s);
                for (String kay : variables.keySet()) {
                    if (s.equals(kay)) {
                        newFormula.removeLast();
                        newFormula.addAll(Arrays.asList(variables.get(kay)));
                    }
                }
            }
            formula = newFormula.toArray(new String[0]);
        }
        return formula;
    }

    /**
     * This method return formula in arrays view. I split string line by
     * symbol and check him to value and operator and create array.
     *
     * @param line Start formula
     * @return formula in array view
     * @throws ArrayIndexOutOfBoundsException incorrect formula
     */
    private static String[] parseFormula(String line) throws ArrayIndexOutOfBoundsException {
        ArrayList<String> formula = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] temp = line.replaceAll(" ", "").split("");
        for (int i = 0; i < temp.length; i++) {
            if (Arrays.asList(OPERATORS).contains(temp[i])) {
                if (sb.isEmpty()) {
                    if (temp[i].equals("-")) {
                        if (Arrays.asList(OPERATORS).contains(temp[i + 1])) {
                            formula.add(temp[i]);
                        } else sb.append(temp[i]);
                    } else formula.add(temp[i]);
                } else {
                    formula.add(sb.toString());
                    formula.add(temp[i]);
                    sb = new StringBuilder();
                }
            } else sb.append(temp[i]);
        }
        if (!sb.isEmpty()) formula.add(sb.toString());
        return formula.toArray(new String[0]);
    }

    /**
     * This method find mathematics operators and calculate value with him
     * after return formula without found operator
     *
     * @param formula formula with operators
     * @param o       string value of operator
     * @return new formula without @param {o} operator
     */
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
                } catch (ArithmeticException e) {
                    System.out.println("Divide by zero in formula.");
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
     * @throws ArithmeticException Divide by zero exception
     */
    static double operator(double value1, double value2, String o) throws ArithmeticException {
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
    static HashMap<String, String[]> getParameters(String[] parameters) {
        HashMap<String, String[]> list = new HashMap<>();
        if (parameters.length > 1)
            for (int i = 1; i < parameters.length; i++) {
                String[] value = parameters[i].split("=");
                try {
                    if (value.length > 2) throw new ArrayIndexOutOfBoundsException();
                    if (!list.containsKey(value[0].trim())) {
                        list.put(value[0].trim(), parseFormula(value[1]));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Incorrect parameter: " +
                            parameters[i]);
                }
            }
        return list;
    }
}