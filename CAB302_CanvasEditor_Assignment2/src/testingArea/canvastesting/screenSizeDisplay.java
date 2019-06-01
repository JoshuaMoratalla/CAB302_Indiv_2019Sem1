package testingArea.canvastesting;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class screenSizeDisplay extends JFrame {

    private TextField text = new TextField(10);

    private double jframeHeight;
    private double jframeWidth;


   public screenSizeDisplay(){
        super("Radiobutton test");


        this.setSize(1000,500);
        this.setPreferredSize(new Dimension(1000,500));

        add(text);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
               text.setText(getJFrameSizeAsString());
               System.out.println("Size changed");

            }
        });




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(new Point(100, 100));
        pack();
        setVisible(true);
    }

    public double getJframeHeight() {
        return jframeHeight;
    }

    public double getJframeWidth() {
        return jframeWidth;
    }

    public static void main(String []args){
       new screenSizeDisplay();
    }

    String getJFrameSizeAsString(){
        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        return  String.format("%2f , %2f", getBounds().getWidth(), getBounds().getHeight());
    }


}
