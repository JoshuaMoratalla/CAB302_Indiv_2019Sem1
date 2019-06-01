package DrawnShapes;

import NewShapes.drawableObject;
import NewShapes.fillableObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

// ***************************************************************************************
// ********************************Everything Checked*************************************
// *********************************Except For Tools**************************************
// ***************************************************************************************

public class PolygonFixed extends Polygon implements drawableObject, fillableObject {

    private Color fillColor = ShapeConstants.transparent;
    private Color lineColor = Color.black;

    public PolygonFixed(int amountOfSides, Point2D centrePoint, Point2D endPoint){
        super();

        ArrayList<Point> bufferList = new ArrayList<>(amountOfSides);
        double radius = centrePoint.distance(endPoint);

        double deltaX = endPoint.getX() - centrePoint.getX();
        double deltaY = endPoint.getY() - centrePoint.getY();
        double radianShift = Math.atan2(deltaY,deltaX);

        for(int iterationCount = 0; iterationCount < amountOfSides; iterationCount ++){
            int xCoordinate = (int) (centrePoint.getX() + radius * Math.cos(((iterationCount * 2 * Math.PI )/ amountOfSides) + radianShift));
            int yCoordinate = (int) (centrePoint.getY() + radius * Math.sin(((iterationCount * 2 * Math.PI )/ amountOfSides) + radianShift));
            addPoint(xCoordinate,yCoordinate);
            bufferList.add( new Point(xCoordinate,yCoordinate));
        }
    }

    @Override
    public Shape getShape() {
        return null;
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
            str.append(" ").append(xVal[x]).append(" ").append(yVal[x]);
            //str.append( " (" +xVal[x] +"," +yVal[x]+ ")" );
        }
        return str.toString();
    }

    @Override
    public String toLineColorString(Color currentPenColor) {
        if(currentPenColor != getLineColor()){
            return String.format("PEN %s", Integer.toHexString(getLineColor().getRGB()));
        }else{
            return null;
        }
    }

    @Override
    public String toFillColorString(Color currentFillColor) {
        if(currentFillColor != getFillColor()){
            return String.format("FILL %s", Integer.toHexString(getFillColor().getRGB()));
        }else{
            return null;
        }
    }

    @Override
    public Color getFillColor() {
        return this.getFillColor();
    }

    @Override
    public void changeFillColor(Color newColor) {
        this.fillColor = newColor;
    }

    @Override
    public void removeFillColor() {
        this.fillColor = transparent;
    }
}
