package ru.geekbrains.java_for_testers.multithreading.demo4;

public class Demo4 {

    // Sync (synchronized blocks, methods, static methods)
    // Race condition

    public static void main(String[] args) throws InterruptedException {

        BeerManufacture beerManufacture = new BeerManufacture();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    beerManufacture.addBeerToBottle(1);
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    beerManufacture.pourOutBeerFromBottles(1);
                }
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println(beerManufacture.getCurrentBottleCount());
    }

}
