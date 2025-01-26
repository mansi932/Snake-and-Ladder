package com.snakeladder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowCredits {
    public static final int size = 21;

    public ShowCredits() {
        var frame = new JFrame("Credits");
        frame.setIconImage(Constants.ICON);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var tk = Toolkit.getDefaultToolkit();
        int frameWidth = 400, frameHeight = 350;
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        frame.setResizable(false);
        frame.setLayout(null);
        String[] creators = { "Abhishek Gorai (031)", "Vivek Kumar (196)", "Biswanath Bhuyan (069)",
                "Manasi Das (203)" };
        String[] links = {
                "https://www.linkedin.com/in/abhishek-gorai-8643aa277",
                "https://www.linkedin.com/in/vivek-kumar-94a224277",
                "https://www.linkedin.com/in/biswanath-bhuyan",
                "https://www.linkedin.com/in/mansi-dash123"
        };
        var label1 = new JLabel("Created by...");
        var font = new Font(Constants.TEXT_FONT, Font.ITALIC, size);
        label1.setBounds(5, 2, 300, 100);
        label1.setFont(new Font("Consolas", Font.BOLD, 30));
        label1.setForeground(Color.decode("#210070"));
        frame.add(label1);
        var label2 = new JLabel("1. " + creators[0]);
        label2.setForeground(Constants.TEXT_COLOR);
        label2.setBounds(5, 75, 300, 75);
        label2.setFont(font);
        show(label2, 0, creators, links);

        frame.add(label2);
        var label3 = new JLabel("2. " + creators[1]);
        label3.setForeground(Constants.TEXT_COLOR);
        label3.setBounds(5, 125, 300, 75);
        label3.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, size));
        show(label3, 1, creators, links);

        frame.add(label3);
        var label4 = new JLabel("3. " + creators[2]);
        label4.setForeground(Constants.TEXT_COLOR);
        label4.setBounds(5, 175, 300, 75);
        label4.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, size));
        show(label4, 2, creators, links);
        frame.add(label4);
        var label5 = new JLabel("4. " + creators[3]);
        label5.setForeground(Constants.TEXT_COLOR);
        label5.setBounds(5, 225, 300, 75);
        label5.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, size));
        show(label5, 3, creators, links);
        frame.add(label5);
        frame.setVisible(true);
    }

    void show(JLabel label, int index, String[] creators, String[] links) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setText("<html><u>" + (index + 1) + ". " + creators[index] + "</u></html>");
                label.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setText(index + 1 + ". " + creators[index]);
                label.setForeground(Constants.TEXT_COLOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Link.openURL(links[index]);
            }
        });
    }

    public static void main(String[] args) {
        new ShowCredits();
    }
}
