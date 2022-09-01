package com.shpp.p2p.cs.olemeshev.traine;

class Human {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printName() {
        System.out.println(getName());
    }
}

class HumanTest {
    public static void main(String[] args) {

        Human human = new Human() {
            @Override
            public void printName() {
                System.out.println("Name is " + getName());
            }
        };

        human.setName("Elon");

        human.printName(); //Name is Elon
    }
}