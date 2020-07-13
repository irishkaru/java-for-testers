package ru.geekbrains.java_for_testers.fruit;

public class Apple extends Fruit{
    public Apple() {
        super(1f);
    }

    @Override
    public String toString() {
        return "Apple{" +
            "weight=" + this.getWeight() +
                    '}';
    }
}
