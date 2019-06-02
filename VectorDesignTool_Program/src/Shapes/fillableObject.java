package Shapes;

import java.awt.*;

/**
 * Interface class that differentiates shapes with no fill color or associated fill characteristics. Mainly used when
 * iterating through drawableObjects, so it can determine if the drawable is an instance of fillableObject and then
 * access or augment its fill characteristics.
 * @author Joshua Moratalla
 * @since 05-25-2019
 */

public interface fillableObject {

    /**
     * A transparent constant because transparent as a Color is better than null
     */
    Color transparent = new Color (0,0,0,0);

    /**
     * Retrieves the shape's associated fill color
     * @return the shape's fill color
     */
    Color getFillColor();

    /**
     * Change the fill color so it no longer is transparent
     * @param newColor
     */
    void changeFillColor(Color newColor);

    /**
     * Provides a hexadecimal equivalent of the shape's fill color aswell as distinguishing if the
     * current line color is already in use in the vec file
     * @param currentFillColor
     * @return
     */
    String toFillColorString(Color currentFillColor);
}