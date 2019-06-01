package testingArea.Shape;

import java.awt.*;

public interface fillableObject {
    //public void addFillColor(Color color); //add fill color is somewhat the same as change color

    public Color getFillColor();
    public void changeFillColor(Color newColor); //you can change from to default to new color the same as adding a new color to replace the old one
    public void removeFillColor();

    /*
     for (Shape s : shapeList) {
                g2.setPaint(Color.BLACK);
                g2.draw(s);
                g2.setPaint(colors[(colorIndex++) % 6]);    <-
                g2.fill(s); <-
            }
     */

}
