package com.shpp.p2p.cs.olemeshev.assignment11.recursive;

import com.shpp.p2p.cs.olemeshev.assignment11.CalculatorException;

import java.util.LinkedList;

import static com.shpp.p2p.cs.olemeshev.assignment11.recursive.Lexeme.LexemeType.*;


public class RecursiveParser {
    private static Formula formula;


    /////

    // дробоные числа;
    // минус минус;


    public static void main(String[] args) {
        try {
//        if (args == null || args[0].equals("")
//                || Arrays.equals(args, new String[]{})) {
//            throw new CalculatorException(Arrays.toString(args), 0);
//        }

            //sin(0) - sqrt(64) - (3)* (2^(log2(64)/ 3 + 5* (3 - 2)) * 2
            String line = "sin(0) - sqrt(64) - (3)* (2^(log2(64)/ 3 + 5* (3 - 2)) * 2".replaceAll(" ", "");
            //HashMap<String, String[]> parameters = getParameters(args);
            LinkedList<Lexeme> listOfLexeme = lexemeParser(line);


            //createFinalFormula(lexemeFormula);


            formula = new Formula(listOfLexeme);
            System.out.println(Lexeme.calculate(formula));
        } catch (CalculatorException ignored) {
        }
    }

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
