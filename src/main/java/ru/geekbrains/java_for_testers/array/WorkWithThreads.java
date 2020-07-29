package ru.geekbrains.java_for_testers.array;

import java.util.Arrays;

public class WorkWithThreads {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        //    Необходимо написать два метода, которые делают следующее:
        //   1) Создают одномерный длинный массив, например:
        // 2) Заполняют этот массив единицами.
        // 3) Засекают время выполнения: long a = System.currentTimeMillis().
        // 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        //    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        // 5) Проверяется время окончания метода System.currentTimeMillis().
        // 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        //
        // Отличие первого метода от второго:
        // Первый просто бежит по массиву и вычисляет значения.
        // Для первого метода надо считать время только на цикл расчета:
        // for (int i = 0; i < size; i++) {
        // arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}

        firstMethod();

        //  Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
        // потом склеивает эти массивы обратно в один.
        //    Пример деления одного массива на два:
        // System.arraycopy(arr, 0, a1, 0, h);
        // System.arraycopy(arr, h, a2, 0, h).
        //
        //    Пример обратной склейки:
        // System.arraycopy(a1, 0, arr, 0, h);
        // System.arraycopy(a2, 0, arr, h, h).
        //
        //    Примечание:
        // System.arraycopy() — копирует данные из одного массива в другой:
        // System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника,
        // массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
        //    По замерам времени:
        //    Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
        secondMethod();
    }

    public static void firstMethod() {
        System.out.println("1 метод: ");

        float[] arr = new float[SIZE];

        //заполнение единицами
        Arrays.fill(arr, 1.0f);

        changeValuesInArray(arr,SIZE,0);
        System.out.println(arr[0]+" "+arr[HALF-1]+" "+arr[HALF]+" "+arr[HALF-1]+" "+arr[SIZE-1]);
    }

    public static void secondMethod() {
        System.out.println("2 метод: ");

        float[] arr = new float[SIZE];

        //заполнение единицами
        Arrays.fill(arr, 1.0f);

        //Деление массива
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];


        long time = System.currentTimeMillis();
        System.arraycopy(arr,0,arr1,0,HALF);
        System.arraycopy(arr,HALF,arr2,0,HALF);
        System.out.println("Время разбивки: " + (System.currentTimeMillis() - time));

        //Потоки
        Thread thread1 = new Thread(() ->changeValuesInArray(arr1,HALF,0));
        Thread thread2 = new Thread(() ->changeValuesInArray(arr2,HALF,HALF));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long time2 = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);
        System.out.println("Время склейки: " + (System.currentTimeMillis() - time2));
        System.out.println(arr[0]+" "+arr[HALF-1]+" "+arr[HALF]+" "+arr[HALF-1]+" "+arr[SIZE-1]);
    }

    public static synchronized void changeValuesInArray(float[] arr,int size,int num) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + num) / 5) * Math.cos(0.2f + (i + num) / 5) * Math.cos(0.4f + (i + num) / 2));
        }
        System.out.println("Время расчета значений: " + (System.currentTimeMillis() - time));

    }
}
