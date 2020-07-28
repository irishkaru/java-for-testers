package ru.geekbrains.java_for_testers.multithreading.demo3;

import com.sun.corba.se.spi.ior.ObjectKey;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class Demo3 {

    // Interruption
    // Interrupted exception
    // Join
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                sleep(1000);
                System.out.println("t1 завершил работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                sleep(1000);
                System.out.println("t2 завершил работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Я хочу дождаться пока t1 и t2 завершат работу");

    }

    private static void interruptionSample() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                someLongWork();
            }
        });

        t.start();
//while(t.isActive());//ждем завершения потока ////так не делать
        System.out.println("I waited for finishing Thread t");
        t.interrupt(); //прерывание потока (не забыть в цикле его обработать)
    }

    private static void interruptedExceptionSample() throws InterruptedException {
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Im still working!");
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });


        sleepThread.start();
        sleep(500);
        sleepThread.interrupt();
    }

    public static void someLongWork() {
        long time = System.currentTimeMillis();
        for (long i = 0; i < 100_000L; i++) {
            long a = i * 16;
            if (i > 50_000 && Thread.currentThread().isInterrupted()) {
                System.out.println(System.currentTimeMillis() - time);
                break;
            }
            for (long j = 0; j < a; j++) {
                long b = j * 4;
            }
        }
        System.out.println(System.currentTimeMillis() - time);
    }

}
