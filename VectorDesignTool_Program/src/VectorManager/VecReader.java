package VectorManager;

import GUI.PaintCanvas;
import Shapes.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class Vec Reader is used for translating Files and Paint canvases.
 * @author Joshua Moratalla
 * @since 06-02-2019
 */
public class VecReader {

    /**
     * The Dimension used to correctly scale the coordinates
     * which is sourced by the component using the reader
     */
    private Dimension canvasDimension;

    /**
     * Class Constructor of VecReader
     * @param input the dimension to make the ratio and scale of the
     *              coordinates correct when translated to and from
     *              a load or save trigger.
     */
    public VecReader(Dimension input){
        canvasDimension = input;
    }

    /**
     * Retrieves the scale dimensions width
     * @return the x dimension
     */
    public double getXDimension() {
        return this.canvasDimension.getWidth();
    }

    /**
     * Retrieves the scale dimensions height
     * @return the y dimension
     */
    public double getYDimension() {
        return this.canvasDimension.getWidth();
    }

    /**
     * Translates the shapes of a PaintCanvas into a file and returns it when filled with the
     * vec commands.
     * @param canvas the canvas from which it will provide the drawable objects to translate
     * @param file the file from which the user selected to fill
     * @return the file after it is filled with the vec commands
     * @throws IOException
     */
    public File TranslateCanvas(PaintCanvas canvas , File file) throws IOException{
        ArrayList<drawableObject> canvasShapes = canvas.getDrawableList();

        Color lineColor = Color.black;
        Color fillColor = fillableObject.transparent;

        BufferedWriter textWriter = new BufferedWriter(new FileWriter(file));
        for(drawableObject drawableObject : canvasShapes){

            if(drawableObject instanceof  fillableObject){
                String pen = drawableObject.toLineColorString(lineColor);

                if(pen!=null){
                    textWriter.write(pen);
                    textWriter.newLine();
                }

                String fill = ((fillableObject) drawableObject).toFillColorString(fillColor);
                if(fill != null){
                    textWriter.write(fill);
                    textWriter.newLine();
                }
                textWriter.write(drawableObject.toVectorString(canvasDimension));
                textWriter.newLine();

            }else{
                String pen = drawableObject.toLineColorString(lineColor);
                if(pen!=null){
                    textWriter.write(pen);
                    textWriter.newLine();
                }
                textWriter.write(drawableObject.toVectorString(canvasDimension));
                textWriter.newLine();
            }
        }
        textWriter.close();


        return file;
    }

    /**
     *Returns a list of drawableObjects for the new cPaint canvas to have
     * @param file the file from which it sources its vec commands.
     * @return
     * @throws IOException
     */
    public ArrayList<drawableObject> TranslateFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> linesOfText = new ArrayList<>();

        String lineOfText;
        while ((lineOfText = bufferedReader.readLine()) != null){
            linesOfText.add(lineOfText);
        }
        bufferedReader.close();

        Color lineColor = Color.BLACK;
        Color fillColor = fillableObject.transparent;
        ArrayList<drawableObject> shapePack = new ArrayList<>();
        for(String string: linesOfText){
            //reads if it is a shape command
            drawableObject lineToShape =translateLine(string);
            if( lineToShape != null){
                lineToShape.changeLineColor(lineColor);
                if(lineToShape instanceof fillableObject){
                    ((fillableObject) lineToShape).changeFillColor(fillColor);
                }
                shapePack.add(lineToShape);
            }else{
                String[] splitString = string.split(" ");
                if(splitString[0].equals("PEN")){
                    lineColor = translateColor( splitString[1]);
                }else if(splitString[0].equals("FILL")){
                    fillColor = translateColor( splitString[1]);
                }else{
                    System.out.print("UnreadAble Line Ignored");
                }
            }
        }
        return shapePack;
    }

    /**
     * Returns a drawable object based on the string inputted.
     * @param input the string that assumptively possesses vec commands.
     * @return the equivalent drawobject.
     */
    private drawableObject translateLine(String input){
        String[] lineSplit = input.split(" ");
        String firstPart = lineSplit[0];
        System.out.println(firstPart +"||");

        if(firstPart.equals("PEN") ||firstPart.equals("FILL") ){
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

            }
            return drawObject;
        }
    }

    /**
     * Returns the translation of a hex decimal color into an instance of Color.
     * @param input the string that contains the hexadecimal color
     * @return
     */
    private Color translateColor( String input){
        return Color.decode(input);

    }

    /**
     * Converts the String array into the equivalent Polygon
     * @param lineset the String array with the coordinates of the shape
     * @return
     */
    private PolygonPack convertStringToPolygon(String[] lineset){
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
            int newX = (int)(xPoints[n] * getXDimension());
            int newY =(int) (yPoints[n] * getYDimension());
            newPoints.add(new Point2D.Float(newX,newY));

            System.out.print( String.format("|%d , %d |", newX,newY));

        }
        System.out.println();
        return  new PolygonPack(newPoints);
    }

    /**
     * Converts the String array into the equivalent Line
     * @param lineset the String array with the coordinates of the shape
     * @return
     */
    private LinePack convertStringToLine(String[] lineset){
        int numbersToMakeLine =5;
        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];


        // Dimension displayFrame = this.getSize();

        for(int n = 0; n <frontSnip.length; n++){
            System.out.println(n);
            if(n %2 == 0){
                indivPoints[n] = Double.parseDouble(frontSnip[n]) * getXDimension() ;
            }else{
                indivPoints[n] = Double.parseDouble(frontSnip[n]) * getYDimension();
            }

            //indivPoints[n+1] = Double.parseDouble(frontSnip[n+1]) * displayFrame.getHeight();
        }

        Point2D startPoint = new Point2D.Double(indivPoints[0] ,indivPoints[1]);
        Point2D endPoint = new Point2D.Double(indivPoints[2],indivPoints[3]);

        System.out.print( String.format("LINE %2f %2f %2f %2f", indivPoints[0],indivPoints[1],indivPoints[2],indivPoints[3]));

        return new LinePack(startPoint,endPoint);
    }

    /**
     * Converts the String array into the equivalent Ellipse
     * @param lineset the String array with the coordinates of the shape
     * @return
     */
    private EllipsePack convertStringToEllipse(String[] lineset){
        int numbersToMakeLine =5;

        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];

        for(int n = 0; n <frontSnip.length; n++){
            indivPoints[n] = Double.parseDouble(frontSnip[n]);
        }

        // Dimension displayFrame = this.getSize();

        Point2D startPoint = new Point2D.Double(indivPoints[0]* getXDimension(),indivPoints[1] * getYDimension());
        Point2D endPoint = new Point2D.Double(indivPoints[2] * getXDimension(),indivPoints[3]* getYDimension());

        return new EllipsePack(startPoint,endPoint,true);
    }

    /**
     * Converts the String array into the equivalent Rectangele
     * @param lineset the String array with the coordinates of the shape
     * @return
     */
    private RectanglePack convertStringToRectangle(String[] lineset){
        int numbersToMakeLine =4;

        String[] frontSnip  = Arrays.copyOfRange(lineset,1,numbersToMakeLine );
        double[] indivPoints = new double[numbersToMakeLine];

        for(int n = 0; n <frontSnip.length; n++){
            indivPoints[n] = Double.parseDouble(frontSnip[n]);
        }

        Point2D startPoint = new Point2D.Double(indivPoints[0] *  getXDimension(),indivPoints[1]* getYDimension());
        Point2D endPoint = new Point2D.Double(indivPoints[2]*  getXDimension(),indivPoints[3]* getYDimension());

        return new RectanglePack(startPoint,endPoint);
    }

    /**
     * Converts the String array into the equivalent Plot
     * @param lineset the String array with the coordinates of the shape
     * @return
     */
    private PlotPack convertStringToPlot(String[] lineset){
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
