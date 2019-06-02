package Shapes;

import java.util.EnumSet;

/**
 * Enum class provide a selection of shape choices assigned to the radiobuttons.
 */
public enum ShapeType {

    ELLFIX("Ellipse Fixed"),
    ELLFREE("Ellipse Free"),
    LINEBAS("Line Basic"),
    LINEPOINT("Line Point"),
    POLYFIX("Polygon Fixed"),
    POLYLINE("Polygon Line"),
    POLYSTAR("Polygon Star"),
    RECTFREE("Rectangle Free");

    String shapeTypeName;

    ShapeType(String name){
        this.shapeTypeName = name;
    }

    public String getShapeTypeName() {
        return shapeTypeName;
    }


    /**
     * Matches the enum by the input string
     * @param input string that assumptively matches an enum
     * @return the enum associated with the string.
     */
    public static ShapeType matchEnum(String input){
        ShapeType buffer = null;
        for(ShapeType shape : EnumSet.allOf(ShapeType.class)){
            if(shape.shapeTypeName.equals(input)){
                buffer = shape;
            }
        }
        return buffer;
    }
}
