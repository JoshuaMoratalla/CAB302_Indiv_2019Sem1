package FileReader;

import ShapesPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class PaintCanvas extends JComponent {

    private ArrayList<drawableObject>  drawableList;
    private ArrayList<String> stringList;
    public static Dimension  ratioDimension = new Dimension(1000,1000);
    //hypothetical dimensions.




    public static Color transparent = new Color (0,0,0);

    private Color lineColor = Color.black;
    private Color fillColor =transparent;

    public PaintCanvas(){
        this.drawableList = new ArrayList<>();
    }

    public PaintCanvas(ArrayList<String> input){
        this.drawableList = new ArrayList<>();

        System.out.println("PaintCanvas is initiated");

        for(String string: input){
            //System.out.println(string);
        }
        this.stringList = input;

        for(String string: this.stringList){
            //System.out.println(string);
            drawableObject draw = translateLine(string);

            if(draw!= null){
                draw.changeLineColor(this.lineColor);
                if( draw instanceof fillableObject){
                    ((fillableObject) draw).changeFillColor(this.fillColor);
                }
                getDrawableList().add(draw);


                System.out.print(draw.getLineColor().toString());
                System.out.print(draw.toVectorString());
            }



            // drawableObject draw = getDrawableList().get(getDrawableList().size());

           // System.out.println(draw.toVectorString());
        }
        //read line
        //determine if its pen fill or shape initilization
            //if pen and or fill change relative contexts to color
            //if initilization handle names and split variables by space  >" "<

        //process goes like: change to line Color > draw shape > change toFill color  > fill shape
    }

    public Color getLineColor() {
       return  this.lineColor;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public ArrayList<drawableObject> getDrawableList(){
        return this.drawableList;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //paintBackground(g2);

        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

        g2.setPaint(this.lineColor);

        for(drawableObject drawObject: getDrawableList()){
            Shape shape = drawObject.getShape();
            g2.setPaint(drawObject.getLineColor());
            g2.draw(shape);
            if(drawObject instanceof  fillableObject){
                g2.setPaint(((fillableObject) drawObject).getFillColor());
                g2.fill(shape);
            }
        }


}


    //still need to assign color to line and fill
    public drawableObject translateLine( String input){
        String[] lineSplit = input.split(" ");
        String firstPart = lineSplit[0];
        System.out.print(firstPart +"||");


        if(firstPart.equals("PEN")){
            System.out.println("TextLine is a color command");
            translateLineColor(lineSplit);
            return null;
        }else if( firstPart.equals("FILL")){
            System.out.println("TextLine is a color command");
            translateFillColor(lineSplit);
            return null;
        }else{

            drawableObject drawObject =  null;
            if(firstPart.equals("POLYGON")){
                System.out.println("TextLine is a polygon");
                drawObject = convertStringToPolygon(lineSplit);
            }else if(firstPart.equals("LINE")){
                System.out.println("TextLine is a line");
                drawObject = convertStringToLine(lineSplit);
            }else if(firstPart.equals("ELLIPSE")){
                System.out.println("TextLine is an ellipse");
                drawObject = convertStringToEllipse(lineSplit);
            }else if(firstPart.equals("RECTANGLE")){
                System.out.println("TextLine is a rectangle");
                drawObject = convertStringToRectangle(lineSplit);

            }else if(firstPart.equals("PLOT")){
                System.out.println("TextLine is a plot");
                drawObject = convertStringToPlot(lineSplit);
            }else{
                System.out.println("Feck");
            }
            //translateShape(drawObject, g2);


            return drawObject;
        }


    }

/*
    public void translateShape(drawableObject shapeObject, Graphics2D g2 ){
        Shape shape = shapeObject.getShape();

        g2.setPaint(getLineColor());
        g2.draw(shape);

        g2.setPaint(getFillColor());
        g2.fill(shape);

    }
*/
    public void translateFillColor( String[] input){
        this.lineColor = Color.decode(input[1]);

    }

    public  void translateLineColor( String[] input){
        this.fillColor = Color.decode(input[1]);
    }


    public PolygonPack convertStringToPolygon(String[] lineset){
        System.out.println("returned a poly");
        String[] frontSnip  = Arrays.copyOfRange(lineset,1,lineset.length );
        int indivCoords = frontSnip.length;
        double[] xPoints = new double[indivCoords/2];
        double[] yPoints = new double[indivCoords/2];

        for(int n = 0; n < frontSnip.length/2 ; n++){
            xPoints[n] = Double.parseDouble(frontSnip[2*n]);
            yPoints[n] =Double.parseDouble(frontSnip[(2*n)+1]);
        }

        //making points
        ArrayList<Point2D> newPoints = new ArrayList<>();
        for(int n = 0; n < indivCoords/2; n++){
            int newX = (int)(xPoints[n] * ratioDimension.getWidth());
            int newY =(int) (yPoints[n] * ratioDimension.getHeight());
            newPoints.add(new Point2D.Float(newX,newY));

            System.out.print( String.format("|%d , %d |", newX,newY));

        }
        System.out.println();
        return  new PolygonPack(newPoints);
    }

    public LinePack convertStringToLine(String[] lineset){
        int numbersToMakeLine =5;
        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];


      // Dimension displayFrame = this.getSize();
        Dimension displayFrame = new Dimension(1000,1000);

        for(int n = 0; n <frontSnip.length; n++){
            System.out.println(n);
            if(n %2 == 0){
                indivPoints[n] = Double.parseDouble(frontSnip[n]) * ratioDimension.getWidth() ;
            }else{
                indivPoints[n] = Double.parseDouble(frontSnip[n]) * ratioDimension.getHeight() ;
            }

            //indivPoints[n+1] = Double.parseDouble(frontSnip[n+1]) * displayFrame.getHeight();
        }

        Point2D startPoint = new Point2D.Double(indivPoints[0] ,indivPoints[1]);
        Point2D endPoint = new Point2D.Double(indivPoints[2],indivPoints[3]);

        System.out.print( String.format("LINE %2f %2f %2f %2f", indivPoints[0],indivPoints[1],indivPoints[2],indivPoints[3]));

        return new LinePack(startPoint,endPoint);
    }

    public EllipsePack convertStringToEllipse( String[] lineset){
        int numbersToMakeLine =5;

        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];

        for(int n = 0; n <frontSnip.length; n++){
            indivPoints[n] = Double.parseDouble(frontSnip[n]);
        }

        // Dimension displayFrame = this.getSize();
        Dimension displayFrame = new Dimension(1000,1000);

        Point2D startPoint = new Point2D.Double(indivPoints[0]* ratioDimension.getWidth(),indivPoints[1] *ratioDimension.getHeight());
        Point2D endPoint = new Point2D.Double(indivPoints[2] *ratioDimension.getWidth(),indivPoints[3]*ratioDimension.getHeight());

        return new EllipsePack(startPoint,endPoint,true);
    }

    public RectanglePack convertStringToRectangle(String[] lineset){
        int numbersToMakeLine =4;

        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];

        for(int n = 0; n <frontSnip.length; n++){
            indivPoints[n] = Double.parseDouble(frontSnip[n]);
        }

        Point2D startPoint = new Point2D.Double(indivPoints[0] * ratioDimension.getWidth(),indivPoints[1]* ratioDimension.getHeight());
        Point2D endPoint = new Point2D.Double(indivPoints[2]* ratioDimension.getWidth(),indivPoints[3]* ratioDimension.getHeight());

        return new RectanglePack(startPoint,endPoint);
    }

    public PlotPack convertStringToPlot(String[] lineset){
        int numbersToMakeLine =2;

        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];

        for(int n = 0; n <frontSnip.length; n++){
            indivPoints[n] = Double.parseDouble(frontSnip[n]);
        }

        Point2D startPoint = new Point2D.Double(indivPoints[0],indivPoints[1]);


        return new PlotPack(startPoint);
    }




}
