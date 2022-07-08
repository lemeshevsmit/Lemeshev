package com.shpp.p2p.cs.olemeshev.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * This class build russian army animation and we play mini game kill them.
 *
 * @author Alexandr Lemeshev
 * @since 30.05.2022
 */
public class Assignment3Part6 extends WindowProgram {

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 700;

    // Each flag is rectangle with this width and height.
    private static final double FLAG_WIDTH = 800;
    private static final double FLAG_HEIGHT = 600;

    // Massive of colors of flag Ukraine
    private static final Color[] COLOR_UKRAINE = {
            new Color(20, 50, 255),
            new Color(255, 250, 50)};


    // Each tank is rectangle with this width and height.
    private static final double TANK_WIDTH = 80;
    private static final double TANK_HEIGHT = 40;

    // Count of tanks
    private static final int TANK_COUNT = 6;

    // this is speed of tank, 4px to 1 iteration of cycle while
    private static final double TANK_SPEED = 4;

    // Each helicopter is oval with this width and height.
    private static final double HELICOPTER_WIDTH = 110;
    private static final double HELICOPTER_HEIGHT = 40;

    // Count of helicopters
    private static final int HELICOPTER_COUNT = 3;

    // this is speed of helicopter, 10px to 1 iteration of cycle while
    private static final double HELICOPTER_SPEED = 10;

    // The amount of time to pause between frames (50fps).
    private static final double PAUSE_TIME = 1000.0 / 50;

    // time of animation, default value is 4 sec
    private static final double TIME_OF_ANIMATION = 14;

    // fonts constant: type(name) and size
    private static final String FONTS_NAME = "Source Sans Pro Black";
    private static final int FONT_SIZE = 68;

    // label with score of game
    private GLabel score = null;

    // variable with count of score
    private int countOfKill = 0;

    // auto game == true; player game == false
    private final boolean autopilot = true;

    /**
     * Start method. In this method we print flag with text, wait 1 sec,
     * remove all object and start game.When we start game we turn on
     * method mouseListener, because we need kill technics, I display score
     * label and print enemy army. Then we play 4 sec( it's default ). Then
     * I clear application and display final text.
     */
    public void run() {
        try {
            printFlag();
            printText("ДОБРОГО ВЕЧОРА", "МИ З УКРАЇНИ!");
            Thread.sleep(1000);
            removeAll();
            if (!autopilot) {
                addMouseListeners();
            }
            addLabel();
            printArmy();
            removeAll();
            printText("ГРА ЗАВЕРШЕНА", "РЕЗУЛЬТАТ: " + countOfKill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is bot. We have random coordinate x and y, then we
     * call methods findObjectWithRemove(x,y) and drawFire(x, y)
     */
    private void autoGame() {
        double x = Math.random() * getWidth();
        double y = Math.random() * getWidth();
        findObjectWithRemove(x, y);

//        double x = 0.0;
//        double y = 0.0;
//        //count of shoot if play bot
//        int countOfShoot = 10;
//        for (int i = 0; i < countOfShoot; i++) {
//            x = Math.random() * getWidth();
//            y = Math.random() * getWidth();
//            findObjectWithRemove(x, y);
//            drawFire(x, y);
//        }
    }

    /**
     * This method listening when we click mouse. In this method I remove
     * object if I click for him. Increment score point and draw fire
     * in position where I click.
     *
     * @param mouseEvent mouse parameters on screen
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        findObjectWithRemove(x, y);
    }

    /**
     * This method find object in (x;y) coordinate and remove him.
     * If object be delete add score on label. If I kill draw red fire
     * else
     *
     * @param x coordinate x
     * @param y coordinate y
     */
    private void findObjectWithRemove(double x, double y) {
        GObject comp = getElementAt(x, y);
        //we don't hit specific targets(GLine and GLabel)
        if ((comp != null)
                && (!comp.getClass().getName().equals("acm.graphics.GLine"))
                && (!comp.getClass().getName().equals("acm.graphics.GLabel"))) {
            remove(comp);
            countOfKill++;
            score.setLabel("SCORE: " + countOfKill);
            drawFire(x, y, Color.RED);
        } else {
            drawFire(x, y, Color.YELLOW);
        }
    }

    /**
     * This method draw fire.  It's lines from one center to circle with radius r
     * I build with interval 30 degree
     *
     * @param x     coordinate x
     * @param y     coordinate y
     * @param color color of fire
     */
    private void drawFire(double x, double y, Color color) {
        GLine line;
        //radius of fire
        double r = 20.0;
        for (double i = 0.0; i < 360.0; i += 30.0) {
            line = new GLine(x, y, r * Math.cos(Math.toRadians(i)) + x, r * Math.sin(Math.toRadians(i)) + y);
            line.setColor(color);
            add(line);
            line.sendToBack();
        }
    }

    /**
     * This method draw enemy army. (5 tanks and 2 helicopter I use
     * class tank and helicopter). Then I start animation to 4 sec.
     */
    private void printArmy() {
        //collection's of my object
        ArrayList<Tank> tanksList = new ArrayList<>(TANK_COUNT);
        ArrayList<Helicopter> helicoptersList = new ArrayList<>(HELICOPTER_COUNT);
        int startX = 0;
        int startY = 0;
        int minSpaceWithTechnics = 20;

        for (int i = 0; i < TANK_COUNT; i++) {
            // random number [-300;100)
            startX = (int) (Math.random() * 400) - 300;
            // random number [startY;startY + TANK_HEIGHT)
            startY = startY + (int) (Math.random() * (startY + TANK_HEIGHT));
            tanksList.add(new Tank(TANK_WIDTH, TANK_HEIGHT));
            add(tanksList.get(i), startX, startY + i * TANK_HEIGHT + minSpaceWithTechnics);
        }

        startY = 0;
        for (int i = 0; i < HELICOPTER_COUNT; i++) {
            helicoptersList.add(new Helicopter(HELICOPTER_WIDTH, HELICOPTER_HEIGHT));
            // random number [-300;100)
            startX = (int) (Math.random() * 400) - 300;
            // random number [startY;startY + HELICOPTER_HEIGHT)
            startY = startY + (int) (Math.random() * (startY + HELICOPTER_HEIGHT));
            helicoptersList.add(new Helicopter(HELICOPTER_WIDTH, HELICOPTER_HEIGHT));
            add(helicoptersList.get(i), startX, startY + i * HELICOPTER_HEIGHT + minSpaceWithTechnics);
        }
        //start animation
        doAnimation(tanksList, helicoptersList);
    }


    /**
     * I use cycle 'while(true)' and move tanks with tank speed
     * to horizontal line. Helicopters I move with more speed and
     * they have different trajectory (change y coordinate by 3 px)
     * If we play 4 sec cycle end.
     *
     * @param tanks       list with Tank objects
     * @param helicopters list with Helicopter objects
     */
    private void doAnimation(ArrayList<Tank> tanks, ArrayList<Helicopter> helicopters) {
        LocalDateTime startTime = LocalDateTime.now();
        int orientation;
        do {
            for (Tank tank : tanks) {
                tank.move(TANK_SPEED, (int) (Math.random() * 3) - 1);
            }
            for (int i = 0; i < helicopters.size(); i++) {
                orientation = (i % 2 == 0) ? 3 : -3;
                helicopters.get(i).move(HELICOPTER_SPEED, orientation);
            }
            pause(PAUSE_TIME);
            //play bot if autopilot == true
            if (autopilot) {
                autoGame();
            }
        } while (!(ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()) >= TIME_OF_ANIMATION));
    }

    /**
     * This method build flag of Ukraine. It's from assignment2
     */
    private void printFlag() {

        //find start position of flag
        double startPointX = (getWidth() - FLAG_WIDTH) / 2.0;
        double startPointY = (getHeight() - FLAG_HEIGHT) / 2.0;

        //Find height og one element of flag.
        double heightOfLine = FLAG_HEIGHT / 2.0;

        //build flag
        GRect flagRectangle;
        for (int i = 0; i < 2; i++) {
            flagRectangle = new GRect(startPointX, startPointY, FLAG_WIDTH, heightOfLine);
            flagRectangle.setColor(COLOR_UKRAINE[i]);
            flagRectangle.setFillColor(COLOR_UKRAINE[i]);
            flagRectangle.setFilled(true);
            startPointY += heightOfLine;
            add(flagRectangle);
        }
    }

    /**
     * This method draw two label with text on middle of screen
     *
     * @param text1 string text
     * @param text2 string text
     */
    private void printText(String text1, String text2) {
        GLabel label1 = new GLabel(text1);
        GLabel label2 = new GLabel(text2);
        label1.setFont(FONTS_NAME + "-" + FONT_SIZE);
        label2.setFont(FONTS_NAME + "-" + FONT_SIZE);
        add(label1, (getWidth() - label1.getWidth()) / 2.0, (getHeight() - 1.5 * label1.getHeight()) / 2.0);
        add(label2, (getWidth() - label2.getWidth()) / 2.0, (getHeight() + 3 * label2.getHeight()) / 2.0);
    }

    /**
     * This method add label with score point to up of application
     */
    private void addLabel() {
        score = new GLabel("SCORE: ", 30, 30);
        add(score);
    }

}
