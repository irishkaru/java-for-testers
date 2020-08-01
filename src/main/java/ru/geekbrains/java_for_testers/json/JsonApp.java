package ru.geekbrains.java_for_testers.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonApp {
    public static void main(String[] args) throws IOException {
        writeJson();
        ObjectMapper objectMapper = new ObjectMapper();
        GeekbrainsStudent student =
        objectMapper.readValue(new File("target/student.json"),GeekbrainsStudent.class);
        System.out.println(student.getCourseList());
    }

    private static void writeJson() throws IOException {
        List<Course>courseList = Arrays.asList(
                new Course(1,"Java for QA","Dmitry"),
                new Course(2,"OOP in Java","Dmitry"),
                new Course(3,"Mobile automated testing","Ivan")
        );
        GeekbrainsStudent student = new GeekbrainsStudent(
                1,
                "Oleg",
                "Ivanov",
                courseList
        );
        ObjectMapper studentMapper = new ObjectMapper();
        studentMapper.writeValue(new File("target/student.json"),student);

    }
}
