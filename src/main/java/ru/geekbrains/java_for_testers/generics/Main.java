package ru.geekbrains.java_for_testers.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList =
                new ArrayList<>(Arrays.asList(10, 20, 30));
        Integer intFirst = getFirstElement(intList);

        List<Number> numberList =
                new ArrayList<Number>(Arrays.asList(10, 20, 30));
        List<Integer> integerList =
                new ArrayList<>(Arrays.asList(100, 200, 300));

        System.out.println(numberList);

        Collections.copy(numberList, integerList);

        System.out.println(numberList);


    }

    private static <T> T getFirstElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


   public static void intro() {
        Box box1 = new Box(15);
        Box box2 = new Box(5);

        System.out.println(box1);
        System.out.println(box2);

        // ... 1000 строк кода
        box1.setObject("I <3 Java");
        // ... 1000

        if (box1.getObject() instanceof Integer && box2.getObject() instanceof Integer) {
            int result = (Integer) box1.getObject() + (Integer) box2.getObject();
            System.out.println(result);
        } else {
            System.out.println("Objects in boxes not contains Integers");
        }
    }

   public static void boxWithTwoParams() {

        BoxWithParameter<Integer> box1 = new BoxWithParameter<>(15);
        BoxWithParameter<Integer> box2 = new BoxWithParameter<>(5);
        BoxWithParameter<String> box3 = new BoxWithParameter<>("I <3 Java");

        int result = box1.getObject() + box2.getObject();
        System.out.println(result);

        BoxWithTwoParams<Integer, Integer> doubleIntBox =
                new BoxWithTwoParams<>(500, 500);



        /*
            E - элемент коллекции
            K, V - key, value
            T - type
            S, U, V - соседи буквы Т для обозначения типа
         */
    }

    public static void boxWithNumbers() {
        BoxWithNumbers<Integer> integerBoxWithNumbers =
                new BoxWithNumbers<>(new Integer[]{10, 20, 30});
        BoxWithNumbers<Double> doubleBoxWithNumbers =
                new BoxWithNumbers<>(new Double[]{10.0, 20.0, 30.0});

        System.out.println(integerBoxWithNumbers.isAvgTheSame(doubleBoxWithNumbers));
    }

    private <T> void doSomething(List<T> list) {
        System.out.println("Hello!");
    }
}
