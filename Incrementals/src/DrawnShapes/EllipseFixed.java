package DrawnShapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;



// ***************************************************************************************
// ********************************Everything Checked*************************************
// *********************************Except For Tools**************************************
// ***************************************************************************************

public class EllipseFixed extends Ellipse2D.Float implements drawableObject,fillableObject {
    private Color lineColor = Color.black;
    private Color fillColor =transparent;


    public EllipseFixed(Point2D centre, Point2D Edge){ //making a circle
        super();

        double distance = centre.distance(Edge);
        super.x = (int)(centre.getX() - distance);
        super.y = (int)(centre.getY()-distance);
        super.height = (int) distance;
        super.width = (int)distance;

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
    public Color getFillColor() {
        return this.fillColor;
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
    public String toVectorString() {
        StringBuilder str = new StringBuilder("ELLIPSE");
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
    public String toFillColorString(Color currentFillColor) {
        if(currentFillColor != getFillColor()){
            return String.format("FILL %s", Integer.toHexString(getFillColor().getRGB()));
        }else{
            return null;
        }
    }


}
