package testingArea.Shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FixedOval extends Ellipse2D.Float implements fillableObject,shapeObject{


    public FixedOval(){

    }

    @Override
    public Color getFillColor() {
        return null;
    }

    @Override
    public void changeFillColor(Color newColor) {

    }

    @Override
    public void removeFillColor() {

    }

    @Override
    public void rotateObject(double angleShift) {

    }

    @Override
    public void scaleObject(double scaleSize) {

    }

    @Override
    public Color getLineColor() {
        return null;
    }

    @Override
    public void changeLineColor(Color newColor) {

    }

    @Override
    public String toVectorString() {
        return null;
    }
}
