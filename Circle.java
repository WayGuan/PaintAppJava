import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Circle class for creating circle
 *
 * @author Weiwei Guan Created on Apr 9th, 2021
 */
public class Circle extends GeometricObject {

    private double radius; // radius of the circle

    /**
     * circle constructor
     * @param x x coordinate for the object
     * @param y y coordinate for the object
     * @param fillColor the fill color for the object
     * @param radius radius of the circle
     */
    public Circle(double x, double y, Color fillColor, double radius) {
        super(x, y, fillColor); // Need to break up the super to catch the exceptions for x, y non positive value
        try {
            this.radius = radius;
        }catch(NumberFormatException nfe){
            throw new NumberFormatException("In Constructor. Size must be a double value.");
        }
    }

    /**
     * method to draw circle
     * @param gc the graphics content to draw on
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    /**
     * toString method to show the information about the object
     * @return the information about the object
     */
    @Override
    public String toString() {
        return "The size of Square is: " + this.radius + "It is located at " + this.getX() + ", " + this.getY();
    }
}
