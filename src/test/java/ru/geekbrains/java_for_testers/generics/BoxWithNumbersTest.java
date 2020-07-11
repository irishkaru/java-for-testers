package ru.geekbrains.java_for_testers.generics;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxWithNumbersTest {
    @Test
   public void testGetAvg() {
       // BoxWithNumbers<Integer> box = new BoxWithNumbers<>(1,2);
        BoxWithNumbers<Integer> box = new BoxWithNumbers<>(new Integer[]{1, 2});
        double avg = box.getAvg();
        Assert.assertTrue(1.5 == avg);
    }

}