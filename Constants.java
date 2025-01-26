package com.snakeladder;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Constants {
    public static final String TITLE = "Snake & Ladder";
    public static final Image ICON = new ImageIcon(Constants.class.getResource("./Assets/Icon.png")).getImage();
    public static final Image BLUE = new ImageIcon(Constants.class.getResource("./Assets/PlayerImages/Blue.png"))
            .getImage();
    public static final Image GREEN = new ImageIcon(Constants.class.getResource("./Assets/PlayerImages/Green.png"))
            .getImage();
    public static final Image YELLOW = new ImageIcon(Constants.class.getResource("./Assets/PlayerImages/Yellow.png"))
            .getImage();
    public static final Image PURPLE = new ImageIcon(Constants.class.getResource("./Assets/PlayerImages/Purple.png"))
            .getImage();
    public static final Color TEXT_COLOR = Color.decode("#077b8a");
    public static final Color FOREGROUND = Color.decode("#cbf6db");
    public static final Color BACKGROUND = Color.decode("#ed3572");
    public static final Color FRAME_BACKGROUND = Color.decode("#ecc19c");
    public static final Color RADIO_BUTTON_FOREGROUND = Color.decode("#786c3b");
    public static final Color TEXT_FIELD_FOREGROUND = Color.decode("#3498db");
    public static final Color TEXT_FIELD_BORDER = Color.decode("#2e4053");
    public static final Color GREEN_COLOR = Color.decode("#50C878");
    public static final Color PURPLE_COLOR = Color.decode("#800080");
    public static final Color SNAKE_COLOR = Color.RED;
    public static final Color LADDER_COLOR = Color.BLUE;
    public static final Color ODD_LABEL_COLOR = Color.decode("#FFFDD0");
    public static final Color EVEN_LABEL_COLOR = Color.YELLOW;
    public static final int ANIMATION_DURATION = 2000;
    public static final String NUMBER_FONT = "Bahnschrift SemiLight";
    public static final String TEXT_FONT = "Verdana";
    public static final String BUTTON_FONT = "Consolas";

    public static void addEnter(JFrame frame, JButton button, int keyCode) {
        frame.requestFocus();
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == keyCode)
                    button.doClick();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
    }

    public static void addEnter(JFrame frame, JButton button) {
        addEnter(frame, button, KeyEvent.VK_ENTER);
    }
}
