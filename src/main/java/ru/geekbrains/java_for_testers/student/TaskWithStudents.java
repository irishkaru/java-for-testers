package ru.geekbrains.java_for_testers.student;

import ru.geekbrains.java_for_testers.io.Person;
import ru.geekbrains.java_for_testers.streamapi.ExampleApp;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskWithStudents {

    //1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
    //на которые подписаны студенты
    public List<Course> uniqCourses(List<Student> studentStream) {
        return studentStream.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x -> x.stream())
                .distinct()
                .collect(Collectors.toList());
    }


    //2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных
    //(любознательность определяется количеством курсов)
    public List<Student> inquiringStudents(List<Student> studentStream) {
        return studentStream.stream()
              .sorted(Comparator.comparing(p -> p.getAllCourses().size(), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());

    }


    //3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
    // которые посещают этот курс
    public List<Student> listStudentsOnCourse(List<Student> studentStream, Course course) {
        return studentStream.stream()
                .filter(p -> p.getAllCourses().contains(course))
                .collect(Collectors.toList());
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
