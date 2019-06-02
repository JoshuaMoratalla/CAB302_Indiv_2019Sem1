package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowColorChooser extends JFrame implements ActionListener {
    public static Dimension staticDimensions = new Dimension(1000,1000);

    private Color currentLineColor;
    private JComponent LineColorShower;

    public windowColorChooser(){
        super("Color Chooser");
        setWindow();
        this.currentLineColor = Color.black;

        setJMenuBar(setColorMenu());
        pack();
        setVisible(true);

    }

    private void setWindow(){
        setPreferredSize(staticDimensions);

        Dimension windowPos = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( new Point((int)(windowPos.getWidth() - staticDimensions.getWidth())/2,(int) (windowPos.getHeight() - staticDimensions.getHeight())/2));
    }

    private JMenuBar setColorMenu(){
        JMenuBar colorMenuBar = new JMenuBar();

        JMenu colorMenu = new JMenu("Color");

        JButton LineColorButton = new JButton("Line Color");
        LineColorButton.setActionCommand("NewLineColor");
        LineColorButton.addActionListener(this::actionPerformed);
        this.LineColorShower = new JPanel();
        LineColorShower.setForeground(this.currentLineColor);
        LineColorShower.setBackground(this.currentLineColor);


        colorMenu.add(LineColorButton);
        colorMenu.add(LineColorShower);

        colorMenuBar.add(colorMenu);

        return colorMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("NewLineColor")){
            /*
            int menuPosition = 0;
            int menuItemPosition = 1;

            for(int x = 0; x <2 ; x++){
                for(int y = 0; y <2 ; y++){
                    try{
                        System.out.print( String.format("&&& %s &&&",getJMenuBar().getMenu(x).getItem(y).getText()));
                    }
                    catch (NullPointerException ex){
                        //System.out.print(String.format("|Not found in menu %d, menu item %d |", x,y));
                    }
                }
            }
            */
            //System.out.print( getJMenuBar().getMenu(menuPosition).getItem(menuItemPosition).getText());
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
           // Color newColor = JColorChooser.showDialog(this, "Select a new Line Color", currentLineColor);

        }
    }

    public static void main (String []args){
        new windowColorChooser();
    }


}
