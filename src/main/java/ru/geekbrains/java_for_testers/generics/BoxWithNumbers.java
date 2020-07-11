package ru.geekbrains.java_for_testers.generics;

//Создаем типизированную коробочку, Тип Number
//если в строке ниже мы уберем extends Number, то в эту коробку можно будет положить
//любой тип: строку, число (BoxWithNumbers<String> box = new BoxWithNumbers<>("A","B");)
//extends Serializable ? , extends Number & Serializable ?? (проверка на принадлежность к числу потом к Serializable
public class BoxWithNumbers<T extends Number> {

        private T[] numbers;

        //(T... numbers) ошибка в тесте ругается на <>
        public BoxWithNumbers(T[] numbers) {
            this.numbers = numbers;
        }

        public double getAvg() {
            double result = 0.0;
            for (Number number: numbers) {
                result += number.doubleValue();
            }
            return result / numbers.length;
        }

        public boolean isAvgTheSame(BoxWithNumbers<?> another) {
            return Math.abs(this.getAvg() - another.getAvg()) < 0.0001;
        }

    }
