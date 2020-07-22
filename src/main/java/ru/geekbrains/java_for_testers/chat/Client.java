package ru.geekbrains.java_for_testers.chat;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {


        try (Scanner scanner = new Scanner(System.in); //добавляем для возможность ввода с клавиатуры
             Socket socket = new Socket("localhost", 6666)) { //"localhost" - если клиент и сервер на одной машине

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                if (in.available() > 0) { //если количество байт отправленных с сервера > 0
                    String clientResponse = in.readUTF(); //считываем
                    System.out.println("Server:" + clientResponse); //выводим на экран
                }

                long timeout = System.currentTimeMillis() + 60 * 10; //устанавливаем задержку по времени
                while ((System.currentTimeMillis() < timeout)) {

                    if (bufferedReader.ready())
                        out.writeUTF(bufferedReader.readLine() + " " +
                                Instant.now().atZone(ZoneId.of("Europe/Moscow"))); //отвечаем клиент
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
