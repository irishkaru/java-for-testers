package ru.geekbrains.java_for_testers.fruit;

public class Orange extends Fruit{
    public Orange() {
        super(1.5f);
    }
    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + this.getWeight() +
                '}';
    }
}
