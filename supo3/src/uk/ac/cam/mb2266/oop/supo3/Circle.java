package uk.ac.cam.mb2266.oop.supo3;

public class Circle extends Shape{
    private String name;

    Circle(String name){
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
