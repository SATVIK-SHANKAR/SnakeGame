// VERSION 1    

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.util.Random;

// import javax.swing.JPanel;

// public class GamePanel extends JPanel implements ActionListener {

//     private static final long serialVersionUID = 1L;

//     static final int WIDTH = 500;
//     static final int HEIGHT = 500;
//     static final int UNIT_SIZE = 20;
//     static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

//     // hold x and y coordinates for body parts of the snake
//     final int x[] = new int[NUMBER_OF_UNITS];
//     final int y[] = new int[NUMBER_OF_UNITS];

//     // initial length of the snake
//     int length = 5;
//     int foodEaten;
//     int foodX;
//     int foodY;
//     char direction = 'D';
//     boolean running = false;
//     Random random;
//     Timer timer;

//     GamePanel() {
//         random = new Random();
//         this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//         this.setBackground(Color.DARK_GRAY);
//         this.setFocusable(true);
//         this.addKeyListener(new MyKeyAdapter());
//         play();
//     }

//     public void play() {
//         addFood();
//         running = true;

//         timer = new Timer(80, this);
//         timer.start();
//     }

//     @Override
//     public void paintComponent(Graphics graphics) {
//         super.paintComponent(graphics);
//         draw(graphics);
//     }

//     public void move() {
//         for (int i = length; i > 0; i--) {
//             // shift the snake one unit to the desired direction to create a move
//             x[i] = x[i - 1];
//             y[i] = y[i - 1];
//         }

//         if (direction == 'L') {
//             x[0] = x[0] - UNIT_SIZE;
//         } else if (direction == 'R') {
//             x[0] = x[0] + UNIT_SIZE;
//         } else if (direction == 'U') {
//             y[0] = y[0] - UNIT_SIZE;
//         } else {
//             y[0] = y[0] + UNIT_SIZE;
//         }
//     }

//     public void checkFood() {
//         if (x[0] == foodX && y[0] == foodY) {
//             length++;
//             foodEaten++;
//             addFood();
//         }
//     }

//     public void draw(Graphics graphics) {

//         if (running) {
//             graphics.setColor(new Color(210, 115, 90));
//             graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

//             graphics.setColor(Color.white);
//             graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

//             for (int i = 1; i < length; i++) {
//                 graphics.setColor(new Color(40, 200, 150));
//                 graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
//             }

//             graphics.setColor(Color.white);
//             graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
//             FontMetrics metrics = getFontMetrics(graphics.getFont());
//             graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2,
//                     graphics.getFont().getSize());

//         } else {
//             gameOver(graphics);
//         }
//     }

//     public void addFood() {
//         foodX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
//         foodY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
//     }

//     public void checkHit() {
//         // check if head run into its body
//         for (int i = length; i > 0; i--) {
//             if (x[0] == x[i] && y[0] == y[i]) {
//                 running = false;
//             }
//         }

//         // check if head run into walls
//         if (x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT) {
//             running = false;
//         }

//         if (!running) {
//             timer.stop();
//         }
//     }

//     public void gameOver(Graphics graphics) {
//         graphics.setColor(Color.red);
//         graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
//         FontMetrics metrics = getFontMetrics(graphics.getFont());
//         graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

//         graphics.setColor(Color.white);
//         graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
//         metrics = getFontMetrics(graphics.getFont());
//         graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2,
//                 graphics.getFont().getSize());

//     }

//     @Override
//     public void actionPerformed(ActionEvent arg0) {
//         if (running) {
//             move();
//             checkFood();
//             checkHit();
//         }
//         repaint();
//     }

//     public class MyKeyAdapter extends KeyAdapter {
//         @Override
//         public void keyPressed(KeyEvent e) {
//             switch (e.getKeyCode()) {
//                 case KeyEvent.VK_LEFT:
//                     if (direction != 'R') {
//                         direction = 'L';
//                     }
//                     break;

//                 case KeyEvent.VK_RIGHT:
//                     if (direction != 'L') {
//                         direction = 'R';
//                     }
//                     break;

//                 case KeyEvent.VK_UP:
//                     if (direction != 'D') {
//                         direction = 'U';
//                     }
//                     break;

//                 case KeyEvent.VK_DOWN:
//                     if (direction != 'U') {
//                         direction = 'D';
//                     }
//                     break;
//             }
//         }
//     }
// }

// VERSION 2

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.util.Random;

// public class GamePanel extends JPanel implements ActionListener {

//     static final int WIDTH = 500;
//     static final int HEIGHT = 500;
//     static final int UNIT_SIZE = 20;
//     static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

//     final int x[] = new int[NUMBER_OF_UNITS];
//     final int y[] = new int[NUMBER_OF_UNITS];

//     int length = 5;
//     int foodEaten;
//     int foodX;
//     int foodY;
//     char direction = 'R';
//     boolean running = false;
//     boolean gameStarted = false;
//     Random random;
//     Timer timer;
//     JButton startButton;
//     JButton restartButton;

//     GamePanel() {
//         random = new Random();
//         this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//         this.setBackground(Color.DARK_GRAY);
//         this.setFocusable(true);
//         this.setLayout(null);
//         this.addKeyListener(new MyKeyAdapter());

//         // Start Button
//         startButton = new JButton("Start Game");
//         startButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2, 150, 50);
//         startButton.addActionListener(e -> startGame());
//         this.add(startButton);

//         // Restart Button
//         restartButton = new JButton("Play Again");
//         restartButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2 + 60, 150, 50);
//         restartButton.addActionListener(e -> restartGame());
//         restartButton.setVisible(false);
//         this.add(restartButton);
//     }

//     public void startGame() {
//         if (!gameStarted) {
//             gameStarted = true;
//             running = true;
//             startButton.setVisible(false);
//             restartButton.setVisible(false);
//             initializeSnake();
//             addFood();
//             timer = new Timer(80, this);
//             timer.start();
//             requestFocusInWindow();
//         }
//     }

//     private void initializeSnake() {
//         length = 5;
//         foodEaten = 0;
//         direction = 'R';

//         // Initial snake position starting from left
//         for (int i = 0; i < length; i++) {
//             x[i] = WIDTH / 2 - (i * UNIT_SIZE);
//             y[i] = HEIGHT / 2;
//         }
//     }

//     public void restartGame() {
//         initializeSnake();
//         startGame();
//     }

//     public void move() {
//         for (int i = length; i > 0; i--) {
//             x[i] = x[i - 1];
//             y[i] = y[i - 1];
//         }

//         // Screen wrapping
//         switch (direction) {
//             case 'L':
//                 x[0] = (x[0] - UNIT_SIZE + WIDTH) % WIDTH;
//                 break;
//             case 'R':
//                 x[0] = (x[0] + UNIT_SIZE) % WIDTH;
//                 break;
//             case 'U':
//                 y[0] = (y[0] - UNIT_SIZE + HEIGHT) % HEIGHT;
//                 break;
//             case 'D':
//                 y[0] = (y[0] + UNIT_SIZE) % HEIGHT;
//                 break;
//         }
//     }

//     public void checkFood() {
//         if (x[0] == foodX && y[0] == foodY) {
//             length++;
//             foodEaten++;
//             addFood();
//         }
//     }

//     public void addFood() {
//         foodX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
//         foodY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
//     }

//     public void checkCollisions() {
//         // Check self collision
//         for (int i = length; i > 0; i--) {
//             if (x[0] == x[i] && y[0] == y[i]) {
//                 running = false;
//             }
//         }

//         if (!running) {
//             timer.stop();
//             restartButton.setVisible(true);
//         }
//     }

//     @Override
//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         draw(g);
//     }

//     public void draw(Graphics g) {
//         if (running) {
//             // Draw food
//             g.setColor(Color.RED);
//             g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

//             // Draw snake head
//             g.setColor(Color.GREEN);
//             g.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

//             // Draw snake body
//             for (int i = 1; i < length; i++) {
//                 g.setColor(new Color(45, 180, 0));
//                 g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
//             }

//             // Draw score
//             g.setColor(Color.WHITE);
//             g.setFont(new Font("Arial", Font.BOLD, 20));
//             g.drawString("Score: " + foodEaten, 10, 20);
//         } else {
//             gameOver(g);
//         }
//     }

//     public void gameOver(Graphics g) {
//         // Game Over text
//         g.setColor(Color.RED);
//         g.setFont(new Font("Arial", Font.BOLD, 50));
//         FontMetrics metrics = getFontMetrics(g.getFont());
//         g.drawString("Game Over",
//                 (WIDTH - metrics.stringWidth("Game Over")) / 2,
//                 HEIGHT / 2);

//         // Score display
//         g.setColor(Color.WHITE);
//         g.setFont(new Font("Arial", Font.BOLD, 25));
//         metrics = getFontMetrics(g.getFont());
//         g.drawString("Score: " + foodEaten,
//                 (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2,
//                 HEIGHT / 2 + 50);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (running) {
//             move();
//             checkFood();
//             checkCollisions();
//         }
//         repaint();
//     }

//     class MyKeyAdapter extends KeyAdapter {
//         @Override
//         public void keyPressed(KeyEvent e) {
//             switch (e.getKeyCode()) {
//                 case KeyEvent.VK_LEFT:
//                     if (direction != 'R')
//                         direction = 'L';
//                     break;
//                 case KeyEvent.VK_RIGHT:
//                     if (direction != 'L')
//                         direction = 'R';
//                     break;
//                 case KeyEvent.VK_UP:
//                     if (direction != 'D')
//                         direction = 'U';
//                     break;
//                 case KeyEvent.VK_DOWN:
//                     if (direction != 'U')
//                         direction = 'D';
//                     break;
//             }
//         }
//     }
// }

// VERSION 3

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    final int x[] = new int[NUMBER_OF_UNITS];
    final int y[] = new int[NUMBER_OF_UNITS];

    int length = 6;
    int foodEaten;
    int foodX;
    int foodY;
    char direction = 'R';
    boolean running = false;
    Random random;
    Timer timer;
    JButton startButton;
    JButton restartButton;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setFocusable(true);

        // Start Button
        startButton = new JButton("Start Game");
        startButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2, 150, 50);
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> startGame());
        this.add(startButton);

        // Restart Button
        restartButton = new JButton("Play Again");
        restartButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2 + 60, 150, 50);
        restartButton.setBackground(Color.GREEN);
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setVisible(false);
        this.add(restartButton);

        this.addKeyListener(new MyKeyAdapter());
        initGame();
    }

    private void initGame() {
        random = new Random();
        timer = new Timer(100, this);
    }

    private void startGame() {
        // Initialize snake
        for (int i = 0; i < length; i++) {
            x[i] = UNIT_SIZE * (length - i);
            y[i] = HEIGHT / 2;
        }

        addFood();
        running = true;
        startButton.setVisible(false);
        restartButton.setVisible(false);
        timer.start();
        requestFocusInWindow();
    }

    private void restartGame() {
        // Reset game state
        length = 6;
        foodEaten = 0;
        direction = 'R';
        running = false;

        // Clear previous game state
        for (int i = 0; i < NUMBER_OF_UNITS; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        // Hide restart button
        restartButton.setVisible(false);

        // Start new game
        startGame();
    }

    public void addFood() {
        foodX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        // Shift body
        for (int i = length; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Move head based on direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

        // Screen wrap
        if (x[0] < 0)
            x[0] = WIDTH - UNIT_SIZE;
        if (x[0] >= WIDTH)
            x[0] = 0;
        if (y[0] < 0)
            y[0] = HEIGHT - UNIT_SIZE;
        if (y[0] >= HEIGHT)
            y[0] = 0;
    }

    public void checkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            length++;
            foodEaten++;
            addFood();
        }
    }

    public void checkCollision() {
        // Self collision
        for (int i = length; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        if (!running) {
            timer.stop();
            restartButton.setVisible(true);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // Draw snake head
            g.setColor(Color.GREEN);
            g.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            // Draw snake body
            for (int i = 1; i < length; i++) {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Draw score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + foodEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over",
                (WIDTH - metrics.stringWidth("Game Over")) / 2,
                HEIGHT / 2);

        // Score display
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Score: " + foodEaten,
                (WIDTH - getFontMetrics(g.getFont()).stringWidth("Score: " + foodEaten)) / 2,
                HEIGHT / 2 + 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;
            }
        }
    }
}