package ru.geekbrains.java_for_testers.generics;

public class BoxWithParameter<T> {

    private T object;

    public BoxWithParameter(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}