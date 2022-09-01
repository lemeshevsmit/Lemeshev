package com.shpp.p2p.cs.olemeshev.traine;

import java.io.*;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {

        String filename = "people.dat";

        // створимо список об'єктів, які будемо записувати
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("Tom", 30, 175, false));
        people.add(new Person("Sam", 33, 178, true));

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(people);
            System.out.println("File has been written");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        // десеріалізація у новий список
        ArrayList<Person> newPeople = new ArrayList<Person>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            newPeople = (ArrayList<Person>) ois.readObject();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        for(Person p : newPeople) {
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        }
    }
}

class Person implements Serializable{
    private String name;
    private int age;
    private transient double height;
    private transient boolean married;

    Person(String name, int age, double height, boolean married){
        this.name = name;
        this.age = age;
        this.height = height;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public boolean isMarried() {
        return married;
    }
}
