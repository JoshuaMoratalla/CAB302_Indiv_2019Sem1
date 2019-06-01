package NewShapes;

import java.awt.*;

public interface fillableObject {

    Color transparent = new Color (0,0,0,0);

    Color getFillColor();
    void changeFillColor(Color newColor); //you can change from to default to new color the same as adding a new color to replace the old one
    void removeFillColor();
    String toFillColorString(Color currentFillColor);
}
