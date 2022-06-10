package utility;

import librarymanagementsystem.model.Material;

interface SkippableList<T extends Comparable<? super T>> {

    int LEVELS = 5;

    boolean delete(T target);
    void print();
    void insert(T data);
    T search(T data);
}