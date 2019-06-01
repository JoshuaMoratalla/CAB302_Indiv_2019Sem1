import java.util.EnumSet;

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
    int shapePoints;

    ShapeType(String name){
        this.shapeTypeName = name;
    }

    public String getShapeTypeName() {
        return shapeTypeName;
    }

    public static ShapeType matchEnum(String input){

        ShapeType buffer = null;
        for(ShapeType shape : EnumSet.allOf(ShapeType.class)){
            if(shape.shapeTypeName == input){
                buffer = shape;
            }
        }
        return buffer;
    }
}
