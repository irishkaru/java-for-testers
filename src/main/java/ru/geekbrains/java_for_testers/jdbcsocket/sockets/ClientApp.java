package ru.geekbrains.java_for_testers.jdbcsocket.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in); //добавляем для возможность ввода с клавиатуры
             Socket socket = new Socket("localhost", 6666)) { //"localhost" - если клиент и сервер на одной машине
            //пока поработаем с реальным сервером
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                outputStream.writeUTF(scanner.nextLine());
                System.out.println(inputStream.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
