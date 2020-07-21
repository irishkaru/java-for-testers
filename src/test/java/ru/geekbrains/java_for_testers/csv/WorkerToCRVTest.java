package ru.geekbrains.java_for_testers.csv;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkerToCRVTest {
    ////////////////Тесты на сохранение в файл//////////////////////////////////////////////
    @Test
    void saveNullFile() {
        //Пустой значения
        String filename = "test.txt";
        //Данные
        String[] header = {};
        int[][] data = new int[][]{};

        AppData DataOut = new AppData();
        DataOut.setHeader(header);
        DataOut.setData(data);


        WorkerToCRV fileOut = new WorkerToCRV(filename,'|');
        fileOut.save(DataOut);
    }

    @Test
    void saveFileWithAnotherSeparator() {
        //Проверка сепаратора
        String filename = "test.txt";
        //Данные
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = new int[][]{
                {11, 12, 13},
                {21, 22, 23}};

        AppData DataOut = new AppData();
        DataOut.setHeader(header);
        DataOut.setData(data);


        WorkerToCRV fileOut = new WorkerToCRV(filename,'|');
        fileOut.save(DataOut);
    }

    @Test
    void saveFileWithoutHeader() {
        String filename = "test.txt";
        //Данные
        String[] header = {};
        int[][] data = new int[][]{
                {11, 12, 13},
                {21, 22, 23}};

        AppData DataOut = new AppData();
        DataOut.setHeader(header);
        DataOut.setData(data);


        WorkerToCRV fileOut = new WorkerToCRV(filename,'|');
        fileOut.save(DataOut);
    }

    @Test
    void saveFileWithoutData() {
        String filename = "test.txt";
        //Данные
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = new int[][]{};


        AppData DataOut = new AppData();
        DataOut.setHeader(header);
        DataOut.setData(data);


        WorkerToCRV fileOut = new WorkerToCRV(filename,'|');
        fileOut.save(DataOut);
    }//Результат пустой

    //////////////////////Тесты на чтения файла////////////////////////////////////////////
    @Test
    void readNullFile() {
        //Пустой файл
        String filename = "test3.txt";

        WorkerToCRV file = new WorkerToCRV(filename);
        AppData appData = file.readFile();
        Assert.assertNull(appData.getHeader());
        Assert.assertNull(appData.getData());
        System.out.println(Arrays.deepToString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));
    }
    @Test
    void readFileWithHeader() {
        //Header без зачений
        String filename = "test4.txt";
        WorkerToCRV file = new WorkerToCRV(filename);

        AppData appData = file.readFile();
        Assert.assertNotNull(appData.getHeader());
        Assert.assertNull(appData.getData());
        System.out.println(Arrays.deepToString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));

    }
    @Test
    void readFileWithError() {
        //строки со значением заполнены буквами
        String filename = "test5.txt";
        WorkerToCRV file = new WorkerToCRV(filename);

        try {
            AppData appData = file.readFile();
        }catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("Ошибка: приведения типов");
        }
    }

    @Test
    void readFileWithStrangeValue() {
        //Значений больше чем заголовков
        String filename = "test6.txt";
        WorkerToCRV file = new WorkerToCRV(filename);

        AppData appData = file.readFile();
        System.out.println(Arrays.deepToString(appData.getHeader()));
       System.out.println(Arrays.deepToString(appData.getData())); //Отбросил последний элемент
    }

    @Test
    void readFileWithStrangeHeader() {
        //Значений меньше чем заголовков
        String filename = "test7.txt";
        WorkerToCRV file = new WorkerToCRV(filename);

        AppData appData = file.readFile();
        System.out.println(Arrays.deepToString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData())); //Заполнил нулями
    }
    @Test
    void readFileWithAnotherSeparator() {
        //Проверка работы с другим разделителем
        String filename = "src/test/resources/test8.txt";
        WorkerToCRV file = new WorkerToCRV(filename,',');

        AppData appData = file.readFile();
        System.out.println(Arrays.deepToString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData())); //Заполнил нулями
    }

    @Test
    void readFileWithoutHeader() {
        //без заголовка
        String filename = "test9.txt";
        WorkerToCRV file = new WorkerToCRV(filename);
        AppData appData = file.readFile();

        System.out.println(Arrays.deepToString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));
    }
}