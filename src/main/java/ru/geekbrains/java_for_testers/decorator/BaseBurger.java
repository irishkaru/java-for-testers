package ru.geekbrains.java_for_testers.decorator;

public class BaseBurger implements IBurger {
    private static final String BURGER = " булочка и котлета";

    @Override
    public void cook() {
        System.out.print(BURGER + " приготовлены");
    }
}
