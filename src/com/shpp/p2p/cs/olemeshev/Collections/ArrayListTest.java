package com.shpp.p2p.cs.olemeshev.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class to my realization of ArrayList I use JUnit5
 *
 * @author Aleksandr Lemeshev
 * @since 30.08.2022
 */
class ArrayListTest {
    private ArrayList<String> stringList = new ArrayList<>();
    private ArrayList<Integer> intList = new ArrayList<>();
    private ArrayList<Person> personList = new ArrayList<>();
    private final Field field = stringList.getClass().getDeclaredField("initCapacity");
    private Object value;

    ArrayListTest() throws NoSuchFieldException {
        field.setAccessible(true);
    }

    @BeforeAll
    static void startTesting() {
        System.out.println("Start testing...");
    }

    @AfterAll
    static void endTesting() {
        System.out.println("Testing the end.");
    }

    /**
     * Nested class ArrayList with type String. In this class I tested method isEmpty and
     * field countOfElement and initCapacity
     */
    @Tag("String")
    @Nested
    @DisplayName("Test <String> ArrayList:")
    class StringArrayList {

        @BeforeEach
        void setUp() {
            stringList.add("Tyrion Lannister");
            stringList.add("Arya Stark");
            stringList.add("Tyrion Lannister");
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
                assertEquals(3, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete last element")
            void sizeAfterDeleteLastElement() {
                stringList.removeLast();
                assertEquals(2, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after add new element")
            void sizeAfterAdd() {
                stringList.add("Ser Davos Seaworth");
                assertEquals(4, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete element with index")
            void sizeAfterDeleteOneElement() {
                stringList.remove(1);
                assertEquals(2, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete all elements in cycle")
            void sizeAfterDeleteAllByOne() {
                while (stringList.size() != 0) {
                    stringList.removeLast();
                }
                assertEquals(0, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("Size after delete all")
            void sizeAfterDeleteAll() {
                stringList.delete();
                assertEquals(0, stringList.size(), "size of list not expected");
            }
        }

        @Nested
        @DisplayName("Testing isEmpty method:")
        class IsEmptyStringList {

            @Test
            @DisplayName("Default value list with 3 elements")
            void defaultEmpty() {
                assertFalse(stringList.isEmpty(), "list is empty");
            }

            @Test
            @DisplayName("Testing after add new element")
            void emptyAfterAddElement() {
                stringList.add("Tormund Giantsbane");
                assertFalse(stringList.isEmpty(), "list is empty");
            }

            @Test
            @DisplayName("Testing after delete all elements")
            void emptyAfterDelete() {
                stringList.delete();
                assertTrue(stringList.isEmpty(), "list is not empty");
            }
        }

        @Nested
        @DisplayName("Testing initial capacity:")
        class InitCapacity {

            @Test
            @DisplayName("Default constructor with initial capacity ==10")
            void defaultCapacity() throws IllegalAccessException {
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("Capacity  after add new 10 elements")
            void capacityAfterAddElements() throws IllegalAccessException {
                value = field.get(stringList);
                for (int i = 0; i < 10; i++) {
                    stringList.add("White walker#" + (i + 1));
                }
                value = field.get(stringList);
                assertEquals(20, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("Capacity after delete in cycle all elements")
            void capacityAfterCycleDelete() throws IllegalAccessException {
                while (stringList.size() != 0) {
                    stringList.removeLast();
                }
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("Capacity after method trimToSize")
            void capacityAfterTrim() throws IllegalAccessException {
                stringList.trimToSize();
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("Incorrect input capacity")
            void incorrectCapacity() {
                Exception exception = assertThrows(IllegalArgumentException.class, () ->
                        stringList = new ArrayList<>(-5));
                assertEquals("Illegal Capacity: -5", exception.getMessage());
            }

            @Test
            @DisplayName("Capacity after add one new element")
            void capacityAfterAddNewElement() throws IllegalAccessException {
                stringList.add("Eddard Stark");
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("Capacity after creating new list with constructor with parameters")
            void capacityInConstructor() throws IllegalAccessException {
                stringList = new ArrayList<>(6);
                value = field.get(stringList);
                assertEquals(6, value, "unexpected initial capacity");
            }
        }
    }

    /**
     * Nested class ArrayList with type Integer. In this class I tested next methods
     * add, remove, removeLast, delete
     */
    @Tag("Integer")
    @Nested
    @DisplayName("Test <Integer> ArrayList:")
    class IntegerArrayList {

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
        @DisplayName("Test add new element to <Integer> ArrayList:")
        class AddElement {

            @Test
            @DisplayName("Add new element to not empty collection")
            void addElement() {
                intList.add(6);
                assertEquals(6, intList.size(), "count elements not correct");
                assertEquals(6, intList.get(5), "incorrect element get");
                assertEquals(6, intList.removeLast(), "incorrect elements get after delete");
            }

            @Test
            @DisplayName("Add element to new list")
            void addElementToEmptyList() {
                intList = new ArrayList<>();
                intList.add(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.get(0), "incorrect element get");
                assertEquals(25, intList.removeLast(), "incorrect elements get after delete");
            }

            @Test
            @DisplayName("Add element after delete last")
            void addElementAfterDeleteLast() {
                assertEquals(5, intList.removeLast(), "incorrect elements get after delete");
                intList.add(25);
                assertEquals(5, intList.size(), "count elements not correct");
                assertEquals(25, intList.get(4), "incorrect element get");
            }

            @Test
            @DisplayName("Add element after delete all")
            void addElementAfterDeleteAll() {
                intList.delete();
                assertTrue(intList.isEmpty(), "deleting all elements not correct");
                intList.add(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.get(0), "incorrect element get");
            }
        }

        @Nested
        @DisplayName("Test delete element:")
        class DeleteElement {

            @Test
            @DisplayName("Delete all elements")
            void deleteAll() {
                assertEquals(5, intList.size(), "incorrect size");
                intList.delete();
                assertEquals(0, intList.size(), "incorrect size");
            }

            @Test
            @DisplayName("Delete last elements")
            void deleteLast() {
                assertEquals(5, intList.removeLast(), "incorrect deleted element");
                assertEquals(4, intList.size(), "Incorrect size after delete last element");
                assertEquals(4, intList.removeLast(), "incorrect deleted element");
                assertEquals(3, intList.size(), "Incorrect size after delete last element");
                intList.add(99);
                assertEquals(99, intList.removeLast(), "incorrect deleted element");
                assertEquals(3, intList.size(), "Incorrect size after delete last element");
            }

            @ParameterizedTest
            @DisplayName("Delete element from index")
            @ValueSource(ints = {0, 2, 3})
            void deleteElementByIndex(int argument) {
                assertEquals(intList.get(argument), intList.remove(argument), "incorrect deleted element");
                assertFalse(intList.isEmpty(), "list is empty");
                assertEquals(4, intList.size(), "Incorrect size after delete last element");
            }

            @ParameterizedTest
            @DisplayName("Exception of delete")
            @ValueSource(ints = {-1, 55})
            void deleteElementIncorrectIndex(int argument) {
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> intList.remove(argument));
                assertEquals("Index out of range: " + argument, exception.getMessage());
            }

            @Test
            @DisplayName("Delete last in empty list")
            void deleteLastInEmpty() {
                intList.delete();
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> intList.removeLast());
                assertEquals("Index out of range: -1", exception.getMessage());
                exception = assertThrows(IndexOutOfBoundsException.class, () -> intList.remove(0));
                assertEquals("Index out of range: 0", exception.getMessage());
            }
        }
    }

    /**
     * Nested class ArrayList with type Person. In this class I tested two methods get and set
     */
    @Tag("Person")
    @Nested
    @DisplayName("Test <Person> ArrayList:")
    class PersonArrayList {
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
        @DisplayName("Test get method <Person> ArrayList:")
        class Getter {

            @Test
            @DisplayName("Get element from default not empty collection")
            void defaultGetter() {
                assertEquals("Valera", personList.get(1).name, "unexpected name of person");
                assertEquals(37, personList.get(1).age, "unexpected age of person");
                assertEquals("Petya", personList.get(personList.size() - 1).name, "unexpected name of person");
            }

            @Test
            @DisplayName("Get from incorrect index")
            void incorrectGetter() {
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(-1));
                assertEquals("Index out of range: -1", exception.getMessage());
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(55));
                assertEquals("Index out of range: 55", exception.getMessage());
                personList.remove(personList.size() - 1);
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(personList.size()));
                assertEquals("Index out of range: " + personList.size(), exception.getMessage());
            }

            @Test
            @DisplayName("Get element after remove or add element")
            void getterAfterRemoveOrAdd() {
                personList.removeLast();
                assertEquals("Vasya", personList.get(personList.size() - 1).name, "unexpected name of person");
                personList.add(new Person("Taras", 25));
                assertEquals("Taras", personList.get(personList.size() - 1).name, "unexpected name of person");
            }

            @Test
            @DisplayName("Get element from empty list")
            void getterFromEmptyList() {
                while (personList.size() != 0) {
                    personList.removeLast();
                }
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(0));
                assertEquals("Index out of range: 0", exception.getMessage());
                personList.delete();
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(0));
                assertEquals("Index out of range: 0", exception.getMessage());
                personList = new ArrayList<>(1);
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.get(0));
                assertEquals("Index out of range: 0", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("Test set method <Person> ArrayList:")
        class Setter {

            @Test
            @DisplayName("Range element from default not empty collection")
            void defaultSetter() {
                Person person = new Person("Stepan", 55);
                assertEquals("Valera", personList.get(1).name, "unexpected name of person");
                personList.set(1, person);
                assertEquals("Stepan", personList.get(1).name, "unexpected name of person");
                assertEquals(55, personList.get(1).age, "unexpected age of person");
            }

            @Test
            @DisplayName("Range from incorrect index")
            void incorrectSetter() {
                Person person = new Person("Stepan", 55);
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.set(-1, person));
                assertEquals("Index out of range: -1", exception.getMessage());
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.set(55, person));
                assertEquals("Index out of range: 55", exception.getMessage());
            }

            @Test
            @DisplayName("Range added element")
            void setterNewElement() {
                Person person = new Person("Stepan", 55);
                personList.add(person);
                Person newPerson = new Person("Stepan", 45);
                personList.set(personList.size() - 1, newPerson);
                assertEquals(45, personList.get(personList.size() - 1).age, "unexpected age of person");
            }

            @Test
            @DisplayName("Range element from empty list")
            void setterFromEmptyList() {
                while (personList.size() != 0) {
                    personList.removeLast();
                }
                Person person = new Person("Stepan", 55);
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.set(0, person));
                assertEquals("Index out of range: 0", exception.getMessage());

                personList.delete();
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.set(0, person));
                assertEquals("Index out of range: 0", exception.getMessage());

                personList = new ArrayList<>(1);
                exception = assertThrows(IndexOutOfBoundsException.class, () -> personList.set(0, person));
                assertEquals("Index out of range: 0", exception.getMessage());
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