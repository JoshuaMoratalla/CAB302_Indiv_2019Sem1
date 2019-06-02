package Shapes;

import java.awt.*;

/**
 * Interface class that provides the basic contracts for shape objects
 * and also so they can be iterated easily when saved to a file. In addition, since the Shape interface doesn't
 * provide the shapes their own colors, it ensures the colors are always assigned to a shape and kept there.
 * @author Joshua Moratalla
 * @since 05-25-2019
 */

public interface drawableObject {

    /**
     * Retrieves the ShapePack's respective shape in so they can be iterated through the common interface of shape.
     * @return
     */
    Shape getShape();


    /**
     * Used as all shapes use a line to form a structure
     * @return associated line for the Shape pack
     */
    Color getLineColor();

    /**
     * Change of line color since all shapes need to change line colors
     * @param newColor
     */
    void changeLineColor(Color newColor);

    /**
     * Provides a string equivalent of the vec command for the respective shape
     * @param scaleRatio the ratio to provide its zero-to-one equivalent
     * @return a string equivalent of a vec command and the coordinates
     */
    String toVectorString(Dimension scaleRatio);

    /**
     *  Provides a hexadecimal equivalent of the shapes line color aswell as distinguishing if the current
     *  fill color is already in use in the vec file
     * @param currentLineColor
     * @return
     */
    String toLineColorString(Color currentLineColor);

}