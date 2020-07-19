package ru.geekbrains.java_for_testers.io;

public class Person{
    private long id;
    private String name;
    private String email;


    public Person(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}