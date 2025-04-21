package assignment9;

import edu.princeton.cs.introcs.StdDraw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Explosive {
    private static final double BOMB_SIZE = 0.05;
    private static final double FALL_DURATION = 5.0; // in seconds
    private static final double SPAWN_INTERVAL = 2.0; // in seconds
    private static final double FRAME_DELAY = 0.05; // 50 ms per frame

    private class Bomb {
        double x;
        double startTime;

        public Bomb(double x, double startTime) {
            this.x = x;
            this.startTime = startTime;
        }

        public double getY(double currentTime) {
            double elapsed = currentTime - startTime;
            double t = Math.min(elapsed / FALL_DURATION, 1.0); // percent fallen
            return 1.0 - t; // fall from y = 1.0 to y = 0.0
        }

        public boolean isOffScreen(double currentTime) {
            return getY(currentTime) <= 0;
        }
    }

    private List<Bomb> bombs = new ArrayList<>();
    private double lastSpawnTime = 0;

    public void update(double currentTime) {
        // Spawn new bomb every 5 seconds
        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL) {
            double randomX = 0.05 + Math.random() * 0.90; // x in [0.05, 0.95]
            bombs.add(new Bomb(randomX, currentTime));
            lastSpawnTime = currentTime;
        }

        // Remove bombs that have gone off-screen
        Iterator<Bomb> iter = bombs.iterator();
        while (iter.hasNext()) {
            Bomb b = iter.next();
            if (b.isOffScreen(currentTime)) {
                iter.remove();
            }
        }
    }

    public void draw(double currentTime) {
        for (Bomb b : bombs) {
            double y = b.getY(currentTime);
            StdDraw.picture(b.x, y, "assignment9/bomb.png", BOMB_SIZE, BOMB_SIZE);
        }
    }

    public boolean checkCollision(double x, double y, double currentTime) {
    	final double hitboxRadius = BOMB_SIZE * 0.75; // Use only 75% of visual size for hit detection
        for (Bomb b : bombs) {
            double bombY = b.getY(currentTime);
            if (Math.abs(b.x - x) < hitboxRadius && Math.abs(bombY - y) < hitboxRadius) {
                return true;
            }
        }
        return false;
    }
}
