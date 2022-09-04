package com.shpp.p2p.cs.olemeshev.traine;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

public class Lambda {

    public static void main(String[] args) throws InterruptedException {

        Function<Integer, Integer> abs = integer -> Math.abs(integer);
        IntUnaryOperator absAltRef = Math::abs;
        Function<Integer, Integer> absRef = Math::abs;  // 1. static method reference Тут звертаємось до статичного класу


        ThreadLocalRandom random = ThreadLocalRandom.current();
        IntSupplier intSupplier = random::nextInt;  // 2. non - static instance method reference Тут звертаємоя до обєкту РАН ДОМ


        Supplier<String> stringSupplier = "helllo"::toUpperCase;  // атьтернатива
        Function<String, String> upperFunction = String::toUpperCase; // 3 .Static reference of non-static method Тут звертаємося до класу але не статичного
        UnaryOperator<String> upperFunctionAlt = String::toUpperCase;


        ///////////////////////////////////////

        Function<Integer, String> stringFunction = "Hello"::substring;
        String s = stringFunction.apply(2);
        var ss = stringFunction.apply(2);
        System.out.println(s);
        System.out.println(ss);

        BiFunction<String, Integer, String> stringFunction1 = String::substring;
        String sss = stringFunction1.apply("Andrey", 2);
        System.out.println(sss);

        //По замовчуванню якщо використовувати лябді то вони створюють новий метод свій
        // а якщо використовувати метод РЕФЕРЕНСЕ то він привязується до основоного метода.


        //функция вищого порядку
        Function<Function<String, Integer>, Predicate<String>> hOrderFunction =
                strToIntFunction -> str -> strToIntFunction.apply(str) > 0;
        Predicate<String> positiveNumber = hOrderFunction.apply(Integer::parseInt);
        System.out.println(positiveNumber.test("-123"));

        //Функцыя композицыъ
        //f(x), g(x) -> f(g(x))
        IntUnaryOperator squere = x -> x * x;
        IntUnaryOperator increment = x -> x + 1;

        IntUnaryOperator incrementThenSquere = squere.compose(increment);
        IntUnaryOperator squreThenIncrement = squere.andThen(increment);
        System.out.println(incrementThenSquere.applyAsInt(3));
        System.out.println(squreThenIncrement.applyAsInt(3));



        ////////////////////////////////////////
        Predicate<String> predicate1 = Objects::nonNull;
        Predicate<String> predicate2 = String::isEmpty;

        System.out.println(predicate2.test(""));
        System.out.println(predicate2.test("null"));

        Consumer<Integer> consumer = System.out::println;

        consumer.andThen(v-> System.out.println(v+5)).accept(44);
        consumer.accept(45);
        consumer.accept(46);

        Supplier<String> supplier = ()->String.valueOf(System.currentTimeMillis());

        System.out.println(supplier.get());
        Thread.sleep(100);
        System.out.println(supplier.get());
        Thread.sleep(1000);
        System.out.println(supplier.get());

    }
}
