package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowSetUp extends JFrame implements ActionListener {


    public static Dimension staticDimensions = new Dimension(1000,1000);


    public windowSetUp(){
        super("Basic Window Setup");
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
        setJMenuBar(menuBAR);

        JMenu menuFile = new JMenu("File");
        JMenu menuShapes = new JMenu("Shapes");
        JMenu menuColors = new JMenu("Colors");
        JMenu menuUndoHistory = new JMenu("History");

        getJMenuBar().add(menuFile);
        getJMenuBar().add(menuShapes);
        getJMenuBar().add(menuColors);
        getJMenuBar().add(menuUndoHistory);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main (String []args){
        new windowSetUp();
    }
}
