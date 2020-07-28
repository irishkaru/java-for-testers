package ru.geekbrains.java_for_testers.multithreading.demo2;

import static java.lang.Thread.sleep;

public class Example {
    //Daemon
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            someLongWork();
        });

       // t.setDaemon(true);
        t.start();

        sleep(3000);
        System.out.println("Main thread finished");

    }
    public static void someLongWork() {
        long time = System.currentTimeMillis();
        for (long i = 0; i < 100_000L; i++) {
            long a = i * 8;
            for (long j = 0; j < a; j++) {
                long b = j * 4;
            }
        }
        System.out.println(System.currentTimeMillis() - time);
    }

}
