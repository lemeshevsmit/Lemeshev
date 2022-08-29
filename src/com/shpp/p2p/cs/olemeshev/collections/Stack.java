package com.shpp.p2p.cs.olemeshev.collections;

import java.util.NoSuchElementException;

/**
 * This class its basic realisation of Stack
 *
 * @param <T> type of elements in list
 * @author Aleksandr Lemeshev
 * @since 25.08.2022
 */
public class Stack<T> {
    //size of list (count of elements)
    private int sizeOfList = 0;
    //elements of list
    private Node<T> stack = null;




    /**
     * This method remove all elements from LinkedList;
     */
    public void removeAll() {
//        head = null;
//        tail = null;
        sizeOfList = 0;
    }

    /**
     * Inner class with current element and his relations
     *
     * @param <T> type of element
     */
    private static class Node<T> {
        //current element
        T element;
        //next element
        Node<T> next;
        //previously element
        Node<T> prev;

        /**
         * Constructor with parameters
         *
         * @param prev    previously element
         * @param element current element
         * @param next    next element
         */
        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
