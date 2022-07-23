package com.intellias.lemeshev.exception;

/**
 * Inner class for my exception if person don't have money
 */
public class NoMoneyException extends Throwable {

    /**
     * constructor with parameters of inner class
     *
     * @param money total money of person
     * @param price price of product
     */
    public NoMoneyException(double money, double price) {
        System.err.println("This person don't have money.");
        System.err.println("You have: " + money + ", but you need: " + price);
    }
}