package com.shpp.p2p.cs.olemeshev.Collections;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class testing my collection Queue  I use JUnit5
 *
 * @author Aleksandr Lemeshev
 * @since 26.08.2022
 */
class QueueTest {
    private Queue<String> stringQueue = new Queue<>();

    @BeforeAll
    static void startTesting() {
        System.out.println("Start testing...");
    }

    @AfterAll
    static void endTesting() {
        System.out.println("Testing the end.");
    }

    @BeforeEach
    void setStringQueue() {
        stringQueue.push("Eddard Stark");
        stringQueue.push("Jame Lanister");
        stringQueue.push("Robert Barateon");
    }

    @AfterEach
    void tearDown() {
        stringQueue = null;
    }

    /**
     * Testing method size
     */
    @Nested
    @DisplayName("Testing size of collection:")
    class QueueSize {

        @Test
        @DisplayName("Default size")
        void defaultSize() {
            assertEquals(3, stringQueue.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after get first element")
        void sizeAfterGet() {
            stringQueue.get();
            assertEquals(3, stringQueue.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after pop first element")
        void sizeAfterPop() {
            stringQueue.pop();
            assertEquals(2, stringQueue.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Size after push element")
        void sizeAfterPoll() {
            stringQueue.push("Melisandre");
            assertEquals(4, stringQueue.size(), "size of list not expected");
        }
    }


    /**
     * Testing method get
     */
    @Nested
    @DisplayName("Testing get element from collection:")
    class QueueGet {

        @Test
        @DisplayName("Default size")
        void getNotEmpty() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
        }

        @Test
        @DisplayName("Get from empty list")
        void getEmpty() {
            stringQueue = new Queue<>();
            Exception exception = assertThrows(NoSuchElementException.class, () -> stringQueue.get());
            assertEquals("list is empty", exception.getMessage());
        }

        @Test
        @DisplayName("Get two times")
        void getTwoTimes() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
        }

        @Test
        @DisplayName("Get after pop")
        void getAfterPop() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
            stringQueue.pop();
            assertEquals("Jame Lanister", stringQueue.get(), "element of list not expected");
        }

        @Test
        @DisplayName("Get after push")
        void getAfterPush() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
            stringQueue.push("Melisandre");
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
        }
    }

    /**
     * Testing method pop
     */
    @Nested
    @DisplayName("Testing pop element from collection:")
    class QueuePop {

        @Test
        @DisplayName("Default size")
        void popNotEmpty() {
            assertEquals("Eddard Stark", stringQueue.pop(), "element of list not expected");
        }

        @Test
        @DisplayName("Pop from empty list")
        void PopEmpty() {
            stringQueue = new Queue<>();
            Exception exception = assertThrows(NoSuchElementException.class, () -> stringQueue.pop());
            assertEquals("list is empty", exception.getMessage());
        }

        @Test
        @DisplayName("Pop two times")
        void popTwoTimes() {
            assertEquals("Eddard Stark", stringQueue.pop(), "element of list not expected");
            assertEquals("Jame Lanister", stringQueue.pop(), "element of list not expected");
        }

        @Test
        @DisplayName("Pop element after get")
        void popAfterGet() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
            assertEquals("Eddard Stark", stringQueue.pop(), "element of list not expected");
            assertEquals("Jame Lanister", stringQueue.get(), "element of list not expected");
        }

        @Test
        @DisplayName("Pop after push")
        void popAfterPush() {
            assertEquals("Eddard Stark", stringQueue.get(), "element of list not expected");
            stringQueue.push("Melisandre");
            assertEquals("Eddard Stark", stringQueue.pop(), "element of list not expected");
            assertEquals("Jame Lanister", stringQueue.pop(), "element of list not expected");
        }
    }

    /**
     * Testing method push
     */
    @Nested
    @DisplayName("Testing push element to collection:")
    class QueuePush {

        @Test
        @DisplayName("Default size")
        void pushNotEmpty() {
            assertEquals(3, stringQueue.size(), "size of list not expected");
            stringQueue.push("Missandei");
            assertEquals(4, stringQueue.size(), "size of list not expected");
        }

        @Test
        @DisplayName("Push to empty list")
        void PushEmpty() {
            stringQueue = new Queue<>();
            assertEquals(0, stringQueue.size(), "size of list not expected");
            stringQueue.push("Missandei");
            assertEquals(1, stringQueue.size(), "size of list not expected");
            assertEquals("Missandei", stringQueue.get(), "element of list not expected");
        }

        @Test
        @DisplayName("Push after pop")
        void pushAfterPop() {
            assertEquals("Eddard Stark", stringQueue.pop(), "element of list not expected");
            stringQueue.push("Melisandre");
            assertEquals("Jame Lanister", stringQueue.pop(), "element of list not expected");
        }
    }

}