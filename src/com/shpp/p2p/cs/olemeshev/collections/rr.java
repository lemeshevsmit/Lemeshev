package com.shpp.p2p.cs.olemeshev.collections;

import java.util.Queue;

public class rr {
    public static void main(String[] args) {
        LinkedList<Person> list = new LinkedList<>();

        list.add(new Person("Vasia", 22));
        list.addFromHead(new Person("Petia", 32));
        list.add(new Person("Sasha", 54));
        list.add(new Person("Ula", 41));
        list.addFromHead(new Person("Tania", 32));

        System.out.println(list.size());
        System.out.println(list.getHead());
        System.out.println(list.getTail());
        Person person = list.getElement(3);
        System.out.println(person.name + " " + person.age);

        Person personF = list.getHead();
        System.out.println(personF.name + " " + personF.age);
        Person personL = list.getTail();
        System.out.println(personL.name + " " + personL.age);
list.remove();
list.remove();
list.remove();
list.remove();
list.remove();


    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
