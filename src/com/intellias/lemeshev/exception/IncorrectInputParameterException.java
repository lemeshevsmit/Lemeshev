package com.intellias.lemeshev.exception;

/**
 * class for my exception if input incorrect parameter
 */
public class IncorrectInputParameterException extends Throwable {

    /**
     * constructor with parameters
     *
     * @param parameter incorrect parameter
     */
    public IncorrectInputParameterException(String parameter) {
        System.err.println("You input incorrect parameter = \" " + parameter + " \"");
    }

    /**
     * constructor with parameters
     *
     * @param parameter incorrect parameter
     */
    public IncorrectInputParameterException(double parameter) {
        System.err.println("You input incorrect parameter = \" " + parameter + " \"");
    }

}
