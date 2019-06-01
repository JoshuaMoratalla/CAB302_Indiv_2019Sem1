import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class windowAll extends JFrame implements ActionListener {

    public static Dimension staticDimensions = new Dimension(1000,1000);
    private ShapeType shapeSelection;


    private Color currentLineColor;
    private JComponent LineColorShower;

    public windowAll(){

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
        JMenu colorMenu = new JMenu("Color");
        JButton LineColorButton = new JButton("Line Color");
        LineColorButton.setActionCommand("NewLineColor");

        LineColorButton.addActionListener(this::actionPerformed);
        this.LineColorShower = new JPanel();
        LineColorShower.setForeground(this.currentLineColor);
        LineColorShower.setBackground(this.currentLineColor);

        colorMenu.add(LineColorButton);
        colorMenu.add(LineColorShower);

        return colorMenu;
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

    }
    public static void main (String []args){
        new windowAll();
    }
}
