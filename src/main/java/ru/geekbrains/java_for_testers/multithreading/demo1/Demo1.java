package ru.geekbrains.java_for_testers.multithreading.demo1;

public class Demo1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new MyDemo1Thread().start();
        }
    }

    private static class MyDemo1Thread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello, world | from " + getName());
        }
    }
}
