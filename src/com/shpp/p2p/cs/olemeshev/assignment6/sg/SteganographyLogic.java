package com.shpp.p2p.cs.olemeshev.assignment6.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
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
            for (int col = 0; col < pixelsMain[row].length; col++)
                result[row][col] = GImage.getRed(pixelsMain[row][col]) % 2 != 0;
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
        int[][] pixels = source.getPixelArray();
        for (int row = 0; row < pixels.length; row++)
            for (int col = 0; col < pixels[row].length; col++) {
                // Extract color components
                int red = GImage.getRed(pixels[row][col]);
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);
                //white color(false)
                if (!message[row][col]) {
                    if (red % 2 != 0) red--;
                } else {  //black color (true)
                    if (red % 2 == 0) red++;
                }
                pixels[row][col] = GImage.createRGBPixel(red, green, blue);
            }
        return new GImage(pixels);
    }

//    /**
//     * This method hide black-white picture in color picture
//     *
//     * @param hideImage secret picture
//     * @param mainImage main picture
//     * @return massive of secret values
//     */
//    public static boolean[][] codePicture(GImage hideImage, GImage mainImage) {
//        //hideImage = new GImage("assets/hide.jpg");
//        //mainImage = new GImage("assets/main.jpg");
//        int[][] pixelsHide = hideImage.getPixelArray();
//        int[][] pixelsMain = mainImage.getPixelArray();
//        boolean[][] result = new boolean[pixelsHide.length][pixelsHide[0].length];
//
//        for (int row = 0; row < pixelsMain.length; row++)
//            for (int col = 0; col < pixelsMain[row].length; col++) {
//                // Extract red-color component
//                int redMain = GImage.getRed(pixelsMain[row][col]);
//                int greenMain = GImage.getGreen(pixelsMain[row][col]);
//                int blueMain = GImage.getBlue(pixelsMain[row][col]);
//                int redHide = GImage.getRed(pixelsHide[row][col]);
//                int greenHide = GImage.getGreen(pixelsHide[row][col]);
//                int blueHide = GImage.getBlue(pixelsHide[row][col]);
//                //white color
//                if ((redHide == 255) && (greenHide == 255) && (blueHide == 255)) {
//                    result[row][col] = false;
//                    if (redMain % 2 == 0)
//                        pixelsMain[row][col] = GImage.createRGBPixel(redMain - 1, greenMain, blueMain);
//                }
//                //black color
//                if ((redHide == 0) && (greenHide == 0) && (blueHide == 0)) {
//                    result[row][col] = true;
//                    if (redMain % 2 != 0)
//                        pixelsMain[row][col] = GImage.createRGBPixel(redMain + 1, greenMain, blueMain);
//                }
//            }
//        return result;
//    }
}
