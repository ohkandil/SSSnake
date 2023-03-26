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
                            break;
                        case KeyEvent.VK_DOWN:
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

    protected void start() {
        snakePosX = new int[BOARDSIZE];
        snakePosY = new int[BOARDSIZE];
        snakeLength = 5;
        isMoving = true;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
