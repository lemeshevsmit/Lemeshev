package com.shpp.p2p.cs.olemeshev.traine;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Compar {

    public static void main(String[] args) {
        Comparator<Person> compareAge = Comparator.comparingInt(Person::getAge);
        Comparator<Person> compareName = (o1, o2) -> o1.getName().compareTo(o2.getName());
        Comparator<Person> compareAll = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int resultCompare = o1.getName().compareTo(o2.getName());
                if (resultCompare != 0) return resultCompare;
                else return o1.getAge() - o2.getAge();
            }
        };

        Set<Person> persons = new TreeSet<>(compareAll);

        persons.add(new Person("anton", 22));
        persons.add(new Person("anton", 32));
        persons.add(new Person("vasz", 22));
        persons.add(new Person("vas", 22));
        persons.add(new Person("petz", 21));
        persons.add(new Person("olz", 23));

        System.out.println(persons);

    }

}
