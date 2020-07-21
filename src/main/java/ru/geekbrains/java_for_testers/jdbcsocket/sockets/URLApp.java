package ru.geekbrains.java_for_testers.jdbcsocket.sockets;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

public class URLApp {

    public static void main(String[] args) throws IOException {
//        URL targetURL = new URL("https://geekbrains.ru");
//        Writer writer = new FileWriter("geekbrains.html",true);
//
//        InputStream inputStream = targetURL.openStream();
//        new BufferedReader(new InputStreamReader(inputStream, UTF_8)) //некий поток данных
//                .lines() //построчно считываем ( все строки)
//                 .forEach(str -> {
//                     try {
//                         writer.write(str);
//                     } catch (IOException e) {
//                         e.printStackTrace();
//                     }
//                 });
        //.forEach(System.out::println); //распечатаем каждую строку (System.out::println - метод референс, появился в 8 Java)
        // иначе можно бало строчку записать .forEach(it -> System.out.println(it)); - it(каждая строка) ->(лямбда)
        //полученные данные надо записать в файл. Для этого добавляем строку 19 и меняем forEach

        //Если мы знаем, что нам придется работать с протоколом http, то лучше записать вот так
        URL targetURL = new URL("https://geekbrains.ru");
        HttpURLConnection urlConnection = (HttpURLConnection) targetURL.openConnection();
        urlConnection.setConnectTimeout(300);

        urlConnection.connect();

        System.out.println(urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage());
        System.out.println(urlConnection.getHeaderFields());

      //  urlConnection.getInputStream(); //если надо можно дальше работать с InputStream()
    }
}
