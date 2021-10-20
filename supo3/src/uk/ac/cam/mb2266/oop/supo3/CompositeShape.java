package uk.ac.cam.mb2266.oop.supo3;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends Shape {
    private String name;
    private List<Shape> shapeList;

    private CompositeShape(String name) {
        this.name = name;
        this.shapeList = new ArrayList<>();
    }

    @Override
    public void draw(){
        for(Shape s: shapeList){ s.draw();}
    }

    private void addShape(Shape shape){
        shapeList.add(shape);
    }

    public void removeShape(Shape shape){
        shapeList.remove(shape);
    }

    public static void main(String[] args) {
        Circle circ1 = new Circle("Circle 1");
        Shape borderedCircle1 = new BorderDecorator(circ1);

        CompositeShape compose = new CompositeShape("All the shapes together");
        compose.addShape(circ1);
        compose.addShape(borderedCircle1);

        compose.draw();
    }
}
