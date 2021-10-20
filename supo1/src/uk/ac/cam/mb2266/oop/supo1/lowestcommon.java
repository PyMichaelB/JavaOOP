package uk.ac.cam.mb2266.oop.supo1;

public class lowestcommon {
    public static int lowestCommon(long l1, long l2){
        long combined = l1&l2;
        int position = -1;
        for(int i = 0; i < 64; i++) {
            if (((combined >> i) &  1L) == 1) {
                position = i;
                break;
            }
        }
        return position;
    }

    public static void main(String[] args) {
        long l1 = 15;
        long l2 = 8;
        System.out.println(lowestCommon(l1, l2));
    }
}
