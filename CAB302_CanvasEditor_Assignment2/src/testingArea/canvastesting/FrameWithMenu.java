package testingArea.canvastesting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class FrameWithMenu extends JFrame {

    public FrameWithMenu(){
        super("Frame with menu");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //setting menubar
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setSize(200,20);
        menuBar.setBackground(Color.gray);
        menuBar.setForeground(Color.BLUE);


        //Creating the menuItem File for managing files
        final JMenu fileMenuItem = new JMenu("File");
        menuBar.add(fileMenuItem);



        //Open File menu item with given

        JFileChooser fileChooser = new JFileChooser();
        final JMenuItem openFile_MenuItem = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    fileChooser.setCurrentDirectory( new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(getParent());

                    if(result==JFileChooser.APPROVE_OPTION){
                        File selectedFile = fileChooser.getSelectedFile();
                    }
                /*

                 try{


                    File cFolder = new File("C:\\");
                    Desktop.getDesktop().open(cFolder);
                }catch(IOException exception){
                    exception.printStackTrace();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                 */
            }
        });
        openFile_MenuItem.setText("Open...");


        //add
        fileMenuItem.add(openFile_MenuItem);

        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    public static void main(String []args){
        new FrameWithMenu();
    }
}
