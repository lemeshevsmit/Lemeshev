package com.shpp.p2p.cs.olemeshev.assignment12;

import acm.graphics.GImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class find count of silhouette on picture
 *
 * @author Alexandr Lemeshev
 * @since 31.07.2022
 */
public class Assignment12Part1 {

    public static void main(String[] args) throws IOException {
        if (args == null || args[0].equals("")
                || Arrays.equals(args, new String[]{})) {
            args = new String[]{"test.jpg"};
        }
        GImage image = new GImage(args[0]);
        BufferedImage picture = ImageIO.read(new File(args[0]));
        System.out.println();
    }


//    void Labeling(BIT* img[], int* labels[])
//    {
//        int L = 1;
//        for(int y = 0; y < H; ++y)
//            for(int x = 0; x < W; ++x)
//                Fill(img, labels, x, y, L++);
//    }
//
//    void Fill(BIT* img[], int* labels[], int x, int y, int L)
//    {
//        if( (labels[x][y] == 0) & (img[x][y] == 1) )
//        {
//            labels[x][y] = L;
//            if( x > 0 )
//                Fill(img, labels, x – 1, y, L);
//            if( x < W — 1 )
//            Fill(img, labels, x + 1, y, L);
//            if( y > 0 )
//                Fill(img, labels, x, y — 1, L);
//            if( y < H — 1 )
//            Fill(img, labels, x, y + 1, L);
//        }
//    }
}