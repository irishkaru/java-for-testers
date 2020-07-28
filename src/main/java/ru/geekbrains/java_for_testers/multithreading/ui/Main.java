package ru.geekbrains.java_for_testers.multithreading.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static JFrame mainScreen;


    public static void main(String[] args) {
        setUpUI();
    }

    public static void someLongWork() {
        long time = System.currentTimeMillis();
        for (long i = 0; i < 100_000L; i++) {
            long a = i * 8;
            for (long j = 0; j < a; j++) {
                long b = j * 4;
            }
        }
        System.out.println(System.currentTimeMillis() - time);
    }

    private static void setUpUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainScreen = new JFrame() {
        };
        JPanel jPanel = new JPanel();
        mainScreen.add(jPanel);

        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainScreen.setTitle("Multithreading");
        mainScreen.setResizable(false);
        mainScreen.setSize(screenSize.width / 4, screenSize.height / 4);
        mainScreen.setLocation(screenSize.width / 2, screenSize.height / 2);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        jPanel.add(button1);
        jPanel.add(button2);
        jPanel.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 1 pressed!");
                someLongWork();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 2 pressed!");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 3 pressed!");
            }
        });
    }
}
