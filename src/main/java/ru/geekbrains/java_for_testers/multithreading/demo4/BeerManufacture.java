package ru.geekbrains.java_for_testers.multithreading.demo4;

public class BeerManufacture {

    private long bottleCount;

    public BeerManufacture(long bottles) {
        this.bottleCount = bottles;
    }

    public BeerManufacture() {
        this.bottleCount = 0;
    }

    public long getCurrentBottleCount() {
        return bottleCount;
    }

    public synchronized void addBeerToBottle(long count) {
        this.bottleCount += count;
    }

    public synchronized void pourOutBeerFromBottles(long count) {
        this.bottleCount -= count;
    }

    public static synchronized void doSomethingElse() {
        System.out.println("something else");
    }
}
