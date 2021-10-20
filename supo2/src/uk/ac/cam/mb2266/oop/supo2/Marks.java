package uk.ac.cam.mb2266.oop.supo2;

import jdk.nashorn.api.tree.Tree;

import java.util.*;

public class Marks {
    private TreeMap<String, Double> students;

    private Marks(TreeMap t){
        this.students = t;
        students.put("Beta", 40.0);
        students.put("Gamma", 4.0);
        students.put("Alpha", 30.0);
        students.put("Delta", 5.0);
    }

    public static void main(String[] args) {
        Marks m = new Marks(new TreeMap());
        System.out.println("Median = " + m.median());
    }

    private List sorted(){
        List<String> names = new ArrayList<>();
        Set<String> setOfNames = students.keySet();
        for(String s: setOfNames) {
            names.add(s);
        }
        return names;
    }

    private List scoreList(){
        Collection collOfScores = students.values();
        List<Double> scores = new ArrayList<>(collOfScores);
        Collections.sort(scores);
        return scores;
    }

    private double median(){
        List<Double> scores = scoreList();
        int len = scores.size();
        if (len % 2 == 0){
            return ((scores.get(len/2-1)+scores.get(len/2))/2.0);
        } else {
            return scores.get((len/2));
        }
    }

    /*private static List sortedStudents(List x){
        Collections.sort(x);
        return x;
    }



    @Override
    public int compareTo(Marks o) {
        return this.getName().compareTo(o.getName());
    }*/
}
