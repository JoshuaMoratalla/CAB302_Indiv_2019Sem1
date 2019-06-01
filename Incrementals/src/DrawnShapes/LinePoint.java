package DrawnShapes;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

// ***************************************************************************************
// ********************************Everything Checked*************************************
// *********************************Except For Tools**************************************
// ***************************************************************************************

public class LinePoint extends Line2D.Float implements drawableObject {

    private Color lineColor = Color.black;

    public LinePoint(Point2D.Float point){
        super(point,point);
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
        return this.getLineColor();
    }

    @Override
    public void changeLineColor(Color newColor) {
        this.lineColor = newColor;
    }


    @Override
    public String toVectorString() {
        StringBuilder str = new StringBuilder("LINE");
        str.append(" ").append(getX1()).append(" ").append(getY1());
        str.append(" ").append(getX2()).append(" ").append(getY2());
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
