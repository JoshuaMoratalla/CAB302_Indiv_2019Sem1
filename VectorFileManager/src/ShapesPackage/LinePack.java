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
    public String toVectorString() {

        StringBuilder str = new StringBuilder();
        str.append(" ").append(getLineShape().getX1()).append(" ").append(getLineShape().getY1());
        str.append(" ").append(getLineShape().getX2()).append(" ").append(getLineShape().getY2());
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
