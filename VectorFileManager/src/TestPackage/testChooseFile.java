package TestPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class testChooseFile {

    public static File vecFile =  new File("C:\\Users\\Joshua\\IdeaProjects\\VectorFileManager\\src\\VecExamples\\example1.vec");

    public static void main(String[]args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(vecFile));
        ArrayList<String> linesOfText = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null){
            linesOfText.add(line);
        }
        bufferedReader.close();

        ArrayList<ArrayList<String>> individualComponents = new ArrayList<>();
        for(String singleLine : linesOfText){
            String[] lineSplit = singleLine.split(" ");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(lineSplit));
            individualComponents.add(arrayList);
        }

        for(ArrayList<String> arraylist  : individualComponents){

            StringBuilder str = new StringBuilder();
            for(String string: arraylist){
                str.append(string).append("    ");
            }
            System.out.println(str.toString());
        }
    }


}
