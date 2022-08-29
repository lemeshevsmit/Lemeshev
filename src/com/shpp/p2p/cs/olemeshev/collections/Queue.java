package com.shpp.p2p.cs.olemeshev.collections;

import java.util.NoSuchElementException;

/**
 * This class its basic realisation of Queue (FIFO)
 *
 * @param <T> type of elements in list
 * @author Aleksandr Lemeshev
 * @since 25.08.2022
 */
public class Queue<T> {
    //size of list (count of elements)
    private int sizeOfList = 0;
    //first element of list
    private Node<T> first = null;


    /**
     * This method returns the first element from Queue
     *
     * @return the first element in this list if we can get him
     * @throws NoSuchElementException if this list is empty
     */
    public T get() {
        if (first == null) throw new NoSuchElementException();
        return first.element;
    }

    /**
     * This method return count of elements in Queue
     *
     * @return size of list
     */
    public int size() {
        return sizeOfList;
    }

    /**
     * This method add element to end of Queue.
     *
     * @param element element what we need add
     */
    public void add(T element) {
        if (first == null) first = new Node<>(null, element, null);
        else {
            Node<T> node = new Node<>(null, element, first);
            first.prev = node;
            first = node;
        }
        sizeOfList++;
    }


    /**
     * This method remove first element of Queue and return him
     *
     * @return removed element from list
     * @throws NoSuchElementException if this list is empty
     */
    public T remove() {
        if (first == null) throw new NoSuchElementException();
        if (first.next == null) first = null;
        else {
            Node<T> node = first.next;
            node.prev = null;
            first = node;
        }
        sizeOfList--;
        return first.element;
    }

    /**
     * This method remove first element of Queue and return him
     * or null if list is empty
     *
     * @return removed element from list or null
     */
    public T poll() {
        if (first == null) return null;
        if (first.next == null) first = null;
        else {
            Node<T> node = first.next;
            node.prev = null;
            first = node;
        }
        sizeOfList--;
        return first.element;
    }

    /**
     * This method remove all elements from Queue;
     */
    public void removeAll() {
        first = null;
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
