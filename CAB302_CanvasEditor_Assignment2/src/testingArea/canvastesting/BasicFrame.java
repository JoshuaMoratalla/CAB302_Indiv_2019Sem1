package testingArea.canvastesting;

import javax.swing.*;
import java.awt.*;

public class BasicFrame extends JFrame {

    public BasicFrame(){
        super("BasicFrame");
        this.setPreferredSize(new Dimension(500,500));
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Canvas());
        this.setVisible(true);
    }


   public static void main(String []args){
        new BasicFrame();
   }


}
