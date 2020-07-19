package ru.geekbrains.java_for_testers.csv;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //1. Сохранить данные в csv файл
        //Данные
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = new int[][]{
                {11, 12, 13},
                {21, 22, 23}};

        AppData DataOut = new AppData();
        DataOut.setHeader(header);
        DataOut.setData(data);

        // Сохранить данные в csv файл
        String filenameOut = "test1.txt";
        WorkerToCRV fileOut = new WorkerToCRV(filenameOut);
        fileOut.save(DataOut);

        //2. Реализовать загрузку данных из csv файла. Файл читается целиком
        String filenameIn = "test2.txt";

        WorkerToCRV fileIn = new WorkerToCRV(filenameIn);

        AppData DataIn = fileIn.readFile();
        System.out.println(Arrays.deepToString(DataIn.getHeader()));
        System.out.println(Arrays.deepToString(DataIn.getData()));
    }

}
