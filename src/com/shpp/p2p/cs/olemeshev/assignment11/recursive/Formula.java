package com.shpp.p2p.cs.olemeshev.assignment11.recursive;

import java.util.LinkedList;

/**
 * This class contain final formula in LinkedList of Lexeme view
 *
 * @author Aleksandr Lemeshev
 * @since 27.07.2022
 */
public class Formula {
    //actual position in formula
    public int position;
    //list of lexeme
    public LinkedList<Lexeme> listLexemes;

    /**
     * Constructor with parameters
     *
     * @param lexemes list of lexeme
     */
    public Formula(LinkedList<Lexeme> lexemes) {
        this.listLexemes = lexemes;
    }

    /**
     * this method get next lexeme from list
     *
     * @return lexeme on next position
     */
    public Lexeme nextElement() {
        return listLexemes.get(position++);
    }

    /**
     * this method decrement position
     */
    public void backPosition() {
        position--;
    }
}
