package com.shpp.p2p.cs.olemeshev.assignment10;

import com.shpp.p2p.cs.olemeshev.assignment11.Assignment11Part1;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String[][] correctTests = new String[][]{
                {"1 + a * 2", "a = 2"},
                {"2*4-6/3+6+1/3-6+-1* -1"},
                {"1 + 3 + 5"},
                {"1,0-3.0-5"},
                {"1*3*5"},

                {"-1*-3*-5"},
                {"-1"},
                {"4/2*2+2^3+3--1-3+-5*2+10"},
                {"5*4/2"},
                {"5/4*2"},

                {"3^-1"},
                {"a^b+a*2-4*c a=2 b=3 c=1"},
                {"a^b/a*2-4*c*a a=2 b=3 x=0 c=1"},
                {"1+a*2", "a=-2.0D"},
                {"1+a*2 a=2*3^2"}
        };
        String[] correctAnswers = new String[]{
                "5.0", String.valueOf(7 + 1.0 / 3), "9.0", "-7.0", "15.0",
                "-15.0", "-1.0", "13.0", "10.0", "2.5",
                String.valueOf(Math.pow(3, -1)), "8.0", "0.0", "-3.0", "37.0"
        };

        String[][] incorrectTests = new String[][]{
                {},
                null,
                {"", "s=4"},

                {"1 + a* 2", "a = 2"},
                {"1 + a & 2", "a = 2"},
                {"1+a&2", "a = 2"},

                {"1 + a * 2", "b = 2"},
                {" a ^ b + a * 2 - 4 / c ", "a = 2", "b = 3 ", "c = 0"},
                {"1 + a * 2", "t =tr"},

                {"1 + a*", "t =tr"},
                {"1 + a*-5", "a =tr"},
                {"1 + 2.0*-5", "45"}
        };
        String[] incorrectAnswers = new String[]{
                "Formula is null or empty: []",
                "Formula is null or empty: null",
                "Formula is null or empty: [, s=4]",

                "5.0",
                "Unknown value in formula: For input string: \"a&2\"",
                "Unknown value in formula: For input string: \"a&2\"",

                "Unknown value in formula: For input string: \"a\"",
                "Divide by zero in formula.",
                "Unknown value in formula: For input string: \"a\"",

                "Unknown value in formula: For input string: \"a\"",
                "Unknown value in formula: For input string: \"tr\"",
                ""
        };

//        testing(correctTests, correctAnswers);
//        testing(incorrectTests, incorrectAnswers);


        start();
    }

    static void start() {
        String[] test = new String[]{"2*4-6/3+6+1/3-6+(-1)* (-1)"};
        com.shpp.p2p.cs.vkunitsyn.assignment10.Assignment10Part1.main(test);
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
            Assignment10Part1.main(test);
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
