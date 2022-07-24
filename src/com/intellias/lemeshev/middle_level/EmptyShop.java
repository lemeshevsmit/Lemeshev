package com.intellias.lemeshev.middle_level;

import com.intellias.lemeshev.entity.BuyHistory;
import com.intellias.lemeshev.entity.Person;
import com.intellias.lemeshev.entity.Product;
import com.intellias.lemeshev.exception.IncorrectIdException;
import com.intellias.lemeshev.exception.IncorrectInputParameterException;
import com.intellias.lemeshev.exception.NoMoneyException;
import com.intellias.lemeshev.start_level.Shop;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aleksandr Lemeshev
 * @since 23.07.2022
 */
public class EmptyShop extends Shop {
    private static LinkedHashMap<Integer, Person> listOfPerson;
    private static LinkedHashMap<Integer, Product> listOfProduct;
    private static LinkedList<BuyHistory> listOfTransaction;
    private static final String NEW_LINE = "***************************************************";
    private static AtomicInteger productKay;
    private static AtomicInteger personKay;

    public EmptyShop() {
        listOfPerson = new LinkedHashMap<>();
        listOfProduct = new LinkedHashMap<>();
        listOfTransaction = new LinkedList<>();
        productKay = new AtomicInteger(1);
        personKay = new AtomicInteger(1);
    }

    /**
     * this method create menu logic and check exit flag of application
     *
     * @param exit flag of exit program
     * @return status of flag
     */
    @Override
    public boolean isExit(boolean exit) throws NoMoneyException,
            IncorrectIdException, IncorrectInputParameterException {
        if (!exit) System.out.print("Please, input number of menu:");
        int kay = new Scanner(System.in).nextInt();
        switch (kay) {
            case 1 -> displayMenu();
            case 2 -> displayListOfPerson(listOfPerson);
            case 3 -> displayListOfProduct(listOfProduct);
            case 4 -> buyTheProduct(listOfPerson, listOfProduct, listOfTransaction);
            case 5 -> findProductsByPerson(listOfPerson, listOfTransaction);
            case 6 -> findPersonsByProduct(listOfProduct, listOfTransaction);
            case 7 -> addNewProduct(listOfProduct);
            case 8 -> addNewPerson(listOfPerson);
            case 9 -> deleteProduct(listOfProduct, listOfTransaction);
            case 10 -> deletePerson(listOfPerson, listOfTransaction);
            case 0 -> exit = true;
        }
        return exit;
    }

    /**
     * this method display the menu of application.
     */
    @Override
    public void displayMenu() {
        System.out.println(NEW_LINE);
        System.out.println("MENU: ");
        System.out.println("1. Display menu.");
        System.out.println("2. Display list of users in shop.");
        System.out.println("3. Display list of products in shop.");
        System.out.println("4. Buy the product by person.");
        System.out.println("5. Display list of products by input person.");
        System.out.println("6. Display list of person who buy input product.");
        System.out.println("7. Add new product to shop.");
        System.out.println("8. Add new person to shop.");
        System.out.println("9. Delete product from shop.");
        System.out.println("10. Delete person from shop.");
        System.out.println("0. Exit");
        System.out.println(NEW_LINE);
    }

    /**
     * this method remove person from two list ( persons and transactions)
     *
     * @param persons            list of persons
     * @param listOfTransactions list of transactions
     * @throws IncorrectIdException incorrect input id of product
     */
    private void deletePerson(LinkedHashMap<Integer, Person> persons,
                              LinkedList<BuyHistory> listOfTransactions)
            throws IncorrectIdException {
        System.out.print("Please, input id of person:");
        int id = checkId(Collections.max(persons.keySet()));
        Person person = persons.get(id);
        if (person == null) throw new IncorrectIdException(id);
        listOfTransactions.removeIf(transaction ->
                transaction.getPerson().equals(person));
        persons.remove(id);
        System.out.println("Person correct delete from shop.");
        System.out.println(NEW_LINE);
    }

    /**
     * this method remove product from two list ( products and transactions)
     *
     * @param products           list of products
     * @param listOfTransactions list of transactions
     * @throws IncorrectIdException incorrect input id of product
     */
    private void deleteProduct(LinkedHashMap<Integer, Product> products,
                               LinkedList<BuyHistory> listOfTransactions)
            throws IncorrectIdException {
        System.out.print("Please, input id of product:");
        int id = checkId(productKay.get());
        Product product = products.get(id);///////////////////////check to null
        listOfTransactions.removeIf(transaction ->
                transaction.getProduct().equals(product));
        products.remove(id);
        System.out.println("Product correct delete from shop.");
        System.out.println(NEW_LINE);
    }

    /**
     * this method check input parameters add new person to list
     *
     * @param persons list of persons
     * @throws IncorrectInputParameterException incorrect input parameter
     */
    private void addNewPerson(LinkedHashMap<Integer, Person> persons)
            throws IncorrectInputParameterException {
        System.out.print("Please, input firstname of person:");
        String firstname = checkInputString();
        System.out.print("Please, input lastname of person:");
        String lastname = checkInputString();
        System.out.print("Please, input start money of person:");
        double money = checkInputDouble();
        int id = personKay.getAndIncrement();
        persons.put(id, new Person(id, firstname, lastname, money));
        System.out.println("Person create correct and added to list.");
        System.out.println(NEW_LINE);
    }

    /**
     * this method  check input parameters to create new product
     * and added him to list
     *
     * @param products list of product
     * @throws IncorrectInputParameterException incorrect parameter
     */
    private void addNewProduct(LinkedHashMap<Integer, Product> products)
            throws IncorrectInputParameterException {
        System.out.print("Please, input name of product:");
        String name = checkInputString();
        System.out.print("Please, input price of product:");
        double price = checkInputDouble();
        int id = productKay.getAndIncrement();
        products.put(id, new Product(id, name, price));
        System.out.println("Product create correct and added to list.");
        System.out.println(NEW_LINE);
    }

    /**
     * check input double parameter to correct number and not negative number
     *
     * @return positive double number
     * @throws IncorrectInputParameterException incorrect number
     */
    private double checkInputDouble()
            throws IncorrectInputParameterException {
        double value = 0.0;
        try {
            value = new Scanner(System.in).nextDouble();
        } catch (InputMismatchException e) {
            throw new IncorrectInputParameterException(value);
        }
        if (value <= 0.0) throw new IncorrectInputParameterException(value);
        return value;
    }

    /**
     * this method check input string value to empty
     *
     * @return input string value
     * @throws IncorrectInputParameterException empty parameter
     */
    private String checkInputString()
            throws IncorrectInputParameterException {
        String value = new Scanner(System.in).nextLine().trim();
        if (value.isEmpty()) {
            throw new IncorrectInputParameterException(value);
        } else return value;
    }
}
