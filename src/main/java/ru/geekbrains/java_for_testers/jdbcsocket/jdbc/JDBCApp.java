package ru.geekbrains.java_for_testers.jdbcsocket.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCApp {

    /*

     */

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement insertNewStudentStatement;

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC"); //регистрация single ton

            connection = DriverManager.getConnection("jdbc:sqlite:lessonjdbc.db");

            statement = connection.createStatement();

            // readStudentsFromDB();

            insertNewStudentStatement = connection.prepareStatement(
                "INSERT INTO student (name, score) VALUES (?,?);"
            );

            performDropDB();
            performTableCreate();

            long time = System.currentTimeMillis();
            connection.setAutoCommit(false);
            for (int i = 1; i < 1000; i++) {
                insertNewStudentStatement.setString(1, "student_" + i);
                insertNewStudentStatement.setDouble(2, i);
                insertNewStudentStatement.addBatch(); //1
            }
            insertNewStudentStatement.executeBatch();
            connection.commit();//2 1 и 2 сократили время заполнения
//            populateDBWith10000Students();
            System.out.println(System.currentTimeMillis() - time);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void populateDBWith10000Students() throws SQLException {
        for (int i = 0; i < 10000; i++) {
            statement.executeUpdate(
                "INSERT INTO student (name, score) VALUES" +
                    " ('student" + i + "', " + i + ");"
            );
        }
    }

    private static void performTableCreate() throws SQLException {
        statement.executeUpdate("    CREATE TABLE IF NOT EXISTS student (\n" +
            "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    name  STRING  NOT NULL,\n" +
            "    score DOUBLE  NOT NULL\n" +
            ");");
    }

    private static void performDropDB() throws SQLException {
        statement.executeUpdate("DROP TABLE student");
    }

    private static void readStudentsFromDB() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        while (resultSet.next()) {
            System.out.println(
                resultSet.getInt(1) + " | " +
                resultSet.getString(2) + " | " +
                resultSet.getDouble(3) + " | "
            );
        }
    }

}
