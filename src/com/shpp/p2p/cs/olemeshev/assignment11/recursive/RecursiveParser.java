package com.shpp.p2p.cs.olemeshev.assignment11.recursive;

import static com.shpp.p2p.cs.olemeshev.assignment11.recursive.Lexeme.LexemeType.*;
import static com.shpp.p2p.cs.olemeshev.assignment11.recursive.Lexeme.formula;

import com.shpp.p2p.cs.olemeshev.assignment11.CalculatorException;

import java.util.ArrayList;
import java.util.List;


public class RecursiveParser {
    private static List<Lexeme> finalFormula = new ArrayList<>();


    /////

    // дробоные числа;
    // минус минус;


    public static void main(String[] args) throws CalculatorException {
//        if (args == null || args[0].equals("")
//                || Arrays.equals(args, new String[]{})) {
//            throw new CalculatorException(Arrays.toString(args), 0);
//        }

        //sin(0) - sqrt(64) - (3)* (2^(log2(64)/ 3 + 5* (3 - 2)) * 2
        String formula = "122 - 34 - 3* (55 + 5* (3 - 2)) * 2".replaceAll(" ", "");
        //HashMap<String, String[]> parameters = getParameters(args);
        finalFormula = lexemeParser(formula);
        //createFinalFormula(lexemeFormula);
        Lexeme.Formula f = new Lexeme.Formula(finalFormula);


        System.out.println(formula(f));
    }

//    private static void createFinalFormula(List<Lexeme> lexemeFormula) {
//        for (Lexeme lexeme : lexemeFormula) {
//            if (lexeme.type == PARAMETER) {
//                switch (lexeme.value) {
//                    case "sin" -> lexeme.type = SIN;
//                    case "cos" -> lexeme.type = COS;
//                    case "log2" -> lexeme.type = LOG2;
//                    case "log10" -> lexeme.type = LOG10;
//                    case "sqrt" -> lexeme.type = SQRT;
//                    case "atan" -> lexeme.type = ATAN;
//                    case "tan" -> lexeme.type = TAN;
//                }
//            }
//            finalFormula.add(lexeme);
//        }
//    }

    private static List<Lexeme> lexemeParser(String line)
            throws CalculatorException {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
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
                default -> pos = parseArgument(line, lexemes, pos);
            }
        }
        lexemes.add(new Lexeme(END, ""));
        return lexemes;
    }

    private static int parseArgument(String line,
                                     ArrayList<Lexeme> lexemes,
                                     int pos)
            throws CalculatorException {
        char c = line.charAt(pos);
        if (Character.isDigit(c)) {
            pos = getNumber(line, lexemes, pos);
        } else {
            if (Character.isLetter(c)) {
                pos = getText(line, lexemes, pos);
            } else throw new CalculatorException("", c);
        }
        return pos;
    }

    private static int getNumber(String line,
                                 ArrayList<Lexeme> lexemes,
                                 int pos) {
        StringBuilder sb = new StringBuilder();
        char c = line.charAt(pos);
        do {
            sb.append(c);
            pos++;
            if (pos >= line.length()) break;
            c = line.charAt(pos);
        } while (Character.isDigit(c));
        pos--;
        lexemes.add(new Lexeme(NUMBER, sb.toString()));
        return pos;
    }

    private static int getText(String line,
                               ArrayList<Lexeme> lexemes,
                               int pos) {
        StringBuilder sb = new StringBuilder();
        char c = line.charAt(pos);
        do {
            sb.append(c);
            pos++;
            if (pos >= line.length()) break;
            c = line.charAt(pos);
        } while (Character.isLetter(c) | Character.isDigit(c));
        pos--;
        lexemes.add(checkFunction(sb));
        return pos;
    }

    private static Lexeme checkFunction(StringBuilder sb) {
        String text = sb.toString();
        switch (text) {
            case "sin" -> {
                return new Lexeme(SIN, text);
            }
            case "cos" -> {
                return new Lexeme(COS, text);
            }
            case "log2" -> {
                return new Lexeme(LOG2, text);
            }
            case "log10" -> {
                return new Lexeme(LOG10, text);
            }
            case "sqrt" -> {
                return new Lexeme(SQRT, text);
            }
            case "atan" -> {
                return new Lexeme(ATAN, text);
            }
            case "tan" -> {
                return new Lexeme(TAN, text);
            }
            default -> {
                return new Lexeme(PARAMETER, text);
            }
        }
    }
}
