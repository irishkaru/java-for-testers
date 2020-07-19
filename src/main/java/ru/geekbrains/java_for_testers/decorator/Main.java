package ru.geekbrains.java_for_testers.decorator;

public class Main {
    public static void main(String[] args) {
        IBurger burger = new BaseBurger();
       // burger.cook();
        IBurger burgerWithCheeseAndTomato = new TomatoDecorator(new CheeseDecorator(burger));
        burgerWithCheeseAndTomato.cook();
    }
}
