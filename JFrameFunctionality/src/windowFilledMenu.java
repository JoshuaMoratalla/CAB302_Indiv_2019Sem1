import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.plaf.ColorChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class windowFilledMenu  extends JFrame implements ActionListener {
    public static Dimension staticDimensions = new Dimension(1000,1000);
    private ShapeType shapeSelection;




    public windowFilledMenu(){
        super("FilledMenu");
        setWindow();
        setMenuBar();

        pack();
        setVisible(true);
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
        JMenu menuColors = new JMenu("Colors");
        JMenu menuUndoHistory = new JMenu("History");

        menuBAR.add(menuFile);


        menuBAR.add(menuShapes);
        menuBAR.add(menuColors);
        menuBAR.add(menuUndoHistory);

        setJMenuBar(menuBAR);
    }

    private JMenu setFileMenu(){
        JMenu menuFile = new JMenu("File");
        JButton buttonNew = new JButton("New");
        JButton buttonLoad = new JButton("Load");
        JButton buttonSave= new JButton("Save");

        ActionListener fileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buttonNew){
                    //clear canvas
                    System.out.println("New Button Triggered");
                }else if(e.getSource() == buttonLoad){
                    //load file
                    System.out.println("Load Button Triggered");
                }
                else if(e.getSource() == buttonSave){
                    //save the shapes on the current canvas
                    System.out.println("Save Button Triggered");
                }
            }
        };

        buttonNew.addActionListener(fileListener);
        buttonLoad.addActionListener(fileListener);
        buttonSave.addActionListener(fileListener);

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
        }

        return menuShapes;
    }

    private JMenu setColorMenu(){
        JMenu menuColor = new JMenu();

        JButton LineColor = new JButton("Line Color");
        JLabel LineColorBanner = new JLabel();

        JButton FillColor = new JButton("Fill Color");
        JLabel FillColorBanner = new JLabel();

        LineColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Color newColor = new JColorChooser.showDialog(, "Select new color", LineColorBanner.getBackground());
            }
        });

        return menuColor;

    }

    private windowFilledMenu getWindowFilledMenu(){
        return this;
    }

    private JMenu setUndoMenu(){
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main (String []args){
        new windowFilledMenu();
    }
}

