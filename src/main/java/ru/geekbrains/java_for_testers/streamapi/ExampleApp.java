package ru.geekbrains.java_for_testers.streamapi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleApp {

    /*
        Написать функцию
        Которая на вход получает Stream организаций
        Каждая организация содержит список рабочих
        Вернуть необходимо Stream с уникальными именами всех сотрудников, где сотрудников больше 7
     */

    Stream<String> uniqWorkersNames(Stream<Organization> organizationStream) {
        return organizationStream
            .filter(x -> x.getWorkers().size() > 7)
                //отфильтровали организации подходящие условию
                .map(x -> x.getWorkers())
                //выше получили stream из несколько list с рабочими
                .flatMap(x -> x.stream())
                //у нас Организации, (как коробки) содержащие много элементов Сотрудников
                // flatMap позволяет распаковать коробки
                //т.е. получаем stream с Работниками
                .map(worker -> worker.getName())
                //получаем stream имен
                .distinct();
        //можно упростить еще (ниже)
//            .map(Organization::getWorkers)
//            .flatMap(List::stream)
//            .map(Worker::getName)
//            .distinct();
    }

    /*
        Нужно написать коллектор, который создаст Map с ключем - ID организации
        а в качестве значения - сама организация
     */

    Map<Integer, Organization> integerOrganizationMap(List<Organization> organizations) {
        return organizations.stream()
            .collect(Collectors.toMap(Organization::getId, org -> org));
        //(Organization::getId) можно заменить на org -> org.getId()
        //не важно как обазвать organization или org  или x - это просто так мы называем элемент stream
    }

    interface Organization {

        int getId();

        List<Worker> getWorkers();
    }

    interface Worker {

        String getName();
    }

}
