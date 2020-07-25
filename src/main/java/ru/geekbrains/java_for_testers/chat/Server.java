package ru.geekbrains.java_for_testers.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        // while (true) {
        try (Scanner scanner = new Scanner(System.in);
             ServerSocket serverSocket = new ServerSocket(6666)) { //создаем Сервер соке на порту 6666
            Socket client = serverSocket.accept(); //подключаем клиентский сокет(подключается клиент)
            //Если не будет подключения программа дальше не пойдет
            System.out.println("Client connected!");

            DataInputStream in = new DataInputStream(client.getInputStream());//подключаем Streamы
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            //Дальше через Streamы "общаемся" с клиентом
            while (true) {

                if (in.available() > 0) {
                    String clientResponse = in.readUTF();
                    message(clientResponse);
                    System.out.println("Client:" + clientResponse);
                }
                long end = System.currentTimeMillis() + 60 * 10;
                while ((System.currentTimeMillis() < end)) {
                    if (bufferedReader.ready())

                        out.writeUTF(bufferedReader.readLine() + " " +
                                Instant.now().atZone(ZoneId.of("Europe/Moscow")));
                }
            }
//здесь мы написали серверную часть
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     static void message(String text){

        Connection connection;
        Statement statement;
        PreparedStatement insertNewStudentStatement;

        try {
            Class.forName("org.sqlite.JDBC"); //регистрация single ton

            connection = DriverManager.getConnection("jdbc:sqlite:lesson3.db");

            statement = connection.createStatement();

            insertNewStudentStatement = connection.prepareStatement(
                    "INSERT INTO Message (text) VALUES (?);"
            );

            long time = System.currentTimeMillis();
            connection.setAutoCommit(false);

            insertNewStudentStatement.setString(1, text);
            insertNewStudentStatement.addBatch(); //1

            insertNewStudentStatement.executeBatch();
            connection.commit();//2 1 и 2 сократили время заполнения
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
