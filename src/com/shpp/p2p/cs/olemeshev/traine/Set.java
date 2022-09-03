package com.shpp.p2p.cs.olemeshev.traine;

import java.util.*;
import java.util.function.Function;

public class Set<T> implements java.util.Set<T> {

    private final java.util.Set<T> set = new HashSet<>();

    public static Set<Integer> of(int start, int end) {
        return Set.of(start, end, value -> value + 1);
    }

    public static Set<Float> of(float start, float end) {
        return Set.of(start, end, value -> value + 0.1f);
    }

    public static Set<Double> of(double start, double end) {
        return Set.of(start, end, value -> value + 0.1);
    }

     static <T extends Comparable> Set<T> of(T start, T end, Function<T, T> function) {
        Set<T> range = new Set<>();
        if (start.equals(end)) {
            return range;
        }
        while (start.compareTo(end) <= 0) {
            range.add(start);
            start = function.apply(start);
        }
        return range;
    }


    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean contains(Object o) {
        return set.contains(o);
    }

    public Iterator<T> iterator() {
        return set.iterator();
    }

    public Object[] toArray() {
        return set.toArray();
    }

    public <T1> T1[] toArray(T1[] a) {
        return set.toArray(a);
    }

    public boolean add(T t) {
        return set.add(t);
    }

    public boolean remove(Object o) {
        return set.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return set.addAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    public void clear() {
        set.clear();
    }
}