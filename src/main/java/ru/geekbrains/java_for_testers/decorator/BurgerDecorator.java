package ru.geekbrains.java_for_testers.decorator;

public class BurgerDecorator implements IBurger{
    private IBurger wrapped;

    public BurgerDecorator(IBurger wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void cook(){
        wrapped.cook();
    }
}
