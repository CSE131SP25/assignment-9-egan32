package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.035;
    private double x, y;
    private Color color;

    /**
	○  Creates a new Food at a random location
     */
    public Food() {
        relocate();
        this.color = ColorUtils.solidColor(); // Use ColorUtils to assign a random color
    }

    public void relocate() {
    	this.x = 0.05 + Math.random() * 0.90; // x in [0.05, 0.95]
        this.y = 0.05 + Math.random() * 0.90; // y in [0.05, 0.95]
    }

    /**
	○  Draws the Food
     */
    public void draw() {
    	StdDraw.picture(x, y, "assignment9/apple.png", FOOD_SIZE, FOOD_SIZE);
//        StdDraw.setPenColor(color);
//        StdDraw.filledCircle(x, y, FOOD_SIZE / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
