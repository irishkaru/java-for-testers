package ru.geekbrains.java_for_testers.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1
        InputStream in;   //входящий поток
        OutputStream out;  //исходящий
        Reader reader;
        Writer writer;

        /* если видим Stream - то работа будет с байтами (байтовый поток)
        если Reader или Writer то работа с символами (символьный)*/

        System.out.println(); //PrintOutputStream

        //2
        File file = new File("./src/main"); //в скобках путь в переменную записывается путь
        System.out.println(file.exists()); //существует ли файл или папка(папка это тоже файл) true
        System.out.println(file.getName()); // получить имя файла main
        System.out.println(file.getParent()); // получить родителя ./src
        System.out.println(file.isDirectory()); // это директория true
        System.out.println(file.isFile()); // это файл false
        System.out.println(file.length()); // возврат длины в байтах

        File file1 = new File("./3/3/2");
        System.out.println(file1.mkdir());  //создает папку 2 если есть /3/3 иначе ошибка
        System.out.println(file1.mkdirs());  //создает папку и ее вложенния смотрит на путь и если чего то нет создает

        System.out.println(file.getAbsolutePath());  // возвращает абсолютный путь

        //3 запись в файл
//        try {
//            FileOutputStream fileOutput = new FileOutputStream("1.txt");
//            //если вторым параметрам  в  FileOutputStream добавить true - то это значит дозаписать
//            fileOutput.write(65); // 65 по ASCII это A
//            fileOutput.write("I love Java".getBytes()); // а так удобней записывать в файл
//            fileOutput.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        //4 чтение из файла
//        FileInputStream fileInput = null;
//        try {
//            fileInput = new FileInputStream("1.txt");
//            int x = 0;
//            while ((x = fileInput.read()) != -1){
//                System.out.print((char)x);
//                //а если оставить x без (char), то выйдет списком байты каждого символа
//                //Если в файле не латиница, то будет абракадабра потому как там зашито двумя байтами
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (fileInput != null) {
//                    fileInput.close(); // здесь fileInput не доступно
//                    //значит придется разбить FileInputStream fileInput = new FileInputStream("1.txt");
//                    //FileInputStream fileInput = null; перед try
//                    //добавляем перехват для close и проверку на null
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //5 так как 4 чрезмерно раздут поэтому воспользуемся конструкцией try with resoursies
        //перепишем этот код
        //Закрывать ресурс не надо, сам закроется
        try (FileInputStream fileInputStream = new FileInputStream("1.txt")){
            int x = 0;
            while ((x = fileInputStream.read()) != -1){
                System.out.print((char)x);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
        //6
        //если нам надо записать файл на 5mb заполненный символом A
//        long time = System.currentTimeMillis();
//        try(FileOutputStream fileOutputStream = new FileOutputStream("2.txt")){
//            for(int i =0; i < 5 * 1024 * 1024; i++){
//                fileOutputStream.write(65);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        System.out.println("time: " + (System.currentTimeMillis() - time));
        //Очень долго работает 17903 мсек

        //7 сделаем по другому
        long time = System.currentTimeMillis();
        try(OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream("2.txt"))){
            for(int i =0; i < 5 * 1024 * 1024; i++){
                fileOutputStream.write(65);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
        //этот работает 118  мсек

        //8
        //
        try(BufferedReader inBuf = new BufferedReader(new FileReader("1.txt"))){
            String str = null;
            while ((str = inBuf.readLine()) != null) { //читаем построчно все, выводим просто str
                String[] tokens = str.split("\\s"); //а здесь нам только 1 столбец нужен
                System.out.println(tokens[0]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //9
        System.out.println(readPersonFromFile("1.txt"));
        //10

//        Path path = Paths.get("./src");
//        System.out.println(path);
    }

   private static List<Person> readPersonFromFile(String filename){
      List<Person> personList = new ArrayList<>();
       try(BufferedReader in = new BufferedReader(new FileReader(filename))){
           String str = null;
           str = in.readLine();
           while ((str = in.readLine()) != null) {
               String[] tokens = str.split("\\s");
               personList.add(new Person(Long.parseLong(tokens[0]),tokens[1],tokens[2]));
           }
       }catch (IOException e){
           e.printStackTrace();
       }
       return personList;
   }
}
