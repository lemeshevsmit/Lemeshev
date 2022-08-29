package com.shpp.p2p.cs.olemeshev.collections;

import java.util.Arrays;

/**
 * This class is realisation of my ArrayList
 *
 * @param <E> type elements of list
 */
public class ArrayList<E> {
    //start size of collection
    private int initCapacity = 10;

    //collection elements
    private Object[] elements;

    private int countOfElement;

    /**
     * Constructor without parameters create collection with default size==10
     */
    ArrayList() {
        this.elements = new Object[initCapacity];
    }

    /**
     * Constructor with parameters
     *
     * @param capacity start size of empty list
     */
    ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        } else {
            initCapacity = capacity;
            this.elements = new Object[initCapacity];
        }
    }

    /**
     * This method return count of elements in collection
     */
    public int size() {
        return this.countOfElement;
    }

    /**
     * This method return true if collection is empty
     */
    public boolean isEmpty() {
        return this.countOfElement == 0;
    }

    /**
     * This method add element to collection
     */
    public void add(E element) {
        if (countOfElement >= initCapacity) {
            createNewSize();
        }
        if (countOfElement == 0) {
            initCapacity = 10;
            elements = new Object[initCapacity];
        }
        elements[countOfElement] = element;
        countOfElement++;
    }

    /**
     * This method copy elements to new collection with new capacity
     */
    private void createNewSize() {
        initCapacity = countOfElement + initCapacity;
        elements = Arrays.copyOf(elements, initCapacity);
    }

    /**
     * This method return element from collection with input index or
     * throw IndexOutOfBoundsException if index not correct
     *
     * @param index index of element what you want get
     * @return element from collection
     * @throws IndexOutOfBoundsException incorrect index
     */
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * This method modified element of collection with input index to new value
     *
     * @param index   index of element what you want get
     * @param element new value
     * @throws IndexOutOfBoundsException incorrect index
     */
    public void set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
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

    /**
     * This method delete all empty elements from list
     */
    public void trimToSize() {
        if (countOfElement < elements.length) {
            elements = Arrays.copyOf(elements, countOfElement);
        }
    }

    /**
     * This method remove element from list with input index
     *
     * @param index input index of element
     * @return deleted element
     * @throws IndexOutOfBoundsException incorrect index
     */
    public E remove(int index) {
        E deletedElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, countOfElement - 1 - index);
        elements[countOfElement - 1] = null;
        countOfElement--;
        return deletedElement;
    }

    /**
     * This method delete all elements from list, collection have value == null
     */
    public void deleteAll() {
        elements = null;
        countOfElement = 0;
        initCapacity = 10;
    }

    /**
     * This method remove last element of list if list is empty
     * throw IndexOutOfBoundsException -1
     *
     * @return deleted element
     * @throws IndexOutOfBoundsException incorrect index
     */
    public E removeLast() {
        E deletedElement = get(countOfElement - 1);
        elements[countOfElement - 1] = null;
        countOfElement--;
        return deletedElement;
    }

    /**
     * This method check index to correct value
     *
     * @param index input value
     * @throws IndexOutOfBoundsException incorrect index
     */
    private void checkIndex(int index) {
        if ((index < 0) || (index >= countOfElement)) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
