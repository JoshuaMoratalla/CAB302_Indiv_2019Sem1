package testingArea.Shape;

import java.awt.*;

public interface shapeObject {

    public void rotateObject (double angleShift);
    public void scaleObject (double scaleSize);
    public Color getLineColor();
    public void changeLineColor(Color newColor);
    public String toVectorString();



}
