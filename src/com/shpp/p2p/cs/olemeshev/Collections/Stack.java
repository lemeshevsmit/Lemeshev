package com.shpp.p2p.cs.olemeshev.Collections;

import java.util.Arrays;

/**
 * This class is realisation of my Stack (LIFO)
 *
 * @param <E> type elements of list
 * @author Aleksandr Lemeshev
 * @since 14.08.2022
 */
public class Stack<E> {
    //start size of collection
    private int initCapacity = 100;
    //collection elements
    private Object[] elements = new Object[initCapacity];
    private int countOfElement = 0;

    /**
     * This method return count of elements in collection
     */
    public int size() {
        return this.countOfElement;
    }

    /**
     * This method add element to end of collection
     */
    public void push(E element) {
        if (countOfElement >= initCapacity) {
            initCapacity = countOfElement + initCapacity;
            elements = Arrays.copyOf(elements, initCapacity);
        }
        elements[countOfElement] = element;
        countOfElement++;
    }

    /**
     * This method remove last element of list if list is empty
     * throw IndexOutOfBoundsException -1
     *
     * @return last element
     * @throws IndexOutOfBoundsException incorrect index
     */
    public E pop() {
        if (countOfElement - 1 < 0) throw new IndexOutOfBoundsException(countOfElement - 1);

        Object deletedElement = elements[countOfElement - 1];
        elements[countOfElement - 1] = null;
        countOfElement--;
        return (E) deletedElement;
    }


    /**
     * This method return all elements of collection to string
     *
     * @return String with elements of collection
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (countOfElement > 0) {
            sb.append("[\"");
            for (int i = 0; i < countOfElement; i++) {
                sb.append(elements[i]).append("\", \"");
            }
            if (sb.length() > 3) {
                sb.setLength(sb.length() - 3);
                sb.append("]");
            }
            return sb.toString();
        } else return "[]";
    }
}