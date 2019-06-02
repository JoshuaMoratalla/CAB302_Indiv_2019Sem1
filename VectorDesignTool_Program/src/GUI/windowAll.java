package GUI;

import Shapes.*;
import VectorManager.VecReader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class windowAll extends JFrame implements ActionListener {

    public static Dimension staticDimensions = new Dimension(1016,1062);
    private ShapeType shapeSelection =ShapeType.LINEBAS;

    private Color currentLineColor = Color.black;
    private JComponent LineColorShower;

    private Color currentFillColor = fillableObject.transparent;
    private JComponent FillColorShower;

    private ArrayList<Point2D> mouseClicks;
    private PaintCanvas paintCanvas;


    public windowAll(){
        setWindow();
        setMenuBar();
        this.paintCanvas = setPaintCanvas();
        this.paintCanvas.addMouseListener(setMouseListener());
        add(getPaintCanvas());
        /*
        this.paintCanvas = setPaintCanvas();
        this.paintCanvas.addMouseListener(setMouseListener());
        add(getPaintCanvas());
        */

        pack();
        setVisible(true);
    }

    public ShapeType getShapeSelection() {
        return this.shapeSelection;
    }

    public Color getCurrentLineColor() {
        return this.currentLineColor;
    }

    public JComponent getLineColorShower() {
        return this.LineColorShower;
    }

    public ArrayList<Point2D> getMouseClicks() {
        return this.mouseClicks;
    }

    public PaintCanvas getPaintCanvas() {
        return this.paintCanvas;
    }

    private void setWindow(){
        setPreferredSize(staticDimensions);

        Dimension windowPos = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( new Point((int)(windowPos.getWidth() - staticDimensions.getWidth())/2,(int) (windowPos.getHeight() - staticDimensions.getHeight())/2));
    }

    private void setMenuBar(){
        JMenuBar menuBAR = new JMenuBar();
        menuBAR.setOpaque(true);
        menuBAR.setSize(200,20);
        menuBAR.setBackground(Color.gray);
        menuBAR.setForeground(Color.BLUE);


        JMenu menuFile = setFileMenu();
        JMenu menuShapes = setShapesMenu();
        JMenu menuColors = setColorMenu(); ////Unimplemented yet
        JMenu menuUndoHistory = new JMenu("History");////Unimplemented yet

        menuBAR.add(menuFile);

        menuBAR.add(menuShapes);
        menuBAR.add(menuColors);
        menuBAR.add(menuUndoHistory);

        setJMenuBar(menuBAR);
    }

    private JMenu setFileMenu(){
        JMenu menuFile = new JMenu("File");
        JButton buttonNew = new JButton("New");
        buttonNew.setActionCommand("New");

        JButton buttonLoad = new JButton("Load");
        buttonLoad.setActionCommand("Load");

        JButton buttonSave= new JButton("Save");
        buttonSave.setActionCommand("Save");

        buttonNew.addActionListener(this::actionPerformed);
        buttonLoad.addActionListener(this::actionPerformed);
        buttonSave.addActionListener(this::actionPerformed);

        menuFile.add(buttonNew);
        menuFile.add(buttonLoad);
        menuFile.add(buttonSave);

        return  menuFile;
    }

    private JMenu setShapesMenu(){
        JMenu menuShapes = new JMenu("Shapes");
        ButtonGroup shapeRadioButtons = new ButtonGroup();


        Set<ShapeType> shapeSet = EnumSet.allOf(ShapeType.class);

        ArrayList<JRadioButton> radiobuttAsList = new ArrayList<>();

        for( ShapeType shapeType: shapeSet){
            //init
            JRadioButton button = new JRadioButton(shapeType.getShapeTypeName());
            //init
            button.setActionCommand(shapeType.getShapeTypeName());

            shapeRadioButtons.add(button);
            //menuShapes.add(button); //this is if the button itself has to be added to the menushape
            radiobuttAsList.add(button);

        }

        ActionListener shapeChoiceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMouseClicks().clear();
                for (JRadioButton button : radiobuttAsList){
                    if(e.getSource() == button){
                        shapeSelection= ShapeType.matchEnum( e.getActionCommand());
                        System.out.println(String.format("Shape selection changed to: %s", e.getActionCommand()));
                    }
                }
            }
        };

        for(JRadioButton button: radiobuttAsList){
            button.addActionListener(shapeChoiceListener);
            menuShapes.add(button);
            if(button.getText().equals("Line Basic")){
                button.setSelected(true);
            }
        }


        return menuShapes;
    }

    private JMenu setColorMenu(){
        JMenu colorMenu = new JMenu("Color");
        JButton LineColorButton = new JButton("Line Color");
        LineColorButton.setActionCommand("NewLineColor");

        JButton FillColorButton = new JButton("Fill Color");
        FillColorButton.setActionCommand("NewFillColor");

        LineColorButton.addActionListener(this::actionPerformed);
        this.LineColorShower = new JPanel();
        LineColorShower.setForeground(this.currentLineColor);
        LineColorShower.setBackground(this.currentLineColor);


        FillColorButton.addActionListener(this::actionPerformed);
        this.FillColorShower = new JPanel();
        FillColorShower.setForeground(this.currentFillColor);
        FillColorShower.setBackground(this.currentFillColor);

        colorMenu.add(LineColorButton);
        colorMenu.add(LineColorShower);
        colorMenu.add(FillColorButton);
        colorMenu.add(FillColorShower);

        return colorMenu;
    }

    private PaintCanvas setPaintCanvas(){
        return new PaintCanvas();
    }

    private MouseListener setMouseListener(){
        this.mouseClicks = new ArrayList<>();

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClicks.add(new Point2D.Float(e.getX(),e.getY()));
                System.out.println(String.format("Shape Selection when clicked: %s || Current Clicks: %d ",getShapeSelection().getShapeTypeName(),getMouseClicks().size() ));


                if(mouseClicks.size() ==1  && getShapeSelection().equals(ShapeType.LINEPOINT)){

                    drawableObject newPlot = new PlotPack(e.getPoint());
                    newPlot.changeLineColor(getCurrentLineColor());

                    getPaintCanvas().addShapeToCanvas(newPlot);
                    getMouseClicks().clear();

                    System.out.print( "delivered line");

                }else if(mouseClicks.size() == 2 && !getShapeSelection().equals(ShapeType.LINEPOINT) &&
                        !getShapeSelection().equals(ShapeType.POLYLINE)){
                    drawableObject shape = null;
                    System.out.print( "entered the two point area");
                    if(getShapeSelection().equals(ShapeType.ELLFREE)){
                        shape = new EllipsePack(getMouseClicks().get(0),getMouseClicks().get(1),true);
                    }else if(getShapeSelection().equals(ShapeType.ELLFIX)){
                        shape = new EllipsePack(getMouseClicks().get(0),getMouseClicks().get(1),false);
                    }else if(getShapeSelection().equals(ShapeType.LINEBAS)){
                        shape = new LinePack(getMouseClicks().get(0), getMouseClicks().get(1));
                    } else if(getShapeSelection().equals(ShapeType.RECTFREE)){
                        shape = new RectanglePack(getMouseClicks().get(0), getMouseClicks().get(1));
                    }else if(getShapeSelection().equals(ShapeType.POLYFIX)){
                        shape = new PolygonPack(getMouseClicks().get(0), getMouseClicks().get(1),6, true);
                    }else if(getShapeSelection().equals(ShapeType.POLYSTAR)){
                        shape = new PolygonPack(getMouseClicks().get(0), getMouseClicks().get(1),6, false);
                    }


                    if(shape instanceof fillableObject){
                        ((fillableObject)shape).changeFillColor(currentFillColor);
                    }
                    shape.changeLineColor(currentLineColor);
                    getPaintCanvas().addShapeToCanvas(shape);
                    getMouseClicks().clear();


                }else if( mouseClicks.size() > 2 && getShapeSelection().equals(ShapeType.POLYLINE)){
                    //if normal click or double click
                    if(e.getClickCount() > 1 ){
                        System.out.println("Creating Polygon");
                        drawableObject newPolyLine = new PolygonPack(getMouseClicks());
                        newPolyLine.changeLineColor(currentLineColor);
                        ((fillableObject)newPolyLine).changeFillColor(currentFillColor);
                        getPaintCanvas().addShapeToCanvas(newPolyLine);
                        getMouseClicks().clear();
                    }
                }


            }
        };

        return mouseListener;
    }

    private void makeNewCanvas(){
        remove(this.paintCanvas);
        this.paintCanvas = setPaintCanvas();
        this.paintCanvas.addMouseListener(setMouseListener());
        add(getPaintCanvas());
        validate();//


    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //needs to be replicated for the fill color
        if(e.getActionCommand().equals("NewLineColor")){
            JColorChooser colorChooser = new JColorChooser(currentLineColor);
            colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println( e.getSource().toString());
                }
            });
            Color newColor = colorChooser.showDialog(this, "Select a new Line Color", currentLineColor);

            if(newColor!=null){
                currentLineColor = newColor;
                LineColorShower.setBackground(newColor);
            }
        }
        else if(e.getActionCommand().equals("NewFillColor")){
            JColorChooser colorChooser = new JColorChooser(currentLineColor);
            colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println( e.getSource().toString());
                }
            });
            Color newColor = colorChooser.showDialog(this, "Select a new Line Color", currentFillColor);

            if(newColor!=null){
                currentFillColor = newColor;
                FillColorShower.setBackground(newColor);
            }
        }
        else if(e.getActionCommand().equals("New")){
            makeNewCanvas();
        }
        else if( e.getActionCommand().equals("Load")){
            String filePath = null;
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    ".vec","vec");
            fileChooser.setFileFilter(filter);
            int fileVal = fileChooser.showOpenDialog(null);
            if (fileVal == JFileChooser.APPROVE_OPTION){
                filePath = String.valueOf(fileChooser.getSelectedFile());
            }

            if(filePath!= null){
                try {
                    //this.add( new PaintCanvas( new File(filePath)));
                    this.paintCanvas = new PaintCanvas( new File(filePath));
                    add(this.paintCanvas);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                validate();
            }
        }
        else if(e.getActionCommand().equals("Save")){

            JFileChooser fileChooser = new JFileChooser();
            int fileVal = fileChooser.showSaveDialog((JButton)e.getSource());
            if(fileVal == JFileChooser.APPROVE_OPTION){
                File newFile = fileChooser.getSelectedFile();
                if(newFile == null){
                    System.out.print("What ever you did triggered this?");
                }else if(newFile.getName().toLowerCase().endsWith(".vec")){
                    newFile =new File( newFile.getParentFile(), newFile.getName()+ ".vec");
                }
                VecReader vecReader = new VecReader(this.getSize());

                try{
                    File FilledFile = vecReader.TranslateCanvas(getPaintCanvas(),newFile);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main (String []args){
        new windowAll();
    }
}
