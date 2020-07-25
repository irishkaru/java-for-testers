package ru.geekbrains.java_for_testers.fruit;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxWithFruit<T extends Fruit> {

    private ArrayList<T> boxWithFruits;
    private double weightBox;

    public BoxWithFruit() {
        this.boxWithFruits = new ArrayList<>();
    }

    public BoxWithFruit(T... fruits) {
        this.boxWithFruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public double getWeightBox() {
        return weightBox;
        //вариант от Дмитрия без переменной еще и используя stream
       // return boxWithFruits.stream().map(Fruit::getWeight.reduce(Float::sum).orElse(0f));
    }

    public ArrayList<T> getFruits() {
        return boxWithFruits;
    }

    public void addFruit(T fruit) {
        boxWithFruits.add(fruit);
        weightBox++;
    }

    public void moveFruits(BoxWithFruit<T> anotherBox) {
        if (anotherBox != null && anotherBox != this) { //проверка, как перекладывание в саму себя

            for (int i = 0; i < boxWithFruits.size(); i++) {
                anotherBox.addFruit(boxWithFruits.get(i));
            }
            boxWithFruits.clear();
            weightBox = 0.0;
            //если нет переменной здесь тоже меняется
            //anotherBox.boxWithFruits.addAll(this.boxWithFruits); //?(не работает) - работает просто вес не меняется
            //this.boxWithFruits.clear();

        }

    }

    public boolean compareBoxes(BoxWithFruit<?> anotherBox) {
        return (weightBox - anotherBox.weightBox) < 0.0001;
    }

    @Override
    public String toString() {
        return "BoxWithFruit{" +
                "fruits=" + boxWithFruits +
                ", weightBox=" + weightBox +
                '}';
    }
}
