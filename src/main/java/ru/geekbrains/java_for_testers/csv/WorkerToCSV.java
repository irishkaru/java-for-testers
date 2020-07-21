package ru.geekbrains.java_for_testers.csv;

import ru.geekbrains.java_for_testers.io.Person;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkerToCSV {
    private String filename;
    private char SEPARATOR;
    private final char DEFAULT_SEPARATOR = ';';

    public WorkerToCSV(String filename) {
        this.SEPARATOR = DEFAULT_SEPARATOR;
        this.filename = filename;
    }

    public WorkerToCSV(String filename, char SEPARATOR) {
        this.SEPARATOR = SEPARATOR;
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSEPARATOR(char SEPARATOR) {
        this.SEPARATOR = SEPARATOR;
    }

    public String getFilename() {
        return filename;
    }

    public char getSEPARATOR() {
        return SEPARATOR;
    }

    public void fileExistence() {

    }

    public void save(AppData data) {
        //1. Сохранить данные в csv файл
        try (OutputStream fileOutputStream = new BufferedOutputStream(
                new FileOutputStream(filename))) {
            String[] header = data.getHeader();
            int[][] dataTab = data.getData();

            //Запись header - 1 строка
            for (int i = 0; i < header.length; i++) {
                fileOutputStream.write(header[i].toString().getBytes());
                if ((i + 1) != header.length) {
                    fileOutputStream.write(SEPARATOR);
                }
            }
            if (header.length !=0) {
                fileOutputStream.write("\n".getBytes());
            }

            //Записываем - значения
            for (int i = 0; i < dataTab.length; i++) {
                for (int j = 0; j < dataTab[i].length; j++) {
                    fileOutputStream.write(Integer.toString(dataTab[i][j]).getBytes());
                    if ((j + 1) != dataTab[i].length){
                        fileOutputStream.write(SEPARATOR);
                    } else{
                        fileOutputStream.write("\n".getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppData readFile() {
        AppData data = new AppData();

        try {
            Path path = Paths.get(filename);
            //считываем содержимое файла в список строк
            List<String> allLines = Files.readAllLines(path);

            // первая строка - header
            if (allLines.size() > 0) {
                    data.setHeader(allLines.get(0).split(Character.toString(SEPARATOR)));
            }

            //Остальные строки нужно преобразовать в многомерный int массив
            if (allLines.size() > 1) {

                int[][] arr = new int[allLines.size()-1][data.getHeader().length];

                for (int i = 1; i < allLines.size(); i++) {
                    String[] str = allLines.get(i).split(Character.toString(SEPARATOR));
                    int length = str.length;
                    if (data.getHeader().length < str.length){
                        length=data.getHeader().length;
                    }
                    for (int j = 0; j < length; j++) {
                        arr[i-1][j] = Integer.parseInt(str[j]);
                    }
                }
                data.setData(arr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}

