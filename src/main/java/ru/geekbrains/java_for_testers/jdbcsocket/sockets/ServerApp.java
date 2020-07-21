package ru.geekbrains.java_for_testers.jdbcsocket.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;

public class ServerApp {

    public static void main(String[] args) {

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(6666)) { //создаем Сервер соке на порту 6666
                Socket client = serverSocket.accept(); //подключаем клиентский сокет(подключается клиент)
                //Если не будет подключения программа дальше не пойдет
                System.out.println("Client connected!");
                DataInputStream in = new DataInputStream(client.getInputStream());//подключаем Streamы
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                //Дальше через Streamы "общаемся" с клиентом
                while (true) { //в цикле мы можем общаться с клиентом
                    String clientResponse = in.readUTF(); //здесь мы можем у клиента прочитать например UTF
                    System.out.println("Client response:" + clientResponse); //
                    out.writeUTF(clientResponse + " " +
                        Instant.now().atZone(ZoneId.of("Europe/Moscow"))); //отвечаем клиенту
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
//здесь мы написали серверную часть
}
