package ShapesPackage;

import java.awt.Color;
import java.awt.Shape;
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
    public String toVectorString() {
        StringBuilder str = new StringBuilder("LINE");
        str.append(" ").append(getPlotShape().getX1()).append(" ").append(getPlotShape().getY1());
        str.append(" ").append(getPlotShape().getX2()).append(" ").append(getPlotShape().getY2());
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