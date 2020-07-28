package ru.geekbrains.java_for_testers.multithreading.demo1;

public class Demo0 {

    /*
         Способы создания потока:
         1) наследование от Thread
         2) реализовать интерфейс Runnable
     */

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello, world! | from: " + currentThread().getName());
        }
    }

    public static void main(String[] args) {
        /////////1////////////////////////
//        MyThread t = new MyThread();
//       // t.run(); так нельзя чтоб запустить поток надо
//        t.start();
//        MyThread t1 = new MyThread();
//        t1.start();
////////////////////////2////////////
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello from: " + Thread.currentThread().getName());
//            }
//        }).start(); //заменим лямбдой
        new Thread(() ->
            System.out.println("Hello from " + Thread.currentThread().getName())).start();
    }

}
