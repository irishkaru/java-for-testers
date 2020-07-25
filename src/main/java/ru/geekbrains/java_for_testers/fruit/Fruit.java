package ru.geekbrains.java_for_testers.fruit;

public abstract class Fruit {

    protected float weight; //не private т.к.

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
