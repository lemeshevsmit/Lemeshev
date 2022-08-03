package com.shpp.p2p.cs.olemeshev.assignment11;

import java.util.*;

/**
 * This class calculate formula with parameters
 *
 * @author Alexandr Lemeshev
 * @since 25.07.2022
 */
public class Assignment11Part1 {
    //array with mathematics operators
    private static final String[] OPERATORS = new String[]
            {"^", "/", "*", "-", "+"};
    private static final String[] FUNCTIONS = new String[]
            {"sin", "cos", "tan", "atan", "log2", "sqrt", "log10"};

    /**
     * This is main method. In this method I check start parameters and
     * load other methods for calculate result of start formula
     *
     * @param args start parameters (formula and parameters)
     */
    public static void main(String[] args) throws CalculatorException {
        if (args == null || args[0].equals("")
                || Arrays.equals(args, new String[]{})) {
            throw new CalculatorException(Arrays.toString(args), 0);
        }
        HashMap<String, String[]> parameters = getParameters(args);
        String resultFormula = checkFunction(args[0], parameters);
        System.out.println(calculate(resultFormula, parameters));
    }

    /**
     * This method create final view of formula, find all
     * mathematics operators and calculate result value.
     *
     * @param line      Start formula
     * @param variables start parameters
     * @return result of calculate formula
     * @throws CalculatorException incorrect formula
     */
    static double calculate(String line, HashMap<String, String[]> variables)
            throws CalculatorException {
        String[] formula = getFormula(line, variables);
        for (String operator : OPERATORS) {
            formula = findOperation(formula, operator);
            Objects.requireNonNull(formula); //throw exception if null (exit)
        }
        if (formula.length > 1) throw new CalculatorException(formula[1], 7);
        else return Double.parseDouble(formula[0]);
    }

    /**
     * This method create final view of formula without parameters
     *
     * @param line      start formula
     * @param variables list with parameters
     * @return final formula
     */
    private static String[] getFormula(String line,
                                       HashMap<String, String[]> variables) {
        String[] formula = createFormula(line);
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
     */
    private static String[] createFormula(String line) {
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
            } else i = checkExponentView(sb, temp, i);
        }
        if (!sb.isEmpty()) formula.add(sb.toString());
        return formula.toArray(new String[0]);
    }


    /**
     * this method check exponential view in formula if we have symbol "E"
     *
     * @param sb   new formula
     * @param temp array of symbols in formula
     * @param i    current position
     * @return new position and rewrite sb
     */
    private static int checkExponentView(StringBuilder sb, String[] temp, int i) {
        if (temp[i].equals("E")
                && (i + 3 < temp.length)
                && temp[i + 1].equals("-")
                && Character.isDigit(temp[i + 2].charAt(0))
                && Character.isDigit(temp[i + 3].charAt(0))) {
            sb.append(temp[i]).append(temp[i + 1]).append(temp[i + 2]).append(temp[i + 3]);
            i = i + 3;
        } else sb.append(temp[i]);
        return i;
    }

    /**
     * This method find mathematics operators and calculate value with him
     * after return formula without found operator
     *
     * @param formula formula with operators
     * @param o       string value of operator
     * @return new formula without @param {o} operator
     */
    private static String[] findOperation(String[] formula, String o) throws CalculatorException {
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
                    throw new CalculatorException(e.getMessage(), 6);
                }
            } else temp.add(formula[i]);
        }
        return temp.toArray(new String[0]);
    }


    /**
     * This method return list with input parameters. If we
     * don't input parameters return empty list.
     *
     * @param parameters parameters from console
     * @return list with all input parameters
     * @throws CalculatorException incorrect parameters
     */
    static HashMap<String, String[]> getParameters(String[] parameters)
            throws CalculatorException {
        HashMap<String, String[]> list = new HashMap<>();
        if (parameters.length > 1)
            for (int i = 1; i < parameters.length; i++) {
                String[] value = parameters[i].split("=");
                if (value.length > 2) throw new CalculatorException("", 4);
                String kay = value[0].replaceAll(" ", "");
                if (!list.containsKey(kay)) {
                    value[1] = checkFunction(value[1], new HashMap<>());
                    list.put(kay, createFormula(value[1]));
                }
            }
        return list;
    }

    /**
     * this method check formula to contains "(",")" and functions
     * if we have this elements calculate him
     *
     * @param s          input formula
     * @param parameters list with parameters
     * @return new formula without functions and "(", ")"
     * @throws CalculatorException incorrect value
     */
    private static String checkFunction(String s, HashMap<String, String[]> parameters)
            throws CalculatorException {
        while (s.contains("(")) {
            int start = s.lastIndexOf('(');
            int end = s.substring(start).indexOf(')');
            if (end == -1) throw new CalculatorException(")", 8);
            double result;
            for (int i = 0; i < FUNCTIONS.length; i++) {
                if (start - FUNCTIONS[i].length() < 0) {
                    result = calculate(s.substring(start + 1, start + end), parameters);
                    s = s.replace(s.substring(start, start + end + 1), String.valueOf(result));
                    break;
                }
                String subFormula = s.substring(start - FUNCTIONS[i].length(), start + end + 1);
                if (subFormula.contains(FUNCTIONS[i])) {
                    int index = start - FUNCTIONS[i].length() - 1;
                    if ((i == 2) & (index >= 0) && (s.substring(index, index + 1).contains("a"))) {
                        continue;
                    } else {
                        result = calculate(subFormula.substring(subFormula.indexOf('(') + 1,
                                subFormula.indexOf(')')), parameters);
                        result = function(result, FUNCTIONS[i]);
                        s = s.replace(subFormula, String.valueOf(result));
                        break;
                    }
                }
                if (i == FUNCTIONS.length - 1) {
                    result = calculate(s.substring(start + 1, start + end), parameters);
                    s = s.replace(s.substring(start, start + end + 1), String.valueOf(result));
                }
            }
        }
        return s;
    }

    /**
     * This method do arithmetical operation with two double variable
     *
     * @param value1 first value
     * @param value2 second value
     * @param o      String association of math operation
     * @return result of arithmetical operation
     * @throws CalculatorException Divide by zero exception
     */
    static double operator(double value1, double value2, String o)
            throws CalculatorException {
        double result = 0.0;
        switch (o) {
            case "+" -> result = value1 + value2;
            case "-" -> result = value1 - value2;
            case "*" -> result = value1 * value2;
            case "/" -> {
                if (value2 == 0.0) throw new CalculatorException("", 5);
                else result = value1 / value2;
            }
            case "^" -> result = Math.pow(value1, value2);
            default -> {
                return result;
            }
        }
        return result;
    }

    /**
     * This method do arithmetical operation with variable
     *
     * @param value input value
     * @param f     input function
     * @return result of arithmetical operation
     * @throws CalculatorException incorrect value in function
     */
    static double function(double value, String f)
            throws CalculatorException {
        switch (f) {
            case "sin" -> {
                if (value % Math.PI == 0.0) return 0.0;
                else return Math.sin(value);
            }
            case "cos" -> {
                if ((value % (Math.PI / 2.0) == 0.0)
                        & (value % Math.PI != 0.0)) return 0.0;
                else return Math.cos(value);
            }
            case "atan" -> {
                return Math.atan(value);
            }
            case "tan" -> {
                if (value % (Math.PI / 2.0) == 0.0)
                    throw new CalculatorException(Double.toString(value), 3);
                else return Math.tan(value);
            }
            case "log10" -> {
                if (value < 0.0)
                    throw new CalculatorException(Double.toString(value), 1);
                else return Math.log10(value);
            }
            case "log2" -> {
                if (value < 0.0)
                    throw new CalculatorException(Double.toString(value), 1);
                else return Math.log(value) / Math.log(2);
            }
            case "sqrt" -> {
                if (value < 0.0)
                    throw new CalculatorException(Double.toString(value), 2);
                else return Math.sqrt(value);
            }
        }
        return 0.0;
    }
}