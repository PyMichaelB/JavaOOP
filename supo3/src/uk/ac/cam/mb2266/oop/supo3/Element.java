package uk.ac.cam.mb2266.oop.supo3;

class Element<T> {
    T val;
    Element next;

    Element(T value) {
        val = value;
        next = null;
    }
}

