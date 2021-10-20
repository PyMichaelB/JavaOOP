package uk.ac.cam.mb2266.oop.supo1;

public class Exercise3_4 {

    private static void changeTest(int[] test){ test[2] = 1; }

    public static void main(String[] args){
        int[] test = {1,2,3,4};
        changeTest(test);
        System.out.println(java.util.Arrays.toString(test));
    }
}
