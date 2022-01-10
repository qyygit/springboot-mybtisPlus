package com.atguigu.boot.java8test;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class Stream  implements  Comparable{


    public static void main(String[] args) {
//
//        Random random = new Random();
//        random.ints().limit(10).sorted(Comparator.reverseOrder()).forEach();

        List<Integer> strings = Arrays.asList(1,3,2,4,5,6);
// 获取空字符串的数量
//        正序
        List<Integer> collect = strings.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//        倒序
        List<Integer> collect1 = strings.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect1);

//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//
//        List<Integer> integerList = numbers.stream().map(i -> i * i).collect(Collectors.toList());
//
//        List<Integer> squaresList2 = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//
//        System.out.println(integerList);
//        System.out.println(squaresList2);

        //1, 获取集合中,不为空的数据
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//        System.out.println(filtered);

//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
// 获取空字符串的数量
//        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
//        System.out.println(count);

        //1,创建一个集合
//        List<String> listStr = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//
//        Random random = new Random();
//        random.ints().limit(20).sorted().forEach(System.out::println);
//        listStr.forEach(System.out::println);
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        listStr.forEach(System.out::println);





//        // 获取空字符串的数量
//        long count = listStr.stream().filter(string -> string.isEmpty()).count();
//        System.out.println(count);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
