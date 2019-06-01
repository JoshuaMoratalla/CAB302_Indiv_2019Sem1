package testingArea;

import javax.swing.*;
import java.awt.*;

public class JFrameTest extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    
    private JPanel panel_1;

    public JFrameTest(){
        super("TeeHeeHee");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.BLUE);
        mainPanel.add(panel_1);


    }
}

