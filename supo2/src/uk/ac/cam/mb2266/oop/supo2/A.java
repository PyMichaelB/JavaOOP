package uk.ac.cam.mb2266.oop.supo2;

class A {
    A (int a){ System.out.println(a); }
}

class B extends A {
    B(int b){ super(b); }
}

class C extends B {
    private C(int c) { super(c); }
    public static void main(String[] args) {
        C obj = new C(5);
    }
}
