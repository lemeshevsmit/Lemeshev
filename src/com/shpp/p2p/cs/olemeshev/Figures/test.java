package com.shpp.p2p.cs.olemeshev.Figures;

import acm.graphics.GOval;
import acm.graphics.GPolygon;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Scanner;

public class test extends WindowProgram {

    public void createForms(){
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();
        String last = scanner.next();
        int yea = scanner.nextInt();
        System.out.println(name.toLowerCase());
        System.out.println(last.toLowerCase());
        System.out.println(yea*10);
        scanner.close();
    }

    public void run() {

//        Circle c = new Circle(50, 50, 50, Color.BLUE);
//        add(c);
//
//
//        GPolygon p = new GPolygon();
//        p.addVertex(300, 300);
//        p.addVertex(300, 250);
//        p.addVertex(200, 300);
//        add(p);

//
//        Snowflake s = new Snowflake(300, 300, 200, 1, 1, Color.BLUE);
//        add(s);
//        while (true){
//            s.move(0,1);
//            s.rotate();
//            pause(100);
//        }

    }



    public String aggregateSingle(String name, String age, String planet){
        return "name - "+ name + ", age - "+ age + ", planet - "+ planet;
    }


    public String[] aggregateAll(String[] names, int[] ages, String[] planets){
        String[] s = new String[3];
        for (int i = 0; i< names.length; i++){
            s[i] = aggregateSingle(names[i], String.valueOf(ages[i]),planets[i]);
        }
        return s;
    }

    public String[] getPlanets(String galaxy){
        String s="";
        if ("Miaru".equals(galaxy)) {
            return new String[]{"Maux", "Reux", "Piax"};
        } else if ("Milkyway".equals(galaxy)) {
            return new String[]{"Earth", "Mars", "Jupiter"};
        } else if ("DangerBanger".equals(galaxy)) {
            return new String[]{"Fobius", "Demius"};
        }
        return new String[]{};
    }

    public String getMyPrizes(int ticket) {
        String result = "";
        if (ticket % 10 == 0) {
            result += "crystall";
        }

        if (ticket % 10 == 7) {
            result += " chip";
        }

        if (ticket > 200) {
            result += " coin";
        }

        return result.strip();
    }

}
