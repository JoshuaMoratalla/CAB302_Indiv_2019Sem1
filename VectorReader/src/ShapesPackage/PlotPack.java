package ShapesPackage;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class PlotPack implements drawableObject {
    private Line2D.Float plotShape ;
    private Color lineColor = Color.black;

    public PlotPack(Point2D point){
        this.plotShape = new Line2D.Float(point,point);
    }

    public Line2D.Float getPlotShape() {
        return this.plotShape;
    }

    @Override
    public Shape getShape() {
        return this.plotShape;
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
    public String toVectorString(Dimension scaleRatio) {
        StringBuilder str = new StringBuilder("PLOT");

        double scaledX1 = getPlotShape().getX1()/scaleRatio.getWidth();
        double scaledY1 =  getPlotShape().getY1()/scaleRatio.getHeight();

        str.append(" ").append(scaledX1).append(" ").append(scaledY1);
        return str.toString();
    }

    @Override
    public String toLineColorString(Color currentPenColor) {
        if(currentPenColor != getLineColor()){
            return String.format("PEN #%s", Integer.toHexString(getLineColor().getRGB()));
        }else{
            return null;
        }
    }
}