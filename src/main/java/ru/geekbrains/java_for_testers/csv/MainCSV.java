package ru.geekbrains.java_for_testers.csv;

import java.util.Arrays;

public class MainCSV {

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
        String filenameOut = "src/test/resources/test1.txt";
        WorkerToCSV fileOut = new WorkerToCSV(filenameOut);
        fileOut.save(DataOut);

        //2. Реализовать загрузку данных из csv файла. Файл читается целиком
        String filenameIn = "src/test/resources/test2.txt";

        WorkerToCSV fileIn = new WorkerToCSV(filenameIn);

        AppData DataIn = fileIn.readFile();
        System.out.println(Arrays.deepToString(DataIn.getHeader()));
        System.out.println(Arrays.deepToString(DataIn.getData()));
    }

}
