package ru.geekbrains.java_for_testers.decorator;

public class TomatoDecorator extends BurgerDecorator {
    private static final String TOMATO = "томат,";

    public TomatoDecorator(IBurger wrapped) {
        super(wrapped);
    }
    @Override
    public void cook(){
        System.out.print(TOMATO);
        super.cook();
    }

}
