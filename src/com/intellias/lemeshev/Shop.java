package com.intellias.lemeshev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Aleksandr Lemeshev
 * @since 22.07.2022
 */
public class Shop {
    private static final HashMap<Integer, Person> listOfPerson = new HashMap<>();
    private static final HashMap<Integer, Product> listOfProduct = new HashMap<>();
    private static final ArrayList<BuyHistory> listOfTransaction = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        createStartParameters();
        boolean exit = false;
        displayMenu();
        while (!exit) {
            exit = isExit(exit);
        }
        scanner.close();
    }

    /**
     * this method create menu logic and check exit flag of application
     *
     * @param exit flag of exit program
     * @return status of flag
     */
    private static boolean isExit(boolean exit) {
        if (!exit) System.out.print("Please, input number of menu:");
        int kay = scanner.nextInt();
        try {
            switch (kay) {
                case 1 -> displayMenu();
                case 2 -> displayListOfPerson();
                case 3 -> displayListOfProduct();
                case 4 -> buyTheProduct();
                case 5 -> displayProductOfPerson();
                case 0 -> exit = true;
            }
        } catch (NoMoneyException e) {
            exit = true;
        }
        return exit;
    }

    private static void displayProductOfPerson() {
        System.out.print("Please, input id of person:");
        int personId = scanner.nextInt();   ////////////////////////////////////
        Person person = listOfPerson.get(personId);
        HashMap<String, Integer> list = new HashMap<>();
        for (BuyHistory transaction : listOfTransaction) {
            if (transaction.getPerson().equals(person)) {
                Product product = transaction.getProduct();
                String kay = product.getName();
                if (!list.containsKey(kay)) {
                    list.put(kay, 1);
                } else {
                    list.replace(kay, list.get(kay) + 1);
//                    list.replace(product.getName(),
//                            list.get(product.getName()).intValue(),
//                            list.get(product.getName()).intValue()++);
                }
            }
        }
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
     * @throws NoMoneyException if person don't have money
     */
    private static void buyTheProduct() throws NoMoneyException {
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
            throw new NoMoneyException(money, price);
        } else {
            doTransaction(personId, productId);
        }
    }

    /**
     * this method add to transaction list new transaction, display message about
     * successful buy and withdraw money from person
     *
     * @param personId  person who buy
     * @param productId product to sale
     */
    private static void doTransaction(int personId, int productId) {
        Person buyer = listOfPerson.get(personId);
        Product product = listOfProduct.get(productId);
        buyer.setMoney(buyer.getMoney() - product.getPrice());
        listOfTransaction.add(new BuyHistory(listOfTransaction.size() + 1, buyer, product));
        System.out.println("Person: " + buyer.getFirstName() + " " + buyer.getLastName()
                + " with id = " + personId + " successful buy product: "
                + product.getName() + " with id = " + productId);
    }

    /**
     * this method display  all person list with all parameters
     */
    private static void displayListOfPerson() {
        System.out.println("Person list:");
        for (Map.Entry<Integer, Person> person : listOfPerson.entrySet()) {
            System.out.println("ID: " + person.getKey()
                    + "; firstname: " + person.getValue().getFirstName()
                    + "; lastname: " + person.getValue().getLastName()
                    + "; money: " + person.getValue().getMoney());
        }
    }

    /**
     * this method display  all product list with all parameters
     */
    private static void displayListOfProduct() {
        System.out.println("Product list:");
        for (Map.Entry<Integer, Product> product : listOfProduct.entrySet()) {
            System.out.println("ID: " + product.getKey()
                    + "; name: " + product.getValue().getName()
                    + "; price: " + product.getValue().getPrice());
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
     * Inner class for my exception if person don't have money
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
            System.err.println("You have: " + money + ", but you need: " + price);
        }
    }
}
