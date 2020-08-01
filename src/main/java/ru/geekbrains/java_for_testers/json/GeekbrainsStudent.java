package ru.geekbrains.java_for_testers.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)  //в случае если одно поле мы убирем например
//lastName, то при загрузки из файла не будет ошибки он просто проигнорирует
public class GeekbrainsStudent {
    private long id;
    private String firstName;
    private String lastName;
    private List<Course> courseList;

    public GeekbrainsStudent() {
        //нужен для работы
    }
    public GeekbrainsStudent(long id, String firstName, String lastName, List<Course> courseList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseList = courseList;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
