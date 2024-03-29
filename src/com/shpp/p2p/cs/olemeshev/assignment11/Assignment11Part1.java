package com.shpp.p2p.cs.olemeshev.assignment11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static com.shpp.p2p.cs.olemeshev.assignment11.Lexeme.LexemeType.*;


/**
 * This class parse string formula with parameters in recursive method.
 *
 * @author Aleksandr Lemeshev
 * @since 28.07.2022
 */
public class Assignment11Part1 {
    //reserve formula
    public static Formula formula;

    /**
     * It's start method. In this method I catch exception, parse formula,
     * check parameters, create new formula and calculate him.
     *
     * @param args input data(formula and parameters)
     */
    public static void main(String[] args) {
        try {
            if (args == null || args[0].equals("")
                    || Arrays.equals(args, new String[]{})) {
                throw new CalculatorException(Arrays.toString(args), 0);
            }
            LinkedList<Lexeme> listOfLexeme =
                    lexemeCreator(args[0].replaceAll(" ", ""));
            HashMap<String, LinkedList<Lexeme>> parameters = getParameters(args);
            formula = new Formula(listOfLexeme); //save formula
            Formula f = analyzeFormula(listOfLexeme, parameters);
            System.out.println(Lexeme.calculate(f));
        } catch (CalculatorException ignored) {
        }
    }

    /**
     * This method find parameters in formula and past value from HashMap
     *
     * @param lexemes input formula
     * @param params  input parameters
     * @return new formula in class Formula view
     */
    public static Formula analyzeFormula(LinkedList<Lexeme> lexemes,
                                          HashMap<String, LinkedList<Lexeme>> params) {
        LinkedList<Lexeme> formula = new LinkedList<>(lexemes);
        LinkedList<Lexeme> subFormula = new LinkedList<>();
        for (int i = 0; i < formula.size(); i++) {
            Lexeme lexeme = formula.get(i);
            if (lexeme.type == PARAMETER) {
                for (String kay : params.keySet()) {
                    if (lexeme.value.equals(kay)) {
                        formula.remove(i);
                        subFormula.add(new Lexeme(LEFT_BRACKET, "("));
                        subFormula.addAll(params.get(kay));
                        subFormula.add(new Lexeme(RIGHT_BRACKET, ")"));
                        formula.addAll(i, subFormula);
                        subFormula = new LinkedList<>();
                        break;
                    }
                }
            }
        }
        return new Formula(formula);
    }

    /**
     * this method get parameters from string array args  and create
     * LinkedList of Lexeme with parsed arguments
     *
     * @param args input parameters
     * @return HashMap with lists of lexeme
     * @throws CalculatorException calculator error
     */
    public static HashMap<String, LinkedList<Lexeme>> getParameters(String[] args)
            throws CalculatorException {
        HashMap<String, LinkedList<Lexeme>> parameters = new HashMap<>();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                String[] value = args[i].replaceAll(" ", "").split("=");
                if (value.length > 2) throw new CalculatorException("", 4);
                if (value.length < 2) continue;
                String kay = value[0];
                if (!parameters.containsKey(kay)) {
                    LinkedList<Lexeme> list = lexemeCreator(value[1]);
                    list.removeLast(); //remove END
                    parameters.put(kay, list);
                }
            }
        }
        return parameters;
    }

    /**
     * This method create LinkedList of Lexeme from input string line.
     * I use switch - case Character, if I parse mathematics operation
     * or (left/right) bracket I add new Lexeme else I call method
     * parseArgument()
     *
     * @param line input string formula
     * @return list of Lexeme
     * @throws CalculatorException calculator error
     */
    private static LinkedList<Lexeme> lexemeCreator(String line)
            throws CalculatorException {
        LinkedList<Lexeme> lexemes = new LinkedList<>();
        for (int pos = 0; pos < line.length(); pos++) {
            char symbol = line.charAt(pos);
            switch (symbol) {
                case '(' -> lexemes.add(new Lexeme(LEFT_BRACKET, symbol));
                case ')' -> lexemes.add(new Lexeme(RIGHT_BRACKET, symbol));
                case '+' -> lexemes.add(new Lexeme(PLUS, symbol));
                case '-' -> lexemes.add(new Lexeme(MINUS, symbol));
                case '*' -> lexemes.add(new Lexeme(MULTIPLY, symbol));
                case '/' -> lexemes.add(new Lexeme(DIVIDE, symbol));
                case '^' -> lexemes.add(new Lexeme(POW, symbol));
                default -> pos = parseArgument(line, pos, lexemes);
            }
        }
        lexemes.add(new Lexeme(END, ""));
        return lexemes;
    }

    /**
     * This method check other character symbol (digit or letter)
     * If symbol digit - may be it's - NUMBER, if symbol letter -
     * may be PARAMETER or FUNCTION
     *
     * @param line    input formula
     * @param pos     position of character symbol
     * @param lexemes list of lexeme
     * @return position to next character symbol
     * @throws CalculatorException calculator error
     */
    private static int parseArgument(String line, int pos,
                                     LinkedList<Lexeme> lexemes)
            throws CalculatorException {
        char symbol = line.charAt(pos);
        if (Character.isDigit(symbol)) {
            pos = getArgument(line, pos, true, lexemes);
        } else {
            if (Character.isLetter(symbol)) {
                pos = getArgument(line, pos, false, lexemes);
            } else throw new CalculatorException(String.valueOf(symbol), 9);
        }
        return pos;
    }

    /**
     * This method get argument, it's can be one symbol or many.
     * In number I add all digit, '.' and ',' symbols. In parameter I add
     * all letter and digit, then I check this parameter may be it's
     * mathematics function in method createTextLexeme()
     *
     * @param line    input formula
     * @param pos     position of character symbol
     * @param rule    true - digit; false - letter
     * @param lexemes list of lexeme
     * @return position to next character symbol
     */
    private static int getArgument(String line, int pos, boolean rule,
                                   LinkedList<Lexeme> lexemes) {
        StringBuilder sb = new StringBuilder();
        char symbol = line.charAt(pos);
        do {
            sb.append(symbol);
            pos++;
            if (pos >= line.length()) break;
            symbol = line.charAt(pos);
        } while (rule
                ? Character.isDigit(symbol) | symbol == '.' | symbol == ','
                : Character.isLetter(symbol) | Character.isDigit(symbol));
        pos--;
        if (rule) lexemes.add(new Lexeme(NUMBER, sb.toString()));
        else createTextLexeme(lexemes, sb);
        return pos;
    }

    /**
     * This method check mathematics function in parameter and add
     * lexeme if find them, else add parameter
     *
     * @param lexemes list of lexeme
     * @param sb      analyzed string parameter
     */
    private static void createTextLexeme(LinkedList<Lexeme> lexemes,
                                         StringBuilder sb) {
        String text = sb.toString();
        switch (text) {
            case "sin" -> lexemes.add(new Lexeme(SIN, text));
            case "cos" -> lexemes.add(new Lexeme(COS, text));
            case "log2" -> lexemes.add(new Lexeme(LOG2, text));
            case "log10" -> lexemes.add(new Lexeme(LOG10, text));
            case "sqrt" -> lexemes.add(new Lexeme(SQRT, text));
            case "atan" -> lexemes.add(new Lexeme(ATAN, text));
            case "tan" -> lexemes.add(new Lexeme(TAN, text));
            default -> lexemes.add(new Lexeme(PARAMETER, text));
        }
    }
}
