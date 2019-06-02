package Shapes;

import java.awt.*;

public interface drawableObject {

    Shape getShape();
    void rotateObject(double angleShift);
    void scaleObject(double scaleSize);
    //shapeTools modifications

    Color getLineColor();
    void changeLineColor(Color newColor);

    //all drawable have line colors

    String toVectorString();
    String toLineColorString(Color currentPenColor);



    /* all need to be converted to vec file */
}