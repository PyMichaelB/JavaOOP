package uk.ac.cam.mb2266.oop.supo3;

public class OOPLinkedList<T> {
    Element<T> head;

    OOPLinkedList(T h){
        head = new Element(h);
        head.next = null;
    }

    public static void main(String[] args){
        Shape circle1 = new Circle("Circle 1");
        Shape circle2 = new Circle("Circle 2");
        Shape rect1 = new Rectangle("Rectangle 1");
        OOPLinkedList l = new OOPLinkedList<Shape>(circle1);
        l.add(circle2);
        l.add(rect1);
    }

    void print(){
        Element ele = head;
        while (ele != null) {
            System.out.println(ele.val);
            ele = ele.next;
        }
    }

    int length(){
        Element<T> ele = head;
        int len = 0;
        while(ele != null){
            len++;
            ele = ele.next;
        }
        return len;
    }

    T head(){
        return head.val;
    }

    void removeHead(){
        head = head.next;
    }

    void add(T r){
        Element<T> l = new Element(r);
        l.next = head;
        head = l;
    }

    T nth(int n) {
        int i = 1;
        Element<T> l = head;
        while (i < n && l.next != null) {
            l=l.next;
            i++;
        }
        return l.val;
    }
}
