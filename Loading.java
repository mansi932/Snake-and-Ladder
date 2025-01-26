package com.snakeladder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

// import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
// import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class Loading {
    JFrame frame;
    JProgressBar pb;
    Timer timer;
    JLabel title, label;
    JButton button;

    public Loading() {
        frame = new JFrame(Constants.TITLE);
        int frameWidth = 500, frameHeight = 500;
        var tk = Toolkit.getDefaultToolkit();
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        // var mediaPlayer = MediaPlayerFactory.newInstance().newHeadlessMediaPlayer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Constants.ICON);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        title = new JLabel(Constants.TITLE);
        title.setBounds(0, 0, frame.getWidth(), 100);
        title.setFont(new Font("Comic Sans MS", Font.ITALIC, 65));
        title.setForeground(Constants.TEXT_COLOR);
        label = new JLabel();
        label.setBounds(0, 90, frame.getWidth(), 281);
        label.setIcon(new ImageIcon(
                new ImageIcon(this.getClass().getResource("./Assets/loading_screen.jpg")).getImage().getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH)));
        pb = new JProgressBar();
        pb.setBounds(5, 380, 475, 25);
        pb.setVisible(false);
        timer = new Timer(500, e -> {
            if (pb.getValue() == 100) {
                timer.stop();
                // frame.setFocusable(false);
                frame.dispose();
                new InitializeGame();
            }
            pb.setValue(pb.getValue() + 20);
        });
        button = new JButton("Start");
        button.setBounds(200, 410, 100, 50);
        button.setFocusable(false);
        button.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 20));
        button.setForeground(Constants.FOREGROUND);
        button.setBackground(Constants.BACKGROUND);
        button.addActionListener(e -> {
            pb.setVisible(true);
            button.setEnabled(false);
            timer.start();
        });
        pb.setForeground(Constants.TEXT_COLOR);
        pb.setBackground(Constants.BACKGROUND);
        frame.add(pb);
        frame.add(title);
        frame.add(label);
        frame.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        Constants.addEnter(frame, button);
    }
}
