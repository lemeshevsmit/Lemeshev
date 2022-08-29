package com.shpp.p2p.cs.olemeshev.collections;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class testing my collection Queue
 *
 * @author Aleksandr Lemeshev
 * @since 26.08.2022
 */
class QueueTest {
    private Queue<String> stringQueue = new Queue<>();
    private static final String G = "\u001B[32m";
    private static final String R = "\u001B[31m";
    private static final String E = "\u001B[0m";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Start testing...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Testing complete.");
    }

    @Tag("String")
    @BeforeEach
    void setStringQueue() {
        stringQueue.add("Eddard Stark");
        stringQueue.add("Jame Lanister");
        stringQueue.add("Robert Barateon");
    }

    @Tag("String")
    @AfterEach
    void tearDown() {
        stringQueue = null;
    }

    @Tag("String")
    @Test
    @DisplayName("Get first element from <String> queue")
    void get() {
        //assertEquals("Vasia",queue);
        System.out.println("dd");
    }

    @Tag("String")
    @DisplayName("Get size of queue")
    @Test
    void sizeStringQueue() {
        assertEquals(3, stringQueue.size(), "Size of queue not expected");
        stringQueue.add("Petir Beilish");
        assertEquals(4, stringQueue.size(), "Size of queue not expected");
        stringQueue.poll();
        assertEquals(3, stringQueue.size(), "Size of queue not expected");
        stringQueue.removeAll();
        assertEquals(0, stringQueue.size(), "Size of queue not expected");
        stringQueue = null;
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> stringQueue.size(),
                "NullPointerException was expected");
        thrown.getMessage();
    }

    @Tag("Integer")
    @Test
    void sizeIntegerQueue() {
        System.out.println("sd");
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void poll() {
    }

    @Test
    void removeAll() {
    }
}