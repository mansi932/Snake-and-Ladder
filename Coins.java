package com.snakeladder;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Coins {
	int row, col;
	Icon icon;
	static int i = 0;
	boolean used = false;
	public static final Image[] icons = { Constants.BLUE,
			Constants.GREEN, Constants.YELLOW, Constants.PURPLE };

	public Coins(int players) {
		this.row = 0;
		this.col = 9;
		this.icon = new ImageIcon(icons[i++].getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		if (i == players)
			i = 0;
	}
}
