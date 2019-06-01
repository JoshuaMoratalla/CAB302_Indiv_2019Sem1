package testingArea.multiTabbing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class mainFrame extends  JFrame{
    public static ArrayList<Tabs> TabList;

    public mainFrame(){
        super("name");

//################################################
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menubar
        JMenuBar cyanMenuBar = new JMenuBar();
        cyanMenuBar.setOpaque(true);
        cyanMenuBar.setBackground(Color.cyan);
        cyanMenuBar.setPreferredSize(new Dimension(200, 20));
        JMenu menuItem = new JMenu("File");
        cyanMenuBar.add(menuItem);
        this.setJMenuBar(cyanMenuBar);


        //grid that will consist of the canvas and the tools of the canvas like paint, fill, brush etc
        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        this.add(gridPanel);

        //addition of the button
        JPanel toolSet = new JPanel();
        toolSet.setMaximumSize(new Dimension( 50,20));
        toolSet.setMinimumSize(new Dimension( 50,20));
        toolSet.setPreferredSize(new Dimension(50,20));
        toolSet.add(new Label("Add Painbucket and Brush Here"));
        gridPanel.add(toolSet);


        //canvas in tabbed pane
        Canvas canvas = new Canvas();
        canvas.setSize(400, 400);

        //addition of the Tabbed pane
        JTabbedPane tabbedPane  = new JTabbedPane();
        tabbedPane.add(canvas);
        gridPanel.add(tabbedPane);



//#######################################################################
        //setPreferredSize(new Dimension(300, 200));
        setLocation(new Point(100, 100));
        pack();
        setVisible(true);



    }



    private static void createTab( ){
       Tabs newTab = new Tabs("thingo");
       TabList.add(newTab);
    }
}
