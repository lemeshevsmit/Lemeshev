package com.shpp.p2p.cs.olemeshev.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * In this task we build weekend morning exercises information
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part1 extends TextProgram {

    /* this is count of cardio and  blood-pressure days which we need do in week */
    private int cardioDays = 5;
    private int bloodPressureDays = 3;

    /**
     * Start of program. In this method we input information, check
     * input data for norm of weekend exercises and print result. I use
     * cycle 'foreach' and see all days of week, if time is good I
     * decrement our (cardio/blood-pressure) days.
     */
    public void run() {
        //in this massive we have minutes which we do in day
        double[] days = new double[7];

        // input information
        println("Weekend graphics of my morning exercises:");
        for (int i = 0; i < 7; i++) {
            print("How many minutes did you do on day " + (i + 1) + "? ");
            days[i] = readDouble();
            //check stupid
            if (days[i] < 0.0) {
                println("Please input correct number.");
                return;
            }
        }
        //find have many days need to good week
        calculateWeekendNormOfExercises(days);
        //print result
        printResultOfWeek();
    }


    /**
     * This method find have many days need to good week.
     * If we spend time more then 40 min in day we do norm of
     * doctor recommendation and decrement count to our days.
     * If we spend time more then 30 min but less 40 min in day
     * then we decrement count only cardio days.
     *
     * @param days massive with minutes
     */
    private void calculateWeekendNormOfExercises(double[] days) {
        for (double minutesInDay : days) {
            if (minutesInDay >= 40) {
                cardioDays--;
                bloodPressureDays--;
            } else if (minutesInDay >= 30) {
                cardioDays--;
            }
        }
    }

    /**
     * This method print result of weekend morning exercises.
     */
    private void printResultOfWeek() {
        String greatJob = "   Great job! You've done enough exercise ";
        String needed = "   You needed to train hard for at least ";
        String ends = " more day(s) a week!";
        String cardio = "Cardiovascular health:";
        String bloodPressure = "Blood pressure:";
        String forCardio = "for cardiovascular health.";
        String forBloodPressure = "to keep a low blood pressure.";
        //print text
        printResult(cardio, cardioDays, greatJob + forCardio, needed, ends);
        printResult(bloodPressure, bloodPressureDays, greatJob + forBloodPressure, needed, ends);
    }

    /**
     * This method print text with parameters. If we do limit in week we
     * have positive result else - negative
     *
     * @param intro    intro text
     * @param days     days for check limit
     * @param positive positive text
     * @param negative negative text
     * @param ends     ends of negative text
     */
    private void printResult(String intro, int days, String positive, String negative, String ends) {
        println(intro);
        if (days <= 0) {
            println(positive);
        } else {
            println(negative + days + ends);
        }
    }
}
