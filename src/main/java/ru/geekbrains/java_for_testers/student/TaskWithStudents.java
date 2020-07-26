package ru.geekbrains.java_for_testers.student;

import ru.geekbrains.java_for_testers.streamapi.ExampleApp;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TaskWithStudents {

    //1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
    //на которые подписаны студенты
    public Stream<String> uniqCourses(Stream<Student> studentStream) {
        return studentStream
                .map(x -> x.getAllCourses())
                .flatMap(x -> x.stream())
                .map(course -> course.getName())
                .distinct();
    }


    //2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных
    //(любознательность определяется количеством курсов)
    public Stream<String> inquiringStudents(Stream<Student> studentStream) {
        return studentStream
              .sorted(Comparator.comparing(p -> p.getAllCourses().size(), Comparator.reverseOrder()))
                .limit(3)
                .map(student -> student.getName());

    }


    //3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
    // которые посещают этот курс

    public Stream<String> listStudentsOnCourse(Stream<Student> studentStream, String course) {
        return studentStream
                .filter(p -> p.getAllCourses().contains(course))
                .map(student -> student.getName());
    }

//////////////////////////////////////////////////////////
    public interface Student {
        String getName();

        List<Course> getAllCourses();

    }

    public interface Course {
        String getName();

    }
}
