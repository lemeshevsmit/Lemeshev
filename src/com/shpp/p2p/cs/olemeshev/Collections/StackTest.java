package com.shpp.p2p.cs.olemeshev.Collections;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class to my realization of Stack  I use JUnit5
 *
 * @author Aleksandr Lemeshev
 * @since 30.08.2022
 */
class StackTest {
    Stack<Integer> stack = new Stack<>();

    @BeforeAll
    static void startTesting() {
        System.out.println("Start testing...");
    }

    @AfterAll
    static void endTesting() {
        System.out.println("Testing the end.");
    }

    @BeforeEach
    void setUp() {
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(9);
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    /**
     * Testing method size
     */
    @Nested
    @DisplayName("Testing size of collection:")
    class StackSize {

        @Test
        @DisplayName("Default size")
        void defaultSize() {
            assertEquals(5, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after pop last element")
        void sizeAfterPop() {
            stack.pop();
            assertEquals(4, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after push new element")
        void sizeAfterPush() {
            stack.push(11);
            assertEquals(6, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after create new Stack")
        void sizeNew() {
            stack = new Stack<>();
            assertEquals(0, stack.size(), "size of list not expected");
        }
    }


    /**
     * Testing method push
     */
    @Nested
    @DisplayName("Testing push to collection:")
    class StackPush {

        @Test
        @DisplayName("Default push to not empty collection")
        void pushDefault() {
            stack.push(Integer.MAX_VALUE);
            assertEquals(6, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Push element after pop last element")
        void pushAfterPop() {
            assertEquals(9, stack.pop(), "element after pop not expected");
            stack.push(Integer.MAX_VALUE);
            assertEquals(5, stack.size(), "size of list not expected");
            assertEquals(Integer.MAX_VALUE, stack.pop(), "element after pop not expected");
        }

        @Test
        @DisplayName("Push 101 elements to Stack")
        void push101Element() {
            int size = stack.size();
            for (int i = 0; i <= 100; i++) {
                stack.push(i);
            }
            assertEquals(101 + size, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Push after create new Stack")
        void pushNew() {
            stack = new Stack<>();
            assertEquals(0, stack.size(), "size of list not expected");
            stack.push(-4);
            assertEquals(1, stack.size(), "size of list not expected");
            assertEquals(-4, stack.pop(), "element after pop not expected");
        }
    }

    /**
     * Testing method push
     */
    @Nested
    @DisplayName("Testing pop of collection:")
    class StackPop {

        @Test
        @DisplayName("Default pop from not empty collection")
        void popDefault() {
            assertEquals(9, stack.pop(), "element after pop not expected");
        }

        @Test
        @DisplayName("Pop element after pop last element")
        void popAfterPop() {
            assertEquals(9, stack.pop(), "element after pop not expected");
            assertEquals(7, stack.pop(), "element after pop not expected");
            assertEquals(5, stack.pop(), "element after pop not expected");
            assertEquals(2, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Pop all elements from Stack")
        void popAll() {
            while (stack.size() != 0) {
                assertTrue(isNumeric(String.valueOf(stack.pop())), "pop element is not number");
            }
            assertEquals(0, stack.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Pop after create new Stack")
        void popNew() {
            stack = new Stack<>();
            assertEquals(0, stack.size(), "size of list not expected");
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> stack.pop());
            assertEquals("Index out of range: -1", exception.getMessage());
        }
    }

    /**
     * This method check input string value if it is a number
     *
     * @param s check string
     * @return true - is number; false - other
     */
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}