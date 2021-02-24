package com.gitee.jdk8.lambda;

import java.util.Optional;

/**
 *
 *用于尽量避免空指针异常
 *
 *
 * Optional.of(T t) : 创建一个 Optional 实例
 * Optional.empty() : 创建一个空的 Optional 实例
 * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * isPresent() : 判断是否包含值
 * orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 *
 *
 *
 * @author jie
 */
public class OptionalDemo {
    public static void main(String[] args) {

        Optional<String> s = Optional.of(new String());
        Optional<Object> empty = Optional.empty();
        Optional<Object> o = Optional.ofNullable(null);
    }
}
