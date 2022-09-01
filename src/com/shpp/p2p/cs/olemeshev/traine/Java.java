package com.shpp.p2p.cs.olemeshev.traine;

import java.util.*;
import java.util.stream.Collectors;

public class Java {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        System.out.println(Arrays.toString(array));
        reverse(array);
        System.out.println(Arrays.toString(array));

        //Annotation a = Java.class.getAnnotation(MyAnnotation.class);
        var anotation = Java.class.getAnnotation(MyAnnotation.class);
        System.out.println(anotation.method());
    }

    @MyAnnotation(method = "this method reverse array")
    private static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

    }

    private static void reverse(ArrayList<Integer> list) {
        int s = list.size();
        for (int i = 0; i < s / 2; i++) {
            int temp = list.get(i);
            list.set(i, list.get(s - 1 - i));
            list.set(s - 1 - i, temp);
        }
    }


    private <T> void reverse(List<T> list) {
        int s = list.size();
        for (int i = 0; i < s / 2; i++) {
            T temp = list.get(i);
            list.set(i, list.get(s - 1 - i));
            list.set(s - 1 - i, temp);
        }
    }


    public Map<String, User> createMap(List<User> list) {
        Map<String, User> map = new HashMap<String, User>();
        for (User u : list) {
            map.put(u.getEmail(), u);
        }
        //return map;
        return list.stream().collect(Collectors.toMap(User::getEmail, u -> u, (a, b) -> b));
    }

    boolean containsOne(List<User> users, User Target) {
        return users.stream().filter(u -> u.equals(Target)).count() == 1;
    }

}
