package uk.ac.cam.mb2266.oop.supo1;

public class OOPSortedLinkedList extends OOPLinkedList {
    private OOPLinkedListElement rest;

    private OOPSortedLinkedList(int r){
        super(r);
    }

    public static void main(String[] args){
        OOPSortedLinkedList test = new OOPSortedLinkedList(1);
        test.addSort(4);
        test.addSort(3);
        test.addSort(80);
        test.print();
    }

    // Had a little bit of help from Yulong with this method
    private void addSort(int r){
        OOPLinkedListElement l1 = head;
        OOPLinkedListElement n = new OOPLinkedListElement(r);
        if (r <= l1.value) {
            //i.e. if the value to be added is less than the first element
            super.add(r);
        } else {
            while (true) {
                if (l1.next == null){
                    l1.next = new OOPLinkedListElement(r);
                    break;
                } else if (l1.next.value >= r){
                    n.next = l1.next;
                    l1.next = n;
                    break;
                } else {
                    l1 = l1.next;
                }
            }
        }
    }

}