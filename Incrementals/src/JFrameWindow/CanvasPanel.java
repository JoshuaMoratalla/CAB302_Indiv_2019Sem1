package JFrameWindow;

import DrawnShapes.ShapeType;
import DrawnShapes.drawableObject;
import MouseListeners.TwoPointMouseListener;
import NewShapes.LinePack;
import NewShapes.PlotPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CanvasPanel extends JComponent {


    private ArrayList<drawableObject> shapeList = new ArrayList<>();

    private ArrayList<Point> pointList;

    //static one point mouse listeners
    //public static MouseAdapter twoPointListener = new TwoPointMouseListener();
    //static three point mouse listeners


    //on a global scale the mouse listeners will be applied to the mainPanel/tabbedPane while
    //so each canvas child of the tabbedpane doesnt have to iterate mouse listeners for every child.

    public CanvasPanel(){

        this.setSize(1000,1000);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                ShapeType shapeType = MainJFrameWindow.getShapeSelection();
                //single points
                if(shapeType == ShapeType.LINEPOINT){
                    Point2D point = new Point2D.Float(e.getX(),e.getY());
                    getShapeList().add(new PlotPack(point));
                    pointList.clear();
                }else if( pointList.size()==1 && ( shapeType == ShapeType.LINEBAS || shapeType == ShapeType.RECTFREE||
                        shapeType == ShapeType.ELLFIX || shapeType == ShapeType.POLYFIX || shapeType == ShapeType.POLYSTAR
                        )){
                    getPointList().add(new Point(e.getX(),e.getY()));

                }

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }
        });
        //for now we're saying the CanvasPanel is the TabbedPane

        //it will have all the mouse listeners
    }

    public ArrayList<Point> getPointList() {
        return this.pointList;
    }

    public ArrayList<drawableObject> getShapeList() {
        return this.shapeList;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //paintBackground(g2);

        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

        for(drawableObject object : shapeList) {
            g2.setPaint(Color.BLACK);
            g2.draw(object.getShape());
            System.out.println("should be drawing");
        }
    }

    public void addShapeToCanvas(drawableObject shapeToAdd){
        this.shapeList.add(shapeToAdd);

    }


    //
//public void updateMouseListener(){
//  ShapeType shapetype = MainJFrameWindow.getShapeSelection();
//  if(shapetype ==  ShapeType.LINEBAS || shapetype ==  ShapeType.RECTFREE ||
//      shapetype ==  ShapeType.ELLFIX || shapetype ==  ShapeType.ELLFREE ||
//      shapetype ==  ShapeType.POLYFIX){
//      addMouseListener(twoPointListener);
//  }
//}
//


}
