package com.shpp.p2p.cs.olemeshev.traine;

import java.util.Iterator;
import java.util.Random;

/**
 * @author anatolii vakaliuk
 */
public class IteratorDemo {

    public static void main(String[] args) {
        for (Integer randomNumber : new RandomSource(20)) {
            System.out.println("Next random number=" + randomNumber);
        }
    }

    private static final class RandomSource implements Iterable<Integer> {

        private Random random = new Random();

        private int size;

        public RandomSource(int size) {
            this.size = size;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int currentElement = 0;
                @Override
                public boolean hasNext() {
                    System.out.println("in hasNext");
                    return currentElement < size;
                }

                @Override
                public Integer next() {
                    System.out.println(" next");
                    currentElement++;
                    return random.nextInt();
                }
            };
        }
    }

}
