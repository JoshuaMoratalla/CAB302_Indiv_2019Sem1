package Shapes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Polygon Pack constructor that acts as a container for colors and the polygon shape
 */
public class PolygonPack implements drawableObject, fillableObject {
    private Polygon polygonShape;
    private Color fillColor = transparent;
    private Color lineColor = Color.black;

    //Creating of fixed polygon and stars
    public PolygonPack(Point2D startPoint, Point2D endPoint, int amountOfSides, boolean isRegular){
        if(isRegular){
            this.polygonShape = makePolygon(startPoint,endPoint,amountOfSides);
        }else{
            this.polygonShape = makeStar(startPoint,endPoint,amountOfSides);
        }
    }


    //Creating of polygon for polyline or through reading files
    public PolygonPack(ArrayList<Point2D> listOfPoints){
        int numberofPoints = listOfPoints.size();
        int[] xPoints = new int[numberofPoints];
        int[] yPoints = new int[numberofPoints];

        for(int n = 0; n < numberofPoints ; n++ ){
            Point2D point = listOfPoints.get(n);
            xPoints[n] =(int) point.getX();
            yPoints[n] =(int) point.getY();
        }

        this.polygonShape = new Polygon(xPoints,yPoints,numberofPoints);
    }

    /**
     *  Creates a fixed polygon that makes the point of the polygon to follow the mouse direction
     * @param startPoint first click
     * @param endPoint     second click
     * @param amountOfSides the amount of sides
     * @return
     */
    private Polygon makePolygon(Point2D startPoint, Point2D endPoint, int amountOfSides){

        double radius = startPoint.distance(endPoint);
        double deltaX = endPoint.getX() - startPoint.getX();
        double deltaY = endPoint.getY() - startPoint.getY();
        double radianShift = Math.atan2(deltaY,deltaX);
        int[] xPoints = new int[amountOfSides];
        int[] yPoints = new int[amountOfSides];

        for(int n = 0; n < amountOfSides; n ++){
            xPoints[n] = (int) (startPoint.getX() + radius * Math.cos(((n * 2 * Math.PI )/ amountOfSides)
                    + radianShift));
            yPoints[n] = (int) (startPoint.getY() + radius * Math.sin(((n * 2 * Math.PI )/ amountOfSides)
                    + radianShift));
        }

        return new Polygon(xPoints,yPoints,amountOfSides);
    }


    /**
     * Uses the same logic as the fixed polygon part however a second amount of points are turned by half
     * the separation between points and drawn closer to emulate a stars indentiation.
     * @param startPoint
     * @param endPoint
     * @param amountOfSides
     * @return
     */
    private Polygon makeStar(Point2D startPoint, Point2D endPoint, int amountOfSides){

        ArrayList<Point> bufferOuterList = new ArrayList<>(amountOfSides);
        ArrayList<Point> bufferInnerList = new ArrayList<>(amountOfSides);
        double outerRadius = startPoint.distance(endPoint);
        double innerRadius = outerRadius/ 2;

        double deltaX = endPoint.getX() - startPoint.getX();
        double deltaY = endPoint.getY() - startPoint.getY();
        double radShift_Outer = Math.atan2(deltaX,deltaY);

        double anglePerCorner = 360.0 / amountOfSides;
        double radShift_Inner = Math.toRadians(anglePerCorner/2) + radShift_Outer;

        for(int iterationCount = 0; iterationCount < amountOfSides; iterationCount ++){
            int xCoordinate = (int) (startPoint.getX() + outerRadius *
                    Math.cos(((iterationCount * 2 * Math.PI )/ amountOfSides) + radShift_Outer));
            int yCoordinate = (int) (startPoint.getY() + outerRadius *
                    Math.sin(((iterationCount * 2 * Math.PI )/ amountOfSides) + radShift_Outer));
            bufferOuterList.add( new Point(xCoordinate,yCoordinate));
            System.out.print("Amount of Sides"+amountOfSides);
        }

        for(int iterationCount = 0; iterationCount < amountOfSides; iterationCount ++){
            int xCoordinate = (int) (startPoint.getX() +
                    innerRadius * Math.cos(((iterationCount * 2 * Math.PI )/ amountOfSides) + radShift_Inner));
            int yCoordinate = (int) (startPoint.getY() +
                    innerRadius * Math.sin(((iterationCount * 2 * Math.PI )/ amountOfSides) + radShift_Inner));
            bufferInnerList.add( new Point(xCoordinate,yCoordinate));
        }

        int[] xPoint = new int[amountOfSides *2];
        int[] yPoint = new int[amountOfSides *2];

        for(int n = 0; n < amountOfSides  ; n ++){

            xPoint[2*n] =(int) bufferOuterList.get(n).getX();
            yPoint[2*n] =(int) bufferOuterList.get(n).getY();

            xPoint[(2*n)+1] =(int) bufferInnerList.get(n).getX();
            yPoint[(2*n)+1] =(int) bufferInnerList.get(n).getY();
            // addPoint((int) bufferOuterList.get(x).getX(),(int) bufferOuterList.get(x).getY());
            // addPoint((int) bufferInnerList.get(x).getX(),(int) bufferInnerList.get(x).getY());
        }

        return  new Polygon(xPoint,yPoint,amountOfSides*2);
    }


    private Polygon getPolygonShape() {
        return this.polygonShape;
    }

    @Override
    public Shape getShape() {
        return this.polygonShape;
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
    public String toVectorString(Dimension scaleRatio) {
        int[] intXval = polygonShape.xpoints;
        int[] intYval = polygonShape.ypoints;

        int points = getPolygonShape().npoints;
        double[] doubleXval = new double[points];
        double[] doubleYval = new double[points];

        for(int n = 0 ; n < points ;n++ ){
            doubleXval[n] = intXval[n];
            doubleYval[n] = intYval[n];
        }

        StringBuilder str = new StringBuilder("POLYGON");

        for(int n = 0 ; n < getPolygonShape().npoints; n ++){
            double scaledX1 = doubleXval[n]/scaleRatio.getWidth();
            double scaledY1 = doubleYval[n]/scaleRatio.getHeight();
            str.append(" ").append(scaledX1).append(" ").append(scaledY1);
        }
        return str.toString();
    }

    @Override
    public String toLineColorString(Color currentLineColor) {
        if(currentLineColor != getLineColor()){
            return String.format("PEN #%s", Integer.toHexString(getLineColor().getRGB()));
        }else{
            return null;
        }
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    @Override
    public void changeFillColor(Color newColor) {
        this.fillColor = newColor;
    }

    @Override
    public String toFillColorString(Color currentFillColor) {
        if(currentFillColor != getFillColor()){
            return String.format("FILL #%s", Integer.toHexString(getFillColor().getRGB()));
        }else{
            return null;
        }
    }
}