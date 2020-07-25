package ru.geekbrains.java_for_testers.fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args){
        //1. Написать метод, который меняет два элемента массива (ссылочного типа) местами
        System.out.println("Task1 __________");
        String[] strArray = {"hello", "world"};
        System.out.println(Arrays.deepToString(strArray));

        changeElements(strArray, 0, 1);
        System.out.println(Arrays.deepToString(strArray));

        //2.Написать метод, который преобразует массив в ArrayList
        System.out.println("Task2__________");
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(intArray));

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        //convertToArrayList(intArray, arrayList);

        Collections.addAll(arrayList, intArray);
        System.out.println(arrayList);

        System.out.println("Task3__________");
        //Коробка с яблоками
        BoxWithFruit<Apple> appleBox = new BoxWithFruit<>();

        //Добавляем яблоки в коробку
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        System.out.println("Вес коробки с яблоками " + appleBox.getWeightBox());

        //Коробка с апельсинами
        BoxWithFruit<Orange> orangeBox = new BoxWithFruit<>();

        //Добавляем апельсины в коробку
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        System.out.println("Вес коробки с апельсинами " +orangeBox.getWeightBox());

        //Сравниваем вес коробок
        if (orangeBox.compareBoxes(appleBox)) {
            System.out.println("Вес коробок одинаков");
        }
        else {
            System.out.println("Вес коробок разный");
        }

        //вторая коробка с яблоками
        BoxWithFruit<Apple> appleBox2 = new BoxWithFruit<>();

        //Добавляем яблоки в коробку2
        appleBox2.addFruit(new Apple());
        System.out.println("Вес второй коробки с яблоками до перекладывания " + appleBox2.getWeightBox());

        //Пересыпем коробку с яблоками
        appleBox.moveFruits(appleBox2);
        //appleBox.moveFruits(orangeBox); //подсвечивает красным

        System.out.println("Вес второй коробки с яблоками " + appleBox2.getWeightBox());
        System.out.println("Вес коробки с яблоками " + appleBox.getWeightBox());

        ////////////////////////////////////////////////
        ////Как видим при нашей структуре
        ///Мы можем сделать общую коробку с фруктами
        ///и положить туда и яблоки и апельсины
        BoxWithFruit<Fruit> withFruitBox = new BoxWithFruit<>();

        //Добавляем яблоки в коробку
        withFruitBox.addFruit(new Orange());
        withFruitBox.addFruit(new Apple());
        /////чтобы это исправить (если надо) надо:
        /////Сделать интерфейс Н: Packable (пустой) так называемый маркерный интерфейс
        ////В BoxWithFruit добавляем
        ///public class BoxWithFruit<T extends Fruit & Packable>
        ///и помечаем интерфейсом классы Apple и Orange
        ///public class Apple extends Fruit implements Packable{
        ///public class Orange extends Fruit implements Packable{
        ///теперь в коробку не сможем положить и то и другое


    }

    public static void changeElements(Object[] array, int index1, int index2) {
        Object object = array[index1];
        array[index1] = array[index2];
        array[index2] = object;
    }

//    public void convertToArrayList(T[] array,ArrayList<T> arrayList){ {
//        Collections.addAll(arrayList, array);
//    }

}
