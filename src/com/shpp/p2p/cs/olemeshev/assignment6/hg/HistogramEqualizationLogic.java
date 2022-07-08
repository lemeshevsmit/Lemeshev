package com.shpp.p2p.cs.olemeshev.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;
    private static final int SIZE_OF_ARRAY = 256;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] result = new int[SIZE_OF_ARRAY];
        for (int[] luminance : luminances)
            for (int i : luminance)
                result[i]++;
        return result;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] result = new int[SIZE_OF_ARRAY];
        int firstElement = 0;
        result[firstElement] = histogram[firstElement];
        if (histogram.length > 1)
            for (int j = 1; j < histogram.length; j++)
                result[j] = result[j - 1] + histogram[j];
        return result;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        int totalPixels = totalPixelsIn(luminances);
        int[] histogram = histogramFor(luminances);
        int[] cumulative = cumulativeSumFor(histogram);
        int[][] result = new int[luminances.length][luminances[0].length];
        for (int row = 0; row < luminances.length; row++)
            for (int col = 0; col < luminances[row].length; col++)
                result[row][col] = MAX_LUMINANCE * cumulative[luminances[row][col]] / totalPixels;
        return result;
    }
}
