package uk.ac.cam.mb2266.oop.supo1;

public class OOPLinkedList {
    OOPLinkedListElement head;

    OOPLinkedList(int h){
        head = new OOPLinkedListElement(h);
        head.next = null;
    }

    public static void main(String[] args){
        OOPLinkedList l = new OOPLinkedList(1);
    }

    void print(){
        OOPLinkedListElement ele = head;
        while (ele != null) {
            System.out.println(ele.value);
            ele = ele.next;
        }
    }

    int length(){
        OOPLinkedListElement ele = head;
        int len = 0;
        while(ele != null){
            len++;
            ele = ele.next;
        }
        return len;
    }

    int head(){
        return head.value;
    }

    void removeHead(){
        head = head.next;
    }

    void add(int r){
        OOPLinkedListElement l = new OOPLinkedListElement(r);
        l.next = head;
        head = l;
    }

    int nth(int n) {
        int i = 1; OOPLinkedListElement l = head;
        while (i < n && l.next != null) {
            l=l.next;
            i++;
        }
        return l.value;
    }
}
