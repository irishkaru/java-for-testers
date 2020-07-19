package ru.geekbrains.java_for_testers.decorator;

public class CheeseDecorator extends BurgerDecorator{
    private static final String CHEESE = "сыр,";

    public CheeseDecorator(IBurger wrapped) {
        super(wrapped);
    }
    @Override
    public void cook(){
        System.out.print(CHEESE);
        super.cook();
    }

}
