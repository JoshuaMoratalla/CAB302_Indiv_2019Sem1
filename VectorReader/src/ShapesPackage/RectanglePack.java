
package ShapesPackage;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectanglePack implements drawableObject, fillableObject {

    private Rectangle2D.Float rectangleShape;
    private Color lineColor = Color.black;
    private Color fillColor =transparent;

    public RectanglePack(Point2D startPoint, Point2D endPoint){
        int x,y, width,height;

        if(startPoint.getX()<endPoint.getX()){
            x =(int) startPoint.getX();
        }else{
            x =(int) endPoint.getX();
        }

        if(startPoint.getY()<endPoint.getY()){
            y =(int) startPoint.getY();
        }else{
            y =(int) endPoint.getY();
        }

        width  = (int) Math.abs(endPoint.getX() -startPoint.getX());
        height = (int)Math.abs(endPoint.getY() -startPoint.getY());

        this.rectangleShape = new Rectangle2D.Float(x,y,width,height);
    }

    public Rectangle2D.Float getRectangleShape() {
        return this.rectangleShape;
    }

    @Override
    public Shape getShape() {
        return this.rectangleShape;
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
        StringBuilder str = new StringBuilder("RECTANGLE");

        double scaledX1 = getRectangleShape().getMinX()/scaleRatio.getWidth();
        double scaledY1 = getRectangleShape().getMinY()/scaleRatio.getHeight();
        double scaledX2 = getRectangleShape().getMaxX()/scaleRatio.getWidth();
        double scaledY2 = getRectangleShape().getMaxY()/scaleRatio.getHeight();

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

    @Override
    public String toFillColorString(Color currentFillColor) {
        if(currentFillColor != getFillColor()){
            return String.format("FILL %s", Integer.toHexString(getFillColor().getRGB()));
        }else{
            return null;
        }
    }
}

