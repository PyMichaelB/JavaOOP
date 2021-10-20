package uk.ac.cam.mb2266.oop.supo1;

public class Vector2D {

    private float x1; private float y1;
    private float x2; private float y2;
    private float mag1; private float mag2;

    private Vector2D(float x1, float y1, float x2, float y2){
        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
    }

    private void magnitude() {
        mag1 = (float) Math.sqrt(x1*x1+y1*y1);
        mag2 = (float) Math.sqrt(x2*x2+y2*y2);
    }

    private float dot(){
        return x1*x2+y1*y2;
    }

    private Vector2D addition(){
        //
        return new Vector2D(x1+x2, y1+y2, x1+x2, y1+y2);
    }

    private Vector2D normalisation(){
        return new Vector2D(x1/mag1, y1/mag1,
                x2/mag2, y2/mag2);
    }

    public static void main(String[] args) {
        Vector2D vectors = new Vector2D(4, 3, 2, 1);
    }
}
