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
    private JButton saveButton;

    public String example1 = "C:\\Users\\Joshua\\IdeaProjects\\VectorReader\\src\\VecFiles\\example1.vec";
    public String example2 = "C:\\Users\\Joshua\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example2.vec";
    public String example3 = "C:\\Users\\Joshua\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example3.vec";




    private PaintCanvas paintCanvas;

    public ShapeDisplay() throws IOException {
        super("Display thingo");
        System.out.println("ShapeDisplay(Frame) is initiated");

        setMenu();
        //add JComponent that will read the file and paint it
        this.setPreferredSize(new Dimension(1016,1062));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

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
        this.saveButton = new JButton("Save");
        this.loadButton.addActionListener(this::actionPerformed);
        this.saveButton.addActionListener(this::actionPerformed);

        fileMenu.add(loadButton);
        fileMenu.add(saveButton);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
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
                    //this.add( new PaintCanvas( new File(filePath)));
                    this.paintCanvas = new PaintCanvas( new File(filePath));
                    add(this.paintCanvas);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                validate();
            }

        }else if( e.getSource() == saveButton){
            PaintCanvas canvas = getPaintCanvas();

            JFileChooser fileChooser = new JFileChooser();
            int fileVal = fileChooser.showSaveDialog(saveButton);
            if(fileVal == JFileChooser.APPROVE_OPTION){
                File newFile = fileChooser.getSelectedFile();
                if(newFile == null){
                    System.out.print("What ever you did triggered this?");
                }else if(newFile.getName().toLowerCase().endsWith(".vec")){
                    newFile =new File( newFile.getParentFile(), newFile.getName()+ ".vec");
                }
                VecReader vecReader = new VecReader(this.getSize());

                try{
                    File FilledFile = vecReader.TranslateCanvas(getPaintCanvas(),newFile);


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        }

    }

    public PaintCanvas getPaintCanvas() {
        return this.paintCanvas;
    }

    public static void main(String []args) throws IOException {
        ShapeDisplay shapeDisplay = new ShapeDisplay();

    }


}
