package ShapesPackage;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LinePack implements drawableObject {

    private Line2D.Float lineShape;
    private Color lineColor = Color.black;

    public LinePack(Point2D startPoint, Point2D endPoint){
        this.lineShape = new Line2D.Float(startPoint,endPoint);
    }

    //public LinePack(Point2D[] points){
    //    this.lineShape = new Line2D.Float(points[0],points[1]);
    //}

    public Line2D.Float getLineShape() {
        return lineShape;
    }

    @Override
    public Shape getShape() {
        return lineShape;
    }

    @Override
    public void rotateObject(double angleShift) {

    }

    @Override
    public void scaleObject(double scaleSize) {

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
        StringBuilder str = new StringBuilder();

        double scaledX1 = getLineShape().getX1()/scaleRatio.getWidth();
        double scaledY1 = getLineShape().getY1()/scaleRatio.getHeight();
        double scaledX2 = getLineShape().getX2()/scaleRatio.getWidth();
        double scaledY2 = getLineShape().getY2()/scaleRatio.getHeight();

        str.append(" ").append(scaledX1).append(" ").append(scaledY1);
        str.append(" ").append(scaledX2).append(" ").append(scaledY2);
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
}