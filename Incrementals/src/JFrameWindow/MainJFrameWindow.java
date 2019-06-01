package JFrameWindow;

import DrawnShapes.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EnumSet;
import java.util.HashSet;

public class MainJFrameWindow extends JFrame implements ActionListener {


    MouseListener menuListener;
    MouseListener canvasListener;

    //public final int buffer


//*****************************************************************************
    private static JMenu jMenu;

    private static HashSet<JRadioButton>  shapeButtonReference;

    private static ShapeType shapeSelection = ShapeType.LINEPOINT;

    private static CanvasPanel canvas;

//*****************************************************************************
    public MainJFrameWindow(){
        super("Main JFrame Window");
        initWindow(1000);
        initMenu();

        this.canvas = new CanvasPanel();
        add(canvas);////to add the canvas///

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println( e.getComponent().getAlignmentX());
               // System.out.println( e.getComponent().getAlignmentY());
                //System.out.println(e.getComponent().getSize().getWidth() + " || "+ e.getComponent().getSize().getHeight());

                System.out.println(String.format("%d || %d", getCanvas().getWidth(), getCanvas().getHeight()));



            }
        });
        pack();
        setVisible(true);
    }

    public void initWindow(int windowSize){

        //this.setSize(windowSize,windowSize);
        this.setPreferredSize(new Dimension(windowSize,windowSize));
        Dimension windowPos = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point((windowPos.width- windowSize)/2,(windowPos.height-windowSize)/2));

    }

    public void initMenu(){
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setSize(200,20);
        menuBar.setBackground(Color.gray);
        menuBar.setForeground(Color.BLUE);

        final JMenu toolsMenu = new JMenu("Tools");
        ButtonGroup shapeChooser = new ButtonGroup();
        jMenu = new JMenu( getShapeSelection().getShapeTypeName());
        shapeButtonReference = new HashSet<>();

        for(ShapeType shape: EnumSet.allOf(ShapeType.class)){
            JRadioButton button = new JRadioButton(shape.getShapeTypeName());
            button.setActionCommand(shape.getShapeTypeName());
            button.addActionListener(this::actionPerformed);

            shapeChooser.add(button);
            toolsMenu.add(button);

            shapeButtonReference.add(button);
        }


        this.jMenu.setName(getShapeSelection().getShapeTypeName());

        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for(JRadioButton button : shapeButtonReference){
            if(source == button){
                System.out.println(String.format("%s selected", button.getText()));
                shapeSelection = ShapeType.matchEnum(button.getText());
            }
        }
        System.out.println(String.format("Shape selection is %s", shapeSelection.getShapeTypeName()));

    }


    public static ShapeType getShapeSelection() {
        return shapeSelection;
    }

    public static CanvasPanel getCanvas() {
        return canvas;
    }

    public static void main(String []args){
        new MainJFrameWindow();
    }


}
