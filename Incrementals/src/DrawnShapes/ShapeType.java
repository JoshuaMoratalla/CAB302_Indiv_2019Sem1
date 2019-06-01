package DrawnShapes;

import JFrameWindow.MainJFrameWindow;

import java.awt.event.ActionEvent;
import java.util.EnumSet;

public enum ShapeType {
    ELLFIX("Ellipse Fixed"),
    ELLFREE("Ellipse Free"),
    LINEBAS("Line Basic"),
    LINEPOINT("Line Point"),
    POLYFIX("Polygon Fixed"),
    POLYLINE("Polygon Line"),
    POLYSTAR("Polygon Star"),
    RECTFREE("Rectangle Free");
    String shapeTypeName;
    ShapeType(String name){
        this.shapeTypeName = name;
    }

    public String getShapeTypeName() {
        return shapeTypeName;
    }

    public static ShapeType matchEnum(String input){

        ShapeType buffer = null;
        for(ShapeType shape : EnumSet.allOf(ShapeType.class)){
            if(shape.shapeTypeName == input){
              buffer = shape;
            }
        }
        return buffer;
    }
}

/*
    package JFrameWindow;

import DrawnShapes.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainJFrameWindow extends JFrame implements ActionListener {


    MouseListener menuListener;
    MouseListener canvasListener;


//*****************************************************************************
    private static JMenu jMenu;

    private static JRadioButtonMenuItem radioButtonPOLYGON;
    private static JRadioButtonMenuItem radioButtonSTAR ;
    private static JRadioButtonMenuItem radioButtonCIRCLE ;
    private static JRadioButtonMenuItem radioButtonLINE ;
    private static JRadioButtonMenuItem radioButtonCUSTOM;
    private static ShapeType shapeSelection;

//*****************************************************************************
    public MainJFrameWindow(){
        super("Main JFrame Window");
        initWindow(1000);
        initMenu();

        add(new JPanel());////////////////////////////////////////
    }

    public void initWindow(int windowSize){

        //this.setSize(windowSize,windowSize);
        this.setPreferredSize(new Dimension(windowSize,windowSize));
        Dimension windowPos = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point((windowPos.width- windowSize)/2,(windowPos.height-windowSize)/2));
        pack();
        setVisible(true);
    }

    public void initMenu(){
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setSize(200,20);
        menuBar.setBackground(Color.gray);
        menuBar.setForeground(Color.BLUE);

        final JMenu toolsMenu = new JMenu("Tools");

        ButtonGroup shapeChooser = new ButtonGroup();

        this.radioButtonPOLYGON = new JRadioButtonMenuItem("Polygon",true);
        this.radioButtonPOLYGON.setActionCommand("Polygon");
        this.radioButtonPOLYGON.addActionListener(this::actionPerformed);

        this.radioButtonSTAR = new JRadioButtonMenuItem("Star");
        this.radioButtonSTAR.setActionCommand("Star");
        this.radioButtonSTAR.addActionListener(this::actionPerformed);

        this.radioButtonCIRCLE = new JRadioButtonMenuItem("Circle");
        this.radioButtonCIRCLE.setActionCommand("Circle");
        this.radioButtonCIRCLE.addActionListener(this::actionPerformed);

        this.radioButtonLINE = new JRadioButtonMenuItem("Line");
        this.radioButtonLINE.setActionCommand("Line");
        this.radioButtonLINE.addActionListener(this::actionPerformed);

        this.radioButtonCUSTOM = new JRadioButtonMenuItem("Custom");
        this.radioButtonCUSTOM.setActionCommand("Custom");
        this.radioButtonCUSTOM.addActionListener(this::actionPerformed);

        jMenu = new JMenu( getShapeSelection().getShapeTypeName());
        shapeChooser.add(radioButtonPOLYGON);
        shapeChooser.add(radioButtonSTAR);
        shapeChooser.add(radioButtonCIRCLE);
        shapeChooser.add(radioButtonLINE);
        shapeChooser.add(radioButtonCUSTOM);

        toolsMenu.add(radioButtonPOLYGON);
        toolsMenu.add(radioButtonSTAR);
        toolsMenu.add(radioButtonCIRCLE);
        toolsMenu.add(radioButtonLINE);
        toolsMenu.add(radioButtonCUSTOM);

        this.shapeSelection = ShapeType.LINE;
        this.jMenu.setName(getShapeSelection().getShapeTypeName());

        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == radioButtonPOLYGON){
            this.shapeSelection = ShapeType.POLYGON;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Polygon was selected");
        }else if(source == radioButtonSTAR){
            this.shapeSelection = ShapeType.STAR;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Star was selected");
        }else if(source == radioButtonLINE) {
            this.shapeSelection = ShapeType.LINE;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Line was selected");
        }else if(source == radioButtonCIRCLE){
            this.shapeSelection = ShapeType.CIRCLE;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Circle was selected");
        }else if (source ==radioButtonCUSTOM){
            this.shapeSelection = ShapeType.CUSTOM;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Custom was selected");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == radioButtonPOLYGON){
            this.shapeSelection = ShapeType.POLYGON;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Polygon was selected");
        }else if(source == radioButtonSTAR){
            this.shapeSelection = ShapeType.STAR;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Star was selected");
        }else if(source == radioButtonLINE) {
            this.shapeSelection = ShapeType.LINE;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Line was selected");
        }else if(source == radioButtonCIRCLE){
            this.shapeSelection = ShapeType.CIRCLE;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Circle was selected");
        }else if (source ==radioButtonCUSTOM){
            this.shapeSelection = ShapeType.CUSTOM;
            this.jMenu.setName(getShapeSelection().getShapeTypeName());
            System.out.print("Custom was selected");
        }
    }
    public static ShapeType getShapeSelection() {
        return shapeSelection;
    }



    public static void main(String []args){
        new MainJFrameWindow();
    }


}

    */