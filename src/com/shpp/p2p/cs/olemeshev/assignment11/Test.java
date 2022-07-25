package com.shpp.p2p.cs.olemeshev.assignment11;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String[][] correctTests = new String[][]{
                {"1+(2+3*(4+5-sin(45*cos(a))))/7", "a=(1+3)-1"},
                {"sqrt(2^3)"},
                {"sqrt(9*sin(pi/2))+ cos(pi) - log2(64)*log10(10) + 2^(sqrt(x))", "pi=" + Math.PI, "x = 9"}
        };
        String[] correctAnswers = new String[]{
                "5.373176543474313",
                "2.8284271247461903",
                "4.0"
        };

        String[][] incorrectTests = new String[][]{
                {"sqrt(-5)"},
                {"log2(-50)"},
                {"log10(-30)"},
                {"tan(pi/2)", "pi=" + Math.PI},
                {"3/cos(pi/2)", "pi=" + Math.PI}
        };
        String[] incorrectAnswers = new String[]{
                "Negative number=-5.0 in function [sqrt]. Please, check input value.",
                "Negative number=-50.0 in function [log]. Please, check input value.",
                "Negative number=-30.0 in function [log]. Please, check input value.",
                "Function [tan] doesn't exist in number=PI/2 in peroid PI. Please, check input value.",
                ""
        };

        testing(correctTests, correctAnswers);
        testing(incorrectTests, incorrectAnswers);


        //start();
    }

    static void start() {
        String[] test = new String[]{"1 + a * 2", "t =tr"};
        Assignment11Part1.main(test);
    }

    /**
     * It's test method.
     *
     * @param tests   arrays with tests
     * @param answers array with answers
     */
    private static void testing(String[][] tests, String[] answers) throws FileNotFoundException {
        // запоминаю старую консоль
        PrintStream out = System.out;
        // создаю поток для записи в файл
        FileOutputStream file = new FileOutputStream("result.txt");
        //создаю поток
        BufferedOutputStream buffer = new BufferedOutputStream(file);
        // создаю консоль для вывода
        PrintStream console = new PrintStream(buffer, true);
        // меняю консоль на новую для вывода в файл.
        System.setOut(console);
        for (String[] test : tests) {
            Assignment11Part1.main(test);
        }
        System.setOut(out);

        FileOpener fileOpener = new FileOpener();
        ArrayList<String> result = fileOpener.openFile("result.txt");
        for (int i = 0; i < result.size(); i++) {
            String value = result.get(i);
            if (value.equals(answers[i])) {
                System.out.println("Test: " + Arrays.toString(tests[i]) + " PASSED!");
                System.out.println("Result: " + value);
            } else {
                System.out.println("Test: " + Arrays.toString(tests[i]) + " CRUSH!");
                System.out.println("You output: " + value);
                System.out.println("Expected: " + answers[i]);
            }
        }
    }
}
