package MouseListeners;

import DrawnShapes.*;
import JFrameWindow.CanvasPanel;
import JFrameWindow.MainJFrameWindow;
import NewShapes.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TwoPointMouseListener extends MouseAdapter {

    private CanvasPanel canvasPanel;//

    private Point startPoint;
    private  Point endPoint;
    private boolean processStarted = false;


    public TwoPointMouseListener(){
        this.canvasPanel = MainJFrameWindow.getCanvas();

    }
    //should be done globally





    @Override
    public void mousePressed(MouseEvent e) {

        super.mousePressed(e);
        this.startPoint = new Point(e.getX(),e.getY());

        System.out.println("Entered Mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Entered Mouse Released");

        updateCanvas();
        drawableObject createdShape = null;
        Point endpoint = new Point(e.getX(),e.getY());
        ShapeType shapeselection = MainJFrameWindow.getShapeSelection();

        /*

        line free
        rectangle Free

        ellipse fixed
        ellipse free
        rectangle

        fixed polygon
getPlotShape().
           /still need to add side modifier for polygon
         */
        if(shapeselection == ShapeType.LINEBAS){
            createdShape = new LinePack(startPoint,endpoint);

        } else if(shapeselection == ShapeType.RECTFREE){
            createdShape = new RectanglePack(startPoint,endpoint);

        } else if(shapeselection == ShapeType.ELLFIX) {
            createdShape = new EllipsePack(startPoint, endpoint, false);

        }else if(shapeselection == ShapeType.ELLFREE){
                createdShape = new EllipsePack(startPoint,endpoint,true);

        } else if(shapeselection == ShapeType.POLYFIX){
            createdShape = new PolygonPack( startPoint,endpoint,6,true);

        } else if(shapeselection == ShapeType.POLYSTAR){
            createdShape = new PolygonPack( startPoint,endpoint,6,false);
        }

        this.canvasPanel.addShapeToCanvas(createdShape);

        System.out.println("Created Shape");

        this.startPoint = null;



    }

    public void mouseDragged(){

    }



    public void updateCanvas(){
        this.canvasPanel = MainJFrameWindow.getCanvas();
    }



}
