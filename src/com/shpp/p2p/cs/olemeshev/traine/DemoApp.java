package com.shpp.p2p.cs.olemeshev.traine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        //получаем методи нашого класу
        Method[] methods = Randomize.class.getDeclaredMethods();
        System.out.println(methods[0]);


        // создаем екземпляр класу
        var exemplar = new Randomize();
        // визиваємо метод з данними
        var result1 = methods[0].invoke(exemplar, List.of(1, 2, 3, 4, 5));

        // создаємо екземпляр не через new, а через рефлексію.
        Class<Randomize> randomizeClass = Randomize.class;
        Constructor<Randomize> constructor = randomizeClass.getConstructor();
        Randomize exemp = constructor.newInstance();
        var result2 = methods[0].invoke(exemp, List.of(1, 2, 3, 4, 5));


    }
}
