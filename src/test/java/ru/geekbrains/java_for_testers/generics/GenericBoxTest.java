package ru.geekbrains.java_for_testers.generics;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenericBoxTest {
    @Test
    public  void testGenericBox(){

        GenericBox<Integer> box = new GenericBox<>();
        GenericBox<String> strBox = new GenericBox<>();

        Integer content = box.getContent();
        String content1 = strBox.getContent();
    }

}