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

    public synchronized void addBeerToBottle(long count) { //синхронизация метода
        this.bottleCount += count;
    }

    public void pourOutBeerFromBottles(long count) {
        synchronized (this) { //синхронизация блока
            this.bottleCount -= count;
        }
    }

    public static void doSomethingElse() {
        synchronized (BeerManufacture.class) { //синхронизация блока в статическом методе
            System.out.println("something else");
        }
    }
}
