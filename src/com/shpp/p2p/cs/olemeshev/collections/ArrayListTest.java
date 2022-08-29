package com.shpp.p2p.cs.olemeshev.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private ArrayList<String> stringList = new ArrayList<>();
    private ArrayList<Integer> intList = new ArrayList<>();
    private ArrayList<Person> personList = new ArrayList<>();
    Field field = stringList.getClass().getDeclaredField("initCapacity");
    Object value;

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

    @Tag("String")
    @Nested
    @DisplayName("Test <String> ArrayList")
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
            @DisplayName("default size")
            void defaultSize() {
                assertEquals(3, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("size after delete last element")
            void sizeAfterDeleteLastElement() {
                stringList.removeLast();
                assertEquals(2, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("size after add new element")
            void sizeAfterAdd() {
                stringList.add("Ser Davos Seaworth");
                assertEquals(4, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("size after delete element with index")
            void sizeAfterDeleteOneElement() {
                stringList.remove(1);
                assertEquals(2, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("size after delete all elements in cycle")
            void sizeAfterDeleteAllByOne() {
                while (stringList.size() != 0) {
                    stringList.removeLast();
                }
                assertEquals(0, stringList.size(), "size of list not expected");
            }

            @Test
            @DisplayName("size after delete all")
            void sizeAfterDeleteAll() {
                stringList.deleteAll();
                assertEquals(0, stringList.size(), "size of list not expected");
            }
        }

        @Nested
        @DisplayName("Testing isEmpty method")
        class IsEmptyStringList {

            @Test
            @DisplayName("default value list with 3 elements")
            void defaultEmpty() {
                assertFalse(stringList.isEmpty(), "list is empty");
            }

            @Test
            @DisplayName("testing after add new element")
            void emptyAfterAddElement() {
                stringList.add("Tormund Giantsbane");
                assertFalse(stringList.isEmpty(), "list is empty");
            }

            @Test
            @DisplayName("testing after delete all elements")
            void emptyAfterDelete() {
                stringList.deleteAll();
                assertTrue(stringList.isEmpty(), "list is not empty");
            }
        }

        @Nested
        @DisplayName("Testing initial capacity")
        class InitCapacity {

            @Test
            @DisplayName("default constructor with initial capacity ==10")
            void defaultCapacity() throws IllegalAccessException {
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("capacity  after add new 10 elements")
            void capacityAfterAddElements() throws IllegalAccessException {
                value = field.get(stringList);
                for (int i = 0; i < 10; i++) {
                    stringList.add("White walker#" + (i + 1));
                }
                value = field.get(stringList);
                assertEquals(20, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("capacity after delete in cycle all elements")
            void capacityAfterCycleDelete() throws IllegalAccessException {
                while (stringList.size() != 0) {
                    stringList.removeLast();
                }
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("capacity after method trimToSize")
            void capacityAfterTrim() throws IllegalAccessException {
                stringList.trimToSize();
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("incorrect input capacity")
            void incorrectCapacity() {
                Exception exception = assertThrows(IllegalArgumentException.class, () ->
                        stringList = new ArrayList<>(-5));
                assertEquals("Illegal Capacity: -5", exception.getMessage());
            }

            @Test
            @DisplayName("capacity after add one new element")
            void capacityAfterAddNewElement() throws IllegalAccessException {
                stringList.add("Eddard Stark");
                value = field.get(stringList);
                assertEquals(10, value, "unexpected initial capacity");
            }

            @Test
            @DisplayName("capacity after creating new list with constructor with parameters")
            void capacityInConstructor() throws IllegalAccessException {
                stringList = new ArrayList<>(6);
                value = field.get(stringList);
                assertEquals(6, value, "unexpected initial capacity");
            }
        }
    }

    @Tag("Integer")
    @Nested
    @DisplayName("Test <Integer> ArrayList")
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
        @DisplayName("Test add new element to <Integer> ArrayList")
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
                intList.deleteAll();
                assertTrue(intList.isEmpty(), "deleting all elements not correct");
                intList.add(25);
                assertEquals(1, intList.size(), "count elements not correct");
                assertEquals(25, intList.get(0), "incorrect element get");
            }
        }

        @Nested
        @DisplayName("Test delete element")
        class DeleteElement {

            @Test
            @DisplayName("Delete all elements")
            void deleteAll() {
                assertEquals(5, intList.size(), "incorrect size");
                intList.deleteAll();
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
                intList.deleteAll();
                Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> intList.removeLast());
                assertEquals("Index out of range: -1", exception.getMessage());
                exception = assertThrows(IndexOutOfBoundsException.class, () -> intList.remove(0));
                assertEquals("Index out of range: 0", exception.getMessage());
            }
        }
    }

    @Tag("Percon")
    @Nested
    @DisplayName("Test <Person> ArrayList")
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
    }

    record Person(String name, int age) {
    }
}