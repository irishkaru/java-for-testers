package ru.geekbrains.java_for_testers.chat;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

public class Client {

    private static int PORT;

    public Client(int PORT) {
        this.PORT = PORT;
    }

    public static void run() {

        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket("localhost", PORT)) {

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

    public static void main(String[] args) {
        Client client = new Client(5080);
        client.run();
    }

}
