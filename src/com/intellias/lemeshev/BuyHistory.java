package com.intellias.lemeshev;

/**
 * @author Aleksandr Lemeshev
 * @since 23.07.2022
 */
public class BuyHistory {
    private int id;
    private Person person;
    private Product product;

    /**
     * Constrictor with parameters
     * @param id id transaction
     * @param person Person who buy
     * @param product Product to sale
     */
    public BuyHistory(int id, Person person, Product product) {
        this.id = id;
        this.person = person;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Product getProduct() {
        return product;
    }
}
