package testingArea.layoutTesting;

import javax.swing.*;
import java.awt.*;

public class boxlayoutTest extends JFrame {

    public boxlayoutTest(){
        super("Box layout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        JPanel panel_1 = new JPanel();
        panel_1.add(new Label("Panel 1"));

        JPanel panel_2 = new JPanel();
        panel_2.add(new Label("Panel 2"));

        boxPanel.add(panel_1);
        boxPanel.add(panel_2);

        add(boxPanel);

        setLocation(new Point(100, 100));
        pack();
        setVisible(true);
    }

    public static void main (String []args){
        boxlayoutTest testVariable = new boxlayoutTest();
    }
}
