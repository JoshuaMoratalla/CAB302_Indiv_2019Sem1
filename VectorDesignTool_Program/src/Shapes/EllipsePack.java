package Shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class EllipsePack implements drawableObject, fillableObject {

    private Ellipse2D.Float ellipseShape;
    private Color lineColor = Color.black;
    private Color fillColor =transparent;


    /**
     *
     * @param startPoint centre point of the circle to be made
     * @param endPoint the edge of the circle if its fixed, if not then its the other point
     * @param isFree controller for whether it is fixed or free
     */
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
            double distance = startPoint.distance(endPoint);//dis
            int x = (int)(startPoint.getX() - distance);
            int y = (int)(startPoint.getY() - distance);
            int width = (int)distance*2;
            int height = (int) distance*2;
            this.ellipseShape = new Ellipse2D.Float(x,y,width,height);
        }

    }

    private Ellipse2D.Float getEllipseShape() {
        return this.ellipseShape;
    }

    @Override
    public Shape getShape() {
        return this.ellipseShape;
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
    public String toVectorString(Dimension scaleRatio) {
        StringBuilder str = new StringBuilder("ELLIPSE");

        double scaledX1 = getEllipseShape().getMinX()/scaleRatio.getWidth();
        double scaledY1 = getEllipseShape().getMinY()/scaleRatio.getHeight();
        double scaledX2 = getEllipseShape().getMaxX()/scaleRatio.getWidth();
        double scaledY2 = getEllipseShape().getMaxY()/scaleRatio.getHeight();

        str.append(" ").append(scaledX1).append(" ").append(scaledY1);
        str.append(" ").append(scaledX2).append(" ").append(scaledY2);

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
    public String toFillColorString(Color currentFillColor) {
        if(currentFillColor != getFillColor()){
            return String.format("FILL #%s", Integer.toHexString(getFillColor().getRGB()));
        }else{
            return null;
        }
    }


}