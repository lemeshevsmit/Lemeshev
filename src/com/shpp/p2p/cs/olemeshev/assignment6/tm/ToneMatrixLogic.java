package com.shpp.p2p.cs.olemeshev.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples, double[][] bass) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        double max = 0;
        int countOfActiveCells = 0;
        for (int i = 0; i < toneMatrix.length; i++)
            //find active cell
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    //concat sound
                    result[j] += samples[i][j];
                    // find max intensity
                    max = Math.max(max, Math.abs(result[j]));
                }
                countOfActiveCells++;
            }
        //normalized
        if ((countOfActiveCells > 0) && (max > 1))
            for (int i = 0; i < result.length; i++)
                result[i] /= max;
        return result;
    }
}

//        //test
//        for (double d : result) {
//            if (Math.abs(d) > 1) System.out.println("test fall. Have" + d);
//        }

//        //test
//        double test = 0;
//        for (double d : result) {
//            test = Math.max(Math.abs(d), test);
//        }
//        System.out.println("Max: " + test);