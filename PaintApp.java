import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The class aims to create a Paint app
 * Could add a ChoiceBox for picking colors when get time
 *
 * @author Weiwei Guan Created on Apr 9th, 2021
 */
public class PaintApp extends Application {

    // TODO: Instance Variables for View Components and Model
    private ArrayList<GeometricObject> shapes;
    private GraphicsContext gc;
    private Label objectSelectionLabel; // label shows if circle or square is selected
    private Button circleButton, squareButton, drawButton, unDrawButton, clearButton;

    private Label locationLabel, sizeLabel, colorLabel, errorLabel;
    private TextField xText, yText, sizeText, redColorTextField, greenColorTextField, blueColorTextField;

    private boolean isCircle = true; // to distinguish if a object is a circle
    private Color color;


    // TODO: Private Event Handlers and Helper Methods

    /**
     * method to pick the color from the user input color value
     *
     * @return the user color
     */
    private Color getColor() {
        int redColor, greenColor, blueColor;
        try {
            redColor = Integer.parseInt(redColorTextField.getText());
            greenColor = Integer.parseInt(greenColorTextField.getText());
            blueColor = Integer.parseInt(blueColorTextField.getText());
            if (redColor < 0 || redColor > 255 || greenColor < 0 || greenColor > 255 || blueColor < 0 || blueColor > 255) {
                errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
                errorLabel.setText("Color value must be integer between 0 and 255.");
                System.out.println("Color value must be integer between 0 and 255.");
                throw new IllegalArgumentException("Color value must be integer between 0 and 255.");
            } else {
                color = Color.rgb(redColor, greenColor, blueColor);
                errorLabel.setText("No Error.");
            }
        } catch (NumberFormatException nfe) {
            errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
            errorLabel.setText("Bad color value. Wrong Data type.");
            System.out.println("Bad color value. Wrong Data type.");
            throw new NumberFormatException();
        }
        return color;
    }

    /**
     * mouse click event handler
     *
     * @param me the mouse event
     */
    private void mouseClick(MouseEvent me) {
        double x = me.getX();
        double y = me.getY();
        double size;

        // get the color and catch the IllegalArgumentException
        try {
            getColor();
        } catch (IllegalArgumentException iae) {
            return;
        }

        // draw the object and add to the arraylist
        try {
            size = Double.parseDouble(sizeText.getText());
            if (size <= 0) {
                errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
                errorLabel.setText("Size must be bigger than 0."); // let the user know something went wrong
                System.out.println("Size must be bigger than 0.");
                return;
            }
            if (isCircle) {
                Circle circle = new Circle(x, y, color, size);
                circle.draw(gc);
                shapes.add(circle);
                errorLabel.setStyle("-fx-background-color: lightgreen; -fx-alignment: center;");
                errorLabel.setText("No Error.");
            } else {
                Square square = new Square(x, y, color, size);
                square.draw(gc);
                shapes.add(square);
                errorLabel.setStyle("-fx-background-color: lightgreen; -fx-alignment: center;");
                errorLabel.setText("No Error.");
            }

        } catch (NumberFormatException e) {
            errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
            errorLabel.setText("Size must be a double value."); // let the user know something went wrong
            System.out.println("Size must be a double value.");
        }

        if (!shapes.isEmpty()) {
            unDrawButton.setDisable(false); // enable the undraw button when arraylist is not empty
        }
    }


    // drag handler

    /**
     * mouse drag event handler
     *
     * @param me mouse event
     */
    private void dragHandler(MouseEvent me) {
        double x = me.getX();
        double y = me.getY();
        double size;

        // get the color and catch the IllegalArgumentException
        try {
            getColor();
        } catch (IllegalArgumentException iae) {
            return;
        }

        // draw the object and add to the arraylist
        try {
            size = Double.parseDouble(sizeText.getText());
            if (size <= 0) {
                errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
                errorLabel.setText("Size must be bigger than 0."); // let the user know something went wrong
                System.out.println("Size must be bigger than 0.");
                return;
            }
            if (isCircle) {
                Circle circle = new Circle(x, y, color, size);
                circle.draw(gc);
                shapes.add(circle);
            } else {
                Square square = new Square(x, y, color, size);
                square.draw(gc);
                shapes.add(square);
            }
        } catch (NumberFormatException e) {
            errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
            errorLabel.setText("Size must be a double value."); // let the user know something went wrong
            System.out.println("Size must be a double value.");
        }
    }

    /**
     * event handler for clicking the circle button
     *
     * @param ae action event
     */
    private void clickCircleButton(ActionEvent ae) {
        isCircle = true;
        objectSelectionLabel.setText("Click Draw button or Click on canvas to draw a circle.");
    }

    /**
     * event handler for clicking the square button
     *
     * @param ae action event
     */
    private void clickSquareButton(ActionEvent ae) {
        isCircle = false;
        objectSelectionLabel.setText("Click Draw button or Click on canvas to draw a square.");
    }

    /**
     * event handler for clicking the draw button
     *
     * @param ae action event
     */
    private void drawHandler(ActionEvent ae) {
        double x = 0;
        double y = 0;
        double size = 0;

        // get the color and catch the IllegalArgumentException
        try {
            getColor();
        } catch (IllegalArgumentException iae) {
            return;
        }

        // collect and validate the x, y value
        try {
            x = Double.parseDouble(xText.getText());
            y = Double.parseDouble(yText.getText());
            if (x < 0 || x > 800 || y < 0 || y > 400) {
                errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
                errorLabel.setText("Location must be in range between (0,0) and (800,400)"); // try catch?  still drawing with bad value!
                System.out.println("Location must be in range between (0,0) and (800,400)");
                return; // If x, y are not valid, return
            }
        } catch (NumberFormatException nfe) {
            errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
            errorLabel.setText("Location value should be double.");
            System.out.println("Location value should be double.");
            return;
        }

        // draw the object and add to the arraylist
        try {
            size = Double.parseDouble(sizeText.getText());
            if (size <= 0) {
                errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
                errorLabel.setText("Size must be bigger than 0."); // let the user know something went wrong
                System.out.println("Size must be bigger than 0.");
                return;
            }
            if (isCircle) {
                Circle circle = new Circle(x, y, color, size);
                circle.draw(gc);
                shapes.add(circle);
            } else {
                Square square = new Square(x, y, color, size);
                square.draw(gc);
                shapes.add(square);
            }
            errorLabel.setStyle("-fx-background-color: lightgreen; -fx-alignment: center;");
            errorLabel.setText("No Error."); // needed here?

        } catch (NumberFormatException nfe) {
            errorLabel.setStyle("-fx-background-color: red; -fx-alignment: center;");
            errorLabel.setText("Size must be a double value."); // let the user know something went wrong
            System.out.println("Size must be a double value.");
        }
        if (!shapes.isEmpty()) {
            unDrawButton.setDisable(false);
        }
    }

    /**
     * event handler for clicking the undraw button
     *
     * @param ae action event
     */
    private void unDrawHandler(ActionEvent ae) {
        int index = shapes.size();
        shapes.remove(index - 1); // remove the last element
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 400); // redraw the canvas
        for (GeometricObject geo : shapes) { // draw every element
            geo.draw(gc);
        }
        if (shapes.isEmpty()) {
            unDrawButton.setDisable(true); // disable the button when arraylist is empty
        }
    }

    /**
     * event handler for clicking the clear button
     *
     * @param ae action event
     */
    private void clearHandler(ActionEvent ae) {
        shapes.clear(); // remove all the elements
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 400);
        if (shapes.isEmpty()) {
            unDrawButton.setDisable(true);
        }
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception illegal argument number format exceptions
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 500); // set the size here
        stage.setTitle("Paint App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        Canvas canvas = new Canvas(800, 400);

        // 1. Create the model
        shapes = new ArrayList<>();
        // 2. Create the GUI components
        objectSelectionLabel = new Label("Choose Circle or Square to draw on canvas.");
        circleButton = new Button("Circle");
        squareButton = new Button("Square");
        locationLabel = new Label("Location:");
        xText = new TextField("200");
        yText = new TextField("200");

        sizeLabel = new Label("Size:");
        sizeText = new TextField("10");
        colorLabel = new Label("Color:");
        redColorTextField = new TextField("100");
        greenColorTextField = new TextField("100");
        blueColorTextField = new TextField("100");
        drawButton = new Button("Draw");
        unDrawButton = new Button("UnDraw");
        clearButton = new Button("Clear");
        errorLabel = new Label("No Error.");


        // 3. Add components to the root
        root.getChildren().addAll(canvas, objectSelectionLabel, circleButton, squareButton, locationLabel, xText, yText, sizeLabel, sizeText,
                colorLabel, redColorTextField, greenColorTextField, blueColorTextField, drawButton, unDrawButton, clearButton, errorLabel);
        // 4. Configure the components (colors, fonts, size, location)

        objectSelectionLabel.setPrefWidth(800);
        objectSelectionLabel.setPrefHeight(25);
        objectSelectionLabel.setFont(new Font("Arial", 20));
        objectSelectionLabel.setStyle("-fx-background-color: lightgreen; -fx-alignment: center;");

        circleButton.relocate(20, 430);
        squareButton.relocate(80, 430);
        locationLabel.relocate(150, 430);
        xText.relocate(200, 430);
        yText.relocate(240, 430);
        sizeLabel.relocate(300, 430);
        sizeText.relocate(330, 430);
        colorLabel.relocate(380, 430);
        redColorTextField.relocate(420, 430);
        greenColorTextField.relocate(460, 430);
        blueColorTextField.relocate(500, 430);
        drawButton.relocate(550, 430);
        unDrawButton.relocate(610, 430);
        clearButton.relocate(670, 430);


        errorLabel.relocate(0, 460);
        errorLabel.setPrefWidth(800);
        errorLabel.setFont(new Font("Arial", 20));
        errorLabel.setStyle("-fx-background-color: lightgreen; -fx-alignment: center;");

        circleButton.setPrefWidth(60);
        squareButton.setPrefWidth(60);

        xText.setPrefWidth(40);
        yText.setPrefWidth(40);
        sizeLabel.setPrefWidth(30);
        sizeText.setPrefWidth(40);
        colorLabel.setPrefWidth(40);
        redColorTextField.setPrefWidth(40);
        greenColorTextField.setPrefWidth(40);
        blueColorTextField.setPrefWidth(40);
        drawButton.setPrefWidth(60);
        unDrawButton.setPrefWidth(60);
        circleButton.setPrefWidth(60);
        unDrawButton.setDisable(true);

        // 5. Add Event Handlers and do final setup

        canvas.setLayoutY(25);
        gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 400);

        circleButton.setOnAction(this::clickCircleButton);
        squareButton.setOnAction(this::clickSquareButton);
        drawButton.setOnAction(this::drawHandler);
        unDrawButton.setOnAction(this::unDrawHandler);
        clearButton.setOnAction(this::clearHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mouseClick);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
