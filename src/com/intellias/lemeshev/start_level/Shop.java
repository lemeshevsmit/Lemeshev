package com.intellias.lemeshev.start_level;

import com.intellias.lemeshev.entity.BuyHistory;
import com.intellias.lemeshev.entity.Person;
import com.intellias.lemeshev.entity.Product;
import com.intellias.lemeshev.exception.IncorrectIdException;
import com.intellias.lemeshev.exception.IncorrectInputParameterException;
import com.intellias.lemeshev.exception.NoMoneyException;

import java.util.*;

/**
 * @author Aleksandr Lemeshev
 * @since 22.07.2022
 */
public class Shop {
    private static final HashMap<Integer, Person> listOfPerson = new HashMap<>();
    private static final HashMap<Integer, Product> listOfProduct = new HashMap<>();
    private static final LinkedList<BuyHistory> listOfTransaction = new LinkedList<>();
    private static final String NEW_LINE = "***************************************************";

    /**
     * constructor
     */
    public Shop() {
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
     * this method create menu logic and check exit flag of application
     *
     * @param exit flag of exit program
     * @return status of flag
     */
    public boolean isExit(boolean exit)
            throws NoMoneyException, IncorrectIdException, IncorrectInputParameterException {
        if (!exit) System.out.print("Please, input number of menu:");
        int kay = new Scanner(System.in).nextInt();
        switch (kay) {
            case 1 -> displayMenu();
            case 2 -> displayListOfPerson(listOfPerson);
            case 3 -> displayListOfProduct(listOfProduct);
            case 4 -> buyTheProduct(listOfPerson, listOfProduct);
            case 5 -> findProductsByPerson(listOfPerson, listOfTransaction);
            case 6 -> findPersonsByProduct(listOfProduct, listOfTransaction);
            case 0 -> exit = true;
        }
        return exit;
    }

    /**
     * this method find and display list of person who have product with input id
     *
     * @param products           list of products
     * @param listOfTransactions list of transactions
     * @throws IncorrectIdException incorrect input id number of product
     */
    public void findPersonsByProduct(HashMap<Integer, Product> products,
                                     LinkedList<BuyHistory> listOfTransactions)
            throws IncorrectIdException {
        System.out.print("Please, input id of product:");
        Product product = products.get(checkId(products.size()));
        Set<String> list = new LinkedHashSet<>();
        for (BuyHistory transaction : listOfTransactions) {
            if (transaction.getProduct().equals(product)) {
                Person person = transaction.getPerson();
                String kay = person.getFirstName() + " " + person.getLastName();
                list.add(kay);
            }
        }
        if (list.size() > 0) {
            System.out.println("Person list who have product with id = "
                    + product.getId() + " - " + product.getName() + " :");
            for (String s : list) {
                System.out.println(s);
            }
        }
        System.out.println(NEW_LINE);
    }

    /**
     * this method create a list of products that a person with input id has bought
     *
     * @param persons            list of persons
     * @param listOfTransactions list of transactions
     * @throws IncorrectIdException incorrect input id number of person
     */
    public void findProductsByPerson(HashMap<Integer, Person> persons,
                                     LinkedList<BuyHistory> listOfTransactions)
            throws IncorrectIdException {
        System.out.print("Please, input id of person:");
        Person person = persons.get(checkId(persons.size()));
        HashMap<String, Integer> list = new HashMap<>();
        for (BuyHistory transaction : listOfTransactions) {
            if (transaction.getPerson().equals(person)) {
                Product product = transaction.getProduct();
                String kay = product.getName();
                if (!list.containsKey(kay)) {
                    list.put(kay, 1);
                } else list.replace(kay, list.get(kay) + 1);
            }
        }
        displayProductOfPerson(person, list);
    }

    /**
     * this method displays a list of products that a person with input id has bought
     *
     * @param person person
     * @param list   list with product and count
     */
    private void displayProductOfPerson(Person person, HashMap<String, Integer> list) {
        if (list.size() > 0) {
            System.out.println("Product list of person id = " + person.getId() + " - "
                    + person.getFirstName() + " " + person.getLastName() + " :");
            for (String kay : list.keySet()) {
                System.out.println("Product: " + kay + " count: " + list.get(kay));
            }
        }
        System.out.println(NEW_LINE);
    }


    /**
     * this method check person or product id
     * and throw exception if value not correct or not found
     * else return input value
     *
     * @return input id number
     * @throws IncorrectIdException incorrect id number;
     */
    public int checkId(int size) throws IncorrectIdException {
        int id;
        try {
            id = new Scanner(System.in).nextInt();
        } catch (Exception e) {
            throw new IncorrectIdException();
        }
        if ((id > 1) & (id < size)) {
            return id;
        }
        throw new IncorrectIdException(id);
    }

    /**
     * this method display the menu of application.
     */
    public void displayMenu() {
        System.out.println(NEW_LINE);
        System.out.println("MENU: ");
        System.out.println("1. Display menu.");
        System.out.println("2. Display list of users in shop.");
        System.out.println("3. Display list of products in shop.");
        System.out.println("4. Buy the product by person.");
        System.out.println("5. Display list of products by input person.");
        System.out.println("6. Display list of person who buy input product.");
        System.out.println("0. Exit");
        System.out.println(NEW_LINE);
    }

    /**
     * this method check input id numbers of person and product to correct
     * and do transaction if all parameters is good
     *
     * @param persons  list of persons
     * @param products list of products
     * @throws NoMoneyException     if person don't have money
     * @throws IncorrectIdException incorrect id number;
     */
    public void buyTheProduct(HashMap<Integer, Person> persons,
                              HashMap<Integer, Product> products)
            throws NoMoneyException, IncorrectIdException {
        System.out.print("Please, input person ID:");
        int personId = checkId(persons.size());
        System.out.print("Please, input product ID: ");
        int productId = checkId(products.size());
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
    private void checkTransaction(int personId, int productId)
            throws NoMoneyException {
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
    private void doTransaction(int personId, int productId) {
        Person buyer = listOfPerson.get(personId);
        Product product = listOfProduct.get(productId);
        buyer.setMoney(buyer.getMoney() - product.getPrice());
        listOfTransaction.add(new BuyHistory(listOfTransaction.size() + 1, buyer, product));
        System.out.println("Person: " + buyer.getFirstName() + " " + buyer.getLastName()
                + " with id = " + personId + " successful buy product: "
                + product.getName() + " with id = " + productId);
        System.out.println(NEW_LINE);
    }

    /**
     * this method display  all person list with all parameters
     *
     * @param persons list of persons
     */
    public void displayListOfPerson(HashMap<Integer, Person> persons) {
        System.out.println("Person list in shop:");
        for (Map.Entry<Integer, Person> person : persons.entrySet()) {
            System.out.println("ID: " + person.getKey()
                    + "; firstname: " + person.getValue().getFirstName()
                    + "; lastname: " + person.getValue().getLastName()
                    + "; money: " + person.getValue().getMoney());
        }
        System.out.println(NEW_LINE);
    }

    /**
     * this method display  all product list with all parameters
     *
     * @param products list of products
     */
    public void displayListOfProduct(HashMap<Integer, Product> products) {
        System.out.println("Product list in shop:");
        for (Map.Entry<Integer, Product> product : products.entrySet()) {
            System.out.println("ID: " + product.getKey()
                    + "; name: " + product.getValue().getName()
                    + "; price: " + product.getValue().getPrice());
        }
        System.out.println(NEW_LINE);
    }
}
