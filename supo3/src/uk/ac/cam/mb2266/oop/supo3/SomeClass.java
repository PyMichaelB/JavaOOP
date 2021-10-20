package uk.ac.cam.mb2266.oop.supo3;

public class SomeClass extends SomeOtherClass implements Cloneable {
    private int[] mData;

    public Object clone() {
        SomeClass sc = new SomeClass();
        sc.mData = mData.clone();
        return sc;
    }
}