import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Graphics extends JPanel implements ActionListener {

    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int TICKSIZE = 50;
    static final int BOARDSIZE = (int) ((WIDTH * HEIGHT) / (Math.pow(TICKSIZE, 2)));

    final Font font = new Font("TimesRoman", Font.BOLD, 30);

    int[] snakePosX = new int[BOARDSIZE];
    int[] snakePosY = new int[BOARDSIZE];
    int snakeLength;
    

    char direction = 'R';
    boolean isMoving = false;
    final Timer timer = new Timer(150, this);


    public Graphics() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isMoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                }
            }
        });

        start();


    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (isMoving) {
            g.setColor(Color.darkGray);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakePosX[i], snakePosY[i], TICKSIZE, TICKSIZE);

            }
        }
    }

protected void move() {
    for (int i = snakeLength; i > 0; i--) {
        snakePosX[i] = snakePosX[i - 1];
        snakePosY[i] = snakePosY[i - 1];
    }
    switch (direction) {
        case 'U':
            snakePosY[0] = snakePosY[0] - TICKSIZE;
            break;
        case 'D':
            snakePosY[0] = snakePosY[0] + TICKSIZE;
            break;
        case 'L':
            snakePosX[0] = snakePosX[0] - TICKSIZE;
            break;
        case 'R':
            snakePosX[0] = snakePosX[0] + TICKSIZE;
            break;
    }
}

    

    protected void start() {
        snakePosX = new int[BOARDSIZE];
        snakePosY = new int[BOARDSIZE];
        snakeLength = 5;
        isMoving = true;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
        }
        repaint();
    }
}
