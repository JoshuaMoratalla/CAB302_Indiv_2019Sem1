package DrawnShapes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleFree extends Rectangle2D.Float implements drawableObject,fillableObject {

    private Color fillColor = ShapeConstants.transparent;
    private Color lineColor = Color.black;

    public RectangleFree(Point2D startPoint, Point2D endPoint){
        super((startPoint.getX()>endPoint.getX())?(int) startPoint.getX():(int)endPoint.getX(),
                (startPoint.getX()>endPoint.getX())? (int)startPoint.getX():(int)endPoint.getX(),
                (int) Math.abs(endPoint.getX() -startPoint.getX()),
                (int)Math.abs(endPoint.getY() -startPoint.getY()));
    }

    @Override
    public Shape getShape() {
        return null;
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
        StringBuilder str = new StringBuilder("RECTANGLE");
        str.append(" ").append(getMinX()).append(" ").append(getMinY());
        str.append(" ").append(getMaxX()).append(" ").append(getMaxY());
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
