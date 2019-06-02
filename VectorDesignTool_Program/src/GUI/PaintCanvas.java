package GUI;

import Shapes.*;
import VectorManager.VecReader;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Class PainCanvas is used for holding the canvas on which the user draws on.
 * @author Joshua Moratalla
 * @since 06-02-2019
 */
public class PaintCanvas extends JComponent {

    /**
     * ArrayList of drawableObjects for this PaintCanvas instance.
     */
    private ArrayList<drawableObject>  drawableList;

    /**
     * ArrayList of indexes in the drawableList that can be deleted.
     */
    private ArrayList<Integer> UndoList;

    /**
     * Max size for undo history.
     */
    public static int MaxUndoSize = 10;

    /**
     * Transparent variable, however I am not sure if i was supposed to reference the other transparent in my fillable
     * object interface.
     */
    public static Color transparent = new Color (0,0,0);

    private Color lineColor = Color.black;


    /**
     * Class Constructor for new Canvases.
     */
    public PaintCanvas(){
        this.drawableList = new ArrayList<>();
        this.UndoList = new ArrayList<>();
    }


    /**
     *  Class Constructor for loaded files.
     * @param file
     * @throws IOException
     */
    public PaintCanvas(File file) throws IOException {
        System.out.println("PaintCanvas is initiated");
        this.UndoList = new ArrayList<>();

        VecReader vecReader = new VecReader(new Dimension(1000,1000));
        this.drawableList = vecReader.TranslateFile(file);
    }

    /**
     * Retrieves drawing list.
     * @return the drawableObject List associated with this instance of PaintCanvas.
     */
    public ArrayList<drawableObject> getDrawableList(){
        return this.drawableList;
    }

    /**
     * Retrieves the UndoList
     * @return the UndoList associated with this instance of PaintCanvas.
     */
    private ArrayList<Integer> getUndoList() {
        return this.UndoList;
    }

    /**
     *  Adds a shape to the drawableObject List;
     * @param newShape the drawableObject to be added into the PaintCanvas' list of drawables
     */
    public void addShapeToCanvas(drawableObject newShape){
        getDrawableList().add(newShape);

        while(getUndoList().size() >= MaxUndoSize){
            getUndoList().remove(0);
        }
        getUndoList().add(getDrawableList().size());
        repaint();
    }

    /**
     *  Removes the shapes associated by the UndoList's index value
     * @param UndoPosition The position of which the undo list will clear the shapes
     */
    public void UndoFromHistory(int UndoPosition){
        while (getUndoList().size() > UndoPosition){
            int size = getUndoList().size();
            int drawPos = getUndoList().get(size);
            getDrawableList().remove(drawPos);
            getUndoList().remove(size);
        }
    }

    /**
     *Removes the shape at the top of the Undo list then removes the Undo position
     */
    public void UndoMostRecent(){
        int size = getUndoList().size();
        int drawPos = UndoList.get(size);
        getDrawableList().remove(drawPos);
        getUndoList().remove(size);
    }

    /**
     * Function used to iterate and color all the shapes in the drawable list
     * @param g the graphics associted with the Component of this PaintCanvas instance
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(1));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        g2.setPaint(this.lineColor);

        g2.draw(new Rectangle2D.Float(0,0,this.getWidth(),this.getHeight()));

        for(drawableObject drawObject: getDrawableList()){
            Shape shape = drawObject.getShape();
            g2.setPaint(drawObject.getLineColor());
            g2.draw(shape);
            if(drawObject instanceof  fillableObject && ((fillableObject) drawObject).getFillColor() != transparent){
                g2.setPaint(((fillableObject) drawObject).getFillColor());
                g2.fill(shape);
            }
        }
    }


}