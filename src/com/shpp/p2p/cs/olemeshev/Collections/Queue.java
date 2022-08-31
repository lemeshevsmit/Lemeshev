package com.shpp.p2p.cs.olemeshev.Collections;

import java.util.NoSuchElementException;

/**
 * This class its basic realisation of Queue (FIFO)
 *
 * @param <T> type of elements in list
 * @author Aleksandr Lemeshev
 * @since 15.08.2022
 */
public class Queue<T> {
    //size of list (count of elements)
    private int sizeOfList = 0;
    //head element of list
    private Node<T> head = null;
    //last element of list
    private Node<T> tail = null;

    /**
     * This method returns the head element in LinkedList or throws exception
     *
     * @return the head element in this list if we can read him
     * @throws NoSuchElementException if this list is empty
     */
    public T get() {
        Node<T> node = head;
        if (node == null) throw new NoSuchElementException("list is empty");
        return node.element;
    }

    /**
     * This method return count of elements in LinkedList
     *
     * @return size of list
     */
    public int size() {
        return sizeOfList;
    }

    /**
     * This method add element to end of LinkedList(tail). If list is empty add element to head
     * and if  we have only head add element to tail. In other case we add element
     * to end of list (replace tail)
     *
     * @param element element what we need add
     */
    public void push(T element) {
        if (tail != null) {
            Node<T> last = tail;
            Node<T> node = new Node<>(last, element, null);
            last.next = node;
            tail = node;
        } else {
            if (head != null) {
                tail = new Node<>(head, element, null);
                head.next = tail;
            } else {
                head = new Node<>(null, element, null);
            }
        }
        sizeOfList++;
    }


    /**
     * This method remove head element of LinkedList and replace him to
     * next element if we can but if list is empty throws exception
     *
     * @throws NoSuchElementException if this list is empty
     */
    public T pop() {
        if (head == null) throw new NoSuchElementException("list is empty");
        T deletedElement = head.element;
        if (head.next != null) {
            Node<T> node = head.next;
            if (node.equals(tail)) {
                head = tail;
                head.prev = null;
                head.next = null;
                tail = null;
            } else {
                head = node;
                head.prev = null;
            }
        } else head = null;
        sizeOfList--;
        return deletedElement;
    }

    /**
     * This method return all elements of collection to string
     *
     * @return String with elements of collection
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (sizeOfList > 0) {
            sb.append("[\"");
            Node<T> node = head;
            for (int i = 0; i < sizeOfList; i++) {
                sb.append(node.element).append("\", \"");
                node = node.next;
            }
            if (sb.length() > 3) {
                sb.setLength(sb.length() - 3);
                sb.append("]");
            }
            return sb.toString();
        } else return "[]";
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