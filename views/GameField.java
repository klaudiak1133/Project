package views;

import snake.*;
import utils.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameField extends JPanel {

    private final static Map<Integer, Direction> directionMap;
    private final int TILE_SIZE;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;

    private Snake snake;
    private Food food;
    private Direction direction;
    private Timer timer;
    private boolean run;
    private String message;
    private boolean GameOver;

    static {
        directionMap = new HashMap<>();
        directionMap.put(37, Direction.LEFT);
        directionMap.put(38, Direction.UP);
        directionMap.put(39, Direction.RIGHT);
        directionMap.put(40, Direction.DOWN);
    }

    public GameField() {
        super();
        direction = Direction.RIGHT;
        TILE_SIZE = 15;
        SCREEN_WIDTH = 450;
        SCREEN_HEIGHT = 500;
        snake = new Snake(45, 45, 15, TILE_SIZE, direction, SCREEN_WIDTH, SCREEN_HEIGHT);
        food = new Food(TILE_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean canMove = snake.move(direction);
                boolean ate = snake.eat(food);
                food.shouldChangePosition(ate);
                repaint();
                if (!canMove) {
                    timer.stop();
                    message = "Game Over!";
                    GameOver=true;
                }
            }
        });
        timer.start();
        run = true;
        message = "";
        GameOver=false;
    }

    public boolean isRun() {
        return run;
    }

    public boolean isGameOver() {
        return GameOver;
    }
    
    

    public void changeDirection(int keyCode) {

        Direction directionOther = directionMap.get(keyCode);
        if (!direction.isOppositeDirection(directionOther)) {
            direction = directionOther;
        }
    }

    public void pause(int keyCode) {
        if (run) {
            timer.stop();
            message = "Pause";
        } else {
            timer.start();
            message = "";
        }
        run = !run;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGameField(g);

    }

    private void drawGameField(Graphics g) {
        drawFood(g);
        drawSnake(g);
        drawTextMessage(g);
    }

    private void drawFood(Graphics g) {
        Color color = Color.RED;
        g.setColor(color);
        g.fillOval(food.getX(), food.getY(), TILE_SIZE, TILE_SIZE);
    }

    private void drawSnake(Graphics g) {
        Color color = Color.orange;
        for (SnakePart snakePart : snake) {
            g.setColor(color);
            g.fillRect(snakePart.getX(), snakePart.getY(), TILE_SIZE, TILE_SIZE);
            color = Color.BLUE;
        }
    }

    private void drawTextMessage(Graphics g) {
        Color color = Color.DARK_GRAY;
        g.setColor(color);
        g.setFont(new Font("Verdana", Font.PLAIN, 40));
        FontMetrics font = g.getFontMetrics();
        int x = (SCREEN_WIDTH - font.stringWidth(message)) / 2;
        int y = (SCREEN_HEIGHT - font.getHeight()) / 2;
        g.drawString(message, x, y);
    }

}
