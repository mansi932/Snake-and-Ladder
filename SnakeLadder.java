package com.snakeladder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SnakeLadder {
    public static final Color[] color = { Color.BLUE, Constants.GREEN_COLOR, Color.YELLOW, Constants.PURPLE_COLOR };
    public static final Icon[] diceFaces = new Icon[6];
    int chance;
    JFrame frame;
    JLabel lb;
    JLabel[] labels = new JLabel[101];
    int[] snakeStart, snakeEnd;
    int[] ladderStart, ladderEnd;
    Pieces[] coins;
    Map<Integer, Integer> snakeMapStart, snakeMapEnd;
    Map<Integer, Integer> ladderMapStart, ladderMapEnd;
    private Timer timer;
    JButton dice;
    boolean gameOver = false;
    boolean gameStarted = false;
    Random random = new Random();
    Icon crown = new ImageIcon(
            new ImageIcon(getClass().getResource("./Assets/crown.png")).getImage().getScaledInstance(48, 48,
                    Image.SCALE_SMOOTH));

    public SnakeLadder(int players, String[] names) {
        for (int i = 0; i < 6; i++) {
            var img = new ImageIcon(getClass().getResource("./Assets/DiceImages/" + (i + 1) + ".png")).getImage();
            diceFaces[i] = new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        }
        chance = 0;
        snakeStart = new int[] { 97, 95, 88, 63, 48, 36, 32 };
        snakeEnd = new int[] { 78, 56, 24, 18, 26, 6, 10 };
        snakeMapStart = new HashMap<>(snakeStart.length);
        snakeMapEnd = new HashMap<>(snakeStart.length);
        for (int i = 0; i < snakeStart.length; i++) {
            snakeMapStart.put(snakeStart[i], snakeEnd[i]);
            snakeMapEnd.put(snakeEnd[i], snakeStart[i]);
        }
        ladderStart = new int[] { 1, 4, 8, 21, 28, 50, 71, 80 };
        ladderEnd = new int[] { 38, 14, 30, 42, 76, 67, 92, 99 };
        ladderMapStart = new HashMap<>(ladderStart.length);
        ladderMapEnd = new HashMap<>(ladderStart.length);
        for (int i = 0; i < ladderStart.length; i++) {
            ladderMapStart.put(ladderStart[i], ladderEnd[i]);
            ladderMapEnd.put(ladderEnd[i], ladderStart[i]);
        }
        frame = new JFrame(Constants.TITLE);
        frame.setIconImage(Constants.ICON);
        int frameWidth = 515, frameHeight = 700;
        var tk = Toolkit.getDefaultToolkit();
        frame.setBounds((int) (tk.getScreenSize().getWidth() - frameWidth) >> 1,
                (int) (tk.getScreenSize().getHeight() - frameHeight) >> 1, frameWidth, frameHeight);
        frame.getContentPane().setBackground(Constants.FRAME_BACKGROUND);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int r = 0, c = 0;
        for (int i = 100; i > 0; i--, c = (c + 1) % 10) {
            if (i != 100 && i % 10 == 0)
                r++;
            labels[i] = new JLabel(Integer.toString(i));
            labels[i].setFont(new Font(Constants.NUMBER_FONT, Font.TRUETYPE_FONT, 20));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if ((r & 1) == 0)
                labels[i].setBounds(c * 50, r * 50, 50, 50);
            else
                labels[i].setBounds((9 - c) * 50, r * 50, 50, 50);
            labels[i].setOpaque(true);
            labels[i].setBackground((i & 1) == 1 ? Constants.ODD_LABEL_COLOR : Constants.EVEN_LABEL_COLOR);
            Color col;
            if (snakeMapStart.containsKey(i) || snakeMapEnd.containsKey(i)) {
                col = Constants.SNAKE_COLOR;
                addSnakeHover(labels[i], labels, snakeMapStart.containsKey(i) ? snakeMapStart : snakeMapEnd, i);
            } else if (ladderMapEnd.containsKey(i) || ladderMapStart.containsKey(i)) {
                col = Constants.LADDER_COLOR;
                addLadderHover(labels[i], labels, ladderMapStart.containsKey(i) ? ladderMapStart : ladderMapEnd, i);
            } else
                col = Color.BLACK;
            labels[i].setForeground(col);
            frame.add(labels[i]);
        }
        labels[100].setText(null);
        labels[100].setIcon(crown);
        labels[0] = new JLabel("0");

        var s = "Turn: " + names[0];
        lb = new JLabel(String.format("%16s", s));
        lb.setBounds(100, 510, 250, 50);
        lb.setFont(new Font(Constants.TEXT_FONT, Font.ITALIC, 26));
        lb.setForeground(color[0]);
        lb.setBackground(Constants.FRAME_BACKGROUND);
        lb.setOpaque(true);
        frame.add(lb);
        coins = new Pieces[players];
        dice = new JButton("Roll");
        dice.setIcon(null);
        dice.setBounds(200, 600, 100, 50);
        dice.setHorizontalTextPosition(SwingConstants.CENTER);
        dice.setVerticalTextPosition(SwingConstants.CENTER);
        dice.setFocusable(false);
        dice.setForeground(Constants.FOREGROUND);
        dice.setBackground(Constants.BACKGROUND);
        dice.setFont(new Font(Constants.BUTTON_FONT, Font.ITALIC, 24));
        dice.addActionListener(e -> move(labels, dice, players, names));
        timer = new Timer(1500, e -> {
            var ic = labels[100].getIcon();
            int z = 0;
            for (int i = 0; i < players; i++) {
                if (coins[i].icon.equals(ic)) {
                    z = i;
                    break;
                }
            }
            frame.dispose();
            timer.stop();
            new ShowWinner(names[z], color[z]);
        });
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!gameOver)
                    move(labels, dice, players, names);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                dice.doClick();
            }

        });
        frame.add(dice);
        frame.setVisible(true);
    }

    void move(JLabel[] labels, JButton dice, int players, String[] names) {
        if (!gameStarted) {
            for (int i = 0; i < players; i++)
                coins[i] = new Pieces(players);
            gameStarted = true;
        }
        int x = rollDice();
        dice.setIcon(diceFaces[x - 1]);
        dice.setText(null);
        var k = move(labels, x, players, names);
        var s = "Turn: " + names[k];
        lb.setText(String.format("%16s", s));
    }

    public int move(JLabel[] labels, int x, int players, String[] names) {
        int currIndex = coins[chance].index;
        int newIndex = currIndex + x;
        if (snakeMapStart.containsKey(newIndex))
            newIndex = snakeMapStart.get(newIndex);
        else if (ladderMapStart.containsKey(newIndex))
            newIndex = ladderMapStart.get(newIndex);
        if (newIndex > 100) {
            newIndex = currIndex;
        }
        for (var coin : coins) {
            labels[coin.index].setIcon(null);
            labels[coin.index].setText(Integer.toString(coin.index));
        }
        coins[chance].index = newIndex;
        for (var coin : coins) {
            labels[coin.index].setText(null);
            labels[coin.index].setIcon(coin.icon);
            if (!labels[100].getIcon().equals(crown)) {
                gameOver = true;
                dice.setEnabled(false);
                timer.start();
            }
        }
        if (x == 6)
            return chance;
        chance = (chance + 1) % players;
        lb.setForeground(color[chance]);
        return chance;
    }

    public int rollDice() {
        return random.nextInt(1, 7);
    }

    public void addSnakeHover(JLabel label, JLabel[] labels, Map<Integer, Integer> map, int val) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int snakeStart = val;
                label.setForeground(label.getBackground().equals(Constants.ODD_LABEL_COLOR) ? Constants.ODD_LABEL_COLOR
                        : Constants.EVEN_LABEL_COLOR);
                label.setBackground(Constants.SNAKE_COLOR);
                int snakeEnd = map.get(snakeStart);
                labels[snakeEnd].setForeground(
                        labels[snakeEnd].getBackground().equals(Constants.ODD_LABEL_COLOR) ? Constants.ODD_LABEL_COLOR
                                : Constants.EVEN_LABEL_COLOR);
                labels[snakeEnd].setBackground(Constants.SNAKE_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int snakeStart = val;
                label.setForeground(Constants.SNAKE_COLOR);
                label.setBackground((snakeStart & 1) == 1 ? Constants.ODD_LABEL_COLOR : Constants.EVEN_LABEL_COLOR);
                int snakeEnd = map.get(snakeStart);
                labels[snakeEnd].setForeground(Constants.SNAKE_COLOR);
                labels[snakeEnd].setBackground(
                        (snakeEnd & 1) == 1 ? Constants.ODD_LABEL_COLOR : Constants.EVEN_LABEL_COLOR);
            }
        });
    }

    public void addLadderHover(JLabel label, JLabel[] labels, Map<Integer, Integer> map, int val) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int ladderStart = val;
                label.setForeground(label.getBackground().equals(Constants.ODD_LABEL_COLOR) ? Constants.ODD_LABEL_COLOR
                        : Constants.EVEN_LABEL_COLOR);
                label.setBackground(Constants.LADDER_COLOR);
                int ladderEnd = map.get(ladderStart);
                labels[ladderEnd].setForeground(
                        labels[ladderEnd].getBackground().equals(Constants.ODD_LABEL_COLOR) ? Constants.ODD_LABEL_COLOR
                                : Constants.EVEN_LABEL_COLOR);
                labels[ladderEnd].setBackground(Constants.LADDER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int ladderStart = val;
                label.setForeground(Constants.LADDER_COLOR);
                label.setBackground((ladderStart & 1) == 1 ? Constants.ODD_LABEL_COLOR : Constants.EVEN_LABEL_COLOR);
                int ladderEnd = map.get(ladderStart);
                labels[ladderEnd].setForeground(Constants.LADDER_COLOR);
                labels[ladderEnd].setBackground(
                        (ladderEnd & 1) == 1 ? Constants.ODD_LABEL_COLOR : Constants.EVEN_LABEL_COLOR);
            }
        });
    }
}
