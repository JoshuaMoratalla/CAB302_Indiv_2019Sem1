package testingArea.Shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FreeRectangle extends java.awt.Rectangle implements  fillableObject,shapeObject{

    private Point2D centrePoint;
    static Color transparent = new Color(0,0,0,0);

    private Color lineColor = Color.black;
    private Color fillColor = transparent;


    public FreeRectangle(ArrayList<Point> points){
        super();
        for (Point point: points){
            add(point);
        }
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    @Override
    public void changeFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public void removeFillColor() {

    }

    @Override
    public void rotateObject(double angle) {

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
