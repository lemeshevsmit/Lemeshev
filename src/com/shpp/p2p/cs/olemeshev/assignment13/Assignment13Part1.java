package com.shpp.p2p.cs.olemeshev.assignment13;

import acm.graphics.GImage;

import java.awt.*;
import java.util.Queue;
import java.util.*;
import java.util.stream.IntStream;

/**
 * This class find count of silhouettes on input image with BFS algorithm
 *
 * @author Aleksandr Lemeshev
 * @since 11.08.2022
 */
public class Assignment13Part1 {
    //background color
    private static Color background;
    // sensitivity of colors object and background [1..254]
    private static final int SENSITIVITY = 100;
    // accuracy of trash in picture [0..100] in %
    private static final double ACCURACY = 0.1;
    //count of erosion image (delete 1 pixel from circuit of silhouette)
    private static final int EROSION_COUNT = 0;
    //total count of pixels in picture
    private static int totalPixels;
    //silhouettes constant
    private static final int ONE = 1;
    //background constant
    private static final int ZERO = 0;

    /**
     * This method is start of program. I check all exception and start analyze
     * input image. I find background color, I do binarization  of image and
     * last find count of silhouettes.
     *
     * @param args input path to image
     */
    public static void main(String[] args) {
        try {
            long now = System.currentTimeMillis();
            if (args == null || Arrays.equals(args, new String[]{})) {
                args = new String[]{"test.jpg"};
            }
            GImage image = new GImage(args[0]);
            String fileType = args[0].toLowerCase().substring(args[0].lastIndexOf('.') + 1);
            int[][] pixels = image.getPixelArray();
            modificateImage(fileType, pixels);
            System.out.println("Analyze image: <\u001B[35m" + args[0] + "\u001B[0m> with "
                    + "total pixel count: \u001B[35m" + totalPixels + "\u001B[0m on SENSITIVITY:"
                    + " \u001B[35m" + SENSITIVITY + "\u001B[0m and ACCURACY: \u001B[35m" + ACCURACY
                    + "\u001B[0m % with count of erosion pixels: \u001B[35m" + EROSION_COUNT + "\u001B[0m "
                    + "\nFind \u001B[32m" + findSilhouettes(pixels) + " silhouettes" + "\u001B[0m on <\u001B[35m"
                    + ColorTextName.getColorName(background) + "\u001B[0m> background color with time of search: "
                    + "\u001B[35m" + (System.currentTimeMillis() - now) / 1000.0 + "\u001B[0m sec");
        } catch (acm.util.ErrorException e) {
            System.err.println("File not find, please input correct file.");
        }
    }

    /**
     * This method calculate color of background, do binarization of image
     * and morphological operations with binary images.
     *
     * @param fileType type of input file with image
     * @param pixels   input matrix of pixel
     */
    private static void modificateImage(String fileType, int[][] pixels) {
        boolean pngKay = fileType.equals("png");
        totalPixels = pixels.length * pixels[0].length;
        findBackgroundColor(pixels, pngKay);
        if (EROSION_COUNT != 0) {
            binarizationImage(pixels, background);
            for (int i = 0; i < EROSION_COUNT; i++) {
                erosionImage(pixels);
            }
        } else {
            binarizationImage(pixels, background);
        }
    }

    /**
     * This method delete one pixel from circuit of silhouette. I check all 8 pixel
     * around input. If I found pixel I mark him and add to result array.
     * This is one of morphological operations on binary images. Dilation and Erosion.
     * I do erosion of BLACK color(ONE after binarization)
     *
     * @param pixels input matrix of pixel
     */
    private static void erosionImage(int[][] pixels) {
        int[][] result = new int[pixels.length][pixels[0].length];
        int left = -1, right = 1, top = -1, down = 1;
        for (int i = 0; i < pixels.length; i++)
            for (int j = 0; j < pixels[0].length; j++) {
                if (pixels[i][j] == ONE) {
                    boolean flag = false;
                    for (int k = i + top; k <= i + down & !flag; k++)
                        for (int n = j + left; n <= j + right & !flag; n++) {
                            if (k >= 0 && k < pixels.length && n >= 0 && n < pixels[0].length) {
                                if (pixels[k][n] != ONE) {
                                    flag = true;
                                    result[k][n] = ZERO;
                                }
                            }
                        }
                    if (!flag) result[i][j] = ONE;
                } else result[i][j] = ZERO;
            }
        //modification pixels array
        IntStream.range(0, pixels.length)
                .forEach(i -> System.arraycopy(result[i], 0, pixels[i], 0, result[0].length));
    }


    /**
     * This method find total count of pixel and background color. I create list of colors
     * from input image. Which color have max count pixel than I, init it how background,
     * but if this color not equals with first pixel I change it to new color
     *
     * @param pixels input matrix of pixel
     * @param pngKay input kay to png format of image
     */
    private static void findBackgroundColor(int[][] pixels, boolean pngKay) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //find all colors in picture and calculate count of him
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                int color = pixels[i][j];
                map.put(color, map.containsKey(color) ? map.get(color) + 1 : 1);
            }
        }
        int max = 0; //find color with max count
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                background = new Color(entry.getKey(), pngKay);
            }
        }
        //swap colors if first pixel is different
        Color firstColor = new Color(pixels[0][0], pngKay);
        if (!firstColor.equals(background)) {
            background = firstColor;
        }
    }


    /**
     * This method find count of silhouettes in picture. I create array of way and
     * check all pixel to find start of silhouettes. Then I use method DFS to find
     * count of pixel of silhouette and analyze it with value of ACCURACY
     * If all is good I increment count of silhouettes
     *
     * @param pixels input matrix of pixel
     * @return count of silhouettes
     */
    private static int findSilhouettes(int[][] pixels) {
        short countOfSilhouettes = 0;
        boolean[][] way = new boolean[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] == ONE && !way[i][j]) {
                    if (BFS8(pixels, way, i, j) / totalPixels * 100 >= ACCURACY) {
                        countOfSilhouettes++;
                    }
                } else way[i][j] = true;
            }
        }
        return countOfSilhouettes;
    }

    /**
     * This method is Breadth First Search (BFS) Algorithm. I check all 8 pixels around input
     * position and if I can go to next pixel I add to queue this pixel and continue check
     * next pixels. After I take first pixel from queue while I can do that.
     *
     * @param pixels matrix with input silhouettes
     * @param way    true/false way where I be in previously iteration
     * @param row    actual position of X coordinate
     * @param col    actual position of Y coordinate
     * @return count of pixel in silhouettes
     */
    private static double BFS8(int[][] pixels, boolean[][] way, int row, int col) {
        int left = -1, right = 1, top = -1, down = 1;
        Queue<Pixel> indexList = new ArrayDeque<>();
        int silhouettesPixels = 0;
        way[row][col] = true;
        indexList.add(new Pixel(row, col));
        while (!indexList.isEmpty()) {
            Pixel pixel = indexList.poll();
            int pos_x = pixel.x, pos_y = pixel.y;
            silhouettesPixels++;
            for (int i = top; i <= down; i++)
                for (int j = left; j <= right; j++) {
                    if (pos_x + i >= 0 && pos_x + i <= pixels.length - 1
                            && pos_y + j >= 0 && pos_y + j <= pixels[0].length - 1) {
                        if (pixels[pos_x + i][pos_y + j] == ONE && !way[pos_x + i][pos_y + j]) {
                            way[pos_x + i][pos_y + j] = true;
                            indexList.add(new Pixel(pos_x + i, pos_y + j));
                        }
                    }
                }
        }
        return silhouettesPixels;
    }

    /**
     * Inner class to save coordinate [x;y] pixels of image. I use record.
     */
    record Pixel(int x, int y) {
    }

    /**
     * This method create matrix with value [0;1], where ONE - silhouettes; ZERO - background
     * I analyze color of actual pixel and input color if his difference more than SENSITIVITY
     * I modificate this pixel to ONE or ZERO
     *
     * @param pixels input matrix of pixel
     * @param color  color to binarization
     */
    private static void binarizationImage(int[][] pixels, Color color) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (!(Math.abs(GImage.getRed(pixels[i][j]) - color.getRed()) < SENSITIVITY
                        & Math.abs(GImage.getGreen(pixels[i][j]) - color.getGreen()) < SENSITIVITY
                        & Math.abs(GImage.getBlue(pixels[i][j]) - color.getBlue()) < SENSITIVITY
                        & Math.abs(GImage.getAlpha(pixels[i][j]) - color.getAlpha()) < SENSITIVITY)) {
                    pixels[i][j] = ONE;
                } else {
                    pixels[i][j] = ZERO;
                }
            }
        }
    }
}
