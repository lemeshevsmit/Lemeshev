package com.intellias.lemeshev.exception;

/**
 * class for my exception if person don't have money
 */
public class IncorrectIdException extends Throwable {

    /**
     * constructor without
     */
    public IncorrectIdException() {
        System.err.println("Please, input correct id number!");
    }

    /**
     * constructor with parameters of inner class
     *
     * @param id incorrect id
     */
    public IncorrectIdException(int id) {
        System.err.println("Id = " + id +
                " not found. Please, input correct number!");
    }
}
