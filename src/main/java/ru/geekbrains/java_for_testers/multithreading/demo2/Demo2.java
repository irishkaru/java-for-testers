package ru.geekbrains.java_for_testers.multithreading.demo2;

import static java.lang.Thread.sleep;

public class Demo2 {

    // Daemon

    // Priority

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            someLongWork();
        });
        Thread t2 = new Thread(() -> {
            someLongWork();
        });

        t1.setPriority(10);
        t2.setPriority(1);

        t1.setName("High priority thread");
        t2.setName("Low priority thread");

        t2.start();
        t1.start();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    public static void someLongWork() {
        long time = System.currentTimeMillis();
        System.out.println("I am " + Thread.currentThread().getName());
    }

}
