import javafx.scene.paint.Color;

/**
 * This is the parent class for Circle and Rectangle.
 *
 * @author Weiwei Guan Created on Apr 9th, 2021
 */
public abstract class GeometricObject implements Drawable {

    private double x, y; // x and y coordinate for the object
    private Color fillColor; //the fill color for the object

    /**
     * GeometricObject constructor
     * @param x x coordinate for the object
     * @param y y coordinate for the object
     * @param fillColor the fill color for the object
     */
    public GeometricObject(double x, double y, Color fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    /**
     *x position getter
     * @return x coordinate for the object
     */
    public double getX() {
        return x;
    }

    /**
     * y position getter
     * @return y coordinate for the object
     */
    public double getY() {
        return y;
    }

    /**
     * fill color getter
     * @return the fill color for the object
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * toString method to show the information about the object
     * @return the information about the object
     */
    @Override
    public String toString() {
        return String.format("This object is located at %.2f, %.2f with a %s color.",x, y, fillColor);
    }
}
