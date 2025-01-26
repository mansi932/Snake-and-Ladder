package com.snakeladder;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class Pieces {
    public static final Image[] icons = { Constants.BLUE,
            Constants.GREEN, Constants.YELLOW, Constants.PURPLE };
    private static int i = 0;
    int index = 0;
    Icon icon;

    Pieces(int players) {
        icon = new ImageIcon(icons[i++].getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        if (i == players)
            i = 0;
    }
}
