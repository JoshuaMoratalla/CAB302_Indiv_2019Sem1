package testingArea;

import javax.swing.*;
import java.awt.*;

public class testMain extends JFrame {


   private static void createAndShowGUI() {

       JFrame.setDefaultLookAndFeelDecorated(true);

       //Create and set up the window.
       JFrame frame = new JFrame("HelloWorldSwing");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //Add the label.
       JLabel label = new JLabel("Hello Java Swing World");
       label.setForeground(Color.white);
       frame.getContentPane().add(label); // PREFERRED WAY TO ADD COMPONENTS
//      frame.add(label);


       //##########################################
       // everything inbetween the hashtagged comments is your own code




       JPanel panel = new JPanel();
       panel.setBackground(Color.BLUE);
       frame.add(panel);
       panel.add(label);
       //##########################################
       //Display the window.
       frame.setPreferredSize(new Dimension(300, 100));
       frame.setLocation(new Point(200, 200));
       frame.pack();
       frame.setVisible(true);
   }
    public static void main(String []args){
       testMain test1 = new testMain();
       testMain.createAndShowGUI();
    }
}
