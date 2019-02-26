package com.ronald;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 函数式编程的使用
 * Stream 与IO流并不是同一回事
 */
public class TestStream {

    public static void main(String[] args) {

        TestStream ts = new TestStream();
        ts.forEach();
        ts.testReduce();
        ts.testCollect();
    }

    public void forEach() {
        Stream<String> stream = Stream.of("I", "like", "bananer", "too");
        //filter 过滤操作
        stream.filter(str -> str.length() > 3)
                .forEach(str -> System.out.println(str));


    }

    public void testReduce(){
        Stream<String> stream = Stream.of("I", "like", "bananer", "too");
        Optional<String> reduce = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println(reduce.get());

        Stream<String> stream2 = Stream.of("I", "like", "bananer", "too");
        //对stream 中字符串的长度求和
        int lenSum = stream2.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        System.out.println(lenSum);
    }

    public void testCollect(){
        Stream<String> stream = Stream.of("I", "like", "bananer", "too");
//        List<String> collect = stream.collect(Collectors.toList());
        Map<String, Integer> collect = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(collect);
    }
}
