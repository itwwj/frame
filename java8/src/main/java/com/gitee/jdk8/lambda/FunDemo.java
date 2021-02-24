package com.gitee.jdk8.lambda;

import cn.hutool.core.io.LineHandler;
import cn.hutool.core.lang.func.VoidFunc0;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 方法引用
 * 三种使用情况：
 * <p>
 * 对象::实例方法
 * <p>
 * 类::静态方法
 * <p>
 * 类::实例方法
 * <p>
 * 当需要引用方法的第一个参数是调用对象，并且第二个参数是需要引用方法的第二个参数(或无参数)时： ClassName::methodName 。
 *
 * @author jie
 */
public class FunDemo {

    /**
     * (x) -> System.out.println(x);  等同于：   System.out::println
     */
    @Test
    public void consumer() {
        consumer("axiba", System.out::println);
    }

    public void consumer(String s, Consumer<String> consumer) {
        consumer.accept(s);
    }


    @Test
    public void test() {
        //这两个表达式一致
        BinaryOperator<Double> bo = (x, y) -> Math.pow(x, y);

        BinaryOperator<Double> bo1 = Math::pow;

    }

    @Test
    public void test1() {
        Function<Integer, Car> fun = Car::new;
        Car apply = fun.apply(1);
    }

    @Test
    public void array() {
        /**
         * 两个表达式相同
         */
        Function<Integer, Integer[]> fun = (n) -> new Integer[n];

        Function<Integer, Integer[]> fun1 = Integer[]::new;
        Integer[] apply = fun1.apply(5);
    }


}

@AllArgsConstructor
class Car {
    private Integer a;
}