package FileReader;

import ShapesPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PaintCanvas extends JComponent {

    private ArrayList<drawableObject>  drawableList;



    public static Color transparent = new Color (0,0,0);

    private Color lineColor = Color.black;
    private Color fillColor =transparent;

    public PaintCanvas(){
        this.drawableList = new ArrayList<>();
    }

    public PaintCanvas(File file) throws IOException {


        System.out.println("PaintCanvas is initiated");



        VecReader vecReader = new VecReader(new Dimension(984,938));

        this.drawableList = vecReader.TranslateFile(file);
        validate();
        repaint();

    }
    public File SaveFile(File saveDialogfile) throws IOException {

        VecReader vecReader = new VecReader(this.getSize());
        File filledFile = vecReader.TranslateCanvas(this,saveDialogfile);
        return filledFile;
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