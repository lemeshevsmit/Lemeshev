package com.shpp.p2p.cs.olemeshev.assignment2;


import com.shpp.cs.a.console.TextProgram;

/**
 * This class find solution of quadratic equation
 *
 * @author Alexandr Lemeshev
 * @since 02.06.2022
 */
public class Assignment2Part1 extends TextProgram {

    /**
     * Start of program. We input parameter a, b,c and calculate
     * roots of quadratic equation with print result
     */
    public void run() {
        println("Input a,b,c to quadratic equation: a*(x^2) + b*x + c = 0");
        double a = readDouble("Please enter a:");
        //check stupid
        if (a == 0.0) {
            println("Please enter correct a!=0.0");
            return;
        }
        double b = readDouble("Please enter b:");
        double c = readDouble("Please enter c:");
        //calculate quadratic equation and print result
        calculateQuadraticEquation(a, b, c);
    }

    /**
     * This method calculate roots of quadratic equation and print result.
     * We find solution of quadratic equation: a*(x^2) + b*x + c = 0
     *
     * @param a parameter a
     * @param b parameter b
     * @param c parameter c
     */
    private void calculateQuadraticEquation(double a, double b, double c) {
        double x1, x2, discriminant;
        discriminant = b * b - 4.0 * a * c;
        if (discriminant < 0) {
            println("There are no real roots");
        } else {
            x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            if (discriminant == 0) {
                println("There is one root: " + x1);
            } else {
                println("There are two roots: " + x1 + " and " + x2);
            }
        }
    }
}
