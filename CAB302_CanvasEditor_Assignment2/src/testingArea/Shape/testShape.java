package testingArea.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class testShape extends JFrame {

    public Point2D centrePoint = new Point.Double(0.50,0.50);

    public testShape(){
        super("Shape Testing App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setPreferredSize(new Dimension(500,500));
        this.setVisible(true);
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(2));
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        g2D.setPaint(Color.BLACK);

        drawAPolygon(g2D,new Point (100,100),50,6);
        drawAPolygon(g2D,new Point (200,100),50,7);
        drawAPolygon(g2D,new Point (300,100),50,8);
        drawAPolygon(g2D,new Point (400,100),50,9);

        /*
        int cornerPoints = 6; //test hexagon
        double radius = 1000;
        ArrayList<Point> PointList= new ArrayList<>(6);
        for(int plotCount = 0; plotCount< cornerPoints ; plotCount++){
            System.out.print(Integer.toString(plotCount));
            double angle = plotCount * 60;

            int xCoord = (int)(radius *Math.cos(angle));
            int yCoord = (int)(radius * Math.sin(angle));
            PointList.add(new Point(xCoord,yCoord));
            //g.drawLine();
        }

        for(int lineCount = 0; lineCount < cornerPoints; lineCount++){
            if(lineCount!= cornerPoints -1){
                Point start = PointList.get(lineCount);
                Point end = PointList.get(lineCount +1);

                Shape line  = new Line2D.Float(start.x,start.y,end.x,end.y);
                g2D.draw(line);
            }else{
                Point start = PointList.get(lineCount);
                Point end = PointList.get(0);

                Shape line  = new Line2D.Float(start.x,start.y,end.x,end.y);
                g2D.draw(line);
            }
        }
        */

    }

    public void drawAPolygon(Graphics2D g , Point centre, int radius, int sides){

        Polygon polygon = new Polygon();

        double radianStartVertically = -1.5708;

        for(int iterationCount = 0; iterationCount < sides; iterationCount ++){
            int xCoord = (int) (centre.x + radius * Math.cos(((iterationCount * 2 * Math.PI )/ sides) + radianStartVertically));
            int yCoord = (int) (centre.y + radius * Math.sin(((iterationCount * 2 * Math.PI )/ sides) + radianStartVertically));

            polygon.addPoint(xCoord,yCoord);

        }
        g.draw(polygon);


    }

    public static void main( String []arg){
        new testShape();
    }
}
