package com.snakeladder;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class InitializeGame {
    JFrame frame;
    JLabel label;
    JButton button;
    int players;

    public InitializeGame() {
        players = 0;
        frame = new JFrame(Constants.TITLE);
        int frameWidth = 400, frameHeight = 300;
        var tk = Toolkit.getDefaultToolkit();
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        frame.setIconImage(Constants.ICON);
        label = new JLabel("Enter number of players:");
        label.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, 26));
        label.setBounds(30, 3, 390, 100);
        label.setForeground(Constants.TEXT_COLOR);
        var b2 = new JRadioButton("2");
        b2.setFont(new Font(Constants.NUMBER_FONT, Font.BOLD, 20));
        b2.setFocusable(false);
        b2.setBounds(175, 75, 100, 40);
        b2.setForeground(Constants.RADIO_BUTTON_FOREGROUND);
        b2.setBackground(Constants.FRAME_BACKGROUND);
        var b3 = new JRadioButton("3");
        b3.setBounds(175, 115, 100, 40);
        b3.setFont(new Font(Constants.NUMBER_FONT, Font.BOLD, 20));
        b3.setFocusable(false);
        b3.setForeground(Constants.RADIO_BUTTON_FOREGROUND);
        b3.setBackground(Constants.FRAME_BACKGROUND);
        var b4 = new JRadioButton("4");
        b4.setFont(new Font(Constants.NUMBER_FONT, Font.BOLD, 20));
        b4.setBounds(175, 155, 100, 40);
        b4.setFocusable(false);
        b4.setForeground(Constants.RADIO_BUTTON_FOREGROUND);
        b4.setBackground(Constants.FRAME_BACKGROUND);
        var bg = new ButtonGroup();
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);
        var btn = new JButton("Next");
        btn.setBounds(140, 200, 100, 50);
        btn.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 20));
        btn.setForeground(Constants.FOREGROUND);
        btn.setBackground(Constants.BACKGROUND);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            if (b2.isSelected())
                players = 2;
            else if (b3.isSelected())
                players = 3;
            else if (b4.isSelected())
                players = 4;
            if (players != 0) {
                frame.setFocusable(false);
                frame.dispose();
                new SetPlayers(players);
            }
        });
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == 98) {
                    b2.setSelected(true);
                } else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == 99) {
                    b3.setSelected(true);
                } else if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == 100) {
                    b4.setSelected(true);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn.doClick();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
        frame.requestFocus();
        frame.add(label);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(btn);
        frame.setVisible(true);
    }
}
