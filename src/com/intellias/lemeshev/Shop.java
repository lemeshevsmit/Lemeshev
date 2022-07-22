package com.intellias.lemeshev;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {
    private static HashMap<Integer, Person> listOfPerson = new HashMap<>();
    private static HashMap<Integer, Product> listOfProduct = new HashMap<>();


    public static void main(String[] args) {
        createStartParameters();
        boolean exit = false;
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            if (!exit) System.out.print("Please, input number of menu:");
            int kay = scanner.nextInt();
            switch (kay) {
                case 1 -> displayMenu();
                case 2 -> displayListOfPerson();
                case 3 -> displayListOfProduct();
                case 4 -> {
                    try {
                        buyTheProduct();
                    } catch (NoMoneyException e) {
                        exit = true;
                    }
                }
                case 0 -> exit = true;
            }
        }
        scanner.close();
    }

    /**
     * this method display the menu of application.
     */
    private static void displayMenu() {
        System.out.println("MENU: ");
        System.out.println("1. Display menu.");
        System.out.println("2. Display list of users.");
        System.out.println("3. Display list of products.");
        System.out.println("4. Buy the product by person.");
        System.out.println("0. Exit");
    }

    /**
     * this method check input id numbers of person and product to correct
     * and do transaction if all parameters is good
     *
     * @throws NoMoneyException
     */
    private static void buyTheProduct() throws NoMoneyException {
        Scanner scanner = new Scanner(System.in);
        int personId = 0, productId = 0;
        try {
            System.out.print("Please, input person ID: ");
            personId = scanner.nextInt();
            System.out.print("Please, input product ID: ");
            productId = scanner.nextInt();
        } catch (Exception e) {
            System.err.print("Please, input correct id number!");
            System.exit(0);
        }
        checkTransaction(personId, productId);
        scanner.close();
    }

    /**
     * this method check input id numbers in lists of person and product?
     * than return message if id not found or person don't have money
     * if all parameters is good do transaction
     *
     * @param personId  person id number
     * @param productId product id number
     * @throws NoMoneyException no money to do transaction
     */
    private static void checkTransaction(int personId, int productId)
            throws NoMoneyException {
        if (!listOfPerson.containsKey(personId)) {
            System.err.print("Person not found! Check person id.");
            System.exit(0);
        }
        if (!listOfProduct.containsKey(productId)) {
            System.err.print("Product not found! Check product id.");
            System.exit(0);
        }
        double money = listOfPerson.get(personId).getMoney();
        double price = listOfProduct.get(productId).getPrice();
        if (money < price) {
            throw new NoMoneyException(money, price) {
            };
        } else {
            doTransaction(personId,productId);
        }
    }

    private static void doTransaction(int personId, int productId) {

    }

    /**
     * this method display  all person list with all parameters
     */
    private static void displayListOfPerson() {
        System.out.println("Person list:");
        for (Map.Entry<Integer, Person> person : listOfPerson.entrySet()) {
            System.out.println("ID: " + person.getKey()
                    + " firstname: " + person.getValue().getFirstName()
                    + " lastname: " + person.getValue().getLastName()
                    + " money: " + person.getValue().getMoney());
        }
    }

    /**
     * this method display  all product list with all parameters
     */
    private static void displayListOfProduct() {
        System.out.println("Product list:");
        for (Map.Entry<Integer, Product> product : listOfProduct.entrySet()) {
            System.out.println("ID: " + product.getKey()
                    + " name: " + product.getValue().getName()
                    + " price: " + product.getValue().getPrice());
        }
    }


    /**
     * its start product and person lists in shop
     */
    private static void createStartParameters() {
        Product[] products = new Product[]{
                new Product(1, "table", 999.99),
                new Product(2, "chair", 200.0),
                new Product(3, "cupboard", 2500.0)};
        Person[] persons = new Person[]{
                new Person(1, "Jody", "Quye", 5000.0),
                new Person(2, "Rolfe", "McKerrow", 9258.5),
                new Person(3, "Lorri", "Valentino", 1200.0)};

        //add product to list
        for (Product p : products) {
            listOfProduct.put(p.getId(), p);
        }

        //add person to list
        for (Person user : persons) {
            listOfPerson.put(user.getId(), user);
        }
    }

    /**
     * Inner class for my exception
     */
    private static class NoMoneyException extends Throwable {

        /**
         * constructor with parameters of inner class
         *
         * @param money total money of person
         * @param price price of product
         */
        public NoMoneyException(double money, double price) {
            System.err.println("This person don't have money.");
            System.err.println("You have: " + money + " and you need: " + price);
        }
    }
}
