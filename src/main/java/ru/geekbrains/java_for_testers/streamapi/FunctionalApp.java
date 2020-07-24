package ru.geekbrains.java_for_testers.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionalApp {

    /////////////////////1/////
    //в функциональном программирование функция зависит от того что в нее подали
    //в java ключевая функция -  объект
    //в функциональном программивание -  функция
    //функциональные интерфейсы (@FunctionalInterface) - содержат один абстрактный метод

    static class Person1 {
        int age;
    }

    public static void introLesson() {
        //Predicate хорошо использовать для написания различных фильтров
        Predicate<Person1> personPredicate = new Predicate<Person1>() {
            @Override
            public boolean test(Person1 person) {
                return person.age >= 18;
            }
        };
        Person1 p = new Person1();
        p.age = 19;
        System.out.println(personPredicate.test(p));

        Function<String, Integer> strToInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
        System.out.println(strToInt.apply("5"));
        //    принимает тип String возвращает тип Integer

        UnaryOperator<Integer> getCube = new UnaryOperator<Integer>() { //*
            @Override
            public Integer apply(Integer integer) {
                return integer * integer * integer;
            }
        };                                                              //*  вот это можно заменить люмбдой
        System.out.println(getCube.apply(10));                       //смотри ниже *

        //*
        //UnaryOperator<Integer> getCube = (integer) -> integer * integer * integer; // лямбда
        // (integer) - в скобках пишется аргументы (т.е. то что в функцию подаем) через запятую
        // integer * integer * integer - а это то что из функции получаем (тело функции)
        // если тело функции больше одной строчки, то заключаем ее в {} и обязательно указываем return
        //Например  -> {
        //System.out.println(integer);
        //return integer * integer * integer;
        //};
        // а это лямбда для верхней функции
        //Predicate<Person> personPredicate = (person) -> person.age >= 18;
    }

    ////////////////////2//////////////////
    //Stream - последовательность элементов
    //из коллекций
    static class Person {
        enum Position { // тип перечисление
            ENGINEER, DIRECTOR, MANAGER
        }

        private final String name;
        private final int age;
        private final Position position;

        public Person(String name, int age, Position position) {
            this.name = name;
            this.age = age;
            this.position = position;
        }

    }

    private static void streamSimpleTask() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Bob1", 35, Person.Position.MANAGER),
                new Person("Bob2", 44, Person.Position.DIRECTOR),
                new Person("Bob3", 25, Person.Position.ENGINEER),
                new Person("Bob4", 42, Person.Position.ENGINEER),
                new Person("Bob5", 55, Person.Position.MANAGER),
                new Person("Bob6", 19, Person.Position.MANAGER),
                new Person("Bob7", 33, Person.Position.ENGINEER),
                new Person("Bob8", 37, Person.Position.MANAGER)
        ));

        List<String> engineersNames = persons.stream() //у коллекций есть метод stream
                .filter(person -> person.position == Person.Position.ENGINEER) //фильтруем
                .sorted(Comparator.comparingInt(o -> o.age)) //сортируем по возрасту
                .map(person -> person.name) //нужно показать только имена
                .collect(Collectors.toList());

        System.out.println(engineersNames);
        persons.stream()
                .filter(p -> p.age < 36) //отфильтровали
                .limit(3) //взяли 3 строки
                .map(p -> p.name) //преобразовали тип из Person в String
                .forEach(System.out::println); //напечатали
    }

    public static void main(String[] args) {
        // streamSimpleTask();
        //stream бывают не только в коллекциях
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9) //сделали stream из цифр и также через точку с ним можно работать
                .forEach(x -> System.out.println(x * x)); //например распечатать их квадрат
        //для чисел есть специальные stream
        IntStream intStream;
        DoubleStream doubleStream;
        ///////
        int sum = IntStream.range(0, 100) //stream от 0 до 100 не включая 100 (IntStream.rangeClosed(0,100) включает)
                .sum(); //сумма
        System.out.println(sum);
        ////////
        //Если нам нужно при тестировании создать 100 Person без разницы на параметры
        List<Person> personList = IntStream.range(1, 101)
                .mapToObj(x -> new Person("person" + x, x, Person.Position.MANAGER)) //число превращаем в объект
                .collect(Collectors.toList()); //создаем  коллекцию
        /////////ниже еще пример с условиями/////
        generateTestPersonsList();

        ///нам нужно сгенерировать бесконечная последовательность чисел с шагом 5, взять первые 100 и получить их сумму
        Optional<Integer> sum1 = Stream.iterate(5, x -> x + 5)  //Optional - контейнер для значения где может быть значение или Null
                .limit(100)
                .reduce((a, b) -> a + b);

        System.out.println(sum1);

        iterateStream();

        //Stream можно генерировать из массивов
        //Например
        streamDontChangeSource();

//        IntStream.rangeClosed(0,100)
//                .forEach(Person::new); //обращение к конструктору

    }

    private static void generateTestPersonsList() {
        List<Person> personsList = IntStream.range(0, 101)
                .mapToObj(x -> {
                    String name = "";
                    if (x % 15 == 0) {
                        name = "Vasily";
                    } else {
                        name = "Ivan";
                    }
                    return new Person(name, x, (x % 2 == 0) ? Person.Position.DIRECTOR : Person.Position.MANAGER);
                })
                .collect(Collectors.toList());

    }

    private static void streamDontChangeSource() {
        String[] arr = {"ABC", "BCA", "GFD", "KLMN"};

        System.out.println(Arrays.stream(arr)
                        .filter(str -> str.length() > 3)
                        .count()); //количество

        List<String> strings = Arrays.stream(arr)
                .filter(str -> str.length() > 3)
                .collect(Collectors.toList());

        strings.forEach(System.out::println);
        System.out.println(arr.length);
    }

    private static void iterateStream() {
        Integer sum = Stream.iterate(5, x -> x + 5) //ставим вместо Optional Integer здесь null уже не будет
                .limit(100)
                .map(it -> {//ни делает ничего, но мы каждый элемент it выведем на экран
                    System.out.println(it);
                    return it;
                })
                .reduce((a, b) -> a + b).orElse(0); // если Null то ставим 0

        System.out.println(sum);
    }

}


