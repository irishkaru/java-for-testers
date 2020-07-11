package ru.geekbrains.java_for_testers.generics;

import org.junit.Assert;
import org.junit.Test;

public class BoxTest {

    @Test
    public  void testMe(){
        Box box = new Box(42L);
       // box.setObject(42L);
        Long content = (Long) box.getObject();
        Assert.assertEquals(new Long(42L),content);
    }

    @Test(expected = ClassCastException.class)
    public  void testWrongContentBehavior(){
        Box box = new Box("Hello");
        // box.setObject("Hello");
        Long content = (Long) box.getObject();
       System.out.println("Hello");
    }

}