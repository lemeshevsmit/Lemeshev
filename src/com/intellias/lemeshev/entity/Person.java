package com.intellias.lemeshev.entity;

/**
 * @author Aleksandr Lemeshev
 * @since 22.07.2022
 */
public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private double money;

    /**
     * Constructor with parameters
     *
     * @param id        person identification number
     * @param firstName firstname of person
     * @param lastName  lastname of person
     * @param money     start money of person
     */
    public Person(int id, String firstName, String lastName, double money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
