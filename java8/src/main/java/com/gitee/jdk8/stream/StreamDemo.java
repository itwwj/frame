package com.gitee.jdk8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Stream 自己不会存储元素。
 * Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * @author jie
 */
public class StreamDemo {

    public void create() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //创建顺序流
        Stream<Integer> stream = list.stream();
        //创建并行流
        Stream<Integer> stringStream = list.parallelStream();

    }


    public void foreach() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //创建顺序流
        //stream.forEach(System.out::println);
        list.stream().forEach((s) -> System.out.println(s));

    }

    /**
     * filter()方法主要是用于接收Lambda表达式，从流中排除某些元素
     */
    public void filter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //创建顺序流
        list.stream().filter((s) -> s > 5).forEach(System.out::println);

    }

    /**
     * 截断流，使其元素不超过给定数量。
     */
    public void limit() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //创建顺序流
        list.stream().limit(5).forEach(System.out::println);

    }

    /**
     * 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素 不足 n 个，则返回一个空流
     */
    public void skip() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //创建顺序流
        list.stream().skip(5).forEach(System.out::println);

    }

    /**
     * 筛选，通过流所生成元素的 hashCode() 和 equals() 去 除重复元素。
     */
    public void distinct() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        //创建顺序流
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 接收一个函数作为参数，该函数会被应用到每个元 素上，并将其映射成一个新的元素
     */
    public void map() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        //创建顺序流
        list.stream().map((s) -> s + 1).forEach(System.out::println);
    }

    /**
     * 接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流
     */
    public void sorted() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        //自然排序
        list.stream().sorted().forEach(System.out::println);
        //自定义排序
        list.stream().sorted((x, y) -> {
            if (x <y) {
                return 1;
            } else {
                return -1;
            }
        }).forEach(System.out::println);
    }

    /**
     * allMatch()方法表示检查是否匹配所有元素。
     */
    public void allMatch() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        boolean b = list.stream().allMatch((e) -> e > 10);
        boolean c = list.stream().allMatch((e) -> e > 0);
        System.out.println(b+"      "+c);
    }

    /**
     * anyMatch方法表示检查是否至少匹配一个元素
     */
    public void anyMatch() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        boolean b = list.stream().anyMatch((e) -> e > 10);
        boolean c = list.stream().anyMatch((e) -> e > 5);
        System.out.println(b+"      "+c);
    }

    /**
     * noneMatch()方法表示检查是否没有匹配所有元素
     */
    public void noneMatch() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        boolean b = list.stream().noneMatch((e) -> e > 10);
        boolean c = list.stream().noneMatch((e) -> e > 5);
        System.out.println(b+"      "+c);
    }

    /**
     * findFirst()方法表示返回第一个元素
     */
    public void findFirst() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first.get());
    }

    /**
     * findAny()方法表示返回当前流中的任意元素 串行返回第一个 并行不确定
     */
    public void findAny() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        Optional<Integer> any = list.stream().findAny();
        System.out.println(any.get());
    }

    /**
     * count()方法表示返回流中元素总数
     */
    public void count() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        long count = list.stream().count();
        System.out.println(count);
    }

    /**
     * max()方法表示返回流中最大值
     */
    public void max() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        Optional<Integer> max = list.stream().max((x, y)->{
            if (x>y){
                return 1;
            }else {
                return -1;
            }
        });
        System.out.println(max.get());
    }
    /**
     * min()方法表示返回流中最小值
     */
    public void min() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 2, 6, 7, 8, 9);
        Optional<Integer> min = list.stream().min((x, y)->{
            if (x>y){
                return 1;
            }else {
                return -1;
            }
        });
        System.out.println(min.get());
    }
}
