package com.shpp.p2p.cs.olemeshev.assignment12;

import acm.graphics.GImage;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class find count of silhouettes on input image
 * In big images(6000px) you need change stack size with command: -Xss128m
 * in Modify Run Configuration
 *
 * @author Aleksandr Lemeshev
 * @since 02.08.2022
 */
public class Assignment12Part1 {
    //background color
    private static Color BACKGROUND;
    // sensitivity of colors object and background
    private static final int SENSITIVITY = 100;
    // accuracy of trash in picture
    private static final double ACCURACY = 1.0;
    //total count of pixels in picture
    private static int TOTAL_PIXELS;

    /**
     * This method is start of program. I check all exception and start analyze
     * input image. I find background color, I do binarization  of image and
     * last find count of silhouettes.
     *
     * @param args input path to image
     */
    public static void main(String[] args) {
        try {
            if (args == null || Arrays.equals(args, new String[]{})) {
                args = new String[]{"test2.jpg"};
            }
            GImage image = new GImage(args[0]);
            int[][] pixels = image.getPixelArray();
            findBackgroundColor(pixels);
            boolean[][] binarImage = binarizationImage(pixels);
            System.out.println("Analyze image: <\u001B[33m" + args[0] +
                    "\u001B[0m> with total pixel count: \u001B[35m" + TOTAL_PIXELS +
                    "\u001B[0m\non  SENSITIVITY: \u001B[35m" + SENSITIVITY +
                    "\u001B[0m and ACCURACY: \u001B[35m" + ACCURACY + "\u001B[0m%\nfind: " +
                    "\u001B[32m " + findSilhouettes(binarImage) + " silhouettes." + "\u001B[0m");
        } catch (acm.util.ErrorException e) {
            System.err.println("File not find, please input correct file.");
        }
    }


    /**
     * This method find total count of pixel and background color.
     * I create list of colors from input image. Which color have max count pixel
     * I init it how background.
     *
     * @param pixels input matrix of pixel
     */
    private static void findBackgroundColor(int[][] pixels) {
        HashMap<Integer, Integer> colors = new HashMap<>();
        TOTAL_PIXELS = pixels.length * pixels[0].length;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (colors.containsKey(pixels[i][j])) {
                    colors.put(pixels[i][j], colors.get(pixels[i][j]) + 1);
                } else colors.put(pixels[i][j], 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : colors.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                BACKGROUND = new Color(entry.getKey());
            }
        }
        System.out.println("Image background color is: " +
                ColorTextName.getColorNameFromRgb(BACKGROUND));
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
    private static int findSilhouettes(boolean[][] pixels) {
        short countOfSilhouettes = 0;
        boolean[][] way = new boolean[pixels.length][pixels[0].length];
        for (short i = 0; i < pixels.length; i++) {
            for (short j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] && !way[i][j]) {
                    if (DFS8(pixels, way, i, j) / TOTAL_PIXELS * 100 >= ACCURACY) {
                        countOfSilhouettes++;
                    }
                } else way[i][j] = true;
            }
        }
        return countOfSilhouettes;
    }


    /**
     * This method is Depth First Search (DFS) Algorithm. I check all 8 pixels around input
     * position and if I can go to next pixel I call this method again with new position
     *
     * @param pixels matrix with input silhouettes
     * @param way    true/false way where I be in previously iteration
     * @param pos_x  actual position of X coordinate
     * @param pos_y  actual position of Y coordinate
     * @return count of pixel in silhouettes
     */
    private static double DFS8(boolean[][] pixels, boolean[][] way, short pos_x, short pos_y) {
        byte left = (byte) -1, right = (byte) 1, top = (byte) -1, down = (byte) 1;
        int silhouettesPixels = 0;
        way[pos_x][pos_y] = true;
        silhouettesPixels++;
        for (byte i = top; i <= down; i++) {
            for (byte j = left; j <= right; j++) {
                if (pos_x + i >= (short) 0 && pos_x + i <= (short) (pixels.length - 1) &&
                        pos_y + j >= (short) 0 && pos_y + j <= (short) (pixels[0].length - 1)) {
                    if (pixels[pos_x + i][pos_y + j] && !way[pos_x + i][pos_y + j]) {
                        way[pos_x + i][pos_y + j] = true;
                        silhouettesPixels += DFS8(pixels, way, (short) (pos_x + i), (short) (pos_y + j));
                    }
                }
            }
        }
        return silhouettesPixels;
    }


    /**
     * This method create matrix with value [true,false], where true- silhouettes; false - background
     * I analyze color of actual pixel and background color
     *
     * @param pixels input matrix of pixel
     * @return binarization image
     */
    private static boolean[][] binarizationImage(int[][] pixels) {
        boolean[][] result = new boolean[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++)
            for (int j = 0; j < pixels[i].length; j++)
                result[i][j] = !(Math.abs(GImage.getRed(pixels[i][j]) - BACKGROUND.getRed()) < SENSITIVITY
                        & Math.abs(GImage.getGreen(pixels[i][j]) - BACKGROUND.getGreen()) < SENSITIVITY
                        & Math.abs(GImage.getBlue(pixels[i][j]) - BACKGROUND.getBlue()) < SENSITIVITY);
        return result;
    }
}
