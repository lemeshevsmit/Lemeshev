package com.shpp.p2p.cs.olemeshev.assignment11;

import static com.shpp.p2p.cs.olemeshev.assignment11.Lexeme.LexemeType.*;

/**
 * This class have algorithm of recursive parse of
 * mathematics expression. Its based on lexeme and analyze him
 * <p>
 * ------------------------------------------------------------------
 * PARSER GRAMMATICAL RULES:
 * ------------------------------------------------------------------
 * calculate : lowPriority* END ;
 * lowPriority: midPriority [ '+' | '-' midPriority ]* ;
 * midPriority : highPriority [ '*' | '/' highPriority ]* ;
 * highPriority : maxPriority ['^' maxPriority ]* ;
 * maxPriority : NUMBER | FUNCTIONS{} | [ '(' lowPriority ')' ] ;
 * ------------------------------------------------------------------
 *
 * @author Aleksandr Lemeshev
 * @since 26.07.2022
 */

public class Lexeme {
    LexemeType type;
    String value;

    /**
     * Constructor with parameters.
     *
     * @param type  Class of lexeme type
     * @param value String interpretation
     */
    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Constructor with parameters.
     *
     * @param type  Class of lexeme type
     * @param value Character symbol interpretation
     */
    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    /**
     * This class describes type of lexeme
     */
    public enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET,
        SIN, COS, TAN, ATAN, LOG2, LOG10, SQRT,
        PLUS, MINUS, MULTIPLY, DIVIDE, POW,
        NUMBER, PARAMETER, END
    }

    /**
     * This method is start of analyze of formula.
     * I check END of formula if it false I start analyze
     * else return result.
     *
     * @param formula class with list of lexeme
     * @return result of calculate formula
     * @throws CalculatorException error in formula
     */
    public static double calculate(Formula formula)
            throws CalculatorException {
        Lexeme lexeme = formula.nextElement();
        if (lexeme.type == END) {
            return 0.0;
        } else {
            formula.backPosition();
            return lowPriority(formula);
        }
    }

    /**
     * This method check low priority operation [+;-] In switch
     * I select kay == "lexeme type" and do operations. If operations
     * END or RIGHT_BRACKET I came back to previously method.
     *
     * @param formula class with list of lexeme
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    public static double lowPriority(Formula formula)
            throws CalculatorException {
        double value = midPriority(formula);
        while (true) {
            Lexeme lexeme = formula.nextElement();
            switch (lexeme.type) {
                case PLUS -> value += midPriority(formula);
                case MINUS -> value -= midPriority(formula);
                case END, RIGHT_BRACKET -> {
                    formula.backPosition();
                    return value;
                }
                default -> throw new CalculatorException(lexeme.type +
                        " " + formula.nextElement().type, 11);
            }
        }
    }

    /**
     * This method int next level priority of math operation.
     * In this method I check MULTIPLY, DIVIDE and do
     * this mathematics operation. If I select previously lexeme type
     * I return back and return value.
     *
     * @param formula class with list of lexeme
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    public static double midPriority(Formula formula)
            throws CalculatorException {
        double value = fighPriority(formula);
        while (true) {
            Lexeme lexeme = formula.nextElement();
            switch (lexeme.type) {
                case MULTIPLY -> value *= fighPriority(formula);
                case DIVIDE -> {
                    double res = fighPriority(formula);
                    if (res == 0.0) throw new CalculatorException("", 5);
                    else value /= res;
                }
                case END, RIGHT_BRACKET, PLUS, MINUS -> {
                    formula.backPosition();
                    return value;
                }
                default -> throw new CalculatorException(lexeme.type +
                        " " + formula.nextElement().type, 11);
            }
        }
    }

    /**
     * This method int height level priority of math operation. In this method
     * I check POW and do this mathematics operation. If I select previously lexeme
     * type I return back and return value.
     *
     * @param formula class with list of lexeme
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    private static double fighPriority(Formula formula) throws CalculatorException {
        double value = maxPriority(formula);
        while (true) {
            Lexeme lexeme = formula.nextElement();
            switch (lexeme.type) {
                case POW -> value = Math.pow(value, maxPriority(formula));
                case END, RIGHT_BRACKET, PLUS, MINUS, MULTIPLY, DIVIDE -> {
                    formula.backPosition();
                    return value;
                }
                default -> throw new CalculatorException(lexeme.type +
                        " " + formula.nextElement().type, 11);
            }
        }
    }

    /**
     * This method analyze last lexeme types: NUMBER and
     * mathematics functions I named this pool FUNCTIONS. If I select
     * number I return value, if i select function I call method
     * calculateFunction() else I check LEFT_BRACKET maybe we have FORMULA in
     * next lexeme. If I select UNKNOWN type I throw calculator exception.
     *
     * @param formula class with list of lexeme
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    public static double maxPriority(Formula formula) throws CalculatorException {
        Lexeme lexeme = formula.nextElement();
        switch (lexeme.type) {
            case MINUS:
                return -maxPriority(formula);
            case NUMBER:
                try {
                    return Double.parseDouble(lexeme.value);
                } catch (NumberFormatException e) {
                    throw new CalculatorException(lexeme.value, 6);
                }
            case SIN, COS, TAN, ATAN, LOG2, LOG10, SQRT: {
                double value = maxPriority(formula);
                return calculateFunction(lexeme.type, value);
            }
            case LEFT_BRACKET: {
                return checkSubFormula(formula);
            }
            default:
                throw new CalculatorException(lexeme.type +
                        " " + formula.nextElement().type, 11);
        }
    }

    /**
     * This method check sub formula. I call secondPriority(formula);
     * After analyze if we get RIGHT_BRACKET it's good, else
     * we can get END == exit of method and return value to previously method.
     *
     * @param formula class with list of lexeme
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    private static double checkSubFormula(Formula formula) throws CalculatorException {
        Lexeme lexeme;
        double value = lowPriority(formula);
        lexeme = formula.nextElement();
        if (lexeme.type != RIGHT_BRACKET) {
            if (lexeme.type == END) {
                formula.backPosition();
                return value;
            } else throw new CalculatorException(lexeme.type + " " + formula.nextElement().type, 11);
        }

        return value;
    }

    /**
     * This method calculate value of mathematics functions. In this method
     * I check all mathematics exception who can be and all parameters of exist.
     *
     * @param type  lexeme type
     * @param value value of argument
     * @return result of calculate lexeme
     * @throws CalculatorException error in formula
     */
    private static double calculateFunction(LexemeType type, double value)
            throws CalculatorException {
        double result = 0.0;
        if (value < 0.0 & (type == LOG2 || type == LOG10 || type == SQRT))
            throw new CalculatorException(type + "] incorrect number=" + value, 10);
        switch (type) {
            case SIN -> {
                if (value % Math.PI == 0.0) result = 0.0;
                else result = Math.sin(value);
            }
            case COS -> {
                if ((value % (Math.PI / 2.0) == 0.0) & (value % Math.PI != 0.0))
                    result = 0.0;
                else result = Math.cos(value);
            }
            case TAN -> {
                if (value % (Math.PI / 2.0) == 0.0)
                    throw new CalculatorException(Double.toString(value), 3);
                result = Math.tan(value);
            }
            case ATAN -> result = Math.atan(value);
            case LOG2 -> result = Math.log(value) / Math.log(2);
            case LOG10 -> result = Math.log10(value);
            case SQRT -> result = Math.sqrt(value);
        }
        return result;
    }
}

