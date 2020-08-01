package ru.geekbrains.java_for_testers.puzzlers;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MainList {

    public static void main(String[] args) {

        //паззлеры с ArrayList
        //1.
        List<String> products = new ArrayList<>();
        products.add("хлеб");
        products.add("молоко");
        products.add("колбаса");

        Stream<String> stream = products.stream();
        //забыли яйца, а как же stream
        //все ж попробуем
        products.add("яйца");

        //выведем
        stream.forEach(System.out::println);
        //яйца попали в stream - ого го
        //late binding - все изменения происходят не когда берем stream, а во время термальная операция
        System.out.println("");

        //2
        for (String product:products){
            if ("хлеб".equals(product)){ //запустим и теперь поменяем колбасу на хлеб
                //и запустим снова
                products.remove("молоко");
            }
            System.out.println(product);
        } //второй запуск приведет к ошибке ConcurrentModificationException
        //честно говоря меня вообще удивляет, что и должна изначально быть ошибка
        //и удаление в цикле нельзя допускать
        //и так тоже нельзя
        //products.stream().forEach(x -> {
        //     if (x.equals("молоко")){
        //         products.remove(x);
        //     }
        // });

        //А как же тогда можно удалять???



    }


}
