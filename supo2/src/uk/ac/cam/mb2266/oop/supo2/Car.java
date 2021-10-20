package uk.ac.cam.mb2266.oop.supo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car implements Comparable<Car> {
    private String manufacturer;
    private int age;

    private Car(String man, int age){
        this.manufacturer = man;
        this.age = age;
    }

    String getManufacturer(){
        return this.manufacturer;
    }

    int getAge(){
        return this.age;
    }

    @Override

    public String toString() {

        return "Car{" +

                "Manufacturer='" + getManufacturer() + '\'' +

                ", Age=" + getAge() +

                '}';

    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("VW", 5));
        cars.add(new Car("BMW", 2));
        cars.add(new Car("Ferrari", 3));
        cars.add(new Car("Fiat", 7));
        cars.add(new Car("Fiat", 4));
        Collections.sort(cars);
        System.out.println(cars);

        Collections.sort(cars, new AgeComparator());
        System.out.println(cars);
    }

    @Override
    public int compareTo(Car o) {
        return this.getManufacturer().compareTo(o.getManufacturer());
    }

}

class AgeComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2){
        int result = o1.getManufacturer().compareTo(o2.getManufacturer());
        if(result == 0) { result = Integer.compare(o1.getAge(), o2.getAge()); }
        return result;
    }
}
