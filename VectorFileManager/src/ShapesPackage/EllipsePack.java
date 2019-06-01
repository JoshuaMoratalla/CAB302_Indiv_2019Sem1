package ShapesPackage;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class EllipsePack implements drawableObject, fillableObject {

    private Ellipse2D.Float ellipseShape;
    private Color lineColor = Color.black;
    private Color fillColor =transparent;


    public EllipsePack(Point2D startPoint, Point2D endPoint, boolean isFree){ //making a circle

        if(isFree){
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

            this.ellipseShape = new Ellipse2D.Float(x,y,width,height);
        }else{
            double distance = startPoint.distance(endPoint);
            int x = (int)(startPoint.getX() - distance);
            int y = (int)(startPoint.getY()-distance);
            int width = (int)distance;
            int height = (int) distance;
            this.ellipseShape = new Ellipse2D.Float(x,y,width,height);
        }

    }

    public Ellipse2D.Float getEllipseShape() {
        return this.ellipseShape;
    }

    @Override
    public Shape getShape() {
        return this.ellipseShape;
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
        str.append(" ").append(getEllipseShape().getMinX()).append(" ").append(getEllipseShape().getMinY());
        str.append(" ").append(getEllipseShape().getMaxX()).append(" ").append(getEllipseShape().getMaxY());
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
