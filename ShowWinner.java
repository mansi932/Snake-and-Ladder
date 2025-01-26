package com.snakeladder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class ShowWinner {
    public ShowWinner(String name, Color c) {
        Image ic = null;
        if (c.equals(Color.BLUE))
            ic = Constants.BLUE.getScaledInstance(100, 100,
                    Image.SCALE_SMOOTH);
        else if (c.equals(Constants.GREEN_COLOR))
            ic = Constants.GREEN.getScaledInstance(100, 100,
                    Image.SCALE_SMOOTH);
        else if (c.equals(Color.YELLOW))
            ic = Constants.YELLOW.getScaledInstance(100, 100,
                    Image.SCALE_SMOOTH);
        else if (c.equals(Constants.PURPLE_COLOR))
            ic = Constants.PURPLE.getScaledInstance(100, 100,
                    Image.SCALE_SMOOTH);
        var frame = new JFrame(Constants.TITLE);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        int frameWidth = 435, frameHeight = 400;
        var tk = Toolkit.getDefaultToolkit();
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setIconImage(Constants.ICON);
        var l1 = new JLabel("Winner is: ");
        l1.setForeground(Constants.TEXT_COLOR);
        l1.setBounds(20, 10, 300, 100);
        l1.setFont(new Font(Constants.TEXT_FONT, Font.PLAIN, 30));
        var l2 = new JLabel(name);
        l2.setBounds(225, 10, 300, 100);
        l2.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, 30));
        l2.setForeground(c);
        var l3 = new JLabel(new ImageIcon(ic));
        l3.setBounds(60, 93, 300, 150);
        var btn = new JButton("<html><u>P</u>lay Again</html>");
        btn.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 18));
        btn.setForeground(Constants.FOREGROUND);
        btn.setBackground(Constants.BACKGROUND);
        btn.setBounds(137, 250, 150, 50);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            frame.dispose();
            new InitializeGame();
        });
        var btn2 = new JButton("<html><u>S</u>ee Credits</html>");
        btn2.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 18));
        btn2.setForeground(Constants.FOREGROUND);
        btn2.setBackground(Constants.BACKGROUND);
        btn2.setBounds(137, 310, 150, 35);
        btn2.setFocusable(false);
        btn2.addActionListener(e -> {
            frame.dispose();
            new ShowCredits();
        });
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(btn);
        frame.add(btn2);
        Constants.addEnter(frame, btn, KeyEvent.VK_P);
        Constants.addEnter(frame, btn2, KeyEvent.VK_S);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ShowWinner("Abhi", Color.BLUE);
    }
}
