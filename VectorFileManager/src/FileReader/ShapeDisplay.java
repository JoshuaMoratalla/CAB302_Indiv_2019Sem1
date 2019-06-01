package FileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShapeDisplay extends JFrame implements ActionListener {


    //references : https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html

    private ArrayList<String> stringList;


    private JButton loadButton;

    public String example1 = "C:\\Users\\n9472380\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example1.vec";
    public String example2 = "C:\\Users\\Joshua\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example2.vec";
    public String example3 = "C:\\Users\\Joshua\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example3.vec";


    public static Dimension WindowSize;

    public ShapeDisplay() throws IOException {
        super("Display thingo");
        System.out.println("ShapeDisplay(Frame) is initiated");

        setMenu();
        //add JComponent that will read the file and paint it
        this.stringList = FileTranslator(example1);
        this.setPreferredSize(new Dimension(1000,1000));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                WindowSize =e.getComponent().getSize();
            }
        });

       // Jm
        pack();
        setVisible(true);
    }

    public void setMenu(){
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setSize(200,20);
        menuBar.setBackground(Color.gray);
        menuBar.setForeground(Color.BLUE);

        JMenu fileMenu = new JMenu("File");
        this.loadButton = new JButton("Load");
        this.loadButton.addActionListener(this::actionPerformed);

        fileMenu.add(loadButton);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }





    public ArrayList<String> FileTranslator( String pathname) throws IOException {
        File file = new File(pathname);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> linesOfText = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null){
           // System.out.println(line);
            linesOfText.add(line);
        }
        bufferedReader.close();

        return linesOfText;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==loadButton ){
            String filePath = null;
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    ".vec","vec");
            fileChooser.setFileFilter(filter);
            int fileVal = fileChooser.showOpenDialog(null);
            if (fileVal == JFileChooser.APPROVE_OPTION){
                filePath = String.valueOf(fileChooser.getSelectedFile());
            }

            if(filePath!= null){
                try {
                    this.add( new PaintCanvas( FileTranslator(filePath)));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }



    public static void main(String []args) throws IOException {
        ShapeDisplay shapeDisplay = new ShapeDisplay();

    }


}
