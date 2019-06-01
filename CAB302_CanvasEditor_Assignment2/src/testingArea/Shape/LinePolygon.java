package testingArea.Shape;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;

public class LinePolygon extends Polygon implements  fillableObject,shapeObject {

    static Color transparent = new Color(0,0,0,0);
    private Color fillColor = transparent;
    private Color lineColor = Color.black;

    public LinePolygon(ArrayList<Point> points){
        super();
        for(Point point: points){
            addPoint((int) point.getX(), (int)point.getY());
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
        this.fillColor = transparent;
    }

    public void snapToEqualRadius(double radius) {
        ///////////fill in function//////////////////////////////////////////////
    }

    @Override
    public void rotateObject(double angle) {
        ///////////fill in function//////////////////////////////////////////////
    }

    @Override
    public void scaleObject(double scaleSize) {
        ///////////fill in function//////////////////////////////////////////////
    }

    @Override
    public Color getLineColor() {
        return this.lineColor;
    }

    @Override
    public void changeLineColor(Color newColor) {
        this.lineColor = newColor;
    }

    @Override
    public String toVectorString() {
        int[] xVal = super.xpoints;
        int[] yVal = super.ypoints;

        StringBuilder str = new StringBuilder("POLYGON");

        for(int x = 0 ; x < npoints; x ++){
            str.append( " " +xVal[x] +" " +yVal[x] );
            //str.append( " (" +xVal[x] +"," +yVal[x]+ ")" );

        }
        return str.toString();
    }
}
