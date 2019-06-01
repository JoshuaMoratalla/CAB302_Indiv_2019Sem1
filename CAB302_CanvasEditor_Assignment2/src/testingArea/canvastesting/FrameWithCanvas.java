package testingArea.canvastesting;

import javax.swing.*;
import java.awt.*;

public class FrameWithCanvas extends JFrame {

    public FrameWithCanvas(){
        super ("Frame with Canvas");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel basePanel = new JPanel( );

       // basePanel.add(new Canvas());
        //basePanel.add(new JButton("Button"));

        this.add(basePanel);
        this.setVisible(true);







        //needs type of shape

    }

    public void paint(Graphics g){
        g.drawOval(100, 100, 100, 100);
    }

    public static void main (String []args){
        new FrameWithCanvas();
    }

    //polygons stars, circles/ovals and simple lines

}
