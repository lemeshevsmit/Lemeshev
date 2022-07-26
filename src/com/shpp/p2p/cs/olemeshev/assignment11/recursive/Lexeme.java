package com.shpp.p2p.cs.olemeshev.assignment11.recursive;

import java.util.List;

/**
 * this class display lexeme
 *
 * @author Aleksandr Lemeshev
 * @since 26.07.2022
 */
public class Lexeme {
    LexemeType type;
    String value;

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET,
        SIN, COS, TAN, ATAN, LOG2, LOG10, SQRT,
        PLUS, MINUS, MULTIPLY, DIVIDE, POW,
        NUMBER, PARAMETER, END
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }


    /*------------------------------------------------------------------
     * PARSER GRAMMATICAL RULES
     *------------------------------------------------------------------*/

//    formula : lastPriority* END ;   *-many limes; secondPriority it's operation[+;-] END - end of formula
//
//    secondPriority: firstPriority ( ( '+' | '-' ) firstPriority )* ;   *-many limes; firstPriority it's operation[*;/]
//
//    firstPriority : value ( ( '*' | '/' ) value )* ;  *-many limes; value it's result of function or POW operation
//
//    value : NUMBER | FUNCTIONS | POW '(' formula ')' ;

    public static double formula(Formula lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.END) {
            return 0.0;
        } else {
            lexemes.back();
            return secondPriority(lexemes);
        }
    }

    public static double secondPriority(Formula lexemes) {
        double value = firstPriority(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case PLUS -> value += firstPriority(lexemes);
                case MINUS -> value -= firstPriority(lexemes);
                case END, RIGHT_BRACKET -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
            }
        }
    }

    public static double firstPriority(Formula lexemes) {
        double value = value(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case MULTIPLY -> value *= value(lexemes);
                case DIVIDE -> value /= value(lexemes);
                case END, RIGHT_BRACKET, PLUS, MINUS -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
            }
        }
    }

    public static double value(Formula lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case POW: {
                double value = Double.parseDouble(lexeme.value);
                lexeme = lexemes.next();
                //if(lexeme.type=)
            }
            case SIN,COS,TAN,ATAN,LOG2,LOG10,SQRT:{
                double value = secondPriority(lexemes);
            }
            case LEFT_BRACKET:
                double value = secondPriority(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != Lexeme.LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
        }
    }

    /**
     * inner class of Lexeme where I save formula
     * and last position
     */
    public static class Formula {
        public int pos;
        public List<Lexeme> lexemes;

        public Formula(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }
}

