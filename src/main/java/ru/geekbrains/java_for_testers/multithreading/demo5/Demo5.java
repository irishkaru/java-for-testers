package ru.geekbrains.java_for_testers.multithreading.demo5;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {

    static AtomicInteger x = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 40000; i++) {
                x.getAndIncrement();
            }
        });
        t1.start();


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 40000; i++) {
                x.getAndDecrement();
            }
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main: " + x);
    }

}
