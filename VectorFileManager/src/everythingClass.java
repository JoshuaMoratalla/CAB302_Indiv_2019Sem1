


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class everythingClass extends JFrame implements ActionListener {
    final Color color1 = Color.BLUE;
    final Color color2 = Color.RED;
    final Color color3 = Color.GREEN;

    ActionListener actionListener;

    private JRadioButtonMenuItem radioButtonPOLYGON;
    private JRadioButtonMenuItem radioButtonSTAR ;
    private JRadioButtonMenuItem radioButtonCIRCLE ;
    private JRadioButtonMenuItem radioButtonLINE ;
    private JRadioButtonMenuItem radioButtonCUSTOM;


    private JMenu selectionAsText;


    static shapeType shapeSelection;

    enum shapeType{
        POLYGON("Polygon"),
        STAR("Star"),
        LINE ("Line"),
        CIRCLE("Circle"),
        CUSTOM("Custom");
        String shapeTypeName;
        shapeType( String name){
            this.shapeTypeName = name;
        }
    }

    public everythingClass(){
        super("Radiobutton test");
        init_FrameComponents();

        this.add(new canvasListener());
        this.setSize(500,500);
        this.setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(new Point(100, 100));
        pack();
        setVisible(true);
    }

    public void init_FrameComponents(){
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

        this.shapeSelection = shapeType.POLYGON;

        selectionAsText = new JMenu( getShapeSelection().shapeTypeName);
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

        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

    }

    public shapeType getShapeSelection(){
        return this.shapeSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == radioButtonPOLYGON){
            this.shapeSelection = shapeType.POLYGON;
            this.selectionAsText.setName(shapeType.POLYGON.shapeTypeName);
            System.out.print("Polygon was selected");
        }else if(source == radioButtonSTAR){
            this.shapeSelection = shapeType.STAR;
            System.out.print("Star was selected");
        }else if(source == radioButtonLINE) {
            this.shapeSelection = shapeType.LINE;
            System.out.print("Line was selected");
        }else if(source == radioButtonCIRCLE){
            this.shapeSelection = shapeType.CIRCLE;
            System.out.print("Circle was selected");
        }else if (source ==radioButtonCUSTOM){
            this.shapeSelection = shapeType.CUSTOM;
            System.out.print("Custom was selected");
        }
    }



    public static void main(String []args){
        new everythingClass();
    }

    private class canvasListener extends JComponent {

        ArrayList<Shape> shapeList = new ArrayList<>();
        Point StartPoint, EndPoint;

        public canvasListener(){
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    StartPoint = new Point(e.getX(),e.getY());
                }
                @Override
                public void mouseReleased(MouseEvent e){
                    EndPoint = new Point(e.getX(),e.getY());

                    Shape createdShape;

                    shapeType varShapeType = getShapeSelection();
                    if(varShapeType == shapeType.CIRCLE || varShapeType == shapeType.STAR){
                        createdShape = makeCircle(StartPoint.x,StartPoint.y, (int) EndPoint.getX(),(int)EndPoint.getY());
                    }else if( varShapeType == shapeType.CUSTOM){
                        createdShape = makeRectangle(StartPoint.x,StartPoint.y, e.getX(),e.getY());

                    }else if(varShapeType == shapeType.POLYGON){
                            createdShape = makePolygon( StartPoint,EndPoint,9);
                    }else{
                        //single click with very little motion can trigger an error
                        //might need to readjust endpoints when that scenario
                        createdShape =  new Line2D.Float(StartPoint,EndPoint);
                    }

                    shapeList.add(createdShape);
                    StartPoint = null;

                    repaint();
                }
            });
            this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    EndPoint = new Point(e.getX(), e.getY());
                    repaint();
                }
            });
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //paintBackground(g2);

            g2.setStroke(new BasicStroke(2));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

            for (Shape s : shapeList) {
                g2.setPaint(Color.BLACK);
                g2.draw(s);
                g2.setPaint(Color.BLACK);
                g2.fill(s);
            }

            //this is the marker
            if (StartPoint != null && EndPoint != null) {
                g2.setPaint(Color.LIGHT_GRAY);
                Shape r = makeRectangle(StartPoint.x, StartPoint.y, EndPoint.x, EndPoint.y);
                g2.draw(r);
            }
        }

        private Shape makeRectangle (int x1, int y1, int x2, int y2){
            return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
        }

        private Shape makeCircle( int x1, int y1, int x2, int y2){
            return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
        }

        private Shape makeLine ( int x1, int y1, int x2, int y2){
            return new Line2D.Float(x1,y1,x2,y2);
        }
        private Shape makePolygon ( Point p1, Point p2 ,  int sides){
           // return new testingArea.Shape.FixedPolygon(sides,p1,p2);
            return null;
        }

    }
}
