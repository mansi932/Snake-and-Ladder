package com.snakeladder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetPlayers {
    public SetPlayers(int players) {
        var frame = new JFrame(Constants.TITLE);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        int frameWidth = 400, frameHeight = players * 100 + (6 - players) * 25;
        var tk = Toolkit.getDefaultToolkit();
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setIconImage(Constants.ICON);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JTextField[] tf = new JTextField[players];
        Image[] images = { Constants.BLUE,
                Constants.GREEN, Constants.YELLOW,
                Constants.PURPLE };
        var label = new JLabel("Icon");
        label.setForeground(Constants.TEXT_COLOR);
        label.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, 20));
        label.setBounds(15, 0, 50, 45);
        frame.add(label);
        var label1 = new JLabel("Name");
        label1.setBounds(175, 0, 100, 45);
        label1.setForeground(Constants.TEXT_COLOR);
        label1.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, 20));

        frame.add(label1);
        for (int i = 0; i < players; i++) {
            var l = new JLabel(new ImageIcon(images[i].getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            l.setBounds(10, 50 + 75 * i, 50, 50);
            tf[i] = new JTextField();
            tf[i].setBounds(100, 50 + 75 * i, 200, 50);
            tf[i].setFont(new Font(Constants.TEXT_FONT, Font.LAYOUT_LEFT_TO_RIGHT, 24));
            tf[i].setForeground(Constants.TEXT_FIELD_FOREGROUND);
            tf[i].setBackground(Constants.FRAME_BACKGROUND);
            tf[i].setBorder(BorderFactory.createLineBorder(Constants.TEXT_FIELD_BORDER));
            frame.add(l);
            frame.add(tf[i]);
        }
        String[] names = new String[players];
        var submit = new JButton("Play");
        submit.setBounds(150, frameHeight - 90, 100, 50);
        submit.setFocusable(false);
        submit.setForeground(Constants.FOREGROUND);
        submit.setBackground(Constants.BACKGROUND);
        submit.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 20));
        submit.addActionListener(e -> {
            boolean flag = true;
            for (int i = 0; i < players; i++) {
                names[i] = tf[i].getText();
                if (names[i].isBlank())
                    flag = false;
            }
            if (flag) {
                frame.setFocusable(false);
                frame.dispose();
                new SnakeLadder(players, names);
            }
        });
        frame.add(submit);
        frame.setVisible(true);
        Constants.addEnter(frame, submit);
    }
}
