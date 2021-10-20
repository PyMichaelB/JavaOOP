package uk.ac.cam.mb2266.oop.supo3;

public class BorderDecorator extends ShapeDecorator {

    public BorderDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setBorder(decoratedShape);
        /*Here is where you would actually create the border. In
        this case we just call a method which prints Border: true*/
    }

    private void setBorder(Shape decoratedShape){
        System.out.println("Border: true");
    }
}
