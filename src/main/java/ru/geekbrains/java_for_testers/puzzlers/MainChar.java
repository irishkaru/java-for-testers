package ru.geekbrains.java_for_testers.puzzlers;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MainChar {
    static String str;

    public static void main(String[] args) {

        System.out.println("");

        System.out.println(Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get());
        System.out.println("");

        run();
        Set<Character> set = new HashSet<>();
        for (char ch = 'а'; ch <= 'я'; ch++) {
            set.add(ch);
            set.remove(ch - 1);
        }
        System.out.println(set.size());//32

        System.out.println("");

        System.out.println(+ +'2');

        System.out.println("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            sb.append((char) i);
        }
        String raw = sb.toString();
        String aligned = raw.toUpperCase().toLowerCase();
        System.out.println(Integer.compare(raw.length(),aligned.length()));
    }

    static void run() {
        str = "Привет";
        Supplier<String> s1 = str::toUpperCase;
        Supplier<String> s2 = () -> str.toUpperCase();
        str = "hello";
        System.out.println(s1.get());
        System.out.println(s2.get());
    }


}

