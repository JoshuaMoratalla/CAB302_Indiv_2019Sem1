package testingArea.multiTabbing;

import javax.swing.*;
import java.util.ArrayList;

public class Tabs extends JFrame {

    private String tabName;


    public Tabs(String tabName){
        this.tabName = tabName;
        //needs its own canvas
        //also needs its own layer list like photoshop
    }

    public Tabs(){
        this.tabName = "untitled";

        //needs its own canvas
        //also needs its own layer list like photoshop
    }

    public String getTabName(){
        return this.tabName;
    }


}
