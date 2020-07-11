package ru.geekbrains.java_for_testers.generics;

public class GenericBox<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
