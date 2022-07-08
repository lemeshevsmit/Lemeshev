package com.shpp.p2p.cs.olemeshev.assignment6.sg;

import acm.graphics.GImage;

public class SteganographyLogicExt {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixelsMain = source.getPixelArray();
        boolean[][] result = new boolean[pixelsMain.length][pixelsMain[0].length];
        for (int row = 0; row < pixelsMain.length; row++)
            for (int col = 0; col < pixelsMain[row].length; col++) {
                if ((GImage.getRed(pixelsMain[row][col]) % 2 != 0)
                        && (GImage.getGreen(pixelsMain[row][col]) % 2 != 0)
                        && (GImage.getBlue(pixelsMain[row][col]) % 2 != 0))
                    result[row][col] = true;
                result[row][col] = (GImage.getRed(pixelsMain[row][col]) % 2 == 0)
                        && (GImage.getGreen(pixelsMain[row][col]) % 2 == 0)
                        && (GImage.getBlue(pixelsMain[row][col]) % 2 == 0);
            }
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        return hideColorPic(source);
    }



    /**
     * This method hide color picture in color picture
     *
     * @param mainImage main picture
     * @return new code GImage
     */
    public static GImage hideColorPic(GImage mainImage) {
        GImage hideImage = new GImage("assets/hideColor.png");
        int[][] pixelsHide = hideImage.getPixelArray();
        int[][] pixelsMain = mainImage.getPixelArray();
        int[][] pic = mainImage.getPixelArray();

        for (int row = 0; row < pixelsMain.length; row++)
            for (int col = 0; col < pixelsMain[row].length; col++) {
                // Extract red-color component
                int redMain = GImage.getRed(pixelsMain[row][col]);
                int greenMain = GImage.getGreen(pixelsMain[row][col]);
                int blueMain = GImage.getBlue(pixelsMain[row][col]);
                int redHide = GImage.getRed(pixelsHide[row][col]);
                int greenHide = GImage.getGreen(pixelsHide[row][col]);
                int blueHide = GImage.getBlue(pixelsHide[row][col]);

                //white
                if ((redHide == 255) && (greenHide == 255) && (blueHide == 255)) {
                    redMain = (redMain % 2 != 0) ? redMain : redMain + 1;
                    greenMain = (greenMain % 2 != 0) ? greenMain : greenMain + 1;
                    blueMain = (blueMain % 2 != 0) ? blueMain : blueMain + 1;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //black
                if ((redHide == 0) && (greenHide == 0) && (blueHide == 0)) {
                    redMain = (redMain % 2 != 0) ? redMain - 1 : redMain;
                    greenMain = (greenMain % 2 != 0) ? greenMain - 1 : greenMain;
                    blueMain = (blueMain % 2 != 0) ? blueMain - 1 : blueMain;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //red
                if ((redHide == 255) && (greenHide == 0) && (blueHide == 0)) {
                    redMain = (redMain % 2 != 0) ? redMain + 1 : redMain;
                    greenMain = (greenMain % 2 != 0) ? greenMain : greenMain + 1;
                    blueMain = (blueMain % 2 != 0) ? blueMain : blueMain + 1;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //green
                if ((redHide == 0) && (greenHide == 255) && (blueHide == 0)) {
                    redMain = (redMain % 2 != 0) ? redMain : redMain + 1;
                    greenMain = (greenMain % 2 != 0) ? greenMain + 1 : greenMain;
                    blueMain = (blueMain % 2 != 0) ? blueMain : blueMain + 1;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //blue
                if ((redHide == 0) && (greenHide == 0) && (blueHide == 255)) {
                    redMain = (redMain % 2 != 0) ? redMain : redMain + 1;
                    greenMain = (greenMain % 2 != 0) ? greenMain : greenMain + 1;
                    blueMain = (blueMain % 2 != 0) ? blueMain + 1 : blueMain;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //yellow
                if ((redHide == 255) && (greenHide == 255) && (blueHide == 0)) {
                    redMain = (redMain % 2 != 0) ? redMain + 1 : redMain;
                    greenMain = (greenMain % 2 != 0) ? greenMain + 1 : greenMain;
                    blueMain = (blueMain % 2 != 0) ? blueMain : blueMain + 1;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //pink
                if ((redHide == 255) && (greenHide == 0) && (blueHide == 255)) {
                    redMain = (redMain % 2 != 0) ? redMain + 1 : redMain;
                    greenMain = (greenMain % 2 != 0) ? greenMain : greenMain + 1;
                    blueMain = (blueMain % 2 != 0) ? blueMain + 1 : blueMain;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                    continue;
                }

                //sky
                if ((redHide == 0) && (greenHide == 255) && (blueHide == 255)) {
                    redMain = (redMain % 2 != 0) ? redMain : redMain + 1;
                    greenMain = (greenMain % 2 != 0) ? greenMain + 1 : greenMain;
                    blueMain = (blueMain % 2 != 0) ? blueMain + 1 : blueMain;
                    pic[row][col] = GImage.createRGBPixel(redMain, greenMain, blueMain);
                }
            }
        return new GImage(pic);
    }
}
