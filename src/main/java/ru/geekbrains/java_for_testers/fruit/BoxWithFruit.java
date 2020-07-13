package ru.geekbrains.java_for_testers.fruit;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxWithFruit<T extends Fruit> {
    private ArrayList<T> boxWithFruits;
    private double weightBox;

    public BoxWithFruit(T... fruits) {
        this.boxWithFruits = new ArrayList<T>(Arrays.asList(fruits));
    }

    public double getWeightBox() {
        return weightBox;
    }

    public ArrayList<T> getFruits() {
        return boxWithFruits;
    }

    public void addFruit(T fruit){
        boxWithFruits.add(fruit);
        weightBox++;
    }

    public void moveFruits(BoxWithFruit<T> anotherBox){

        for (int i = 0; i < boxWithFruits.size(); i++) {
            anotherBox.addFruit(boxWithFruits.get(i));
        }

        //anotherBox.boxWithFruits.addAll(boxWithFruits); //?(не работает) - работает просто вес не меняется
        boxWithFruits.clear();
        weightBox = 0.0;

    }

    public boolean compareBoxes(BoxWithFruit<?> anotherBox){
        return weightBox == anotherBox.weightBox;
    }

    @Override
    public String toString() {
        return "BoxWithFruit{" +
                "fruits=" + boxWithFruits +
                ", weightBox=" + weightBox +
                '}';
    }
}
