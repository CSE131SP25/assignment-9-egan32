package assignment9;

import java.util.LinkedList;

public class Snake {
    private static final double SEGMENT_SIZE = 0.03;
    private static final double MOVEMENT_SIZE = 0.03;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = MOVEMENT_SIZE;
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if (direction == 1 && deltaY == 0) { // Up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2 && deltaY == 0) { // Down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3 && deltaX == 0) { // Left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4 && deltaX == 0) { // Right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    public void move() {
        double newX = segments.getFirst().getX() + deltaX;
        double newY = segments.getFirst().getY() + deltaY;

        BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
        segments.addFirst(newHead);
        segments.removeLast();
    }

    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    public boolean eatFood(Food f) {
        double headX = segments.getFirst().getX();
        double headY = segments.getFirst().getY();

        double dx = headX - f.getX();
        double dy = headY - f.getY();
        double distance = Math.hypot(dx, dy);

        // Shrink the effective hitbox radius (e.g. 75% of actual sum)
        double effectiveHitRadius = (SEGMENT_SIZE + Food.FOOD_SIZE) * 0.75;

        if (distance <= effectiveHitRadius) {
            segments.addLast(new BodySegment(headX, headY, SEGMENT_SIZE));
            return true;
        }
        return false;
    }


    public boolean isInbounds() {
        double headX = segments.getFirst().getX();
        double headY = segments.getFirst().getY();
        return headX > 0 && headX < 1 && headY > 0 && headY < 1;
    }

    public double getHeadX() {
        return segments.getFirst().getX();
    }

    public double getHeadY() {
        return segments.getFirst().getY();
    }
}
