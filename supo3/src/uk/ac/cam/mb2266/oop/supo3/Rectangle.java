package uk.ac.cam.mb2266.oop.supo3;

public class Rectangle extends Shape {
    private String name;

    Rectangle(String name){
        this.name = name;
    }

    private String getName(){
        return name;
    }

    public void draw(){
        System.out.println(name);
    }

    @Override
    public String toString(){
        return name;
    }
}
