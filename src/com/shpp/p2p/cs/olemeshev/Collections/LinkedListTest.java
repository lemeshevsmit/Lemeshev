package com.shpp.p2p.cs.olemeshev.Collections;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class to my realization of LinkedList  I use JUnit5
 *
 * @author Aleksandr Lemeshev
 * @since 31.08.2022
 */
class LinkedListTest {
    private LinkedList<String> stringList = new LinkedList<>();
    private LinkedList<Integer> intList = new LinkedList<>();
    private LinkedList<Person> personList = new LinkedList<>();


    @BeforeAll
    static void startTesting() {
        System.out.println("Start testing...");
    }

    @AfterAll
    static void endTesting() {
        System.out.println("Testing the end.");
    }

    /**
     * Nested class LinkedList with type String. In this class I tested methods
     * remove, removeFromHead,removeAll, size
     */
    @Tag("String")
    @Nested
    @DisplayName("Test <String> LinkedList:")
    class StringLinkedList {

        @BeforeEach
        void setUp() {
            stringList.add("Tyrion Lannister");
            stringList.add("Arya Stark");
            stringList.add("Jon Snow");
            stringList.add("Brienne Tarth");
        }

        @AfterEach
        void tearDown() {
            stringList = null;
        }

        @Nested
        @DisplayName("Testing size of collection:")
        class SizeOfStringList {

            @Test
            @DisplayName("Default size")
            void defaultSize() {
                assertEquals(4, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete last element")
            void sizeAfterDeleteLastElement() {
                stringList.remove();
                assertEquals(3, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after add new element")
            void sizeAfterAdd() {
                stringList.add("Ser Davos Seaworth");
                assertEquals(5, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after add new element to head")
            void sizeAfterAddToHead() {
                stringList.addFromHead("Ser Davos Seaworth");
                assertEquals(5, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete element from head")
            void sizeAfterDeleteFromHead() {
                stringList.removeFromHead();
                assertEquals(3, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete all elements in cycle")
            void sizeAfterDeleteAllByOne() {
                while (stringList.size() != 0) {
                    stringList.remove();
                }
                assertEquals(0, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after remove all")
            void sizeAfterRemoveAll() {
                stringList.removeAll();
                assertEquals(0, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after create new list")
            void sizeAfterCreateNew() {
                stringList = new LinkedList<>();
                assertEquals(0, stringList.size(), "size of list not expected");
            }
        }

        @Nested
        @DisplayName("Testing remove method:")
        class RemoveStringList {

            @Test
            @DisplayName("Remove from not empty list")
            void removeNotEmpty() {
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                stringList.remove();
                assertEquals("Jon Snow", stringList.getTail(), "unexpected last element");
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after add to tail")
            void removeAfterAddToTail() {
                stringList.add("Daenerys Targaryen");
                assertEquals("Daenerys Targaryen", stringList.getTail(), "unexpected last element");
                stringList.remove();
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after add to head")
            void removeAfterAddToHead() {
                stringList.addFromHead("Daenerys Targaryen");
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                stringList.remove();
                assertEquals("Jon Snow", stringList.getTail(), "unexpected last element");
                assertEquals("Daenerys Targaryen", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after remove from head")
            void removeAfterRemoveFromHead() {
                stringList.removeFromHead();
                stringList.removeFromHead();
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                stringList.remove();
                assertEquals("Jon Snow", stringList.getTail(), "unexpected last element");
                assertEquals("Jon Snow", stringList.getHead(), "unexpected first element");
                assertEquals("Jon Snow", stringList.getElement(0), "unexpected first element");
            }

            @Test
            @DisplayName("Remove from empty list")
            void removeFromEmpty() {
                stringList = new LinkedList<>();
                assertEquals(0, stringList.size(), "size of list not expected");
                Exception exception = assertThrows(NoSuchElementException.class, () -> stringList.remove());
                assertEquals("list is empty", exception.getMessage());
            }

            @Test
            @DisplayName("Remove from list with one element")
            void removeFromOneElementList() {
                stringList = new LinkedList<>();
                stringList.addFromHead("Cersei Lannister");
                stringList.remove();
                assertEquals(0, stringList.size(), "size of list not expected");
                Exception exception = assertThrows(NoSuchElementException.class, () -> stringList.getTail());
                assertEquals("list is empty", exception.getMessage());
                exception = assertThrows(NoSuchElementException.class, () -> stringList.getHead());
                assertEquals("list is empty", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("Testing removeFromHead method:")
        class RemoveFromHeadStringList {

            @Test
            @DisplayName("Remove from not empty list")
            void removeNotEmpty() {
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected last element");
                stringList.removeFromHead();
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                assertEquals("Arya Stark", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after add to tail")
            void removeAfterAddToTail() {
                stringList.add("Daenerys Targaryen");
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected last element");
                stringList.removeFromHead();
                assertEquals("Daenerys Targaryen", stringList.getTail(), "unexpected last element");
                assertEquals("Arya Stark", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after add to head")
            void removeAfterAddToHead() {
                stringList.addFromHead("Daenerys Targaryen");
                assertEquals("Daenerys Targaryen", stringList.getHead(), "unexpected last element");
                stringList.removeFromHead();
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after remove from head")
            void removeAfterRemoveFromHead() {
                stringList.removeFromHead();
                stringList.removeFromHead();
                assertEquals("Jon Snow", stringList.getHead(), "unexpected last element");
                stringList.removeFromHead();
                assertEquals("Brienne Tarth", stringList.getTail(), "unexpected last element");
                assertEquals("Brienne Tarth", stringList.getHead(), "unexpected first element");
                assertEquals("Brienne Tarth", stringList.getElement(0), "unexpected first element");
            }

            @Test
            @DisplayName("Remove after remove last")
            void removeAfterRemoveLast() {
                stringList.remove();
                assertEquals("Tyrion Lannister", stringList.getHead(), "unexpected last element");
                stringList.removeFromHead();
                assertEquals("Jon Snow", stringList.getTail(), "unexpected last element");
                assertEquals("Arya Stark", stringList.getHead(), "unexpected first element");
            }

            @Test
            @DisplayName("Remove from empty list")
            void removeFromEmpty() {
                stringList = new LinkedList<>();
                assertEquals(0, stringList.size(), "size of list not expected");
                Exception exception = assertThrows(NoSuchElementException.class, () -> stringList.removeFromHead());
                assertEquals("list is empty", exception.getMessage());
            }

            @Test
            @DisplayName("Remove from list with one element")
            void removeFromOneElementList() {
                stringList = new LinkedList<>();
                stringList.add("Cersei Lannister");
                stringList.removeFromHead();
                assertEquals(0, stringList.size(), "size of list not expected");
                Exception exception = assertThrows(NoSuchElementException.class, () -> stringList.getTail());
                assertEquals("list is empty", exception.getMessage());
                exception = assertThrows(NoSuchElementException.class, () -> stringList.getHead());
                assertEquals("list is empty", exception.getMessage());
            }
        }

        @Test
        @DisplayName("Testing method removeAll:")
        void removeAll() {
            stringList.removeAll();
            assertEquals(0, stringList.size(), "size of list not expected");
            Exception exception = assertThrows(NoSuchElementException.class, () -> stringList.getTail());
            assertEquals("list is empty", exception.getMessage());
            exception = assertThrows(NoSuchElementException.class, () -> stringList.getHead());
            assertEquals("list is empty", exception.getMessage());
            int index = 0;
            exception = assertThrows(IndexOutOfBoundsException.class, () -> stringList.getElement(index));
            assertEquals("Index out of range: " + index, exception.getMessage());
        }
    }

    /**
     * Nested class LinkedList with type Integer. In this class I tested next methods
     * add and addFromHead
     */
    @Tag("Integer")
    @Nested
    @DisplayName("Test <Integer> LinkedList:")
    class IntegerLinkedList {

        @BeforeEach
        void setUp() {
            intList.add(1);
            intList.add(2);
            intList.add(3);
            intList.add(4);
            intList.add(5);
        }

        @AfterEach
        void tearDown() {
            intList = null;
        }

        @Nested
        @DisplayName("Test add new element to <Integer> LinkedList:")
        class AddElement {

            @Test
            @DisplayName("Add new element to not empty collection")
            void addElement() {
                intList.add(6);
                assertEquals(6, intList.size(), "count elements not correct");
                assertEquals(6, intList.getTail(), "incorrect element getTail");
                assertEquals(6, intList.getElement(intList.size() - 1), "incorrect elements get by index");
            }

            @Test
            @DisplayName("Add element to new list")
            void addElementToEmptyList() {
                intList = new LinkedList<>();
                intList.add(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element getTail");
                assertEquals(25, intList.getHead(), "incorrect element getHead");
            }

            @Test
            @DisplayName("Add element after delete last")
            void addElementAfterDeleteLast() {
                intList.remove();
                intList.add(25);
                assertEquals(5, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element get");
            }

            @Test
            @DisplayName("Add element after delete first")
            void addElementAfterDeleteFirst() {
                intList.removeFromHead();
                intList.add(25);
                assertEquals(5, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element get");
            }

            @Test
            @DisplayName("Add element after remove all")
            void addElementAfterRemoveAll() {
                intList.removeAll();
                assertEquals(0, intList.size(), "deleting all elements not correct");
                intList.add(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element getTail");
                assertEquals(25, intList.getHead(), "incorrect element getHead");
            }
        }

        @Nested
        @DisplayName("Test add from head side new element to <Integer> LinkedList:")
        class AddToHeadElement {

            @Test
            @DisplayName("Add new element to not empty collection")
            void addElement() {
                intList.addFromHead(6);
                assertEquals(6, intList.size(), "count elements not correct");
                assertEquals(6, intList.getHead(), "incorrect element getTail");
                assertEquals(6, intList.getElement(0), "incorrect elements get by index");
            }

            @Test
            @DisplayName("Add element to new list")
            void addElementToEmptyList() {
                intList = new LinkedList<>();
                intList.addFromHead(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element getTail");
                assertEquals(25, intList.getHead(), "incorrect element getHead");
            }

            @Test
            @DisplayName("Add element after delete last")
            void addElementAfterDeleteLast() {
                assertEquals(1, intList.getHead(), "incorrect element get");
                intList.remove();
                intList.addFromHead(25);
                assertEquals(5, intList.size(), "count elements not correct");
                assertEquals(25, intList.getHead(), "incorrect element get");
            }

            @Test
            @DisplayName("Add element after delete first")
            void addElementAfterDeleteFirst() {
                assertEquals(1, intList.getHead(), "incorrect element get");
                intList.removeFromHead();
                assertEquals(2, intList.getHead(), "incorrect element get");
                intList.addFromHead(25);
                assertEquals(5, intList.size(), "count elements not correct");
                assertEquals(25, intList.getHead(), "incorrect element get");
            }

            @Test
            @DisplayName("Add element after remove all")
            void addElementAfterRemoveAll() {
                intList.removeAll();
                assertEquals(0, intList.size(), "deleting all elements not correct");
                intList.addFromHead(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.getTail(), "incorrect element getTail");
                assertEquals(25, intList.getHead(), "incorrect element getHead");
            }
        }

    }

    /**
     * Nested class LinkedList with type Person. In this class I tested getters
     */
    @Tag("Person")
    @Nested
    @DisplayName("Test <Person> LinkedList:")
    class PersonLinkedList {
        @BeforeEach
        void setUp() {
            personList.add(new Person("Anton", 22));
            personList.add(new Person("Valera", 37));
            personList.add(new Person("Vasya", 18));
            personList.add(new Person("Petya", 19));
        }

        @AfterEach
        void tearDown() {
            personList = null;
        }

        @Nested
        @DisplayName("Test getters methods from <Person> LinkedList:")
        class Getters {

            @Test
            @DisplayName("Get element from default not empty collection")
            void defaultGetter() {
                assertEquals("Valera", personList.getElement(1).name, "unexpected name of person");
                assertEquals(37, personList.getElement(1).age, "unexpected age of person");
                assertEquals("Petya", personList.getTail().name, "unexpected name of person");
                assertEquals("Anton", personList.getHead().name, "unexpected name of person");
            }

            @Test
            @DisplayName("Get from incorrect index")
            void incorrectGetter() {
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.getElement(-1));
                assertEquals("Index out of range: -1", exception.getMessage());
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.getElement(55));
                assertEquals("Index out of range: 55", exception.getMessage());

                personList.removeAll();
                exception = assertThrows(NoSuchElementException.class, () -> stringList.getTail());
                assertEquals("list is empty", exception.getMessage());
                exception = assertThrows(NoSuchElementException.class, () -> stringList.getHead());
                assertEquals("list is empty", exception.getMessage());
            }

            @Test
            @DisplayName("Get element after removes element")
            void getterAfterRemove() {
                int lastIndex = personList.size() - 1;
                assertEquals("Petya", personList.getTail().name, "unexpected name of person");
                assertEquals("Petya", personList.getElement(lastIndex).name, "unexpected name of person");
                assertEquals("Anton", personList.getHead().name, "unexpected name of person");

                personList.remove();
                assertEquals("Vasya", personList.getTail().name, "unexpected name of person");
                assertEquals("Anton", personList.getHead().name, "unexpected name of person");
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.getElement(lastIndex));
                assertEquals("Index out of range: " + lastIndex, exception.getMessage());

                personList.removeFromHead();
                assertEquals("Vasya", personList.getTail().name, "unexpected name of person");
                assertEquals("Valera", personList.getHead().name, "unexpected name of person");
                assertEquals("Valera", personList.getElement(0).name, "unexpected name of person");
            }

            @Test
            @DisplayName("Get element after add element")
            void getterAfterAdd() {
                int lastIndex = personList.size() - 1;
                assertEquals("Petya", personList.getTail().name, "unexpected name of person");
                assertEquals("Petya", personList.getElement(lastIndex).name, "unexpected name of person");
                assertEquals("Anton", personList.getHead().name, "unexpected name of person");

                personList.add(new Person("Taras", 32));
                assertEquals("Taras", personList.getTail().name, "unexpected name of person");
                assertEquals("Taras", personList.getElement(lastIndex + 1).name, "unexpected name of person");
                assertEquals("Anton", personList.getHead().name, "unexpected name of person");

                personList.addFromHead(new Person("Masha", 26));
                assertEquals("Taras", personList.getTail().name, "unexpected name of person");
                assertEquals("Masha", personList.getHead().name, "unexpected name of person");
                assertEquals("Masha", personList.getElement(0).name, "unexpected name of person");
            }
        }
    }

    /**
     * Inner class for testing
     *
     * @param name name of Person
     * @param age  age of Person
     */
    record Person(String name, int age) {
    }
}