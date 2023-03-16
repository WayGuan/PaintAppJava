import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Square class for creating sqaure
 *
 * @author Weiwei Guan Created on Apr 9th, 2021
 */
public class Square extends GeometricObject {

    private double size; // size of the square

    /**
     * square constructor
     * @param x x coordinate for the object
     * @param y y coordinate for the object
     * @param fillColor the fill color for the object
     * @param radius half size of the square
     */
    public Square(double x, double y, Color fillColor, double radius) {
        super(x, y, fillColor);
        try {
            this.size = radius;
        }catch(NumberFormatException nfe){
            throw new NumberFormatException("In Constructor. Size must be a double value.");
        }
    }

    /**
     * method to draw square
     * @param gc the graphics content to draw on
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillRect(getX() - size, getY() - size, size * 2, size * 2);
    }

    /**
     * toString method to show the information about the object
     * @return the information about the object
     */
    @Override
    public String toString() {
        return "The size of Square is: " + this.size + "It is located at " + this.getX() + ", " + this.getY();
    }
}
