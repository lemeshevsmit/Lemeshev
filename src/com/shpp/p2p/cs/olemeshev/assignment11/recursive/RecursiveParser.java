package com.shpp.p2p.cs.olemeshev.assignment11.recursive;

import com.shpp.p2p.cs.olemeshev.assignment11.CalculatorException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static com.shpp.p2p.cs.olemeshev.assignment11.recursive.Lexeme.LexemeType.*;


public class RecursiveParser {
    public static Formula formula;

    public static void main(String[] args) {
        try {
            if (args == null || args[0].equals("")
                    || Arrays.equals(args, new String[]{})) {
                throw new CalculatorException(Arrays.toString(args), 0);
            }
            LinkedList<Lexeme> listOfLexeme =
                    lexemeParser(args[0].replaceAll(" ", ""));
            HashMap<String, LinkedList<Lexeme>> parameters = getParameters(args);
            formula = new Formula(listOfLexeme); //save formula
            Formula f = analyzeFormula(listOfLexeme, parameters);
            System.out.println(Lexeme.calculate(f));
        } catch (CalculatorException ignored) {
        }
    }

    private static Formula analyzeFormula(LinkedList<Lexeme> lexemes,
                                          HashMap<String, LinkedList<Lexeme>> params) {
        LinkedList<Lexeme> formula = new LinkedList<>(lexemes);
        LinkedList<Lexeme> subFormula = new LinkedList<>();
        for (int i = 0; i < formula.size(); i++) {
            Lexeme l = formula.get(i);
            if (l.type == PARAMETER) {
                for (String kay : params.keySet()) {
                    if (l.value.equals(kay)) {
                        formula.remove(i);
                        subFormula.add(new Lexeme(LEFT_BRACKET, "("));
                        subFormula.addAll(params.get(kay));
                        subFormula.add(new Lexeme(RIGHT_BRACKET, ")"));
                        formula.addAll(i, subFormula);
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
    private static HashMap<String, LinkedList<Lexeme>> getParameters(String[] args)
            throws CalculatorException {
        HashMap<String, LinkedList<Lexeme>> parameters = new HashMap<>();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                String[] value = args[i].replaceAll(" ", "").split("=");
                if (value.length > 2) throw new CalculatorException("", 4);
                String kay = value[0];
                if (!parameters.containsKey(kay)) {
                    LinkedList<Lexeme> list = lexemeParser(value[1]);
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
    private static LinkedList<Lexeme> lexemeParser(String line)
            throws CalculatorException {
        LinkedList<Lexeme> lexemes = new LinkedList<>();
        for (int pos = 0; pos < line.length(); pos++) {
            char c = line.charAt(pos);
            switch (c) {
                case '(' -> lexemes.add(new Lexeme(LEFT_BRACKET, c));
                case ')' -> lexemes.add(new Lexeme(RIGHT_BRACKET, c));
                case '+' -> lexemes.add(new Lexeme(PLUS, c));
                case '-' -> lexemes.add(new Lexeme(MINUS, c));
                case '*' -> lexemes.add(new Lexeme(MULTIPLY, c));
                case '/' -> lexemes.add(new Lexeme(DIVIDE, c));
                case '^' -> lexemes.add(new Lexeme(POW, c));
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
        char c = line.charAt(pos);
        if (Character.isDigit(c)) {
            pos = getArgument(line, pos, true, lexemes);
        } else {
            if (Character.isLetter(c)) {
                pos = getArgument(line, pos, false, lexemes);
            } else throw new CalculatorException(String.valueOf(c), 9);
        }
        return pos;
    }

    /**
     * This method get argument, it's can be one symbol or many.
     * In number I add all digit, '.' and ',' symbols. In parameter I add
     * all letter and digit, then I check this parameter may be it's
     * mathematics function in method checkFunction()
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
        char c = line.charAt(pos);
        do {
            sb.append(c);
            pos++;
            if (pos >= line.length()) break;
            c = line.charAt(pos);
        } while (rule
                ? Character.isDigit(c) | c == '.' | c == ','
                : Character.isLetter(c) | Character.isDigit(c));
        pos--;
        if (rule) lexemes.add(new Lexeme(NUMBER, sb.toString()));
        else checkFunction(lexemes, sb);
        return pos;
    }

    /**
     * This method check mathematics function in parameter and add
     * lexeme if find them, else add parameter
     *
     * @param lexemes list of lexeme
     * @param sb      analyzed string parameter
     */
    private static void checkFunction(LinkedList<Lexeme> lexemes,
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
