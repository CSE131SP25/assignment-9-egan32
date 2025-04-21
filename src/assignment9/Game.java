package assignment9;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private Explosive bomb;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        bomb = new Explosive();
    }

    public void play() {
        while (snake.isInbounds()) {
            double currentTime = System.currentTimeMillis() / 1000.0;

            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }

            snake.move();
            bomb.update(currentTime);

            if (snake.eatFood(food)) {
                food.relocate();
            }

            if (bomb.checkCollision(snake.getHeadX(), snake.getHeadY(), currentTime)) {
                System.out.println("Snake hit a falling bomb!");
                break;
            }

            updateDrawing(currentTime);
            StdDraw.pause(50);
        }

        System.out.println("Game Over!");
    }

    private void updateDrawing(double currentTime) {
        StdDraw.clear();
        snake.draw();
        food.draw();
        bomb.draw(currentTime);
        StdDraw.show();
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
            return 4;
        }
        return -1;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
