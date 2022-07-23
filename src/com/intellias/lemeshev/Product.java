package com.intellias.lemeshev;


/**
 * @author Aleksandr Lemeshev
 * @since 22.07.2022
 */
public class Product {
    private final int id;
    private String name;
    private double price;

    /**
     * Constrictor with parameters
     *
     * @param id    product identification number
     * @param name  name of product
     * @param price price of product
     */
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
