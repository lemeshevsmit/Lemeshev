package com.shpp.p2p.cs.olemeshev.collections;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(list.size() - 1));
        System.out.println(list);
        System.out.println(list.remove(3));
        System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list);


//        String name = "k";
//        if (name.length()>1){
//            char c = name.charAt(0);
//            char c2 = name.charAt(name.length()-1);
//            String s = "" + c + c2;
//            System.out.println( s.toUpperCase());
//        }
//        else System.out.println( name.toUpperCase());
    }
}
